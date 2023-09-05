package com.raihanorium.springbatch.config;

import com.raihanorium.springbatch.model.Member;
import com.raihanorium.springbatch.reader.MemberReader;
import com.raihanorium.springbatch.writer.MemberWriter;
import jakarta.annotation.Nonnull;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;


@Configuration
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class BatchConfig {

    @Nonnull
    private final MemberReader memberReader;
    @Nonnull
    private final MemberWriter memberWriter;


    @Bean
    public Job memberJob(JobRepository jobRepository, Step singleStep) {
        return new JobBuilder("memberJob", jobRepository)
                .start(singleStep)
                .build();
    }


    @Bean
    public Step memberStep(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        return new StepBuilder("memberStep", jobRepository)
                .<Member, Member>chunk(10, transactionManager)
                .reader(memberReader)
                .writer(memberWriter)
                .build();
    }
}
