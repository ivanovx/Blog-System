package org.blogy.config;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.HibernateException;
import org.hibernate.search.mapper.orm.Search;
import org.hibernate.search.mapper.orm.session.SearchSession;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HibernateConfig {
    @Bean
    public Session session(SessionFactory sessionFactory) {
        try {
            return sessionFactory.getCurrentSession();
        } catch (HibernateException e) { }

        return sessionFactory.openSession();
    }

    @Bean
    public SearchSession searchSession(Session session) {
        return Search.session(session);
    }
}
