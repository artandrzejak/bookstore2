package bookstore;

import file.FromFile;
import utils.MenuMain;

public class Main {
    public static void main(String[] args) {


        MenuMain menuMain = new MenuMain();

        FromFile.ReadFromFile("categories.csv", "authors.csv", "books.csv");
        String fileCategories = "categories.csv";
        String fileAuthors = "authors.csv";
        String fileBooks = "books.csv";

        menuMain.menuNavigation(fileCategories, fileAuthors, fileBooks);
    }

    public class Multiply {
        public Double multiply(Double a, Double b) {
            return a * b;
        }
    }
}