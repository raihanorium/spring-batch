package com.raihanorium.springbatch.controller;

import com.raihanorium.springbatch.model.Member;
import com.raihanorium.springbatch.repository.MemberRepository;
import jakarta.annotation.Nonnull;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class MemberController {

    @Nonnull
    private final MemberRepository memberRepository;
    @Nonnull
    private final Step memberStep;
    @Nonnull
    private final Job memberJob;
    @Nonnull
    private final JobLauncher jobLauncher;

    @GetMapping("/start")
    public String start() {
        JobParameters jobParameters = new JobParametersBuilder()
                .addString("JobID", String.valueOf(1))
                .toJobParameters();
        try {
            jobLauncher.run(memberJob, jobParameters);
        } catch (JobExecutionAlreadyRunningException | JobRestartException | JobInstanceAlreadyCompleteException |
                 JobParametersInvalidException e) {
            throw new RuntimeException(e);
        }
        return "Started";
    }

    @GetMapping("/members")
    public List<Member> getAll() {
        return memberRepository.findAll();
    }
}
