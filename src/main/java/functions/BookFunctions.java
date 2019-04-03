package functions;

import model.Author;
import model.Book;
import model.Category;

import java.util.*;
import java.util.stream.Collectors;

public class BookFunctions {

    public Book getBookByISBNStream(List<Book> listOfBooks, int isbn) {
        Book book = null;
        if (listOfBooks == null) {
            return book;
        } else {
            book = listOfBooks.stream()
                    .findFirst().filter(x -> x.getIsbnNumber() == isbn)
                    .orElse(null);
            return book;
        }
    }

    public Book getBookByISBNFor(List<Book> listOfBooks, int isbn) {
        Book searchedBook = null;
        if (listOfBooks == null) {
            return searchedBook;
        } else {
            for (Book book : listOfBooks) {
                if (book.getIsbnNumber() == isbn) {
                    searchedBook = book;
                }
            }
        }
        return searchedBook;
    }

    public List<Book> getTwoLastBooksFor(List<Book> listOfBooks) {
        if (listOfBooks == null) {
            return new ArrayList<>();
        } else {
            return listOfBooks.subList(listOfBooks.size() - 2, listOfBooks.size());
        }
    }

    public List<Book> getTwoLastBooksStream(List<Book> listOfBooks) {
        if (listOfBooks == null) {
            return new ArrayList<>();
        } else {
            return listOfBooks.stream().skip(4).collect(Collectors.toList());
        }
    }

    public Book earliestReleasedBookFor(List<Book> listOfBooks) {
        //TODO co jesli bedzie pusta lista

        if (listOfBooks.isEmpty()) {
            System.out.println("The list doesn't exist");
            return null;
        } else {
            Book searchedBook = listOfBooks.get(0);
            for (Book book : listOfBooks) {
                if (book.getYear() < searchedBook.getYear()) {
                    searchedBook = book;
                }
            }
            return searchedBook;
        }
    }

    public Book earliestReleasedBookStream(List<Book> listOfBooks) {
        if (listOfBooks.isEmpty()) {
            System.out.println("The list doesn't exist");
            return null;
        } else {
            return listOfBooks.stream()
                    .min(Comparator.comparing(x -> x.getYear())).get();
        }
    }

    public Book latestReleasedBookFor(List<Book> listOfBooks) {
        Book searchedBook = listOfBooks.get(0);
        for (Book book : listOfBooks) {
            if (book.getYear() > searchedBook.getYear()) {
                searchedBook = book;
            }
        }
        return searchedBook;
    }

    public Book latestReleasedBookStream(List<Book> listOfBooks) {
        if (listOfBooks.isEmpty()) {
            System.out.println("The list doesn't exist");
            return null;
        } else {
            return listOfBooks.stream()
                    .max(Comparator.comparing(x -> x.getYear())).get();
        }
    }

    public int sumOfAllYearsFor(List<Book> listOfBooks) {
        int sum = 0;
        for (Book book : listOfBooks) {
            sum += book.getYear();
        }
        return sum;
    }

    public int sumOfAllYearsStream(List<Book> listOfBooks) {
        return listOfBooks.stream()
                .mapToInt(x -> x.getYear()).sum();
    }

    public int numberOfBooksReleasedPrior2007For(List<Book> listOfBooks) {
        int count = 0;
        for (Book book : listOfBooks) {
            if (book.getYear() > 2007) {
                count++;
            }
        }
        return count;
    }

    public int numberOfBooksReleasedPrior2007Stream(List<Book> listOfBooks) {
        return (int) listOfBooks.stream()
                .filter(x -> x.getYear() > 2007).count();
    }

    public boolean checkIfAllBooksAreReleasedAfter2000For(List<Book> listOfBooks) {
        boolean res = true;
        for (Book book : listOfBooks) {
            if (book.getYear() < 2001) {
                res = false;
            }
        }
        return res;
    }

    public boolean checkIfAllBooksAreReleasedAfter2000Stream(List<Book> listOfBooks) {
        return listOfBooks.stream()
                .anyMatch(x -> x.getYear() < 2001);
    }

    public double averageReleaseYearFor(List<Book> listOfBooks) {
        double avgReleaseYear = 0;
        int i = 0;
        for (Book book : listOfBooks) {
            avgReleaseYear = avgReleaseYear + book.getYear();
            i++;
        }
        return avgReleaseYear / i;
    }

    public double averageReleaseYearStream(List<Book> listOfBooks) {
        if (listOfBooks.isEmpty()) {
            System.out.println("The list doesn't exist");
            return 0;
        } else {
            return listOfBooks.stream()
                    .mapToDouble(x -> x.getYear())
                    .average().getAsDouble();
        }
    }

    public boolean checkIfAnyBookIsReleasedBefore2003For(List<Book> listOfBooks) {
        for (Book book : listOfBooks) {
            if (book.getYear() < 2003) {
                return true;
            }
        }
        return false;
    }

    public boolean checkIfAnyBookIsReleasedBefore2003Stream(List<Book> listOfBooks) {
        return listOfBooks.stream()
                .anyMatch(x -> x.getYear() < 2003);
    }

