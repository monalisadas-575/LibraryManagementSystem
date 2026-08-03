package com.management.MonaLibrary.model;

import com.management.MonaLibrary.dto.enums.BookCategory;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "BookTracking")
public class BookTrackingModel {

    @Id
    public String id;
    public String ISBN;
    public String memberId;
    public LocalDateTime issuedAt;
    public LocalDateTime returnedAt;
}
