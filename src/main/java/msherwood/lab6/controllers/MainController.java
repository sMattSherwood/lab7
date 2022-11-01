package msherwood.lab6.controllers;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import msherwood.lab6.models.Author;
import msherwood.lab6.repos.AuthorRepo;

@Controller
public class MainController {

    AuthorRepo authorRepo;

    public MainController(AuthorRepo authorRepo)
    {
        this.authorRepo = authorRepo;
    }

    @GetMapping(value = "/authors")
    public String getAuthors(Model model)
    {
        Iterable<Author> authors = authorRepo.findAll();
        model.addAttribute("authors", authors);
        return "authorPage";
    }

    @PostMapping("/findLast")
    public String findByLastName(@RequestParam String lastName, Model model)
    {
     List<Author> authors = authorRepo.findByLastName(lastName);
        model.addAttribute("authors", authors);
        return "authorPage";
    }

    @GetMapping("/OrderByLast")
    public String orderByLastName(Model model)
    {
        List<Author> authors = authorRepo.findAllByOrderByLastName();
        model.addAttribute("authors", authors);
        return "authorPage";
    }

    @PostMapping("/addAuthor")
    public String addAuthor(@RequestParam String firstName, @RequestParam String lastName, Model model)
    {
        Author author = new Author();
        author.setAuthorID(null);
        author.setFirstName(firstName);
        author.setLastName(lastName);
        authorRepo.save(author); // this will save the data to the table

        return getAuthors(model);
    }

}
