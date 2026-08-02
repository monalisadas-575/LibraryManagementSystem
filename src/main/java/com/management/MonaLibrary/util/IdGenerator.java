package com.management.MonaLibrary.util;

import org.apache.commons.text.RandomStringGenerator;
import org.springframework.stereotype.Component;

@Component
public class IdGenerator {
    public  String generateByApacheText(int length) {
        String randomString = RandomStringGenerator.builder().setAccumulate(true)
                .withinRange(new char[][] {  { 'A', 'Z' }, { '0', '9' } })
                .get().generate(length);
        System.out.println("Random Alphanumeric String: " + randomString);
        return randomString;
    }
}
