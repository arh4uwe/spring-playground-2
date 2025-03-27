package com.revature.spring_playground_2.services;

import java.util.List;

import javax.naming.AuthenticationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.revature.spring_playground_2.exceptions.ResourceNotFoundException;
import com.revature.spring_playground_2.models.Consumer;
import com.revature.spring_playground_2.models.Pie;
import com.revature.spring_playground_2.repositories.ConsumerRepository;

@Service
public class ConsumerService {
    private ConsumerRepository consumerRepository;
    private PieService pieService;

    @Autowired
    public ConsumerService(ConsumerRepository consumerRepository, PieService pieService) {
        this.consumerRepository = consumerRepository;
        this.pieService = pieService;
    }

    public void register(Consumer consumer) {
        consumerRepository.save(consumer);
    }

    public void order(String username, String pieName) {
        Consumer consumer = consumerRepository.findById(username).orElseThrow(() -> {
            return new ResourceNotFoundException(
                username + " was not found in the list. Please check username and try again."
            );
        });
        consumer.setLastPie(pieService.findPie(pieName));
        consumerRepository.save(consumer);
    }

    public void login(String username, String password) throws AuthenticationException {
        consumerRepository.findByUsernameAndPassword(username, password)
            .orElseThrow(() -> new AuthenticationException(
                "Check username and password credentials as they are invalid"
            ));
    }

    @Transactional(readOnly = true)
    public List<Consumer> findAllConsumersByLastPie(String pieName) {
        Pie pie = pieService.findPie(pieName);
        return (List<Consumer>) consumerRepository.findAllConsumersByLastPie(pie);
    }
}
