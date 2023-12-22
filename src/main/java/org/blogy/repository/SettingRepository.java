package org.blogy.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import org.blogy.entity.Setting;

public interface SettingRepository extends JpaRepository<Setting, Long> {
    Optional<Setting> findByName(String name);

    boolean existsByName(String name);
}
