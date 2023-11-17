package com.tamsil.springbootsqlinit.service;

import com.tamsil.springbootsqlinit.entity.Member;
import com.tamsil.springbootsqlinit.repository.MemberRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public List<Member> findAll() {
        return memberRepository.findAll();
    }
}
