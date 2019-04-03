package utils;

import data.Data;
import model.Author;
import model.Category;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class ExtendList {

    public void addAuthor() {
        Data data = new Data();
        List<Author> listOfAuthors = data.getListOfAuthors();

        System.out.println(generateIDOfAuthor(listOfAuthors));
        Scanner in = new Scanner(System.in);
        System.out.println("Wprowadź imię autora:");
        String name = in.next();
        String age;
        int result = -1;
        do {
            System.out.println("Wprowadź wiek autora:");
            age = in.next();

            if (age.matches("-?\\d+(\\.\\d+)?")) {
                result = Integer.parseInt(age);
            }
        } while ((result > 120) || (result < 0));
        Author author = new Author(generateIDOfAuthor(listOfAuthors), name, Integer.parseInt(age));
        listOfAuthors.add(author);
    }

    private int generateIDOfAuthor(List<Author> listOfAuthors) {
        return listOfAuthors.stream().mapToInt(x -> x.getId()).max().getAsInt() + 1;
    }

    public void addCategory() {
        Data dataStorage = new Data();
        List<Category> listOfCategories = dataStorage.getListOfCategories();

        System.out.println(generateIDOfCategory(listOfCategories));
        Scanner in = new Scanner(System.in);
        System.out.println("Wprowadź nazwę kategorii:");
        String name = in.next();
        System.out.println("Wprowadź priorytet kategorii:");
        int priority = in.nextInt();

        Category category = new Category(generateIDOfCategory(listOfCategories), name, priority);
        listOfCategories.add(category);
    }

    private int generateIDOfCategory(List<Category> listOfCategories) {
        return listOfCategories.stream().mapToInt(x -> x.getId()).max().getAsInt() + 1;
    }

    public void changeCategoryName(List<Category> listOfCategories) {
        Scanner in = new Scanner(System.in);
        int id = 0;
        try {
            do {
                System.out.println("Wybierz index kategorii do zmiany:");
                listOfCategories.stream().forEach(x -> System.out.println("\t\t"
                        + listOfCategories.indexOf(x) + ": \t" + x.getName()));
                System.out.println("\t\t" + listOfCategories.size() + ": \tExit");
                id = in.nextInt();
            } while (id > listOfCategories.size() || id < 0);

            if (id != listOfCategories.size()) {

                int priority = listOfCategories.get(id).getPriority();
                System.out.println("Wprowadź nową nazwę kategorii:");
                String name = in.next();

                listOfCategories.get(id).setName(name);
            }
        } catch (InputMismatchException e) {
            System.out.println("Lista kategorii nie może zostać zmodyfikowana, wybrarno niedostępną opcję.");
        }
    }
}