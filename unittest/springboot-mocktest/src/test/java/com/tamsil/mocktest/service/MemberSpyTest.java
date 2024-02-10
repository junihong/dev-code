package com.tamsil.mocktest.service;

import com.tamsil.mocktest.entity.Member;
import com.tamsil.mocktest.repository.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;

@Slf4j
@ExtendWith(MockitoExtension.class)
class MemberSpyTest {

    @Spy
    private MemberRepository memberRepository;

    @InjectMocks
    private MemberServiceV1 memberServiceV1;

    @Test
    void findByLessonTest() {
        Member member = getMember();

        // given
        given(memberRepository.createMember()).willReturn(member);

        // when
        List<Member> resultList = memberServiceV1.createMember();

        // then
        then(memberRepository).should(times(1)).createMember();
    }

    private static Member getMember() {
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
