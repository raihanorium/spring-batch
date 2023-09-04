package com.raihanorium.springbatch;

import com.raihanorium.springbatch.model.Member;
import com.raihanorium.springbatch.repository.MemberRepository;
import jakarta.annotation.Nonnull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class DatabaseInitializer implements ApplicationRunner {

    @Nonnull
    private final MemberRepository memberRepository;

    @Override
    public void run(ApplicationArguments args) {
        memberRepository.save(Member.builder().id(1L).name("Member 1").build());
        memberRepository.save(Member.builder().id(2L).name("Member 2").build());
        memberRepository.save(Member.builder().id(3L).name("Member 3").build());
        memberRepository.save(Member.builder().id(4L).name("Member 4").build());
        memberRepository.save(Member.builder().id(5L).name("Member 5").build());
        memberRepository.save(Member.builder().id(6L).name("Member 6").build());
        memberRepository.save(Member.builder().id(7L).name("Member 7").build());
        memberRepository.save(Member.builder().id(8L).name("Member 8").build());
        memberRepository.save(Member.builder().id(9L).name("Member 9").build());
        memberRepository.save(Member.builder().id(10L).name("Member 10").build());

    }
}
