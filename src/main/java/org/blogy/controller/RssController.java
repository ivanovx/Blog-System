package org.blogy.controller;

import org.blogy.util.RssFeedView;

import org.springframework.web.servlet.View;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RssController {
    private final RssFeedView view;

    public RssController(RssFeedView view) {
        this.view = view;
    }


    @GetMapping(value = "/rss", produces = { "application/rss+xml" })
    public View feed() {
        return view;
    }
}
