package com.raihanorium.springbatch.writer;

import com.raihanorium.springbatch.model.Member;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Component;

@Component
public class MemberWriter extends FlatFileItemWriter<Member> {

    public MemberWriter() {
        setResource(new FileSystemResource("data/members.csv"));
        setLineAggregator(getDelimitedLineAggregator());
    }

    public DelimitedLineAggregator<Member> getDelimitedLineAggregator() {
        BeanWrapperFieldExtractor<Member> beanWrapperFieldExtractor = new BeanWrapperFieldExtractor<Member>();
        beanWrapperFieldExtractor.setNames(new String[]{"id", "name"});

        DelimitedLineAggregator<Member> delimitedLineAggregator = new DelimitedLineAggregator<Member>();
        delimitedLineAggregator.setDelimiter(",");
        delimitedLineAggregator.setFieldExtractor(beanWrapperFieldExtractor);
        return delimitedLineAggregator;

    }
}
