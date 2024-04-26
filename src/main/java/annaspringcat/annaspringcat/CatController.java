package annaspringcat.annaspringcat;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


@Controller
public class CatController {

    private final CatService catService;

    @Autowired
    public CatController(CatService catService) {
        this.catService = catService;
    }

    @GetMapping("/readfile")
    public String readFile(@RequestParam(name = "filename", defaultValue = "") String filename, Model model) throws FileNotFoundException {
        try {
            model.asMap().put("filename", filename);

            catService.readFile(filename);

            model.addAttribute("cats", catService.getCats());
        } catch (FileNotFoundException e) {
            model.addAttribute("errorMsg", "файл не найден");
        } catch (Exception e) {
            model.addAttribute("errorMsg", "aaaaaa " + e.getMessage());
        }

        return "readfile";
    }

    @GetMapping("/changecolor")
    public String changeColor(@RequestParam(name = "cat_name", defaultValue = "") String cat_name,
                              @RequestParam(name = "cat_color", defaultValue = "") String cat_color,Model model) {
        model.asMap().put("cat_name", cat_name);
        model.asMap().put("cat_color", cat_color);

        for (Cat cat : catService.getCats()) {
            if(cat.getName().equals(cat_name)){cat.setColor(cat_color);}
        }
        catService.addCatTofile();
        model.addAttribute("cats", catService.getCats());
        return "changecolor";
    }
    @GetMapping("/")
    public String toAdd(){
        return "addnewcat";
    }


    @GetMapping("/deletecat")
    public String deleteCat(@RequestParam(name = "cat_name", defaultValue = "") String cat_name, Model model) {
        model.asMap().put("cat_name", cat_name);
        //catService.
        return "deletecat";
    }

    @GetMapping("/addnewcat")
    public String addNewCat(@RequestParam(name = "name", defaultValue = "") String cat_name,
                            @RequestParam(name = "color", defaultValue = "") String cat_color,

                            @RequestParam(name = "age", defaultValue = "") String cat_age, Model model) throws Exception {
        try{
        model.asMap().put("name", cat_name);
        model.asMap().put("color", cat_color);
        double age = Double.parseDouble(cat_age);

        List<Cat> cats = catService.getCats();
        Cat cat = new Cat(cats.size() + 1, cat_name, cat_color, age);
        cats.add(cat);
        catService.addCatTofile();
        model.addAttribute("cats", cats);

        } catch (NumberFormatException e) {
            model.addAttribute("errorMsg", "aaaaaa " + e.getMessage());
        }
        return "addnewcat";
    }
}