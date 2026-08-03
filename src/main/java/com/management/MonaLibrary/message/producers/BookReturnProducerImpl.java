package com.management.MonaLibrary.message.producers;

import com.management.MonaLibrary.message.MyProducer;
import com.management.MonaLibrary.message.messagedto.BookTransportDTO;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * This classis to send BookReturn event for every book a student has issued.
 * This means , if a user issues 5 books at at time , then , 5 separate bookReturnEvents will be sent out.
 */
@Component
public class BookReturnProducerImpl extends MyProducer<BookTransportDTO> {

    private static final String bindingname="bookreturn-out-0";

    public boolean  send(List<BookTransportDTO> bookTransportDTOs){
        bookTransportDTOs.forEach( bookTransportDTO -> {
            super.send(bindingname,bookTransportDTO);
        });
        return true;
    }
}
