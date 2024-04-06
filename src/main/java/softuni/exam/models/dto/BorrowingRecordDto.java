package softuni.exam.models.dto;

import java.time.LocalDate;

    public class BorrowingRecordDto {

        private String bookTitle;
        private String bookAuthor;
        private LocalDate dateBorrowed;
        private String firstName;
        private String lastName;

        public BorrowingRecordDto(String bookTitle, String bookAuthor, LocalDate dateBorrowed, String firstName, String lastName) {
            this.bookTitle = bookTitle;
            this.bookAuthor = bookAuthor;
            this.dateBorrowed = dateBorrowed;
            this.firstName = firstName;
            this.lastName = lastName;
        }
        public String getBookTitle() {
            return bookTitle;
        }

        public void setBookTitle(String bookTitle) {
            this.bookTitle = bookTitle;
        }

        public String getBookAuthor() {
            return bookAuthor;
        }

        public void setBookAuthor(String bookAuthor) {
            this.bookAuthor = bookAuthor;
        }

        public LocalDate getDateBorrowed() {
            return dateBorrowed;
        }

        public void setDateBorrowed(LocalDate dateBorrowed) {
            this.dateBorrowed = dateBorrowed;
        }

        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }
    }
