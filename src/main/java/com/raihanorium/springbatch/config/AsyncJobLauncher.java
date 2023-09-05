package com.raihanorium.springbatch.config;

import jakarta.annotation.Nonnull;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.TaskExecutorJobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.AsyncTaskExecutor;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.stereotype.Component;

@Component
public class AsyncJobLauncher implements JobLauncher {

    private final JobRepository jobRepository;
    private final AsyncTaskExecutor asyncTaskExecutor;

    @Autowired
    public AsyncJobLauncher(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
        this.asyncTaskExecutor = new SimpleAsyncTaskExecutor();
    }

    @Nonnull
    @Override
    public JobExecution run(@Nonnull Job job, @Nonnull JobParameters jobParameters) {
        TaskExecutorJobLauncher jobLauncher = new TaskExecutorJobLauncher();
        jobLauncher.setJobRepository(jobRepository);
        jobLauncher.setTaskExecutor(asyncTaskExecutor);
        try {
            jobLauncher.afterPropertiesSet();
            return jobLauncher.run(job, jobParameters);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
