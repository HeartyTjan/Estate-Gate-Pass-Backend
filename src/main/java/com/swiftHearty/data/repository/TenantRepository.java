package com.swiftHearty.data.repository;

import com.swiftHearty.data.model.Tenant;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface TenantRepository extends MongoRepository<Tenant,String> {

    Optional<Tenant> findTenantByEmail(String email);

    boolean existsTenantByEmail(String email);
}
