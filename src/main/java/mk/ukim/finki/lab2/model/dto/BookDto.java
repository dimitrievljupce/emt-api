package mk.ukim.finki.lab2.model.dto;

import lombok.Data;
import mk.ukim.finki.lab2.model.enumerations.Category;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Data
public class BookDto {

    private String name;

    @Enumerated(EnumType.STRING)
    private Category category;

    private Long author;

    private Integer availableCopies;

    public BookDto() {
    }

    public BookDto(String name, Category category, Long author, Integer availableCopies) {
        this.name = name;
        this.category = category;
        this.author = author;
        this.availableCopies = availableCopies;
    }
}
