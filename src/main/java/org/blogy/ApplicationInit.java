package org.blogy;

import org.blogy.entity.Article;
import org.blogy.entity.Category;
import org.blogy.entity.Role;
import org.blogy.entity.User;
import org.blogy.service.CategoryService;
import org.blogy.service.SettingService;
import org.blogy.service.UserService;
import org.hibernate.search.mapper.orm.massindexing.MassIndexer;
import org.hibernate.search.mapper.orm.session.SearchSession;
import org.springframework.stereotype.Component;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.ApplicationArguments;
import org.springframework.beans.factory.annotation.Value;

import java.util.Map;

@Component
public class ApplicationInit implements ApplicationRunner {

    private final SearchSession searchSession;

    private final UserService userService;

    private final SettingService settingService;

    private final CategoryService categoryService;

    @Value("${blogy.admin.mail}")
    private String adminEmail;

    @Value("${blogy.admin.username}")
    private String adminUsername;

    @Value("${blogy.admin.password}")
    private String adminPassword;

    @Value("${blogy.default.category}")
    private String defaultCategory;

    @Value("${blogy.default.settings.url}")
    private String defaultUrl;

    @Value("${blogy.default.settings.title}")
    private String defaultTitle;

    @Value("${blogy.default.settings.description}")
    private String defaultDescription;

    public ApplicationInit(SearchSession searchSession, UserService userService, SettingService settingService, CategoryService categoryService) {
        this.searchSession = searchSession;
        this.userService = userService;
        this.settingService = settingService;
        this.categoryService = categoryService;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        initIndexer();

        if (!userService.haveAdminUser()) {
            userService.createUser(new User(adminEmail, adminUsername, adminPassword, Role.ADMIN));
        }

        if (settingService.count() == 0) {
            Map.ofEntries(
                Map.entry("url", defaultUrl),
                Map.entry("title", defaultTitle),
                Map.entry("description", defaultDescription)
            ).forEach((name, value) -> settingService.createSetting(name, value));
        }

        if (categoryService.count() == 0) {
            categoryService.createCategory(new Category(defaultCategory));
        }
    }

    private void initIndexer() throws InterruptedException {
        MassIndexer indexer = searchSession.massIndexer(Article.class); //.threadsToLoadObjects();

        indexer.startAndWait();
    }
}
