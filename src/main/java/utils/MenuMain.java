package utils;

import file.ToFile;
import file.FromFile;
import data.Data;
import utils.DataPresenting;
import functions.BookFunctions;
import model.Author;
import model.Book;
import model.Category;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;



public class MenuMain {

    private static int exitFromMenu = 4;

    public void menuNavigation(String fileCategories, String fileAuthors, String fileBooks) {
        int action;

        do {
            showMenu();
            action = getNumberFromUser();
            proceedWithChosenAction(action);
        } while (action != exitFromMenu);
    }

    public void showMenu() {
        System.out.println("Wybierz opcję z menu: ");
        System.out.println("1.  Pokaż listę książek");
        System.out.println("2.  Pokaż listę autorów");
        System.out.println("3.  Pokaż listę kategorii");
        System.out.println("4.  Wyjdź");
        System.out.println("5.  Stwórz nowego autora");
        System.out.println("6.  Stwórz nową kategorię");
        System.out.println("7.  Zapisz autora do pliku");
        System.out.println("8.  Sortuj po roku rosnąco");
        System.out.println("9.  Sortuj po roku malejąco");
        System.out.println("10. Pokaż książki wydane po 2003r.");
        System.out.println("11. Edytuj nazwę kategorii");
        System.out.println("12. Pokaż wszystkie książki z kategorii wzorce");
        System.out.println("13. Pokaż wszystkie książki autora");

    }

    private int getNumberFromUser() {
        Scanner scanner = null;
        int numberOfChosenAction = 0;
        try {
            scanner = new Scanner(System.in);
            numberOfChosenAction = scanner.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Number!");
        }
        return numberOfChosenAction;
    }

    private void proceedWithChosenAction(int inputAction) {
        Data data = new Data();
        List<Author> listOfAuthors = data.getListOfAuthors();
        List<Category> listOfCategories = data.getListOfCategories();
        List<Book> listOfBooks = data.getListOfBooks();
        DataPresenting dataPresenting = new DataPresenting();
        ExtendList extendList = new ExtendList();
        ToFile toFiles = new ToFile();
        BookFunctions bookFunctions = new BookFunctions();

        switch (inputAction) {
            case 1:
                dataPresenting.showBooks(listOfBooks);
                break;
            case 2:
                dataPresenting.showAuthors(listOfAuthors);
                break;
            case 3:
                dataPresenting.showCategories(listOfCategories);
                break;
            case 4:
                System.out.println("Goodbye.");
                break;
            case 5:
                extendList.addAuthor();
                break;
            case 6:
                extendList.addCategory();
                break;
            case 7:
                toFiles.writeAuthorsToFile(data.getListOfAuthors(), "authors.csv");
                break;
            case 8:
                dataPresenting.showBooks(bookFunctions.sortBooksFromOldestStream(listOfBooks));
                break;
            case 9:
                dataPresenting.showBooks(bookFunctions.sortBooksFromNewestStream(listOfBooks));
                break;
            case 10:
                dataPresenting.showBooks(bookFunctions.getBooksReleasedAfter2003stream(listOfBooks));
                break;
            case 11:
                extendList.changeCategoryName(listOfCategories);
                break;
            case 12:
                dataPresenting.showBooks(bookFunctions.getBooksFromWzorce(listOfBooks));
                break;
            case 13:
                dataPresenting.showBooks(bookFunctions.getBooksByAuthor(listOfAuthors, listOfBooks));
                break;
            case 14:

                break;
            default:
                System.out.println("Ta opcja jest niedostępna");
                break;
        }
    }
}