package org.blogy.service;

import java.util.Map;
import java.util.List;

import org.blogy.entity.Setting;

public interface SettingService {
    long count();

    List<Setting> getSettings();

    Map<String, String> getSettingsMap();

    Setting getSetting(long id);

    Setting getSetting(String name);

    Setting createSetting(Setting setting);

    Setting updateSetting(String name, String value);
}
