package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.BookSeedDto;
import softuni.exam.models.entity.Book;
import softuni.exam.repository.BookRepository;
import softuni.exam.service.BookService;
import softuni.exam.util.ValidationUtil;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    public static final String PATH_OF_BOOKS = "src/main/resources/files/json/books.json";

    private BookRepository bookRepository;
    private ModelMapper modelMapper;
    private Gson gson;
    private ValidationUtil validationUtil;

    public BookServiceImpl(BookRepository bookRepository, ModelMapper modelMapper, Gson gson, ValidationUtil validationUtil) {
        this.bookRepository = bookRepository;
        this.modelMapper = modelMapper;
        this.gson = gson;

        this.validationUtil = validationUtil;
    }

    @Override
    public boolean areImported() {
        return bookRepository.count()>0;
    }

    @Override
    public String readBooksFromFile() throws IOException {
        return Files.readString(Path.of(PATH_OF_BOOKS));
    }

    @Override
    public String importBooks() throws IOException {
        StringBuilder sb = new StringBuilder();

       Arrays.stream(gson.fromJson(readBooksFromFile(), BookSeedDto[].class))
               .filter(bookSeedDto->{

                   Optional<Book> bookOptional = bookRepository.findByTitle(bookSeedDto.getTitle());
                   boolean isValid = validationUtil.isValid(bookSeedDto) && bookOptional.isEmpty();

                   if (isValid){
                       sb.append(String.format
                               ("Successfully imported book %s - %s\n"
                               ,bookSeedDto.getAuthor(),bookSeedDto.getTitle()));
                   }else {
                       sb.append("Invalid book\n");
                   }

                       return isValid;
               }).map(bookSeedDto -> modelMapper
                       .map(bookSeedDto,Book.class))
               .forEach(bookRepository::save);


        return sb.toString();
    }
}
