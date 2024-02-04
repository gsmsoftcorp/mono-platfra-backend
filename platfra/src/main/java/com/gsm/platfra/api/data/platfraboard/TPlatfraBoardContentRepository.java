package com.gsm.platfra.api.data.platfraboard;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TPlatfraBoardContentRepository extends JpaRepository<TPlatfraBoardContent, Long> {

}
