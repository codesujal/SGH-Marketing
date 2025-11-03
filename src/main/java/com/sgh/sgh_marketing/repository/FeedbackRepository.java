package com.sgh.sgh_marketing.repository;

import com.sgh.sgh_marketing.model.Feedback;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FeedbackRepository extends MongoRepository<Feedback, String> {
    // pass
}
