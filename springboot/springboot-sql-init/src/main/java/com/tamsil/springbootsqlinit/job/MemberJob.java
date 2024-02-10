package com.tamsil.springbootsqlinit.job;

import com.tamsil.springbootsqlinit.entity.Member;
import com.tamsil.springbootsqlinit.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class MemberJob implements Job {

    private final MemberService memberService;

    public MemberJob(MemberService memberService) {
        this.memberService = memberService;
    }

    @Override
    public void execute() {
        memberService.findAll()
                .stream()
                .map(Member::toString)
                .forEach(log::info);
    }
}
