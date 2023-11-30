package org.blogy.util;

import java.util.Map;
import java.util.List;

import com.rometools.rome.feed.rss.Item;
import com.rometools.rome.feed.rss.Channel;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.feed.AbstractRssFeedView;

import org.blogy.service.ArticleService;
import org.blogy.service.SettingService;

@Component
public class RssFeedView extends AbstractRssFeedView {
    private final ArticleService articleService;

    private final SettingService settingService;

    public RssFeedView(ArticleService articleService, SettingService settingService) {
        this.articleService = articleService;
        this.settingService = settingService;
    }

    @Override
    protected void buildFeedMetadata(Map<String, Object> model, Channel feed, HttpServletRequest request) {
        feed.setTitle(settingService.getSetting("title").getValue());
        feed.setLink(settingService.getSetting("url").getValue());
        feed.setDescription(settingService.getSetting("description").getValue());
    }

    @Override
    protected List<Item> buildFeedItems(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return articleService.getArticles()
                .stream()
                .map(article -> {
                    Item entry = new Item();

                    entry.setTitle(article.getTitle());
                    entry.setAuthor(article.getUser().getUsername());

                    return entry;
                })
                .toList();
    }
}