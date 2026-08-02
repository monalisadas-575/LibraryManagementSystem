package com.management.MonaLibrary.producer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.management.MonaLibrary.Entity.Student;
import com.management.MonaLibrary.transport.TransportStudent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class StudentProducer extends ProducerImpliment<TransportStudent> {

    @Autowired
    private ObjectMapper objectMapper;

    public boolean send(String bindingName, Student student) {
        TransportStudent transportStudent = objectMapper.convertValue(student, TransportStudent.class);
        super.send(bindingName, transportStudent);
        return true;
    }
}
