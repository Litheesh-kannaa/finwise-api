package com.finwise.backend.service;

import com.finwise.backend.model.MutualFundSipAnalysis;
import com.finwise.backend.dao.MutualFundSipAnalysisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class MutualFundSipAnalysisService {

    @Autowired
    private MutualFundSipAnalysisRepository repository;

    // Save a new MutualFundSipAnalysis
    public MutualFundSipAnalysis saveAnalysis(MutualFundSipAnalysis analysis) {
        return repository.save(analysis);
    }

    // Get all analyses
    public List<MutualFundSipAnalysis> getAllAnalyses() {
        return repository.findAll();
    }

    public String analyzeAndRecommend(Long id) {
        Optional<MutualFundSipAnalysis> optionalAnalysis = repository.findById(id);

        if (optionalAnalysis.isPresent()) {
            MutualFundSipAnalysis analysis = optionalAnalysis.get();

            BigDecimal expenseRatio = analysis.getExpenseRatio();
            BigDecimal aum = analysis.getAum();
            BigDecimal pastReturns = analysis.getPastReturns();
            BigDecimal standardDeviation = analysis.getStandardDeviation();
            BigDecimal beta = analysis.getBeta();
            Integer investmentHorizon = analysis.getInvestmentHorizon();
            Integer lockInPeriod = analysis.getLockInPeriod();

            StringBuilder recommendation = new StringBuilder("Recommendation Summary:\n");

            // Expense Ratio Analysis
            if (expenseRatio.compareTo(BigDecimal.valueOf(1.0)) < 0) {
                recommendation.append("✅ Low Expense Ratio: This fund is cost-effective.\n");
            } else {
                recommendation.append("⚠️ High Expense Ratio: Consider reviewing operational costs.\n");
            }

            // AUM Analysis
            if (aum.compareTo(BigDecimal.valueOf(100000000)) > 0) {
                recommendation.append("✅ High AUM: The fund is trusted and stable.\n");
            } else {
                recommendation.append("⚠️ Low AUM: Potentially less reliable.\n");
            }

            // Past Returns
            if (pastReturns.compareTo(BigDecimal.valueOf(12.0)) > 0) {
                recommendation.append("✅ Strong Past Returns: Indicates good historical performance.\n");
            } else {
                recommendation.append("⚠️ Moderate/Low Returns: Consider reviewing.\n");
            }

            // Standard Deviation Analysis
            if (standardDeviation.compareTo(BigDecimal.valueOf(1.0)) < 0.8) {
                recommendation.append("✅ Low Volatility: Suitable for low-risk investors.\n");
            } else {
                recommendation.append("⚠️ High Volatility: May not be suitable for risk-averse investors.\n");
            }

            // Beta Analysis
            if (beta.compareTo(BigDecimal.ONE) <= 0) {
                recommendation.append("✅ Low Market Sensitivity: Safer for conservative investors.\n");
            } else {
                recommendation.append("⚠️ High Market Sensitivity: Suitable for aggressive investors.\n");
            }

            // Investment Horizon Analysis
            if (investmentHorizon >= 5) {
                recommendation.append("✅ Long-term Investment: Ideal for wealth growth.\n");
            } else {
                recommendation.append("⚠️ Short-term Investment: May not yield high returns.\n");
            }

            // Lock-in Period Analysis
            if (lockInPeriod == 0) {
                recommendation.append("✅ No Lock-in: You can exit anytime.\n");
            } else {
                recommendation.append("⚠️ Lock-in Period: Consider liquidity before investing.\n");
            }

            return recommendation.toString();
        } else {
            return "⚠️ Mutual Fund SIP Analysis not found with ID: " + id;
        }
    }


    // Get analysis by ID
    public Optional<MutualFundSipAnalysis> getAnalysisById(Long id) {
        return repository.findById(id);
    }

    // Update an existing MutualFundSipAnalysis
    public MutualFundSipAnalysis updateAnalysis(Long id, MutualFundSipAnalysis updatedAnalysis) {
        Optional<MutualFundSipAnalysis> existingAnalysis = repository.findById(id);

        if (existingAnalysis.isPresent()) {
            MutualFundSipAnalysis analysis = existingAnalysis.get();
            analysis.setUserId(updatedAnalysis.getUserId());
            analysis.setFundName(updatedAnalysis.getFundName());
            analysis.setExpenseRatio(updatedAnalysis.getExpenseRatio());
            analysis.setAum(updatedAnalysis.getAum());
            analysis.setPastReturns(updatedAnalysis.getPastReturns());
            analysis.setStandardDeviation(updatedAnalysis.getStandardDeviation());
            analysis.setBeta(updatedAnalysis.getBeta());
            analysis.setInvestmentHorizon(updatedAnalysis.getInvestmentHorizon());
            analysis.setLockInPeriod(updatedAnalysis.getLockInPeriod());
            return repository.save(analysis);
        }
        return null;
    }

    // Delete analysis by ID
    public void deleteAnalysis(Long id) {
        repository.deleteById(id);
    }
}
