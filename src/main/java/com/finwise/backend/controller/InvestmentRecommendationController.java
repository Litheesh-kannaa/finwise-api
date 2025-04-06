package com.finwise.backend.controller;


import com.finwise.backend.model.InvestmentRecommendation;
import com.finwise.backend.service.InvestmentRecommendationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/investment-recommendations")
public class InvestmentRecommendationController {

    @Autowired
    private InvestmentRecommendationService investmentRecommendationService;

    // Analyze and return a recommendation based on input
    @PostMapping("/analyze")
    public String analyzeRecommendation(@RequestBody InvestmentRecommendation recommendation) {
        return investmentRecommendationService.analyzeRecommendation(recommendation);
    }

    // Create or update a recommendation
    @PostMapping
    public InvestmentRecommendation createOrUpdateRecommendation(@RequestBody InvestmentRecommendation recommendation) {
        return investmentRecommendationService.saveRecommendation(recommendation);
    }

    // Get all recommendations
    @GetMapping
    public List<InvestmentRecommendation> getAllRecommendations() {
        return investmentRecommendationService.getAllRecommendations();
    }

    // Get recommendation by ID
    @GetMapping("/{id}")
    public Optional<InvestmentRecommendation> getRecommendationById(@PathVariable Long id) {
        return investmentRecommendationService.getRecommendationById(id);
    }

    // Delete recommendation by ID
    @DeleteMapping("/{id}")
    public String deleteRecommendation(@PathVariable Long id) {
        investmentRecommendationService.deleteRecommendation(id);
        return "Investment recommendation deleted successfully!";
    }
}
