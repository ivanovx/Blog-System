package org.ivanovx.bloggable.entity;

import lombok.Data;

import jakarta.persistence.Table;
import jakarta.persistence.Entity;
import jakarta.persistence.Column;

@Data
@Entity
@Table(name="settings")
public class Setting extends BaseEntity {
    @Column(unique = true, updatable = false)
    private String name;

    @Column(nullable = false)
    private String value;
}