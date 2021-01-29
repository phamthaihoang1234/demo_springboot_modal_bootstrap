package com.example.demo.countries.controllers;


import com.example.demo.countries.entities.Country;
import com.example.demo.countries.repositories.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
public class CountryController {
    @Autowired
    CountryRepository countryRepository;



    @GetMapping("/")
    public String showPage(Model model, @RequestParam(defaultValue = "0") int page) {
        model.addAttribute("data",
                countryRepository.findAll(PageRequest.of(page, 4)));
        model.addAttribute("currentPage", page);
        return "index";
    }

    @PostMapping("/save")
    public String save(Country country) {
        countryRepository.save(country);
        return "redirect:/";
    }

    @GetMapping("/delete")
    public String deleteCountry(long id) {
//        List<Country> countries = new ArrayList<>();
//        countries = countryRepository.findAll();
//        for (long i=countries.size(); i>= (id+1); countries.size() ){
//            countryRepository.getOne(i).setId(i-1);
//        }
        countryRepository.deleteById(id);
        return "redirect:/";
    }

    @GetMapping("/findOne")
    @ResponseBody
    public Country findOne(long id) {
        return countryRepository.findById(id).get();
    }
}
