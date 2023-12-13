package org.blogy.form;

import lombok.Data;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

import org.blogy.entity.Article;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ArticleForm {
    @NotNull
    @NotBlank
    @NotEmpty
    @Size(min = 5, max = 255)
    private String title;

    @NotNull
    @NotBlank
    @NotEmpty
    private String content;

    @NotNull
    @NotBlank
    @NotEmpty
    private long categoryId;

    public static ArticleForm from(final Article article) {
        return ArticleForm.builder()
                .title(article.getTitle())
                .content(article.getContent())
                .categoryId(article.getCategory().getId())
                .build();
    }
}
