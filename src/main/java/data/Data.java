package data;

import model.Author;
import model.Book;
import model.Category;

import java.util.ArrayList;
import java.util.List;

public class Data {
    private static List<Book> listOfBooks = new ArrayList<>();
    private static List<Author> listOfAuthors = new ArrayList<>();
    private static List<Category> listOfCategories = new ArrayList<>();

    public static List<Book> getListOfBooks() {
        return listOfBooks;
    }

    public static void setListOfBooks(List<Book> listOfBooks) {
        Data.listOfBooks = listOfBooks;
    }

    public static List<Author> getListOfAuthors() {
        return listOfAuthors;
    }

    public static void setListOfAuthors(List<Author> listOfAuthors) {
        Data.listOfAuthors = listOfAuthors;
    }

    public static List<Category> getListOfCategories() {
        return listOfCategories;
    }

    public static void setListOfCategories(List<Category> listOfCategories) {
        Data.listOfCategories = listOfCategories;
    }

}