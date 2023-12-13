package org.blogy.entity;

import lombok.Data;

import jakarta.persistence.*;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Data
@Entity
@Table(name = "articles")
@EntityListeners(AuditingEntityListener.class)
public class Article extends BaseEntity {
    @Column(unique = true, nullable = false)
    private String slug;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false, columnDefinition="TEXT")
    private String content;

   // @ElementCollection(fetch = FetchType.EAGER)
  //  @CollectionTable(name="article_keywords")
  //  private Set<@Pattern(regexp = "^[\\w\\s\\+-]+$") String> keywords;

    @ManyToOne
    private Category category;

    @CreatedBy
    @ManyToOne
    private User user;
}