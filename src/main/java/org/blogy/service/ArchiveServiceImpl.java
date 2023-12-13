package org.blogy.service;

import java.util.Map;
import java.util.List;
import java.util.HashMap;
import java.time.YearMonth;
import java.util.stream.Stream;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import org.blogy.entity.Article;
import org.blogy.repository.ArticleRepository;

@Service
@Transactional(readOnly = true)
public class ArchiveServiceImpl implements ArchiveService {
    private final ArticleRepository articleRepository;

    public ArchiveServiceImpl(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    public Map<YearMonth, Long> get() {
        return getAllArticles()
                .collect(Collectors.groupingBy(article -> YearMonth.from(article.getCreated()),
                        HashMap::new,
                        Collectors.counting()
                ));
    }

    public List<Article> get(int month, int year) {
        YearMonth yearMonth = YearMonth.of(year, month);

        return getAllArticles()
                .filter(article -> YearMonth.from(article.getCreated()).compareTo(yearMonth) == 0)
                .toList();
    }

    private Stream<Article> getAllArticles() {
        return articleRepository.findByOrderByCreatedDesc().stream();
    }
}
