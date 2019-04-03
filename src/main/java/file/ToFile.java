package file;

import model.Author;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;
import java.util.stream.Collectors;

public class ToFile {
    public void writeAuthorsToFile(List<Author> authorsList, String fileName) {

        try {
            String str = authorsList
                    .stream()
                    .map(x -> x.getId() + ";" + x.getName() + ";" + x.getAge())
                    .collect(Collectors.joining("\n"));

            File file = new File(fileName);
            PrintWriter printWriter = new PrintWriter(file);
            printWriter.println(str);
            printWriter.flush();
            printWriter.close();

            System.out.println("Plik został aktualizowany.");
        } catch (FileNotFoundException e) {
            System.out.println("Plik autora nie zostal znaleziony, stworzono nowy");
        }
    }
}