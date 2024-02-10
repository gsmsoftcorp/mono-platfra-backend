package com.gsm.platfra.api.data.platfraboard;

import java.util.Optional;
import javax.swing.text.html.Option;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TPlatfraBoardRepository extends JpaRepository<TPlatfraBoard, Long> {
  Optional<TPlatfraBoard> findByPlatfraBoardSeq(Long platfraBoardId);
}
