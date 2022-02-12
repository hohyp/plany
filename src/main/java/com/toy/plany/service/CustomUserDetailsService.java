package com.toy.plany.service;

import com.toy.plany.entity.User;
import com.toy.plany.repository.UserRepo;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Component("userDetailsService")
public class CustomUserDetailsService implements UserDetailsService {
   private final UserRepo userRepo;

   public CustomUserDetailsService(UserRepo userRepository) {
      this.userRepo = userRepository;
   }

   @Override
   @Transactional
   public UserDetails loadUserByUsername(final String employeeNum) {
      return userRepo.findUserByEmployeeNum(employeeNum)
         .map(user -> createUser(employeeNum, user))
         .orElseThrow(() -> new UsernameNotFoundException(employeeNum + " -> 데이터베이스에서 찾을 수 없습니다."));
   }

   private org.springframework.security.core.userdetails.User createUser(String username, User user) {
      List<GrantedAuthority> grantedAuthorities = user.getAuthorities().stream()
              .map(authority -> new SimpleGrantedAuthority(authority.getAuthorityName()))
              .collect(Collectors.toList());
      return new org.springframework.security.core.userdetails.User(user.getEmployeeNum(),
              user.getPassword(),
              grantedAuthorities);
   }
}
