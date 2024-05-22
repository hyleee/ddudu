package com.ssafy.ddudu.controller;

import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/resources")
//@CrossOrigin(origins="*")
public class ResourceController {

    private final ResourceLoader resourceLoader;

    public ResourceController(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    @GetMapping("/{filename:.+}")
    public Resource getResource(@PathVariable String filename) {
        return resourceLoader.getResource("classpath:/static/resources/" + filename);
    }
}
