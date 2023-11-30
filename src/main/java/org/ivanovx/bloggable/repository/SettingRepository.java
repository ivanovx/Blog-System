package org.ivanovx.bloggable.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import org.ivanovx.bloggable.entity.Setting;

public interface SettingRepository extends CrudRepository<Setting, Long> {
    List<Setting> findAll();

    Optional<Setting> findByName(String name);
}
