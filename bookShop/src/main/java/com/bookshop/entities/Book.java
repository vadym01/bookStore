package com.bookshop.entities;

import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title;
    private String author;
    private String language;
    private String publishingHouse;
    private String genre;
    private boolean available;
    private String dateOfPublication;
    private int numberOfPages;
    private double price;
    private int sales;
    private String formatOfBook;

    @Column(columnDefinition = "text")
    private String description;
    private int numberOfAvailableBooks;
    @Transient
    private MultipartFile image;


    public Book() {
    }

    public String getFormatOfBook() {
        return formatOfBook;
    }

    public void setFormatOfBook(String formatOfBook) {
        this.formatOfBook = formatOfBook;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPublishingHouse() {
        return publishingHouse;
    }

    public void setPublishingHouse(String publishingHouse) {
        this.publishingHouse = publishingHouse;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }


    public String getDateOfPublication() {
        return dateOfPublication;
    }

    public void setDateOfPublication(String dateOfPublication) {
        this.dateOfPublication = dateOfPublication;
    }

    public int getNumberOfPages() {
        return numberOfPages;
    }

    public void setNumberOfPages(int numberOfPages) {
        this.numberOfPages = numberOfPages;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public int getSales() {
        return sales;
    }

    public void setSales(int sales) {
        this.sales = sales;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getNumberOfAvailableBooks() {
        return numberOfAvailableBooks;
    }

    public void setNumberOfAvailableBooks(int numberOfAvailableBooks) {
        this.numberOfAvailableBooks = numberOfAvailableBooks;
    }

    public MultipartFile getImage() {

        return image;
    }

    public void setImage(MultipartFile image) {
        this.image = image;
    }
}
