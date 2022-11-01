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
// this will allow the displayPage to be shown
    @GetMapping(value = "/authors")
    public String getAuthors(Model model)
    {
        Iterable<Author> authors = authorRepo.findAll();
        model.addAttribute("authors", authors);
        return "authorPage";
    }
// this will use the list of author and use the lastName var's to find the users input
    @PostMapping("/findLast")
    public String findByLastName(@RequestParam String lastName, Model model)
    {
     List<Author> authors = authorRepo.findByLastName(lastName);
        model.addAttribute("authors", authors);
        return "authorPage";
    }
// this will order the list of last names
    @GetMapping("/OrderByLast")
    public String orderByLastName(Model model)
    {
        List<Author> authors = authorRepo.findAllByOrderByLastName();
        model.addAttribute("authors", authors);
        return "authorPage";
    }
// this will allow the user to input a new author by using the getters and setters and saving it to the database
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

    // part two of lab7

    // this will allow user input and search for the corisponding last name
    @PostMapping("/findFirst")
    public String findByFirstName(@RequestParam String firstName, Model model)
    {
        List<Author> authors = authorRepo.findByFirstName(firstName);
        model.addAttribute("authors", authors);
        return "authorPage";
    }
// this will order the list of authors by first name 
    @GetMapping("OrderByFirst")
    public String orderByFirstName(Model model)
    {
        List<Author> authors = authorRepo.findAllByOrderByFirstName();
        model.addAttribute("authors", authors);
        return "authorPage";

    }
// end of part 2 of lab 7

}
