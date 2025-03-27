package com.revature.spring_playground_2.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.revature.spring_playground_2.models.Pie;
import com.revature.spring_playground_2.services.PieService;

@Controller
@RequestMapping("pie")
public class PieController {
    private PieService pieService;

    public PieController(PieService pieService) {
        this.pieService = pieService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody List<Pie> getPieList() {
        return pieService.getPieList();
    }

    @GetMapping(params = "pieName")
    public @ResponseBody ResponseEntity<Pie> findPie(@RequestParam String pieName) {
        return new ResponseEntity<Pie>(pieService.findPie(pieName), HttpStatus.ACCEPTED);
    }

    @GetMapping("calories/{calorieLimit}")
    public @ResponseBody ResponseEntity<List<Pie>> findPiesByCalories(@PathVariable int calorieLimit) {
        List<Pie> caloriePieList = pieService.getPiesByCalories(calorieLimit);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(caloriePieList);
    }

    @DeleteMapping("delete/{pieName}")
    public @ResponseBody ResponseEntity<String> deletePie(@PathVariable String pieName) {
        pieService.deletePie(pieName);
        return ResponseEntity.accepted().body("Successfully deleted " + pieName);
    }

    @PatchMapping("patch")
    public @ResponseBody ResponseEntity<String> patchPie(@RequestParam String pieName,
            @RequestParam(defaultValue = "0", required = false) int calories,
            @RequestParam(defaultValue = "0", required = false) int slicesAvailable) {
        pieService.patchPie(pieName, calories, slicesAvailable);
        return ResponseEntity.ok().body("Successfully patched " + pieName);
    }

    @PutMapping("update")
    public @ResponseBody ResponseEntity<String> updatePie(@RequestBody Pie updatedPie) {
        pieService.updatePie(updatedPie);
        return ResponseEntity.ok().body("Pie successfully updated");
    }

    @PostMapping("create")
    public @ResponseBody ResponseEntity<Pie> createPie(@RequestBody Pie newPie) {
        pieService.addPie(newPie);
        return ResponseEntity.status(HttpStatus.CREATED).body(newPie);
    }
}
