package com.tamsil.mocktest.service;

import com.tamsil.mocktest.entity.Member;
import com.tamsil.mocktest.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberServiceV1 {

    private final MemberRepository memberRepository;

    public List<Member> createMember() {
        if (memberRepository.existMember()) {
            throw new DuplicateKeyException("기존에 등록되어 있는 멤버가 있습니다.");
        }

        memberRepository.createMember();
        return Collections.emptyList();
    }
}
