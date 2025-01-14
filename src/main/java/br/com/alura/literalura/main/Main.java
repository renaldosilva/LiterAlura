package br.com.alura.literalura.main;

import br.com.alura.literalura.model.ApiResponse;
import br.com.alura.literalura.model.Author;
import br.com.alura.literalura.model.Book;
import br.com.alura.literalura.model.BookRecord;
import br.com.alura.literalura.repository.AuthorRepository;
import br.com.alura.literalura.repository.BookRepository;
import br.com.alura.literalura.service.HttpService;
import br.com.alura.literalura.service.JsonDeserializer;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Main {

    private Scanner scanner;
    private final String API_ADDRESS_BASE = "https://gutendex.com/books?search=";
    private HttpService httpService;
    private JsonDeserializer jsonDeserializer;
    private AuthorRepository authorRepository;
    private BookRepository bookRepository;

    public Main(AuthorRepository authorRepository, BookRepository bookRepository) {
        this.scanner = new Scanner(System.in);
        this.httpService = new HttpService();
        this.jsonDeserializer = new JsonDeserializer();
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }

    public void run() {
        int option = this.runMainMenu();
        while (option != 0) {
            switch (option) {
                case 1:
                    this.searchBookByTitle();
                    break;
                case 2:
                    this.printRegisteredBooks();
                    break;
                case 3:
                    this.printRegisteredAuthors();
                    break;
                case 4:
                    this.findLivingAuthorsByYear();
                    break;
                case 5:
                    this.findBooksByLanguage();
                    break;
                default:
                    System.out.println("Opção inválida!");
            }

            option = this.runMainMenu();
        }
    }

    private int runMainMenu() {
        System.out.println("""
                --------------------------------------
                Escolha o número de sua opção:
                1 - Buscar livro pelo título
                2 - Listar livros registrados
                3 - Listar autores registrados
                4 - Listar autores vivos em um determinado ano
                5 - Listar livros em um determinado idioma
                0 - Sair
                """);
        int option = this.scanner.nextInt();
        this.scanner.nextLine();

        return option;
    }

    private String runLanguageMenu() {
        System.out.println("""
                Insira o idioma para realizar a busca:
                es - Espanhol
                en - Inglês
                fr - Francês
                pt - Português
                """);
        return this.scanner.nextLine();
    }

    private void searchBookByTitle() {
        System.out.println("Insira o nome do livro que você deseja procurar:");
        String bookName = this.scanner.nextLine();

        String address = this.API_ADDRESS_BASE + URLEncoder.encode(bookName.trim(), StandardCharsets.UTF_8);
        String json = this.httpService.fetchData(address);
        ApiResponse apiResponse = this.jsonDeserializer.fromJson(json, ApiResponse.class);

        if (!apiResponse.results().isEmpty()) {
            BookRecord bookRecord = apiResponse.results().getFirst();
            this.saveBook(bookRecord);
        } else {
            System.out.println("Livro não encontrado!");
        }
    }

    private void saveBook(BookRecord bookRecord) {
        Optional<Book> databaseBook = this.bookRepository.findByTitle(bookRecord.title());

        Book book;
        if (databaseBook.isPresent()) {
            book = databaseBook.get();
        } else {
            book = new Book(bookRecord);
            if (bookRecord.authors() != null && !bookRecord.authors().isEmpty()) {
                Author author = new Author(bookRecord.authors().getFirst());
                this.saveAuthor(book, author);
            }
            this.bookRepository.save(book);
        }

        System.out.println(book);;
    }

    private void saveAuthor(Book book, Author author) {
        Optional<Author> databaseAuthor = this.authorRepository.findByName(author.getName());

        if (databaseAuthor.isPresent()) {
            author = databaseAuthor.get();
            author.addBook(book);
            book.setAuthor(author);
        } else {
            author.addBook(book);
            book.setAuthor(author);
            this.authorRepository.save(author);
        }
    }

    private void printRegisteredBooks() {
        this.bookRepository.findAll().forEach(System.out::println);
    }

    private void printRegisteredAuthors() {
        this.authorRepository.findAll().forEach(System.out::println);
    }

    private void findLivingAuthorsByYear() {
        System.out.println("Insira o ano que deseja pesquisar:");
        int year = this.scanner.nextInt();
        this.scanner.nextLine();

        List<Author> authors = this.authorRepository.findLivingAuthorsByYear(year);
        if (!authors.isEmpty()) {
            authors.forEach(System.out::println);
        } else {
            System.out.println("Nenhum autor encontrado!");
        }
    }

    private void findBooksByLanguage() {
        String option = this.runLanguageMenu();
        List<Book> books = this.bookRepository.findBooksByLanguage(option.toLowerCase().trim());

        if (!books.isEmpty()) {
            books.forEach(System.out::println);
        } else {
            System.out.println("Nenhum livro encontrado!");
        }
    }
}
