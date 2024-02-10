package com.tamsil.mocktest.repository;

import com.tamsil.mocktest.entity.Member;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class MemberRepository {
    private Map<String, Member> memberMap = new HashMap<>();

    public boolean existMember() {
        return false;
    }

    public Member createMember() {
        Member member = Member.builder()
                .name("john")
                .age(30)
                .phoneNumber("000-0000-0000")
                .address("Seoul")
                .gender("MALE")
                .build();
        return member;
    }

}
