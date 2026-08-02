package com.management.MonaLibrary.dto.request;

import com.management.MonaLibrary.myEnum.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookRequestDto {
    private String title;
    private String author;
    private int price;
    private Status bookStatus;
}
