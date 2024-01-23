package com.gsm.platfra.api.services.board.repository;

import com.gsm.platfra.api.entity.platfraboard.TPlatfraBoard;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlatfraBoardRepository extends JpaRepository<TPlatfraBoard, Long> {
  List<TPlatfraBoard> findAllByPlatfraId(String platfraId);
  TPlatfraBoard findByPlatfraBoardSeq(Long platfraBoardId);
}
