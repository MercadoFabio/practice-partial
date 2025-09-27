package com.utn.partial.controllers;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Ping controller class to health check.
 */
@RestController
public class PingController {


    @GetMapping("/ping")
    public String pong() {
        return "pong";
    }
}