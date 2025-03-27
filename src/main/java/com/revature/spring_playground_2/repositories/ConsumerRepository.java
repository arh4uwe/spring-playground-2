package com.revature.spring_playground_2.repositories;

import java.util.Collection;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.revature.spring_playground_2.models.Consumer;
import com.revature.spring_playground_2.models.Pie;

@Repository
public interface ConsumerRepository extends JpaRepository<Consumer, String> {
    Optional<Consumer> findByUsernameAndPassword(String username, String password);

    @Query("SELECT c FROM Consumer c WHERE c.lastPie = ?1")
    Collection<Consumer> findAllConsumersByLastPie(Pie lastPie);
}
