package com.gsm.platfra.api.data.platfraboard;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TPlatfraBoardRepository extends JpaRepository<TPlatfraBoard, Long> {
  List<TPlatfraBoard> findAllByPlatfraId(String platfraId);
  TPlatfraBoard findByPlatfraBoardSeq(Long platfraBoardId);
}
