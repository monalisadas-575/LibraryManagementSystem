package com.management.MonaLibrary.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.management.MonaLibrary.exception.InvalidReturnRequestException;
import com.management.MonaLibrary.model.BookTrackingModel;
import com.management.MonaLibrary.dto.enums.BookAvailableStatus;
import com.management.MonaLibrary.dto.enums.BookCategory;
import com.management.MonaLibrary.dto.request.MemberDetail;
import com.management.MonaLibrary.dto.request.StockBooksRequestDto;
import com.management.MonaLibrary.exception.NotEnoughBooksAvaialble;
import com.management.MonaLibrary.model.BookModel;
import com.management.MonaLibrary.repository.BookRepository;
import com.management.MonaLibrary.repository.BookTrackingRepository;
import com.management.MonaLibrary.utils.IdgeneratorUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Limit;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class StockService {

    public static Logger logger= LoggerFactory.getLogger(StockService.class);

    public final BookRepository bookRepository;
    public final BookTrackingRepository bookTrackingRepository;
    public final IdgeneratorUtil idgeneratorUtil;
    public final ObjectMapper  objectMapper;

    public StockService(BookRepository bookRepository, BookTrackingRepository bookTrackingRepository, IdgeneratorUtil idgeneratorUtil, ObjectMapper objectMapper) {
        this.bookRepository = bookRepository;
        this.bookTrackingRepository = bookTrackingRepository;
        this.idgeneratorUtil = idgeneratorUtil;
        this.objectMapper = objectMapper;
    }

    public void updateNewStocksForBooks(StockBooksRequestDto stockRequestDto, BookCategory category, Integer count){
        if(count<=0) {
            logger.error("Invalid  count has been sent in Request");
            throw new RuntimeException("Invalid count of books");
        }

        List<BookModel> models= new ArrayList<>();
        while(count>0){
            String ISBN= idgeneratorUtil.getAnotherUUid(category.name());
            BookModel bookModel= objectMapper.convertValue(stockRequestDto, BookModel.class);
            bookModel.setCategory(category.getTheCategory());
            bookModel.setISBN(ISBN);
            bookModel.setName(stockRequestDto.getTitle());
            bookModel.setStatus(BookAvailableStatus.AVAILABLE);
            models.add(bookModel);
            count--;
        }

        bookRepository.saveAll(models);
        logger.info("All books has been saved Successfully");
    }

    public void clearOldStocksOFBooksByNameAndCategory(String name, BookCategory category){

        bookRepository.deleteAllByCategoryAndName(category.getTheCategory(),name);
        logger.info("All books from {} category with {} name has been removed Successfully",category,name);
    }

    /**
     * @param category
     * @param name
     * @param count
     * @param memberDetail
     * Here @transactional  is used so that this method sticks to follow the ACID property, either all will happemm or NONE. No in between
     * @return
     */
    @Transactional
    public int borrowBooks(BookCategory category, String name, Integer count, MemberDetail memberDetail){
        if(count<=0) {
            logger.error("Invalid  count has been sent in Request to Borrow");
            throw new RuntimeException("Invalid count of books have been requested");
        }
        List<BookModel> availableBooks= bookRepository.findByCategoryAndNameAndStatus(category.getTheCategory(),name,BookAvailableStatus.AVAILABLE.staus,Limit.of(count));
        List<BookTrackingModel> bookTrackingModels = new ArrayList<>(availableBooks.size());
        if(availableBooks.size() >= count){
            availableBooks.forEach(x -> {
                x.setStatus(BookAvailableStatus.BOOKED);
                BookTrackingModel trackingDTO= new BookTrackingModel(null,x.getISBN(),memberDetail.id(), LocalDateTime.now(ZoneId.of("Asia/Kolkata")));
                bookTrackingModels.add(trackingDTO);
            });
        }
        else{
            logger.error("Not enough books available. Asked amount is {} but available amount is {}", count,availableBooks.size() );
            throw new NotEnoughBooksAvaialble("Not enough books available for this name" + name
                    + " and category"+ category.getTheCategory());
        }
        try {
            bookRepository.saveAll(availableBooks);
            bookTrackingRepository.saveAll(bookTrackingModels);
        }
        catch (Exception e){
            logger.error("Book issue is interrupted with exception message {}",e.getMessage());
            throw e;
        }
        logger.info("Book issue is done Successfully");
        return availableBooks.size();
    }

    public Integer getNumberOfAvailableStocks(BookCategory category, String name){

        List<BookModel> result= bookRepository.findByCategoryAndNameAndStatus(category.thecategory, name,BookAvailableStatus.AVAILABLE.staus);
        return Optional.ofNullable(result).isPresent()? result.size() : 0;

    }

    /**
     * @param memberDetail
     * Here @transactional  is used so that this method sticks to follow the ACID property, either all will happemm or NONE. No in between
     * @return
     */
    @Transactional
    public void returnBooks(MemberDetail memberDetail){
        if(memberDetail.ISBNs().size()<=0) {
            logger.error("Invalid  count has been sent in Request to Return Books");
            throw new RuntimeException("Invalid count of books have been requested for Return");
        }
        List<String> isbns= memberDetail.ISBNs();
        List<BookTrackingModel> bookTrackingModels = bookTrackingRepository.findAllByISBNIn(isbns);
        List<BookModel> bookModels= bookRepository.findAllByISBNIn(isbns);
        bookTrackingModels.forEach(x -> {
            if(!Objects.equals(x.memberId, memberDetail.id())){
                logger.error("This book with ISBN {} was not issued with current user with id {}",x.getISBN(),memberDetail.id());
                throw new InvalidReturnRequestException("Some of thw Books asked for return were never issues in that User's name");
            }
            BookModel bookModel= bookModels.stream().filter(y -> y.getISBN().equalsIgnoreCase(x.ISBN)).findFirst().get();
            if(!BookAvailableStatus.BOOKED.staus.equalsIgnoreCase(bookModel.status.staus)){
                logger.error("This book with ISBN {} is not with BOOKED status currently, Return Request is invalid",x.getISBN());
                throw new InvalidReturnRequestException("This book with ISBN"+ x.getISBN() +" is not with BOOKED status currently, Return Request is invalid");
            }
        });
        bookRepository.updateBooksToAvailableWhereISBNs(isbns);
        logger.info("Books returned successfully...");

    }
}
