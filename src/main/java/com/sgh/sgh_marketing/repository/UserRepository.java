package com.sgh.sgh_marketing.repository;

import com.sgh.sgh_marketing.model.UserModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<UserModel, String> {
    Optional<UserModel> findByEmail(String email);
}
