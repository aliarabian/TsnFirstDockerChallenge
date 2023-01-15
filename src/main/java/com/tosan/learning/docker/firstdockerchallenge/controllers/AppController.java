package com.tosan.learning.docker.firstdockerchallenge.controllers;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AppController {

    @GetMapping(value = "hello")
    public String sayHello(@RequestParam(value = "name", required = false, defaultValue = "Stranger") String name) {
        if (StringUtils.isAllLowerCase(name.substring(0, 1))) {
            name = StringUtils.join(StringUtils.splitByCharacterTypeCamelCase(name), StringUtils.SPACE);
        }
        return "Hello " + name;
    }

    @GetMapping(value = "author")
    public String getAuthor() {
        return "Ali Arabian";
    }
}
