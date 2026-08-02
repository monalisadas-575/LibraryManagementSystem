package com.management.MonaLibrary.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.management.MonaLibrary.Entity.Book;
import com.management.MonaLibrary.Entity.Librarian;
import com.management.MonaLibrary.Entity.Library;
import com.management.MonaLibrary.Entity.Student;
import com.management.MonaLibrary.dto.request.BookRequestDto;
import com.management.MonaLibrary.dto.request.LibrarianRequestDto;
import com.management.MonaLibrary.dto.request.LibraryRequestDto;
import com.management.MonaLibrary.dto.request.StudentRequestDto;
import com.management.MonaLibrary.exception.BookNotFoundException;
import com.management.MonaLibrary.exception.BookNotFoundInLibraryException;
import com.management.MonaLibrary.myEnum.Status;
import com.management.MonaLibrary.producer.StudentProducer;
import com.management.MonaLibrary.repository.BookRepository;
import com.management.MonaLibrary.repository.LibrarianRepository;
import com.management.MonaLibrary.repository.LibraryRepository;
import com.management.MonaLibrary.repository.StudentRepository;
import com.management.MonaLibrary.util.IdGenerator;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class LibraryService {
    public final LibraryRepository libraryRepository;
    public final StudentRepository studentRepository;
    public final ObjectMapper objectMapper;
    public final BookRepository bookRepository;
    public final LibrarianRepository librarianRepository;
    public final IdGenerator idGenerator;
    public final StudentProducer studentProducer;


    public LibraryService(LibraryRepository libraryRepository, StudentRepository studentRepository, ObjectMapper objectMapper, BookRepository bookRepository, LibrarianRepository librarianRepository, IdGenerator idGenerator, StudentProducer studentProducer) {
        this.libraryRepository = libraryRepository;
        this.studentRepository = studentRepository;
        this.objectMapper = objectMapper;
        this.bookRepository = bookRepository;
        this.librarianRepository = librarianRepository;
        this.idGenerator = idGenerator;

        this.studentProducer = studentProducer;
    }
    public void createLibrary(LibraryRequestDto libraryRequestDto){
        Library library = objectMapper.convertValue(libraryRequestDto, Library.class);
        libraryRepository.save(library);


    }
    public void createStudent(StudentRequestDto studentRequestDto){
        Student student = objectMapper.convertValue(studentRequestDto, Student.class);
        student.setRollNumber(idGenerator.generateByApacheText(5));
        studentRepository.save(student);
        studentProducer.send("createstudent-out-0",student);
    }
    public void createLibrarian(LibrarianRequestDto librarianRequestDto){
        Librarian librarian = objectMapper.convertValue(librarianRequestDto, Librarian.class);
        librarianRepository.save(librarian);
    }
    public void addBooks(List<BookRequestDto> bookRequestDtoList){
        List<Book> books = bookRequestDtoList.stream()
                .map(x-> objectMapper.convertValue(x, Book.class)).toList();
        books.forEach(book -> book.setBookStatus(Status.NOT_REGISTERED_LIBRARY));
        bookRepository.saveAll(books);
    }

    public void assignLibrarian(String libraryName,String librarianName){
        Library library =libraryRepository.findByLibraryName(libraryName);
        Librarian librarian = librarianRepository.findByName(librarianName);
        library.setLibrarian(librarian);
        librarian.setLibrary(library);
        libraryRepository.save(library);
    }
    public void addBookToLibrary(String bookName,String libraryName){
        Library library =libraryRepository.findByLibraryName(libraryName);
        Book book =bookRepository.findByTitle(bookName);
        List<Book> books = Optional.ofNullable(library.getBooks())
                .orElseGet(()->{
                    List<Book> list = new ArrayList<>();
                    library.setBooks(list);
                    return list;
                });
        books.add(book);
        books.forEach(book1 -> book1.setBookStatus(Status.REGISTERED_LIBRARY));
        book.setLibrary(library);
        libraryRepository.save(library);
    }

    public void borrowBooksFromLibrary(String rollNumber,List<String> titles){
            Student student = studentRepository.findByRollNumber(rollNumber);
            List<Book> studentBookList = Optional.ofNullable(student.getBooks())
                    .orElseGet(()->{
                        List<Book> list = new ArrayList<>();
                        student.setBooks(list);
                        return list;
                    });
            for(String title:titles){
                Book book = bookRepository.findByTitle(title);
                if(book==null){
                    throw new BookNotFoundException( "Book with title '" + title + "' does not exist");
                } else if (book.getLibrary()==null) {
                    throw new BookNotFoundInLibraryException
                            ("Book '" + title + "' is not available in any library");
                }else{
                    book.setBookStatus(Status.NOT_AVAILABLE);
                    studentBookList.add(book);
                }
            }
            studentRepository.save(student);

    }

    public void returnBook(String rollId,String title,String libraryName){
        Student student = studentRepository.findByRollNumber(rollId);
        Book book = bookRepository.findByTitle(title);
        Library library= libraryRepository.findByLibraryName(libraryName);
        List<Book> studentBookList = student.getBooks();
        studentBookList.remove(book);
        book.setBookStatus(Status.AVAILABLE);
        book.setLibrary(library);
        student.setBooks(studentBookList);
        bookRepository.save(book);
        studentRepository.save(student);

    }
    public void transferBook(String bookTitle,String newLibraryName){
        Book book = bookRepository.findByTitle(bookTitle);
        Library library = libraryRepository.findByLibraryName(newLibraryName);
        book.setLibrary(library);
        book.setBookStatus(Status.REGISTERED_LIBRARY);
        bookRepository.save(book);

    }
    public List<Book> getBorrowedBookList(String rollNumber){
        Student student = studentRepository.findByRollNumber(rollNumber);
        List<Book> student_BookList = student.getBooks();
        return student_BookList;
    }
//    public List<Student> getStudentList(){
//        List<Student> studentList = studentRepository.findAll();
//
//    }
}
