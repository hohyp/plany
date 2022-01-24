package com.toy.plany.service;

import com.toy.plany.dto.request.member.MemberCreateRequest;
import com.toy.plany.dto.response.member.MemberResponse;

public interface MemberService {
    MemberResponse createUser(MemberCreateRequest request);

}
