package org.ivanovx.bloggable.entity;

import lombok.Data;

import jakarta.persistence.*;
import jakarta.validation.constraints.PastOrPresent;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@MappedSuperclass
public abstract class BaseEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @PastOrPresent
    @Column(nullable = false, updatable = false)
    private LocalDateTime created = LocalDateTime.now();

    @PastOrPresent
    private LocalDateTime modified = null;

    @Version
    private Long version = 1L;
}
