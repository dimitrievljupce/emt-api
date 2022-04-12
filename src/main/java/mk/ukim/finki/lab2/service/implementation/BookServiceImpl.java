package mk.ukim.finki.lab2.service.implementation;

import mk.ukim.finki.lab2.model.Author;
import mk.ukim.finki.lab2.model.Book;
import mk.ukim.finki.lab2.model.dto.BookDto;
import mk.ukim.finki.lab2.model.enumerations.Category;
import mk.ukim.finki.lab2.model.exceptions.AuthorNotFoundException;
import mk.ukim.finki.lab2.model.exceptions.BookNotFoundException;
import mk.ukim.finki.lab2.repository.AuthorRepository;
import mk.ukim.finki.lab2.repository.BookRepository;
import mk.ukim.finki.lab2.service.BookService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    public BookServiceImpl(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    @Override
    public List<Book> findAll() {
        return this.bookRepository.findAll();
    }

    @Override
    public Optional<Book> findById(Long id) {
        return Optional.of(this.bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException(id)));
    }

    @Override
    public Optional<Book> create(String name, Category category, Long authorId, Integer availableCopies) {
        Author author = this.authorRepository.findById(authorId)
                .orElseThrow(() -> new AuthorNotFoundException(authorId));
        Book book = new Book(name, category, author, availableCopies);
        this.bookRepository.save(book);
        return Optional.of(book);
    }

    @Override
    public Optional<Book> save(BookDto bookDto) {
        Author author = this.authorRepository.findById(bookDto.getAuthor())
                .orElseThrow(() -> new AuthorNotFoundException(bookDto.getAuthor()));
        Book book = new Book(bookDto.getName(), bookDto.getCategory(), author, bookDto.getAvailableCopies());
        return Optional.of(this.bookRepository.save(book));
    }

    @Override
    public Optional<Book> edit(Long id, BookDto bookDto) {

        Book book = this.bookRepository.findById(id).orElseThrow(() -> new BookNotFoundException(id));
        Author author = this.authorRepository.getById(bookDto.getAuthor());

        book.setName(bookDto.getName());
        book.setCategory(bookDto.getCategory());
        book.setAuthor(author);
        book.setAvailableCopies(bookDto.getAvailableCopies());
        this.bookRepository.save(book);
        return Optional.of(book);
    }

    @Override
    public Optional<Book> delete(Long id) {
        Book book = bookRepository.findById(id).orElseThrow(() -> new BookNotFoundException(id));
        this.bookRepository.delete(book);
        return Optional.of(book);
    }

    @Override
    public Optional<Book> setAvailableCopies(Long id) {
        Book book = this.bookRepository.findById(id).orElseThrow(() -> new BookNotFoundException(id));
        book.setAvailableCopies(book.getAvailableCopies() - 1);
        bookRepository.save(book);
        return Optional.of(book);
    }
}
