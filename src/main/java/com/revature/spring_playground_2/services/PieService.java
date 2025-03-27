package com.revature.spring_playground_2.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.spring_playground_2.exceptions.ResourceNotFoundException;
import com.revature.spring_playground_2.models.Pie;
import com.revature.spring_playground_2.repositories.PieRepository;

@Service
public class PieService {
    private PieRepository pieRepository;

    @Autowired
    public PieService(PieRepository pieRepository) {
        this.pieRepository = pieRepository;
    }

    public List<Pie> getPieList() {
        return (List<Pie>) pieRepository.findAll();
    }

    public Pie findPie(String pieName) throws ResourceNotFoundException {
        return pieRepository.findById(pieName).orElseThrow(() -> {
            return new ResourceNotFoundException(
                pieName + " was not found. Please check name or try another."
            );
        });
    }

    public List<Pie> getPiesByCalories(int limit) throws ResourceNotFoundException {
        return pieRepository.findByCaloriesLessThan(limit);
    }

    public void deletePie(String pieName) {
        pieRepository.deleteById(pieName);
    }

    public void patchPie(String pieName, int calories, int slicesAvailable) throws ResourceNotFoundException {
        Pie pie = pieRepository.findById(pieName).orElseThrow(() -> {
            return new ResourceNotFoundException(
                pieName + " was not found. Please check name or try another."
            );
        });
        if (calories > 0) pie.setCalories(calories);
        if (slicesAvailable > 0) pie.setSlicesAvailable(slicesAvailable);
        pieRepository.save(pie);
    }

    public void updatePie(Pie updatedPie) throws ResourceNotFoundException {
        Pie pie = pieRepository.findById(updatedPie.getName()).orElseThrow(() -> {
            return new ResourceNotFoundException(
                updatedPie.getName() + " was not found. Please check name or try another."
            );
        });
        pie.setCalories(updatedPie.getCalories());
        pie.setSlicesAvailable(updatedPie.getSlicesAvailable());
        pieRepository.save(pie);
    }

    public void addPie(Pie pie) {
        pieRepository.save(pie);
    }
}
