package com.example.pizdasuka.cont;

import com.example.pizdasuka.db.Human;
import com.example.pizdasuka.db.HumanConfToDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@Controller
public class MainController {
    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private HumanConfToDB humanConfToDB;

    @GetMapping("/")
    public String index(Model model) {
        List<Human> humans = humanConfToDB.findAll();
        model.addAttribute("list", humans);
        return "index";
    }

    @PostMapping("/add")
    public RedirectView add(@ModelAttribute("human") Human human) {
        humanConfToDB.add(human);
        return new RedirectView("/");
    }

    @DeleteMapping("/del")
    public RedirectView delete(@ModelAttribute("id") int id) {
        humanConfToDB.delete(id);
        return new RedirectView("/");
    }
}
