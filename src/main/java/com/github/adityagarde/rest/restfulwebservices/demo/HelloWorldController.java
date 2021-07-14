package com.github.adityagarde.rest.restfulwebservices.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Locale;

@RestController
public class HelloWorldController {

    @Autowired
    MessageSource msgSource;

    @RequestMapping(method = RequestMethod.GET, path = "/hello-world")
    public String helloWorld() {
        return "Hello, World!";
    }

    @GetMapping(path = "/hello-world-bean")
    public HelloWorldBean helloWorldBean() {
        return new HelloWorldBean("Hello, World!");
    }

    @GetMapping(path = "/hello-world/path/{name}")
    public HelloWorldBean helloWorldBean(@PathVariable String name) {
        return new HelloWorldBean("Hello, World! " + name);
    }

    @GetMapping(path = "/hello-world-internationalized")
    public String helloWorldI18n(@RequestHeader(name = "Accept-Language", required = false) Locale locale) {
        return msgSource.getMessage("hello.world.message", null, "Hello, World!", locale);
    }

    // Without passing Locale as an argument.
    @GetMapping(path = "/hello-world-internationalized-1")
    public String helloWorldI18ned() {
        return msgSource.getMessage("hello.world.message", null, "Hello, World!", LocaleContextHolder.getLocale());
    }
}
