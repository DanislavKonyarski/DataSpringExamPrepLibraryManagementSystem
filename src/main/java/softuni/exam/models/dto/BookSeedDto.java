package softuni.exam.models.dto;

import com.google.gson.annotations.Expose;

import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

public class BookSeedDto {

//     "author": "F. Scott Fitzgerald",
//    "available": true,
//    "description": "A classic novel set in the roaring 20s",
//    "genre": "CLASSIC_LITERATURE",
//    "title": "The Great Gatsby",
//    "rating": 9.1

    @Expose
    private String author;
    @Expose
    private Boolean available;
    @Expose
    private String description;
    @Expose
    private String genre;
    @Expose
    private String title;
    @Expose
    private Double rating;

    @Size(min = 3, max = 40)
    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }

    @Size(min = 5)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    @Size(min = 3, max = 40)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Positive
    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }
}
