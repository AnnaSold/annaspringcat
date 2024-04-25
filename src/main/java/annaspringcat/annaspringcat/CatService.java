package annaspringcat.annaspringcat;

import org.springframework.stereotype.Service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Service
public class CatService {
    List<Cat> cats = new ArrayList<>();

    public void setCats(List<Cat> cats) {
        this.cats = cats;
    }

    public void readFile (String filename) throws Exception {
        cats = new ArrayList<>();
        Scanner scan = new Scanner(new File(filename));
        while (scan.hasNextLine()) {
            int id = scan.nextInt();
            String name = scan.next();
            String color = scan.next();
            double age = scan.nextDouble();
            Cat cat = new Cat(id, name, color, age);
            cats.add(cat);

        }
        System.out.println(cats);

        scan.close();
    }

    public void addCatTofile(Cat cat) {

        // Записать данные о коте в файл
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("Cats.txt", true))) {
            writer.newLine();
            writer.write(cat.getId() + " " + cat.getName() + " " + cat.getColor() + " " + cat.getAge());
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
            // Обработка ошибки записи в файл
        }

    }

    public List<Cat> getCats(){
        return cats;
    }

}
