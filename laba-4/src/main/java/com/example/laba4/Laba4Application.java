package com.example.laba4;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.context.MessageSource;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


import java.util.Locale;

@SpringBootApplication
@Controller

public class Laba4Application {

    /*@Bean
    public SessionLocaleResolver localeResolver() {
        SessionLocaleResolver slr = new SessionLocaleResolver();
        slr.setDefaultLocale(Locale.US);
        return slr;
    }

    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor() {
        LocaleChangeInterceptor lci = new LocaleChangeInterceptor();
        lci.setParamName("lang");
        return lci;
    }*/

    public static void main(String[] args) {
        SpringApplication.run(Laba4Application.class, args);
    }

    @Autowired
    MessageSource messageSource;

    @GetMapping("/")
    public String index( Model model,
                         @RequestParam(value = "x1", defaultValue = "0") String x1,
                         @RequestParam(value = "x2", defaultValue = "0") String x2,
                         @RequestParam(value = "y1", defaultValue = "0") String y1,
                         @RequestParam(value = "y2", defaultValue = "0") String y2,
                         @RequestParam(value = "z1", defaultValue = "0") String z1,
                         @RequestParam(value = "z2", defaultValue = "0") String z2,
                         @RequestParam(value = "m1", defaultValue = "0") String m1,
                         @RequestParam(value = "l1", defaultValue = "0") String l1,
                         @RequestParam(value = "n1", defaultValue = "0") String n1,
                         @RequestParam(value = "l2", defaultValue = "0") String l2,
                         @RequestParam(value = "m2", defaultValue = "0") String m2,
                         @RequestParam(value = "n2", defaultValue = "0") String n2,
                         @RequestParam(value = "lang", defaultValue = "en") String lang) {
        Locale locale_ = new Locale(lang);
        model.addAttribute("lang", lang);
        model.addAttribute("lines", messageSource.getMessage("lines", null, locale_));
        model.addAttribute("result", messageSource.getMessage("result", null, locale_));
        model.addAttribute("lang_en", messageSource.getMessage("lang.en", null, locale_));
        model.addAttribute("lang_uk", messageSource.getMessage("lang.uk", null, locale_));
        model.addAttribute("lang_choice", messageSource.getMessage("lang_choice", null, locale_));
        return "index";
    }

    @GetMapping("/solve")
    public String solve( Model model,
                            @RequestParam(value = "x1", defaultValue = "0") String x1,
                           @RequestParam(value = "x2", defaultValue = "0") String x2,
                           @RequestParam(value = "y1", defaultValue = "0") String y1,
                           @RequestParam(value = "y2", defaultValue = "0") String y2,
                           @RequestParam(value = "z1", defaultValue = "0") String z1,
                           @RequestParam(value = "z2", defaultValue = "0") String z2,
                           @RequestParam(value = "m1", defaultValue = "0") String m1,
                           @RequestParam(value = "l1", defaultValue = "0") String l1,
                           @RequestParam(value = "n1", defaultValue = "0") String n1,
                           @RequestParam(value = "l2", defaultValue = "0") String l2,
                           @RequestParam(value = "m2", defaultValue = "0") String m2,
                           @RequestParam(value = "n2", defaultValue = "0") String n2,
                           @RequestParam(value = "lang", defaultValue = "en") String lang) {
        //
        model.addAttribute("x1", x1);
        model.addAttribute("y1", y1);
        model.addAttribute("z1", z1);
        model.addAttribute("l1", l1);
        model.addAttribute("m1", m1);
        model.addAttribute("n1", n1);
        model.addAttribute("x2", x2);
        model.addAttribute("y2", y2);
        model.addAttribute("z2", z2);
        model.addAttribute("l2", l2);
        model.addAttribute("m2", m2);
        model.addAttribute("n2", n2);
        Locale locale_ = new Locale(lang);
        model.addAttribute("lang", messageSource.getMessage("lang." + lang, null, locale_));
        model.addAttribute("line", messageSource.getMessage("line", null, locale_));
        canonicalLine line1 = new canonicalLine(x1, y1, z1, l1, m1, n1);
        canonicalLine line2 = new canonicalLine(x2, y2, z2, l2, m2, n2);
        String result = solved(line1, line2);
        model.addAttribute("res", messageSource.getMessage(result, null, locale_));
        return "solve";
    }
    
    private String solved(canonicalLine line1, canonicalLine line2) {
        canonicalLine.relation res = line1.check(line2);
        if (res.equals(canonicalLine.relation.Parallel)) return "parallel";
        if (res.equals(canonicalLine.relation.Copl_intersect)) return "copl_intersect";
        if (res.equals(canonicalLine.relation.Perpendicular)) return "perpendicular";
        if (res.equals(canonicalLine.relation.Fleeting)) return "fleeting";
        return "error";
    }

}
    

