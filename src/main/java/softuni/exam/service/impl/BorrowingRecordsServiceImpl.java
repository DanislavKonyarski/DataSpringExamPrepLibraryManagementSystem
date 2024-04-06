package softuni.exam.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.BorrowingRecordsRootSeedDto;
import softuni.exam.models.entity.Book;
import softuni.exam.models.entity.BorrowingRecord;
import softuni.exam.models.entity.LibraryMember;
import softuni.exam.models.enums.Genre;
import softuni.exam.repository.BookRepository;
import softuni.exam.repository.BorrowingRecordRepository;
import softuni.exam.repository.LibraryMemberRepository;
import softuni.exam.service.BorrowingRecordsService;
import softuni.exam.util.ValidationUtil;
import softuni.exam.util.XmlParser;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BorrowingRecordsServiceImpl implements BorrowingRecordsService {


    public static final String FILE_PATH_BORROWING_RECORDS = "src/main/resources/files/xml/borrowing-records.xml";
    private ModelMapper modelMapper;
    private XmlParser xmlParser;
    private BorrowingRecordRepository borrowingRecordRepository;
    private BookRepository bookRepository;
    private LibraryMemberRepository libraryMemberRepository;
    private ValidationUtil validationUtil;

    public BorrowingRecordsServiceImpl(ModelMapper modelMapper, XmlParser xmlParser, BorrowingRecordRepository borrowingRecordRepository, BookRepository bookRepository, LibraryMemberRepository libraryMemberRepository, ValidationUtil validationUtil) {
        this.modelMapper = modelMapper;
        this.xmlParser = xmlParser;
        this.borrowingRecordRepository = borrowingRecordRepository;
        this.bookRepository = bookRepository;
        this.libraryMemberRepository = libraryMemberRepository;
        this.validationUtil = validationUtil;
    }

    @Override
    public boolean areImported() {
        return borrowingRecordRepository.count()>0;
    }

    @Override
    public String readBorrowingRecordsFromFile() throws IOException {
        return Files.readString(Path.of(FILE_PATH_BORROWING_RECORDS));
    }

    @Override
    public String importBorrowingRecords() throws IOException, JAXBException {

        StringBuilder sb =new StringBuilder();

        BorrowingRecordsRootSeedDto borrowingRecordsRootSeedDto =
                xmlParser.fromFile(FILE_PATH_BORROWING_RECORDS,BorrowingRecordsRootSeedDto.class);

        borrowingRecordsRootSeedDto.getBorrowingRecord().stream()
                .filter(borrowingRecordsSeedDto -> {
                    Optional<Book> book = bookRepository.findByTitle
                            (borrowingRecordsSeedDto.getBorrowingsBookDto().getTitle());
                    Optional<LibraryMember> member = libraryMemberRepository.findById
                            (borrowingRecordsSeedDto.getBorrowingsLibraryDto().getId());
                    boolean isValid = validationUtil.isValid(borrowingRecordsSeedDto)
                            && book.isPresent() && member.isPresent();

                    if (isValid){
                       sb.append(String.format("Successfully imported borrowing record %s - %s\n"
                               ,borrowingRecordsSeedDto.getBorrowingsBookDto().getTitle(),
                               borrowingRecordsSeedDto.getBorrowDate().toString()));
                    }else {
                        sb.append("Invalid borrowing record\n");
                    }
                    return isValid;
                }).map(borrowingRecordsSeedDto -> {
                    BorrowingRecord map = new BorrowingRecord();



                    map.setBorrowingDate(borrowingRecordsSeedDto.getBorrowDate());
                    map.setReturnDate(borrowingRecordsSeedDto.getReturnDate());
                    map.setBook(bookRepository.findByTitle(borrowingRecordsSeedDto.getBorrowingsBookDto().getTitle()).orElse(null));
                    map.setMember(libraryMemberRepository.findById(borrowingRecordsSeedDto.getBorrowingsLibraryDto().getId()).orElse(null));
                    map.setRemarks(borrowingRecordsSeedDto.getRemark());
                    return map;
                }).forEach(borrowingRecordRepository::save);


        return sb.toString();
    }

    @Override
    public String exportBorrowingRecords() {

        StringBuilder stringBuilder = new StringBuilder();

//        borrowingRecordRepository.findByQuery().stream()
//                .forEach(br -> {
//                    stringBuilder.append(String.format("Book title: %s\n"+
//                            "*Book author: &s\n"+
//                            "**Date borrowed: %s\n"+
//                            "***Borrowed by: %s %s",br.getBook().getTitle()
//                            ,br.getBook().getAuthor(),br.getBorrowingDate().toString()
//                            ,br.getMember().getFirstName(),br.getMember().getLastName()
//                    ));
//                });
        borrowingRecordRepository.findBorrowingRecordsBeforeDate()
                .stream().forEach(br ->stringBuilder.append(String.format(
                        "Book title: %s\n"+
                                "*Book author: %s\n"+
                                "**Date borrowed: %s\n"+
                                "***Borrowed by: %s %s\n"
                        ,br.getBookTitle(),br.getBookAuthor(),
                br.getDateBorrowed().toString(),br.getFirstName(),br.getLastName()
                )));

        return stringBuilder.toString();
    }
}
