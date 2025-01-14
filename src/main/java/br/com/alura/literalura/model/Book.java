package br.com.alura.literalura.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String title;

    @ManyToOne
    private Author author;

    private List<String> languages;

    private Long downloads;

    public Book() {}

    public Book(BookRecord bookRecord) {
        this.title = bookRecord.title();
        this.author = null;
        this.languages = bookRecord.languages();
        this.downloads = bookRecord.downloadCount();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public List<String> getLanguages() {
        return languages;
    }

    public void setLanguages(List<String> languages) {
        this.languages = languages;
    }

    public Long getDownloads() {
        return downloads;
    }

    public void setDownloads(Long downloads) {
        this.downloads = downloads;
    }

    @Override
    public String toString() {
        return String.format("""
                        ------ LIVRO ------
                        Título: %s
                        Autor: %s
                        Idioma: %s
                        Número de downloads: %d
                        -------------------
                        """,
                this.title, (this.author != null ? this.author.getName() : "Autor desconhecido"),
                this.languages, this.downloads);
    }
}
