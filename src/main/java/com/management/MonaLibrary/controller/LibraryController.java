package com.management.MonaLibrary.controller;

import com.management.MonaLibrary.Entity.Book;
import com.management.MonaLibrary.Entity.Librarian;
import com.management.MonaLibrary.Service.LibraryService;
import com.management.MonaLibrary.dto.request.BookRequestDto;
import com.management.MonaLibrary.dto.request.LibrarianRequestDto;
import com.management.MonaLibrary.dto.request.LibraryRequestDto;
import com.management.MonaLibrary.dto.request.StudentRequestDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.websocket.server.PathParam;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/library-Management/Library")
public class LibraryController {
    public final LibraryService libraryService;

    public LibraryController(LibraryService libraryService) {
        this.libraryService = libraryService;
    }

    @Operation(summary = "Api to create a single library with Jpa",
            description = "Api to create a single library with Jpa")
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Success"),
            @ApiResponse(responseCode = "400",description = "Bad Request"),
            @ApiResponse(responseCode = "500",description = "Internal Server Error") })
    public void CreateSingleLibrary(@RequestBody LibraryRequestDto libraryRequestDto){
        libraryService.createLibrary(libraryRequestDto);
    }
    @Operation(summary = "Api to create a single student with Jpa",
            description = "Api to create a single student with Jpa")
    @PostMapping("/student")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Success"),
            @ApiResponse(responseCode = "400",description = "Bad Request"),
            @ApiResponse(responseCode = "500",description = "Internal Server Error") })
    public void createSingleStudent(@RequestBody StudentRequestDto studentRequestDto){
        libraryService.createStudent(studentRequestDto);
    }
    @Operation(summary = "Api to create a single librarian with Jpa",
            description = "Api to create a single librarian with Jpa")
    @PostMapping("/librarian")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Success"),
            @ApiResponse(responseCode = "400",description = "Bad Request"),
            @ApiResponse(responseCode = "500",description = "Internal Server Error") })
    public void createLibrarian(@RequestBody LibrarianRequestDto librarianRequestDto){
        libraryService.createLibrarian(librarianRequestDto);
    }
    @Operation(summary = "Api to add a list of books with Jpa",
            description = "Api to add a list of books with Jpa")
    @PostMapping("/books")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Success"),
            @ApiResponse(responseCode = "400",description = "Bad Request"),
            @ApiResponse(responseCode = "500",description = "Internal Server Error") })
    public void addManyBooks(@RequestBody List<BookRequestDto> bookRequestDtoList){
        libraryService.addBooks(bookRequestDtoList);
    }
    @Operation(summary = "Api to assign librarian to a single library with Jpa",
            description = "Api to assign librarian to a single library with Jpa")
    @PatchMapping("/assignLibrarian")
    @ResponseStatus(HttpStatus.OK)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Success"),
            @ApiResponse(responseCode = "400",description = "Bad Request"),
            @ApiResponse(responseCode = "500",description = "Internal Server Error") })
    public void assignLibrarian(@RequestParam("libraryName")String libraryName,@RequestParam("name")String librarianName){
        libraryService.assignLibrarian(libraryName,librarianName);
    }
    @Operation(summary = "Api to add single book with given library with Jpa",
            description = "Api to single book with given library with Jpa")
    @PatchMapping("/addBook")
    @ResponseStatus(HttpStatus.OK)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Success"),
            @ApiResponse(responseCode = "400",description = "Bad Request"),
            @ApiResponse(responseCode = "500",description = "Internal Server Error") })
    public void addBookToLibrary(@RequestParam("title")String title,
                                 @RequestParam("libraryName")String libraryName){
        libraryService.addBookToLibrary(title, libraryName);

    }
    @Operation(summary = "Api to borrow book with given student rollId with Jpa",
            description = "Api to borrow book with given student rollId with Jpa")
    @PatchMapping("/borrowBook")
    @ResponseStatus(HttpStatus.OK)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Success"),
            @ApiResponse(responseCode = "400",description = "Bad Request"),
            @ApiResponse(responseCode = "500",description = "Internal Server Error") })
    public void borrowBooksFromLibrary(@RequestParam("rollNumber") String rollNumber
                                        ,@RequestParam("title") List<String> titles){
        libraryService.borrowBooksFromLibrary(rollNumber,titles);

    }
    @Operation(summary = "Api to return book with given student rollId with Jpa",
            description = "Api to return book with given student rollId with Jpa")
    @PatchMapping("/returnBook")
    @ResponseStatus(HttpStatus.OK)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Success"),
            @ApiResponse(responseCode = "400",description = "Bad Request"),
            @ApiResponse(responseCode = "500",description = "Internal Server Error") })
    public void returnSingleBook(@RequestParam("rollNumber")String rollId,
                                 @RequestParam("title") String title,
                                 @RequestParam("libraryTitle") String library){
        libraryService.returnBook(rollId,title,library);
    }
    @Operation(summary = "Api to transfer book to another library with Jpa",
            description = "Api to transfer book to another library rollId with Jpa")
    @PatchMapping("/transferBook")
    @ResponseStatus(HttpStatus.OK)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Success"),
            @ApiResponse(responseCode = "400",description = "Bad Request"),
            @ApiResponse(responseCode = "500",description = "Internal Server Error") })
    public void transferBook(@RequestParam("title") String bookTitle
                            ,@RequestParam("libraryTitle")String newLibraryName){
        libraryService.transferBook(bookTitle,newLibraryName);
    }
    @Operation(summary = "Api to get borrowed booklist of student with Jpa",
            description = "Api to borrowed booklist of student with Jpa")
    @GetMapping("/GetBook")
    @ResponseStatus(HttpStatus.OK)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Success"),
            @ApiResponse(responseCode = "400",description = "Bad Request"),
            @ApiResponse(responseCode = "500",description = "Internal Server Error") })
    public List<Book> getBorrowedBookList(@RequestParam("rollNumber")String rollNumber){
        return libraryService.getBorrowedBookList(rollNumber);
    }

}
