package com.finwise.backend.controller;

import com.finwise.backend.model.StockAnalysis;
import com.finwise.backend.service.StockAnalysisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/stock-analysis")
public class StockAnalysisController {

    @Autowired
    private StockAnalysisService stockAnalysisService;

    // Get all stock analysis data
    @GetMapping
    public List<StockAnalysis> getAllStockData() {
        return stockAnalysisService.getAllStockData();
    }

    // Get stock analysis by ID
    @GetMapping("/{id}")
    public StockAnalysis getStockDataById(@PathVariable Long id) {
        return stockAnalysisService.getStockDataById(id);
    }

    // Add new stock analysis data
    @PostMapping
    public StockAnalysis addStockData(@RequestBody StockAnalysis stockAnalysis) {
        return stockAnalysisService.addStockData(stockAnalysis);
    }

    // Update stock analysis data
    @PutMapping("/{id}")
    public StockAnalysis updateStockData(@PathVariable Long id, @RequestBody StockAnalysis stockAnalysis) {
        return stockAnalysisService.updateStockData(id, stockAnalysis);
    }

    // Delete stock analysis data
    @DeleteMapping("/{id}")
    public String deleteStockData(@PathVariable Long id) {
        stockAnalysisService.deleteStockData(id);
        return "Stock data with ID " + id + " deleted successfully.";
    }

    // Analyze stock and get recommendation based on predefined logic
    @GetMapping("/analyze/{id}")
    public String analyzeStock(@PathVariable Long id) {
        return stockAnalysisService.analyzeStock(id);
    }
}
