package org.blogy.service;

import java.util.Map;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import org.blogy.entity.Setting;
import org.blogy.repository.SettingRepository;

@Service
@Transactional
public class SettingServiceImpl implements SettingService {
    private final SettingRepository settingRepository;

    public SettingServiceImpl(SettingRepository settingRepository) {
        this.settingRepository = settingRepository;
    }

    @Transactional(readOnly = true)
    public long count() {
        return settingRepository.count();
    }

    @Transactional(readOnly = true)
    public List<Setting> getSettings() {
        return settingRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Setting getSetting(long id) {
        return settingRepository.findById(id).orElseThrow();
    }

    @Transactional(readOnly = true)
    public Setting getSetting(String name) {
        return settingRepository.findByName(name).orElseThrow();
    }

    public Map<String, String> settingsMap() {
        return getSettings().stream().collect(Collectors.toMap(Setting::getName, Setting::getValue));
    }

    public Setting createSetting(String name, String value) {
        Setting setting = new Setting();

        setting.setName(name);
        setting.setValue(value);

        return settingRepository.save(setting);
    }

    public Setting updateSetting(long id, String value) {
        Setting setting = getSetting(id);

        setting.setValue(value);

        return settingRepository.save(setting);
    }
}

