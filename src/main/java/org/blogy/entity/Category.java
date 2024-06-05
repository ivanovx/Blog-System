package org.blogy.entity;

import lombok.Data;

import java.util.Set;

import jakarta.persistence.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Data
@Entity
@Table(name = "categories")
@EntityListeners(AuditingEntityListener.class)
public class Category extends BaseEntity {
    @Column(unique = true, nullable = false)
    private String name;

    @OneToMany
    private Set<Article> articles;

    public Category() { }

    public Category(String name) {
        this.name = name;
    }
}
