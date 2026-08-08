package com.management.MonaLibrary.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StockBooksRequestDto extends LibraryStockRequestDTO {
    private String title;
    private String author;
    private int price;
    private Date date;
    private String description;
}
