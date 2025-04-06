package com.finwise.backend.service;

import com.finwise.backend.model.StockAnalysis;
import com.finwise.backend.dao.StockAnalysisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StockAnalysisService {

    @Autowired
    private StockAnalysisRepository stockAnalysisRepository;

    // Get all stock data
    public List<StockAnalysis> getAllStockData() {
        return stockAnalysisRepository.findAll();
    }

    // Get stock data by ID
    public StockAnalysis getStockDataById(Long id) {
        return stockAnalysisRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Stock data not found with ID: " + id));
    }

    // Add new stock data
    public StockAnalysis addStockData(StockAnalysis stockAnalysis) {
        return stockAnalysisRepository.save(stockAnalysis);
    }

    // Update stock data
    public StockAnalysis updateStockData(Long id, StockAnalysis updatedData) {
        StockAnalysis existingData = getStockDataById(id);

        existingData.setPeRatio(updatedData.getPeRatio());
        existingData.setEps(updatedData.getEps());
        existingData.setPbRatio(updatedData.getPbRatio());
        existingData.setDeRatio(updatedData.getDeRatio());
        existingData.setRoe(updatedData.getRoe());
        existingData.setDividendYield(updatedData.getDividendYield());
        existingData.setMarketCap(updatedData.getMarketCap());
        existingData.setWeek52High(updatedData.getWeek52High());
        existingData.setWeek52Low(updatedData.getWeek52Low());
        existingData.setRsi(updatedData.getRsi());
        existingData.setVolume(updatedData.getVolume());

        return stockAnalysisRepository.save(existingData);
    }

    // Delete stock data by ID
    public void deleteStockData(Long id) {
        StockAnalysis existingData = getStockDataById(id);
        stockAnalysisRepository.delete(existingData);
    }

    // Analyze stock using predefined logic

    public String analyzeStock(Long id) {
        StockAnalysis stockData = getStockDataById(id);

        StringBuilder analysisResult = new StringBuilder("Stock Analysis Summary:\n");

        // 1. Price-to-Earnings (P/E) Ratio Analysis
        if (stockData.getPeRatio() < 15) {
            analysisResult.append("✅ P/E Ratio is low (< 15), indicating the stock is undervalued or a strong buy.\n");
        } else if (stockData.getPeRatio() >= 15 && stockData.getPeRatio() <= 25) {
            analysisResult.append("🔍 P/E Ratio is moderate (15-25), suggesting fair valuation.\n");
        } else {
            analysisResult.append("⚠️ High P/E Ratio (> 25), indicating overvaluation or high growth expectations.\n");
        }

        // 2. Earnings Per Share (EPS) Analysis
        if (stockData.getEps() > 10) {
            analysisResult.append("✅ EPS is high (> 10), showing strong profitability.\n");
        } else if (stockData.getEps() >= 5 && stockData.getEps() <= 10) {
            analysisResult.append("🔍 EPS is moderate (5-10), reflecting steady earnings.\n");
        } else {
            analysisResult.append("⚠️ EPS is low (< 5), indicating weak earnings.\n");
        }

        // 3. Price-to-Book (P/B) Ratio Analysis
        if (stockData.getPbRatio() < 1) {
            analysisResult.append("✅ P/B Ratio is less than 1, stock may be undervalued.\n");
        } else if (stockData.getPbRatio() <= 3) {
            analysisResult.append("🔍 P/B Ratio is within a normal range (1-3).\n");
        } else {
            analysisResult.append("⚠️ High P/B Ratio (> 3), suggesting overvaluation.\n");
        }

        // 4. Debt-to-Equity (D/E) Ratio Analysis
        if (stockData.getDeRatio() < 1) {
            analysisResult.append("✅ Low D/E Ratio (< 1), indicating minimal debt and financial stability.\n");
        } else if (stockData.getDeRatio() <= 2) {
            analysisResult.append("🔍 Moderate D/E Ratio (1-2), company has manageable debt.\n");
        } else {
            analysisResult.append("⚠️ High D/E Ratio (> 2), suggesting high financial risk.\n");
        }

        // 5. Return on Equity (ROE) Analysis
        if (stockData.getRoe() > 20) {
            analysisResult.append("✅ High ROE (> 20%), company is efficiently using shareholder equity.\n");
        } else if (stockData.getRoe() >= 10 && stockData.getRoe() <= 20) {
            analysisResult.append("🔍 Moderate ROE (10-20%), reasonable returns.\n");
        } else {
            analysisResult.append("⚠️ Low ROE (< 10%), inefficient use of shareholder equity.\n");
        }

        // 6. Dividend Yield Analysis
        if (stockData.getDividendYield() > 3) {
            analysisResult.append("✅ High Dividend Yield (> 3%), suitable for income-seeking investors.\n");
        } else if (stockData.getDividendYield() >= 1 && stockData.getDividendYield() <= 3) {
            analysisResult.append("🔍 Moderate Dividend Yield (1-3%), offering decent returns.\n");
        } else {
            analysisResult.append("⚠️ Low Dividend Yield (< 1%), limited income potential.\n");
        }

        // 7. Market Capitalization Analysis
        if (stockData.getMarketCap() >= 100_000) {
            analysisResult.append("✅ Large-cap stock, typically more stable.\n");
        } else if (stockData.getMarketCap() >= 10_000 && stockData.getMarketCap() < 100_000) {
            analysisResult.append("🔍 Mid-cap stock, offering growth potential with moderate risk.\n");
        } else {
            analysisResult.append("⚠️ Small-cap stock, high growth but higher volatility.\n");
        }

        // 8. 52-Week High/Low Analysis
        double currentPriceRange = (stockData.getWeek52High() + stockData.getWeek52Low()) / 2;
        if (stockData.getMarketCap() > currentPriceRange * 0.9) {
            analysisResult.append("⚠️ Trading near 52-week high, possible risk of correction.\n");
        } else if (stockData.getMarketCap() < currentPriceRange * 0.7) {
            analysisResult.append("✅ Trading near 52-week low, potential for upside.\n");
        } else {
            analysisResult.append("🔍 Stock is trading within a normal range.\n");
        }

        // 9. Relative Strength Index (RSI) Analysis
        if (stockData.getRsi() > 70) {
            analysisResult.append("⚠️ RSI > 70, stock may be overbought.\n");
        } else if (stockData.getRsi() < 30) {
            analysisResult.append("✅ RSI < 30, stock may be undervalued.\n");
        } else {
            analysisResult.append("🔍 RSI is in a neutral zone (30-70), no strong trend.\n");
        }

        // 10. Volume Analysis
        if (stockData.getVolume() > 1_000_000) {
            analysisResult.append("✅ High trading volume, strong investor interest.\n");
        } else if (stockData.getVolume() >= 100_000 && stockData.getVolume() <= 1_000_000) {
            analysisResult.append("🔍 Moderate trading volume, stable market activity.\n");
        } else {
            analysisResult.append("⚠️ Low trading volume, potential liquidity issues.\n");
        }

        return analysisResult.toString();
    }

}
