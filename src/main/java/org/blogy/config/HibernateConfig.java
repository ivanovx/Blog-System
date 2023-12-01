package org.blogy.config;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

import org.hibernate.search.mapper.orm.Search;
import org.hibernate.search.mapper.orm.session.SearchSession;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HibernateConfig {
    @Bean
    public SearchSession searchSession(EntityManagerFactory emf) {
        EntityManager entityManager = emf.createEntityManager();

        return Search.session(entityManager);
    }
}
