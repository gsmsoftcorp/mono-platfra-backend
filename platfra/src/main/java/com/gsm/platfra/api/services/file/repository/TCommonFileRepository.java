package com.gsm.platfra.api.services.file.repository;

import com.gsm.platfra.api.entity.common.TCommonFile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TCommonFileRepository extends JpaRepository<TCommonFile, Long> {

}
