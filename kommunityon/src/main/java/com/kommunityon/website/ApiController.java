package com.kommunityon.website;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.context.WebServerApplicationContext;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ApiController {

    @Autowired
    private WebServerApplicationContext webServerAppContext;

    @GetMapping("/porta")
    public ResponseEntity<String> portaApi() {
        int port = webServerAppContext.getWebServer().getPort();
        return ResponseEntity.ok(String.valueOf(port));
    }
}