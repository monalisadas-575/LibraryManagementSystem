package com.management.MonaLibrary.dto.enums;

public enum BookCategory {
    HISTORY("history"),
    MATH("Mathematics"),
    FINANCE("Finance"),
    SCIENCE("Science"),
    ENGLISH("English"),
    JAVA("Java");

    public final String thecategory;
    BookCategory(String category) {
        this.thecategory=category;
    }

    public String getTheCategory(){
        return this.thecategory;
    }


}
