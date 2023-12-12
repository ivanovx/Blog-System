package org.blogy.entity;

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

    @Column(length = 512)
    private String value;

    public Setting() { }

    public Setting(String name, String value) {
        this.name = name;
        this.value = value;
    }
}