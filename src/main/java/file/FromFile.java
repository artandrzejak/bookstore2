package file;

import model.Author;
import model.Book;
import model.Category;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static data.Data.*;

public class FromFile{
    private static List<Book> listOfBooksFromFile = new ArrayList<>();
    private static List<Author> listOfAuthorsFromFile = new ArrayList<>();
    private static List<Category> listOfCategoriesFromFile = new ArrayList<>();



    public static void ReadFromFile(String fileCategories, String fileAuthors, String fileBooks) {
        listOfCategoriesFromFile = readCategoryFile(fileCategories);
        listOfAuthorsFromFile = readAuthorFile(fileAuthors);
        listOfBooksFromFile = readBookFile(fileBooks);

        setListOfCategories(listOfCategoriesFromFile);
        setListOfAuthors(listOfAuthorsFromFile);
        setListOfBooks(listOfBooksFromFile);
    }

    public static List<Category> readCategoryFile(String file) {
        try {
            InputStream is = new FileInputStream(file);
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader reader = new BufferedReader(isr);
            while (reader.ready()) {
                String[] stringArray = reader.readLine().split(";");
                listOfCategoriesFromFile.add(new Category(
                        Integer.valueOf(stringArray[0]), stringArray[1], Integer.valueOf(stringArray[2])));
            }
            reader.close();
            isr.close();
            is.close();
        } catch (IOException e) {
            System.out.println("NIE ZNALEZIONO PLIKU 'CATEGORY.CSV'");
            System.exit(1);
        }
        return listOfCategoriesFromFile;
    }

    public static List<Author> readAuthorFile(String file) {
        try {
            InputStream is = new FileInputStream(file);
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader reader = new BufferedReader(isr);
            while (reader.ready()) {
                String[] stringArray = reader.readLine().split(";");
                listOfAuthorsFromFile.add(new Author(
                        Integer.valueOf(stringArray[0]), stringArray[1], Integer.valueOf(stringArray[2])));
            }
            reader.close();
            isr.close();
            is.close();
        } catch (IOException e) {
            System.out.println("NIE ZNALEZIONO PLIKU 'AUTHOR'");
            System.exit(1);
        }
        return listOfAuthorsFromFile;
    }

    public static List<Book> readBookFile(String file) {
        try {
            InputStream is = new FileInputStream(file);
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader reader = new BufferedReader(isr);
            while (reader.ready()) {
                addBookToList(reader);
            }
            reader.close();
            isr.close();
            is.close();
        } catch (IOException e) {
            System.out.println("Plik 'books.csv' nie zostł znaleziony");
            System.exit(1);
        }
        return listOfBooksFromFile;
    }

    private static void addBookToList(BufferedReader reader) {
        try {
            String[] stringArray = reader.readLine().split(";");
            Category category = getCategory(stringArray[stringArray.length - 1]);
            List<Author> authorsInTheBook = getAuthors(stringArray[5]);
            Book book = new Book(
                    Integer.valueOf(stringArray[0]), stringArray[1], Integer.valueOf(stringArray[2]),
                    Integer.valueOf(stringArray[3]), stringArray[4], authorsInTheBook, category);
            listOfBooksFromFile.add(book);
        } catch (IOException e) {
            System.out.println("Odczyt z pliku nieudany");
            System.exit(1);
        }
    }

    private static Category getCategory(String s) {
        int categoryId = Integer.parseInt(s);
        Optional<Category> optionalCategory = listOfCategoriesFromFile.stream()
                .filter(x -> x.getId() == categoryId)
                .findFirst();
        if (optionalCategory.isPresent()) {
            return optionalCategory.get();
        } else {
            System.out.println("Odczyt z pliku nieudany");
            return null;
        }
    }

    private static List<Author> getAuthors(String s) {
        String[] authorID = s.split(",");
        List<Author> authorsInTheBook = new ArrayList<>();
        try {
            for (String s1 : authorID) {
                authorsInTheBook.add(listOfAuthorsFromFile.stream()
                        .filter(x -> x.getId() == Integer.valueOf(s1))
                        .findFirst().get());
            }
        } catch (NoSuchElementException e) {
            System.out.println("Odczyt z pliku nieudany");
            return null;
        }

        return authorsInTheBook;
    }
}