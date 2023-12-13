package org.blogy.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import org.blogy.entity.Setting;

public interface SettingRepository extends CrudRepository<Setting, Long> {
    List<Setting> findAll();

    Optional<Setting> findByName(String name);

    boolean existsByName(String name);
}
