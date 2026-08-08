package com.management.MonaLibrary.message.producers;

import com.management.MonaLibrary.message.MyProducer;
import com.management.MonaLibrary.message.messagedto.BookTransportDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * This classis to send BookIssue event for every book a student has issued.
 * This means , if a user issues 5 books at at time , then , 5 separate bookEvents will be sent out.
 */
@Component
public class BookIssueProducerImpl extends MyProducer<BookTransportDTO> {

    private static final Logger LOGGER= LoggerFactory.getLogger(BookTransportDTO.class);
    private static final String bindingname="bookissue-out-0";


    public boolean  send(List<BookTransportDTO> bookTransportDTOs){
        bookTransportDTOs.forEach( bookTransportDTO -> {
            super.send(bindingname,bookTransportDTO);
            LOGGER.info("sending book Issue event for book with Id {}",bookTransportDTO.getISBN());
        });
        return true;
    }



}
