package com.toy.plany.controller;

import com.toy.plany.dto.request.member.MemberCreateRequest;
import com.toy.plany.dto.response.member.MemberResponse;
import com.toy.plany.service.MemberService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/member")
public class MemberController {

    private MemberService memberService;

    public MemberController(MemberService memberService){
        this.memberService = memberService;
    }

    public ResponseEntity<MemberResponse> createMember(@RequestBody @Valid MemberCreateRequest request){
        MemberResponse res = memberService.createUser(request);
        return ResponseEntity.ok(res);
    }
}
