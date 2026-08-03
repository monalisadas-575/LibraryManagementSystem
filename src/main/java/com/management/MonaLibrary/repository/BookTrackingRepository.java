package com.management.MonaLibrary.repository;

import com.management.MonaLibrary.model.BookTrackingModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookTrackingRepository extends MongoRepository<BookTrackingModel, String> {
    List<BookTrackingModel> findAllByISBNIn(List<String> isbns);

    @Query("{ 'returnedAt': null, 'ISBN': { $in: ?0 } }")
    List<BookTrackingModel> findBooksTrackingRecordWhichAreNotReturnedYetWithGivenIsbns(List<String> isbns);

}
