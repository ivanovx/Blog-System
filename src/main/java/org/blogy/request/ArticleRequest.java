package org.blogy.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.blogy.entity.Article;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ArticleRequest {
    private String title;

    private String content;

    private long category;

    private String keywords;

    public static ArticleRequest of(Article article) {
        return ArticleRequest
                .builder()
                .title(article.getTitle())
                .content(article.getContent())
                .category(article.getCategory().getId())
                .keywords(String.join(",", article.getKeywords()))
                .build();
    }
}
