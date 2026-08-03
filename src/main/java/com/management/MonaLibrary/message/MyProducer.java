package com.management.MonaLibrary.message;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Component;

@Component
public class MyProducer<PAYLOAD> {

    @Autowired
    public StreamBridge streamBridge;
    public PAYLOAD payload;

    public boolean send(String bindingname,PAYLOAD payload){
        streamBridge.send(bindingname,payload);
        return true;
    }
}
