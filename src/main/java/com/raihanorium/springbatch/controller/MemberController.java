package com.raihanorium.springbatch.controller;

import com.raihanorium.springbatch.config.AsyncJobLauncher;
import com.raihanorium.springbatch.model.Member;
import com.raihanorium.springbatch.repository.MemberRepository;
import jakarta.annotation.Nonnull;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Log4j2
@RestController
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class MemberController {

    @Nonnull
    private final MemberRepository memberRepository;
    @Nonnull
    private final JobRepository jobRepository;
    @Nonnull
    private final Job memberJob;
    @Nonnull
    private final AsyncJobLauncher asyncJobLauncher;

    @GetMapping("/start/{jobId}")
    public String start(@PathVariable(required = false) Long jobId) {
        JobParameters jobParameters = new JobParametersBuilder()
                .addString("jobId", String.valueOf(jobId))
                .toJobParameters();

        JobExecution jobExecution = jobRepository.getLastJobExecution("memberJob", jobParameters);
        if (jobExecution != null) {
            String message = "Job already exists. Current status: " + jobExecution.getStatus() + ". ";
            if (jobExecution.getStatus().isRunning()) {
                message += "Elapsed time: " + ChronoUnit.SECONDS.between(jobExecution.getStartTime(), LocalDateTime.now()) + " s. ";
            } else {
                message += "Time taken: " + ChronoUnit.SECONDS.between(jobExecution.getStartTime(), jobExecution.getEndTime()) + " s. ";
            }
            return message;
        }

        try {
            asyncJobLauncher.run(memberJob, jobParameters);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return "Started.";
    }

    @GetMapping("/members")
    public List<Member> getAll() {
        return memberRepository.findAll();
    }
}
