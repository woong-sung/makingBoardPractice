package com.jojo.Board.Config;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.query.JpaQueryMethodFactory;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@EnableJpaAuditing
@Configuration
public class QuerydslConfig {
    @PersistenceContext
    private EntityManager entityManager;

    @Bean
    public JPAQueryFactory jpaQueryFactory(){
        return new JPAQueryFactory(this.entityManager);
    };
}
