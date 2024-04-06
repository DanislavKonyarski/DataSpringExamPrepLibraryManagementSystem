package softuni.exam.models.dto;


import org.springframework.web.bind.annotation.Mapping;
import softuni.exam.util.LocalDateAdaptor;

import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalDate;

@XmlRootElement(name = "borrowing_record")
@XmlAccessorType(XmlAccessType.FIELD)
public class BorrowingRecordsSeedDto {


    @XmlElement(name = "borrow_date" )
    @XmlJavaTypeAdapter(LocalDateAdaptor.class)
    @NotNull
    private LocalDate borrowDate;
    @XmlElement(name = "return_date")
    @XmlJavaTypeAdapter(LocalDateAdaptor.class)
    @NotNull
    private LocalDate returnDate;
    @XmlElement(name = "book" )
    private BorrowingsBookDto borrowingsBookDto;
    @XmlElement(name = "member")
    private BorrowingsLibraryDto borrowingsLibraryDto;
    @XmlElement(name = "remarks")
    private String remark;





    public LocalDate getBorrowDate() {
        return borrowDate;
    }

    public void setBorrowDate(LocalDate borrowDate) {
        this.borrowDate = borrowDate;
    }


    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    public BorrowingsBookDto getBorrowingsBookDto() {
        return borrowingsBookDto;
    }

    public void setBorrowingsBookDto(BorrowingsBookDto borrowingsBookDto) {
        this.borrowingsBookDto = borrowingsBookDto;
    }

    public BorrowingsLibraryDto getBorrowingsLibraryDto() {
        return borrowingsLibraryDto;
    }

    public void setBorrowingsLibraryDto(BorrowingsLibraryDto borrowingsLibraryDto) {
        this.borrowingsLibraryDto = borrowingsLibraryDto;
    }

    @Size(min= 3, max = 100)
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
