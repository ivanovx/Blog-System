package org.blogy.util;

import java.util.Map;
import java.util.List;

import com.rometools.rome.feed.rss.Channel;
import com.rometools.rome.feed.rss.Item;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.blogy.service.ArticleService;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.feed.AbstractRssFeedView;

@Component
public class RssFeedView extends AbstractRssFeedView {
    private final ArticleService articleService;

    public RssFeedView(ArticleService articleService) {
        this.articleService = articleService;
    }

    @Override
    protected void buildFeedMetadata(Map<String, Object> model, Channel feed, HttpServletRequest request) {
        feed.setTitle("Baeldung RSS Feed");
        feed.setDescription("Learn how to program in Java");
        feed.setLink("http://www.baeldung.com");
    }

    @Override
    protected List<Item> buildFeedItems(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return articleService
                .getArticles()
                //.subList(0, 10)
                .stream()
                .map(article -> {
                    Item entry = new Item();

                    entry.setTitle(article.getTitle());
                    entry.setAuthor(article.getUser().getUsername());
                    //entry.setLink("http://www.baeldung.com/junit-5-test-annotation");
                   //entry.setPubDate(Date.from(article.getCreated()));

                    return entry;
                })
                .toList();
    }
}