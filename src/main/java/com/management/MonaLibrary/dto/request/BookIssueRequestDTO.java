package com.management.MonaLibrary.dto.request;

import com.management.MonaLibrary.dto.enums.MemberType;

public record BookIssueRequestDTO(String personId, MemberType type) {
}