    public List<Book> getBooksStartingWithCAndReleasedAfter2007for(List<Book> listOfBooks) {
        List<Book> booksReleasedAfter2007StartingOnC = new ArrayList<>();

        for (Book book : listOfBooks) {
            if ((book.getYear() > 2007) && (book.getTitle().startsWith("C"))) {
                booksReleasedAfter2007StartingOnC.add(book);
            }
        }
        return booksReleasedAfter2007StartingOnC;
    }

    public List<Book> getBooksStartingWithCAndReleasedAfter2007stream(List<Book> listOfBooks) {
        return listOfBooks.stream()
                .filter(x -> (x.getYear() > 2007) && (x.getTitle().startsWith("C")))
                .collect(Collectors.toList());
    }

    public List<Book> getBooksReleasedAfter2003stream(List<Book> listOfBooks) {
        return listOfBooks.stream()
                .filter(x -> x.getYear() > 2003)
                .collect(Collectors.toList());
    }

    public List<Book> getBooksFromWzorce(List<Book> listOfBooks) {
        return listOfBooks.stream()
                .filter(x -> x.getCategory().getName().equals("Wzorce projektowe"))
                .collect(Collectors.toList());
    }

    public List<Book> getBooksByAuthor(List<Author> listOfAuthors, List<Book> listOfBooks) {
        System.out.println("Please pick the author index:");
        listOfAuthors.forEach(x -> System.out.println("\t\t" + listOfAuthors.indexOf(x) + ": " + x.getName()));

        List<Book> tempList = new ArrayList<>();
        Scanner in = null;
        int action = 0;
        try {
            in = new Scanner(System.in);
            action = in.nextInt();

            for (Book book : listOfBooks) {
                for (Author author : book.getAuthors()) {
                    if (author.getName().equalsIgnoreCase(listOfAuthors.get(action).getName())) {
                        tempList.add(book);
                    }
                }
            }

        } catch (InputMismatchException e) {
            System.out.println("Number bitch!");
        }
        return tempList;
    }

    public List<Book> add100YearsFor(List<Book> listOfBooks) {
        for (Book book : listOfBooks) {
            book.setYear(book.getYear() + 100);
        }
        return listOfBooks;
    }

    public List<Book> add100YearsStream(List<Book> listOfBooks) {
        return listOfBooks.stream()
                .map(book -> new Book(book.getId(), book.getTitle(), book.getIsbnNumber(), book.getYear() + 100,
                        book.getBezel(), book.getAuthors(), book.getCategory())).collect(Collectors.toList());
    }

    public List<String> getBooksDivisibleBy2For(List<Book> listOfBooks) {
        List<String> tempListOfTitles = new ArrayList<>();
        for (Book book : listOfBooks) {
            if (book.getYear() % 2 == 0) {
                tempListOfTitles.add(book.getTitle());
            }
        }
        return tempListOfTitles;
    }

    public List<String> getBooksDivisibleBy2Stream(List<Book> listOfBooks) {
        return listOfBooks.stream()
                .filter(x -> x.getYear() % 2 == 0)
                .map(x -> x.getTitle())
                .collect(Collectors.toList());
    }

    public Map<Integer, Book> getMapOfIsbnAndBooksFor(List<Book> listOfBooks) {
        Map<Integer, Book> mapOfIsbnsAndBooks = new TreeMap<>();

        for (Book book : listOfBooks) {
            mapOfIsbnsAndBooks.put(book.getIsbnNumber(), book);
        }
        return mapOfIsbnsAndBooks;
    }

    public Map<Integer, Book> getMapOfIsbnAndBooksStream(List<Book> listOfBooks) {
        return listOfBooks.stream()
                .collect(Collectors.toMap(x -> x.getIsbnNumber(), x -> x));
    }

    public List<Book> sortBooksFromNewestFor(List<Book> listOfBooks) {
        listOfBooks.sort(((o1, o2) -> Integer.compare(o2.getYear(), o1.getYear())));
        return listOfBooks;
    }

    public List<Book> sortBooksFromNewestStream(List<Book> listOfBooks) {
        return listOfBooks.stream()
                .sorted((o1, o2) -> Integer.compare(o2.getYear(), o1.getYear()))
                .collect(Collectors.toList());
    }

    public List<Book> sortBooksFromOldestFor(List<Book> listOfBooks) {
        listOfBooks.sort(((o1, o2) -> Integer.compare(o1.getYear(), o2.getYear())));
        return listOfBooks;
    }

    public List<Book> sortBooksFromOldestStream(List<Book> listOfBooks) {
        return listOfBooks.stream()
//                .sorted(Comparator.comparing(x -> x.getYear()))
                .sorted((o1, o2) -> Integer.compare(o1.getYear(), o2.getYear()))
                .collect(Collectors.toList());
    }

//    public List<List<Book>> getListOfListsOfBooksFor(List<Book> listOfBooks) {
//        return List.(listOfBooks, 2);
//
//    }

    public List<List<Book>> getListOfListsOfBooksStream(List<Book> listOfBooks) {
        List<List<Book>> mainList = new ArrayList<>();
        List<Book> tempList = new ArrayList<>();

        int j = 0;
        for (int i = 0; i < listOfBooks.size(); i++) {
            tempList.add(listOfBooks.get(i));
            j++;
            if (j == 2) {
                mainList.add(new ArrayList<>(tempList));
                tempList.clear();
                j = 0;
            }
        }
        return mainList;
    }
}