package org.ivanovx.bloggable.service;

import org.ivanovx.bloggable.entity.Setting;
import org.ivanovx.bloggable.repository.SettingRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Transactional
public class SettingService {
    private final SettingRepository settingRepository;

    public SettingService(SettingRepository settingRepository) {
        this.settingRepository = settingRepository;
    }

    @Transactional(readOnly = true)
    public long count() {
        return this.settingRepository.count();
    }

    @Transactional(readOnly = true)
    public List<Setting> getSettings() {
        return this.settingRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Map<String, String> settingsMap() {
        return this.getSettings().stream().collect(Collectors.toMap(Setting::getName, Setting::getValue));
    }

    @Transactional(readOnly = true)
    public Setting getSetting(long id) {
        return this.settingRepository.findById(id).orElseThrow();
    }

    @Transactional(readOnly = true)
    public Setting getSetting(String name) {
        return this.settingRepository.findByName(name).orElseThrow();
    }

    public Setting createSetting(String name, String value) {
        Setting setting = new Setting();

        setting.setName(name);
        setting.setValue(value);

        return this.settingRepository.save(setting);
    }

    public Setting updateSetting(long id, String value) {
        Setting setting = this.getSetting(id);

        setting.setValue(value);

        return this.settingRepository.save(setting);
    }
}
