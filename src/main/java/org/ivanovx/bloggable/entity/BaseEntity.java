package org.ivanovx.bloggable.entity;

import lombok.Data;


import java.time.LocalDateTime;

import jakarta.persistence.*;
import jakarta.validation.constraints.PastOrPresent;

@Data
@MappedSuperclass
public abstract class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @PastOrPresent
    @Column(nullable = false, updatable = false)
    private LocalDateTime created = LocalDateTime.now();

    @PastOrPresent
    private LocalDateTime modified = null;
}
