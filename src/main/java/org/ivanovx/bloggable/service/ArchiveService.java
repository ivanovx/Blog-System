package org.ivanovx.bloggable.service;

import java.util.Map;
import java.util.List;
import java.time.YearMonth;

import org.ivanovx.bloggable.entity.Article;

public interface ArchiveService {
    List<Article> get(int month, int year);

    Map<YearMonth, Long> create();
}
