package com.management.MonaLibrary.controller;

import com.management.MonaLibrary.dto.request.BookIssueRequestDTO;
import com.management.MonaLibrary.dto.enums.BookCategory;
import com.management.MonaLibrary.dto.request.BookReturnRequestDTO;
import com.management.MonaLibrary.dto.request.StockBooksRequestDto;
import com.management.MonaLibrary.service.StockService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController(value = "/books")
public class BookController {


    public final StockService stockService;

    public BookController(StockService stockService) {
        this.stockService = stockService;
    }


    @Operation(summary = "Controller to add new Stocks of books",
            description = "Controller to add new Stocks of books")
    @PostMapping("/stock/{category}")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",description = "Success"),
            @ApiResponse(responseCode = "400",description = "Not Found"),
            @ApiResponse(responseCode = "500",description = "Internal Server Error") })
    public void addNewStock(@RequestBody StockBooksRequestDto stockBooksRequestDto,
                            @PathVariable(name = "category") BookCategory category,
                            @RequestParam(name= "bookCount") Integer bookCount){

        stockService.updateNewStocksForBooks(stockBooksRequestDto,category,bookCount);

    }

    @Operation(summary = "Controller to clear existing OLD Stocks of books",
            description = "Controller to clear Stocks of books")
    @DeleteMapping("/stock/{name}/{category}")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",description = "Success"),
            @ApiResponse(responseCode = "400",description = "Not Found"),
            @ApiResponse(responseCode = "500",description = "Internal Server Error") })
    public void clearOldStocks(
                            @PathVariable(name = "category") BookCategory category,
                            @PathVariable(name= "name") String name){

        stockService.clearOldStocksOFBooksByNameAndCategory(name,category);
    }

    @Operation(summary = "Controller to issue new Books",
            description = "Controller to issue new Books")
    @PostMapping("/issue/{name}/{category}")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",description = "Success"),
            @ApiResponse(responseCode = "400",description = "Not Found"),
            @ApiResponse(responseCode = "500",description = "Internal Server Error") })
    public void issueBook(@RequestParam(name="count") Integer count,
            @PathVariable(name = "category") BookCategory category,
            @PathVariable(name= "name") String name,
          @RequestBody BookIssueRequestDTO bookIssueRequestDTO){

        stockService.issueBooks(category,name,count,bookIssueRequestDTO);
    }

    @Operation(summary = "Controller to know available Number Stocks of books",
            description = "Controller to know available Stocks of books")
    @GetMapping("/count/{name}/{category}")
    @ResponseStatus(HttpStatus.OK)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Success"),
            @ApiResponse(responseCode = "400",description = "Not Found"),
            @ApiResponse(responseCode = "500",description = "Internal Server Error") })
    public Integer getNumberOfAvailableStocks(
                            @PathVariable(name = "category") BookCategory category,
                            @PathVariable(name= "name") String name){

        return stockService.getNumberOfAvailableStocks(category,name);
    }

    @Operation(summary = "Controller to return the issued Books",
            description = "Controller to return the issued Books")
    @PostMapping("/return")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",description = "Success"),
            @ApiResponse(responseCode = "400",description = "Not Found"),
            @ApiResponse(responseCode = "500",description = "Internal Server Error") })
    public void returnBook( @RequestBody BookReturnRequestDTO bookReturnRequestDTO){

        stockService.returnBooks(bookReturnRequestDTO);
    }


}
