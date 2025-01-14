package br.com.alura.literalura.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "authors")
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String name;
    private Integer birthYear;
    private int deathYear;

    @OneToMany(mappedBy = "author", cascade=CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Book> books;

    public Author() {}

    public Author(AuthorRecord authorRecord) {
        this.name = authorRecord.name();
        this.birthYear = authorRecord.birthYear();
        this.deathYear = authorRecord.deathYear();
        this.books = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getBirthYear() {
        return birthYear;
    }

    public void setBirthYear(Integer birthYear) {
        this.birthYear = birthYear;
    }

    public int getDeathYear() {
        return deathYear;
    }

    public void setDeathYear(int deathYear) {
        this.deathYear = deathYear;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public void addBook(Book book) {
        this.books.add(book);
    }

    @Override
    public String toString() {
        return String.format("""
                Autor: %s
                Ano de nascimento: %d
                Ano de falecimento %d
                Livros: %s
                """, this.name, this.birthYear, this.deathYear, this.getBookTitles());
    }

    private String getBookTitles() {
        return "[" + books.stream()
                .map(Book::getTitle)
                .reduce((title1, title2) -> title1 + ", " + title2)
                .orElse("Nenhum livro disponível") + "]";
    }
}
