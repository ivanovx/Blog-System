package org.blogy.service;

import java.util.Map;
import java.util.List;
import java.time.YearMonth;

import org.blogy.entity.Article;

public interface ArchiveService {
    List<Article> get(int month, int year);

    Map<YearMonth, Long> create();
}
