package org.ivanovx.bloggable.entity;

import jakarta.persistence.*;

import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Set;

@Data
@Entity
@Table(name = "articles")
@EntityListeners(AuditingEntityListener.class)
public class Article extends BaseEntity {

    @Column(nullable = false)
    private String slug;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false, columnDefinition="TEXT")
    private String content;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name="article_keywords")
    private Set<@Pattern(regexp = "^[\\w\\s\\+-]+$") String> keywords;

    @ManyToOne
    private Category category;

    @CreatedBy
    @ManyToOne
    private User author;
}