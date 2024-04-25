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
            model.addAttribute("errorMsg","aaaaaa "+ e.getMessage());
        }

        return "readfile";
    }
}