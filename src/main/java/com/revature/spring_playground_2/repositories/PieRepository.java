package com.revature.spring_playground_2.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.revature.spring_playground_2.models.Pie;

@Repository
public interface PieRepository extends CrudRepository<Pie, String> {
    List<Pie> findByCaloriesLessThan(int limit);
}
