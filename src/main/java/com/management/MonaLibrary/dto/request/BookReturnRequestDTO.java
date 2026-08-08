package com.management.MonaLibrary.dto.request;

import com.management.MonaLibrary.dto.enums.MemberType;

import java.util.List;

public record BookReturnRequestDTO(String personId, String bookname, MemberType type, List<String> ISBNs) {
}
