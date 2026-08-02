package com.management.MonaLibrary.utils;

import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class IdgeneratorUtil {

    public String getAnotherUUid(String prefix){
        String uuid= UUID.randomUUID().toString();
        return prefix+" - "+ uuid;
    }
}
