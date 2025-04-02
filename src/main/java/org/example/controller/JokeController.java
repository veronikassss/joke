package org.example.controller;

import org.example.service.JokeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

//@Controller
@RequestMapping("/joke")
public class JokeController {
    JokeService jokeService;

//    @Autowired
    public JokeController(JokeService jokeService) {
        this.jokeService = jokeService;
    }

    @GetMapping
    public String getJoke(Model model) {
        String htmlJoke = String.valueOf(jokeService.getJoke());
        model.addAttribute("joke", htmlJoke);
        return "jokePage";
    }

    @GetMapping("/")
    public String home() {
        return "redirect:/joke"; // Перенаправление на /joke
    }
}
