package com.swiftHearty.data.repository;

import com.swiftHearty.data.model.SecurityPersonnel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface SecurityPersonnelRepository extends MongoRepository<SecurityPersonnel, String> {
    boolean existsSecurityPersonnelByEmail(String email);

    Optional<SecurityPersonnel> findSecurityPersonnelByEmail(String email);
}
