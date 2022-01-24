package com.toy.plany.service;

import com.toy.plany.dto.request.member.MemberCreateRequest;
import com.toy.plany.dto.response.member.MemberResponse;
import com.toy.plany.entity.Department;
import com.toy.plany.entity.Member;
import com.toy.plany.entity.enums.Color;
import com.toy.plany.exception.exceptions.DepartmentNotFoundException;
import com.toy.plany.repository.DepartmentRepo;
import com.toy.plany.repository.MemberRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

public class MemberServiceImpl implements MemberService {

    private final MemberRepo memberRepo;

    private final DepartmentRepo departmentRepo;


    @Autowired
    public MemberServiceImpl(MemberRepo memberRepo, DepartmentRepo departmentRepo) {
        this.memberRepo = memberRepo;
        this.departmentRepo = departmentRepo;
    }

    @Transactional
    public MemberResponse createUser(MemberCreateRequest request) {
        Department department = getDepartmentById(request.getDepartmentId());
        Color color = getColorByRandom();
        Member member = Member.builder()
                .employeeNum(request.getEmployeeNum())
                //TODO 패스워드 암호화
                .password(request.getPassword())
                .slackUid(request.getSlackUid())
                .email(request.getEmail())
                .name(request.getName())
                .department(department)
                .color(color)
                .build();
        Member savedMember = memberRepo.save(member);
        return createMemberDto(savedMember);
    }

    private Color getColorByRandom(){
        //TODO Random return, 없으면 예외 발생하도록 수정
        return Color.RED;
    }

    private Department getDepartmentById(Long departmentId) {
        return departmentRepo.findById(departmentId).orElseThrow(DepartmentNotFoundException::new);
    }

    private MemberResponse createMemberDto(Member member) {
        return MemberResponse.builder()
                .id(member.getId())
                .employeeNum(member.getEmployeeNum())
                .name(member.getName())
                .color(member.getColor().toString())
                .department(member.getDepartment().getName())
                .position(member.getPosition())
                .build();
    }
}
