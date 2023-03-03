package com.groupeisi.examenspringdevops.repository;

import com.groupeisi.examenspringdevops.entity.CVEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author MS GASSAMA
 */
@Repository
public interface ICVRepository extends JpaRepository<CVEntity, Integer> {
    CVEntity findByEmail(String email);

}