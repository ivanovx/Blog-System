package org.ivanovx.bloggable;

import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.ApplicationArguments;
import org.springframework.beans.factory.annotation.Value;

import org.ivanovx.bloggable.entity.Role;
import org.ivanovx.bloggable.entity.User;
import org.ivanovx.bloggable.entity.Category;

import org.ivanovx.bloggable.service.UserService;
import org.ivanovx.bloggable.service.SettingService;
import org.ivanovx.bloggable.service.CategoryService;

@Component
public class ApplicationInit implements ApplicationRunner {
    private final UserService userService;

    private final SettingService settingService;

    private final CategoryService categoryService;

    @Value("${blogy.admin.mail}")
    private String email;

    @Value("${blogy.admin.username}")
    private String username;

    @Value("${blogy.admin.password}")
    private String password;

    @Value("${blogy.default.category}")
    private String defaultCategory;

    public ApplicationInit(UserService userService, SettingService settingService, CategoryService categoryService) {
        this.userService = userService;
        this.settingService = settingService;
        this.categoryService = categoryService;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        if (!userService.haveAdminUser()) {
            userService.createUser(new User(email, username, password, Role.ADMIN));
        }

        if (settingService.count() == 0) {
            Map.ofEntries(
                    Map.entry("title", "Sample title"),
                    Map.entry("description", "Sample description")
            ).forEach((name, value) -> settingService.createSetting(name, value));
        }

        if (categoryService.count() == 0) {
            //this.categoryService.createCategory(Constants.DEFAULT_CATEGORY);
            categoryService.createCategory(new Category(defaultCategory));
        }
    }
}
