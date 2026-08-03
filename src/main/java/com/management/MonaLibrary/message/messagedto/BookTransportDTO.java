package com.management.MonaLibrary.message.messagedto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookTransportDTO {

    public String ISBN;
    public String memberId;
    public LocalDateTime issuedAt;
    public LocalDateTime returnedAt;
}
