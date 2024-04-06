package softuni.exam.models.entity;

import softuni.exam.models.enums.Genre;

import javax.persistence.*;

@Entity
@Table(name = "Books")
public class Book extends BaseEntity{

    @Column(nullable = false)
    private String author;
    @Column(nullable = false)
    private boolean available;
    @Column(columnDefinition = "TEXT",nullable = false)
    private String description;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Genre genre;
    @Column(nullable = false)
    private Double rating;
    @Column(unique = true,nullable = false)
    private String title;

    public Book() {
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
