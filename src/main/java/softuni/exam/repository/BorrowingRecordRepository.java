package softuni.exam.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import softuni.exam.models.dto.BorrowingRecordDto;
import softuni.exam.models.entity.BorrowingRecord;

import java.util.List;

@Repository
public interface BorrowingRecordRepository extends JpaRepository<BorrowingRecord, Long> {

    @Query("SELECT new softuni.exam.models.dto.BorrowingRecordDto( b.book.title, b.book.author, b.BorrowingDate, m.firstName, m.lastName) " +
            "FROM BorrowingRecord b " +
            "JOIN b.book book " +
            "JOIN b.member m " +
            "WHERE book.genre = 'SCIENCE_FICTION' AND b.BorrowingDate < '2021-09-10' " +
            "ORDER BY b.BorrowingDate DESC")
    List<BorrowingRecordDto> findBorrowingRecordsBeforeDate();
}
