package com.revature.spring_playground_2.controllers;

import java.util.List;

import javax.naming.AuthenticationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.revature.spring_playground_2.models.Consumer;
import com.revature.spring_playground_2.services.ConsumerService;

@RestController
@RequestMapping("consumer")
public class ConsumerController {
    private ConsumerService consumerService;

    @Autowired
    public ConsumerController(ConsumerService consumerService) {
        this.consumerService = consumerService;
    }

    @PostMapping("login")
    public ResponseEntity<Void> login(@RequestBody Consumer consumer) throws AuthenticationException {
        consumerService.login(consumer.getUsername(), consumer.getPassword());
        return ResponseEntity.noContent()
            .header("username", consumer.getUsername())
            .build();
    }

    @PostMapping("register")
    public ResponseEntity<String> register(@RequestBody Consumer consumer) {
        consumerService.register(consumer);
        return ResponseEntity.status(HttpStatus.CREATED).body("Successfully registered");
    }

    @PatchMapping("order")
    public ResponseEntity<String> order(@RequestParam String pieName, 
            @RequestHeader("username") String username) {
        consumerService.order(username, pieName);
        return ResponseEntity.accepted().body(
            username + " ordered " + pieName + " pie."
        );
    }

    @GetMapping("byLastPie/{pieName}")
    public ResponseEntity<List<Consumer>> findConsumersByLastPie(@PathVariable String pieName) {
        return ResponseEntity.ok()
            .body(consumerService.findAllConsumersByLastPie(pieName));
    }

    @ExceptionHandler(AuthenticationException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public String handleUnauthorized(AuthenticationException ex) {
        return ex.getMessage();
    }
}
