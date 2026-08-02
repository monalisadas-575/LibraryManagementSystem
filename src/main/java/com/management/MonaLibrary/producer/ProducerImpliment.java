package com.management.MonaLibrary.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.cloud.stream.function.StreamBridge;

@Component
public class ProducerImpliment<PAYLOAD> {
    private PAYLOAD payload;
    @Autowired
    private StreamBridge streamBridge;

    public void send(String bindingName, PAYLOAD payload) {
        streamBridge.send(bindingName, payload);
    }
}
