package org.ivanovx.bloggable.service;

import java.util.List;
import java.util.Map;

import org.ivanovx.bloggable.entity.Setting;

public interface SettingService {
    long count();

    List<Setting> getSettings();

    Map<String, String> settingsMap();

    Setting getSetting(long id);

    Setting getSetting(String name);

    Setting createSetting(String name, String value);

    Setting updateSetting(long id, String value);
}
