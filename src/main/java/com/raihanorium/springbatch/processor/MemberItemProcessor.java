package com.raihanorium.springbatch.processor;

import com.raihanorium.springbatch.model.Member;
import jakarta.annotation.Nonnull;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
public class MemberItemProcessor implements ItemProcessor<Member, Member> {

    @Override
    public Member process(@Nonnull Member member) {
        return member;
    }
}
