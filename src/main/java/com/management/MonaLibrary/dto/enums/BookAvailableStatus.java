package com.management.MonaLibrary.dto.enums;

public enum BookAvailableStatus {
    AVAILABLE("AVAILABLE"),
    BOOKED("BOOKED"),
    UNAVAILABLE("UNAVAILABLE");

    public String staus;
    BookAvailableStatus(String status){
        this.staus=status;
    }

    String getStatus(){
        return this.staus;
    }
}
