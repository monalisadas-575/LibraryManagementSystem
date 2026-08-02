package com.management.MonaLibrary.model;

import com.management.MonaLibrary.dto.enums.BookAvailableStatus;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Book")
@Data
public class BookModel {

    @Id
    public String id;

    public String ISBN;
    public String name;
    public String category;
    public String author;
    public BookAvailableStatus status;
    @Version
    public Long version;
}
