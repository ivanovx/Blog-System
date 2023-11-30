package org.blogy.repository;

import java.util.List;
import java.util.Optional;

import org.blogy.entity.Setting;
import org.springframework.data.repository.CrudRepository;

public interface SettingRepository extends CrudRepository<Setting, Long> {
    List<Setting> findAll();

    Optional<Setting> findByName(String name);
}
