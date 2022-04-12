package mk.ukim.finki.lab2.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Author {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private String surname;

    @ManyToOne
    private Country country;

    public Author() {
    }

    public Author(String name, String surname, Country country) {
        this.name = name;
        this.surname = surname;
        this.country = country;
    }
}
