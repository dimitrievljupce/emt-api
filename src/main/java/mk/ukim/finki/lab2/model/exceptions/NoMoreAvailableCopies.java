package mk.ukim.finki.lab2.model.exceptions;

public class NoMoreAvailableCopies extends RuntimeException {
    public NoMoreAvailableCopies() {
        super(String.format("No more available copies"));
    }
}
