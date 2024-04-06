package softuni.exam.models.entity;

import org.springframework.web.bind.annotation.Mapping;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "BORROWING_RECORDS")
public class BorrowingRecord extends BaseEntity {

    @Column(name = "BORROW_DATE",nullable = false)
    private LocalDate BorrowingDate;
    @Column
    private String remarks;
    @Column(name = "return_Date", nullable = false)

    private LocalDate returnDate;
    @ManyToOne
    private Book book;
    @ManyToOne()
    private LibraryMember member;

    public BorrowingRecord() {
    }

    public LocalDate getBorrowingDate() {
        return BorrowingDate;
    }
    public void setBorrowingDate(LocalDate borrowingDate) {
        BorrowingDate = borrowingDate;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public LibraryMember getMember() {
        return member;
    }

    public void setMember(LibraryMember member) {
        this.member = member;
    }
}
