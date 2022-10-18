package com.springcourse.controllers;

import com.springcourse.models.Person;
import com.springcourse.services.ItemService;
import com.springcourse.services.PeopleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/people")
public class PeopleController {

    /***********Hibernate***********/
//    private final PersonDAO personDAO;
    //    private final PersonValidator personValidator;

    private final PeopleService peopleService;
    private final ItemService itemService;

    @Autowired
    public PeopleController(PeopleService peopleService, ItemService itemService) {
        this.peopleService = peopleService;
//        this.personDAO = personDAO;
//        this.personValidator = personValidator;
        this.itemService = itemService;
    }

    @GetMapping()
    public String index(Model model) {

        /***********Hibernate***********/
//        model.addAttribute("people", personDAO.index());

        model.addAttribute("people", peopleService.findAll());

        itemService.findByItemName("Airpods");
        itemService.findByOwner(peopleService.findAll().get(0));

        peopleService.test();

        return "people/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        /***********Hibernate***********/
//        model.addAttribute("person", personDAO.show(id));

        model.addAttribute("person", peopleService.findOne(id));
        return "people/show";
    }

/*    @GetMapping("/new")
    public String newPerson(Model model) {
        model.addAttribute("person", new Person());
        return "people/new";
    }*/

    @GetMapping("/new")
    public String newPerson(@ModelAttribute("person") Person person) {
        return "people/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("person") @Valid Person person, BindingResult bindingResult) {
//        personValidator.validate(person, bindingResult);

        if (bindingResult.hasErrors()) {
            return "people/new";
        }

        /***********Hibernate***********/
//        personDAO.save(person);

        peopleService.save(person);

        return "redirect:/people";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {

        /***********Hibernate***********/
//        model.addAttribute("person", personDAO.show(id));

        model.addAttribute("person", peopleService.findOne(id));

        return "people/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("person") @Valid Person person, BindingResult bindingResult,
                         @PathVariable("id") int id) {

        if (bindingResult.hasErrors()) {
            return "people/edit";
        }

        /***********Hibernate***********/
//        personDAO.update(id, person);

        peopleService.update(id, person);
        return "redirect:/people";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {

        /***********Hibernate***********/
//        personDAO.delete(id);

        peopleService.delete(id);
        return "redirect:/people";
    }
}
