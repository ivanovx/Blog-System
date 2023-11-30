package org.ivanovx.bloggable.service;

import java.util.Map;
import java.util.List;
import java.util.HashMap;
import java.time.YearMonth;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import org.ivanovx.bloggable.entity.Article;
import org.ivanovx.bloggable.repository.ArticleRepository;

@Service
public class ArchiveServiceImpl implements ArchiveService {
    private final ArticleRepository articleRepository;

    public ArchiveServiceImpl(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    @Transactional(readOnly = true)
    public List<Article> get(int month, int year) {
        YearMonth yearMonth = YearMonth.of(year, month);

        return articleRepository
                .findAll()
                .stream()
                .filter(article -> YearMonth.from(article.getCreated()).compareTo(yearMonth) == 0)
                .toList();
    }

    @Transactional(readOnly = true)
    public Map<YearMonth, Long> create() {
        return articleRepository
                .findAll()
                .stream()
                .collect(Collectors.groupingBy(article -> YearMonth.from(article.getCreated()),
                        HashMap::new,
                        Collectors.counting()
                ));
    }
}
