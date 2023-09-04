package com.raihanorium.springbatch.reader;

import com.raihanorium.springbatch.model.Member;
import jakarta.persistence.EntityManagerFactory;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.database.JpaPagingItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
//@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class MemberReader extends JpaPagingItemReader<Member> implements ItemReader<Member> {

    @Autowired
    EntityManagerFactory entityManagerFactory;

    public MemberReader() {
        setEntityManagerFactory(entityManagerFactory);
        setName("memberReader");
        setQueryString("select m from Member m");
        setPageSize(5);
    }
}
