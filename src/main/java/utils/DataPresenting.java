package utils;

import model.Author;
import model.Book;
import model.Category;

import java.util.List;

public class DataPresenting {
    public void showCategories(List<Category> listOfCategories) {
        for (Category category : listOfCategories) {
            System.out.println(category.toString());
        }
    }

    public void showAuthors(List<Author> listOfAuthors) {
        for (Author author : listOfAuthors) {
            System.out.println(author.toString());
        }
    }

    public void showBooks(List<Book> listOfBooks) {
        for (Book book : listOfBooks) {
            System.out.println(book.toString());
        }
    }
}