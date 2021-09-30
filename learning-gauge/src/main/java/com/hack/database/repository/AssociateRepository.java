package com.hack.database.repository;

import com.hack.database.entity.AssociateEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AssociateRepository extends JpaRepository<AssociateEntity, Long> {
  AssociateEntity findByAssociateEmail(String email);

  AssociateEntity findByAssociateId(String associateId);

}
