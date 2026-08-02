package com.management.MonaLibrary.dto.request;

import com.management.MonaLibrary.dto.enums.MemberType;

import java.util.List;

public record MemberDetail(String id, String name, MemberType type, List<String> ISBNs) {
}
