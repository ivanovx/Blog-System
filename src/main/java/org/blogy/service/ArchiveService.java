package org.blogy.service;

import java.util.Map;
import java.util.List;
import java.time.YearMonth;

import org.blogy.entity.Article;

public interface ArchiveService {
    Map<YearMonth, Long> get();

    List<Article> get(int month, int year);
}
