package org.blogy.entity;

import lombok.Data;

import jakarta.persistence.Table;
import jakarta.persistence.Entity;
import jakarta.persistence.Column;

@Data
@Entity
@Table(name="settings")
public class Setting extends BaseEntity {
    @Column
    private String name;

    @Column
    private String value;
}