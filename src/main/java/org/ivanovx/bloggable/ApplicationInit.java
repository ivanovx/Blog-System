package org.ivanovx.bloggable;

import java.util.Map;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.ApplicationArguments;
import org.springframework.stereotype.Component;

import org.ivanovx.bloggable.util.Constants;
import org.ivanovx.bloggable.service.UserService;
import org.ivanovx.bloggable.service.SettingService;
import org.ivanovx.bloggable.service.CategoryService;

@Component
public class ApplicationInit implements ApplicationRunner {
    private final UserService userService;

    private final SettingService settingService;

    private final CategoryService categoryService;

    public ApplicationInit(UserService userService, SettingService settingService, CategoryService categoryService) {
        this.userService = userService;
        this.settingService = settingService;
        this.categoryService = categoryService;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        if (!userService.haveAdminUser()) {
            this.userService.createUser(Constants.DEFAULT_ADMIN);
        }

        if (this.settingService.count() == 0) {
            Map.of(
                    "title", "Sample title",
                    "description", "Sample description"
            ).forEach((name, value) -> this.settingService.createSetting(name, value));
        }

        if (this.categoryService.count() == 0) {
            this.categoryService.createCategory(Constants.DEFAULT_CATEGORY);
        }
    }
}
