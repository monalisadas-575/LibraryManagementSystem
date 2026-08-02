package com.management.MonaLibrary.repository;

import com.management.MonaLibrary.model.BookModel;
import com.management.MonaLibrary.model.BookTrackingModel;
import org.springframework.data.domain.Limit;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends MongoRepository<BookModel,String> {

    void deleteAllByCategoryAndName(String category,String name);
    List<BookModel> findByCategoryAndNameAndStatus(String category, String name,String status);
    List<BookModel> findByCategoryAndNameAndStatus(String category, String name,String status,Limit limit);

    @Query("{ 'ISBN': { '$in': ?0 } }")
    @Update("{ '$set': { 'status': 'AVAILABLE' } }")
    long updateBooksToAvailableWhereISBNs(List<String> isbns);

    List<BookModel> findAllByISBNIn(List<String> isbns);
}
