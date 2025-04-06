package com.finwise.backend.controller;

import com.finwise.backend.model.MutualFundSipAnalysis;
import com.finwise.backend.service.MutualFundSipAnalysisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/mutual-fund-sip-analysis")
public class MutualFundSipAnalysisController {

    @Autowired
    private MutualFundSipAnalysisService service;

    // Create a new MutualFundSipAnalysis
    @PostMapping
    public MutualFundSipAnalysis createAnalysis(@RequestBody MutualFundSipAnalysis analysis) {
        return service.saveAnalysis(analysis);
    }

    // Analyze and give recommendations
    @GetMapping("/analyze/{id}")
    public String analyzeAndRecommend(@PathVariable Long id) {
        return service.analyzeAndRecommend(id);
    }


    // Get all MutualFundSipAnalysis records
    @GetMapping
    public List<MutualFundSipAnalysis> getAllAnalyses() {
        return service.getAllAnalyses();
    }

    // Get a specific analysis by ID
    @GetMapping("/{id}")
    public Optional<MutualFundSipAnalysis> getAnalysisById(@PathVariable Long id) {
        return service.getAnalysisById(id);
    }

    // Update an existing MutualFundSipAnalysis
    @PutMapping("/{id}")
    public MutualFundSipAnalysis updateAnalysis(@PathVariable Long id, @RequestBody MutualFundSipAnalysis updatedAnalysis) {
        return service.updateAnalysis(id, updatedAnalysis);
    }

    // Delete an analysis by ID
    @DeleteMapping("/{id}")
    public String deleteAnalysis(@PathVariable Long id) {
        service.deleteAnalysis(id);
        return "Analysis with ID " + id + " deleted successfully.";
    }
}
