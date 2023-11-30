package org.blogy.entity;

import lombok.Data;

import java.util.Set;

import jakarta.persistence.*;

@Data
@Entity
@Table(name = "categories")
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
