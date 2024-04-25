package annaspringcat.annaspringcat;


import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


@org.springframework.stereotype.Controller
public class Controller {
    @GetMapping("/readfile")
    public String readFile(@RequestParam(name = "filename", defaultValue = "") String filename, Model model) throws FileNotFoundException {
        try {
            model.asMap().put("filename", filename);
            List<Cat> cats = new ArrayList<>();
            String fname = filename;

            Scanner scan = new Scanner(new File(fname));
            while (scan.hasNextLine()) {
                int id = scan.nextInt();
                String name = scan.next();
                String color = scan.next();
                double age = scan.nextDouble();
                Cat cat = new Cat(id, name, color, age);
                cats.add(cat);
            }
            System.out.println(cats);
            model.addAttribute("cats", cats);
        } catch (FileNotFoundException e) {
            model.addAttribute("errorMsg", "файл не найден");

        } catch (Exception e) {
            model.addAttribute("errorMsg","aaaaaa "+ e.getMessage());
        }

        return "readfile";
    }
}