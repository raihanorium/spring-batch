package com.raihanorium.springbatch.reader;

import com.raihanorium.springbatch.model.Member;
import jakarta.annotation.Nonnull;
import jakarta.persistence.EntityManagerFactory;
import org.springframework.batch.item.database.JpaPagingItemReader;
import org.springframework.stereotype.Component;

@Component
public class MemberReader extends JpaPagingItemReader<Member> {

    public MemberReader(@Nonnull EntityManagerFactory entityManagerFactory) {
        setEntityManagerFactory(entityManagerFactory);
        setName("memberReader");
        setQueryString("select m from Member m");
        setPageSize(5);
    }
}
