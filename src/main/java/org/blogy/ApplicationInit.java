package org.blogy;

import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.ApplicationArguments;
import org.springframework.context.ApplicationContext;
import org.springframework.beans.factory.annotation.Value;

import org.blogy.entity.Role;
import org.blogy.entity.User;
import org.blogy.entity.Setting;
import org.blogy.entity.Category;
import org.blogy.service.UserService;
import org.blogy.service.SettingService;
import org.blogy.service.CategoryService;

@Component
public class ApplicationInit implements ApplicationRunner {
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

    private final ApplicationContext applicationContext;

    public ApplicationInit(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        final UserService userService = applicationContext.getBean(UserService.class);
        final SettingService settingService = applicationContext.getBean(SettingService.class);
        final CategoryService categoryService = applicationContext.getBean(CategoryService.class);

        if (!userService.haveAdminUser()) {
            userService.createUser(new User(adminEmail, adminUsername, adminPassword, Role.ADMIN));
        }

        if (settingService.count() == 0) {
            Map.ofEntries(
                Map.entry("url", defaultUrl),
                Map.entry("title", defaultTitle),
                Map.entry("description", defaultDescription)
            ).forEach((name, value) -> {
                Setting setting = new Setting(name, value);

                settingService.createSetting(setting);
            });
        }

        if (categoryService.count() == 0) {
            categoryService.createCategory(new Category(defaultCategory));
        }
    }
}
