package annaspringcat.annaspringcat;

import org.springframework.stereotype.Service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
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
            while (scan.hasNext()) {
               // String s = scan.nextLine();
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

    public Cat findCat(String name) {
        for (Cat с : cats) {
            if (с.getName().equals(name)) {
                return с;
            }
        }
        return null;
    }
    public void addCatTofile() {

        // Записать данные о коте в файл
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("Cats.txt", false))) {

            for (Cat c : cats) {
                String age = String.valueOf(c.getAge());
                age  =age.replace('.',',');

                writer.write(c.getId() + " " + c.getName() + " " + c.getColor() + " " + age);
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
            // Обработка ошибки записи в файл
        }

    }

    public List<Cat> getCats(){
        return cats;
    }


}
