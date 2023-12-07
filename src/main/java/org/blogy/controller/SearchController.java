package org.blogy.controller;

import org.blogy.entity.Article;

import org.blogy.request.SearchRequest;
import org.hibernate.search.engine.search.query.SearchResult;
import org.hibernate.search.mapper.orm.session.SearchSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/search")
public class SearchController {
    private final SearchSession searchSession;

    public SearchController(SearchSession searchSession) {
        this.searchSession = searchSession;
    }

    @PostMapping
    @ResponseBody
    public List<Article> search(@ModelAttribute SearchRequest search) {
        SearchResult<Article> result = searchSession.search(Article.class)
                .where( f -> f.match().fields( "content" ).matching(search.getValue()))
                .fetch( 20 );

        return result.hits();
    }
}
