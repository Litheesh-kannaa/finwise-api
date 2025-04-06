package com.finwise.backend.service;

import com.finwise.backend.model.InvestmentRecommendation;
import com.finwise.backend.dao.InvestmentRecommendationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InvestmentRecommendationService {

    @Autowired
    private InvestmentRecommendationRepository investmentRecommendationRepository;

    // Analyze parameters and return a personalized recommendation
    public String analyzeRecommendation(InvestmentRecommendation recommendation) {
        // Analyze goal
        String investmentGoal = analyzeGoal(recommendation.getInvestmentGoal());

        // Analyze risk and duration
        String riskAndDuration = analyzeRiskAndDuration(recommendation.getRiskLevel(), recommendation.getInvestmentDuration());

        // Analyze liquidity and return preference
        String liquidityAndReturns = analyzeLiquidityAndReturns(recommendation.getLiquidityRequirement(), recommendation.getReturnsPreference());

        // Combine recommendations based on parameters
        return String.format(
                "Based on your profile, we recommend: %s, %s, and %s.",
                investmentGoal, riskAndDuration, liquidityAndReturns
        );
    }

    private String analyzeGoal(InvestmentRecommendation.InvestmentGoal goal) {
        switch (goal) {
            case WEALTH_GROWTH:
                return "Equity Mutual Funds or Stocks for long-term growth";
            case RETIREMENT:
                return "Retirement-focused mutual funds or Pension Plans";
            case EMERGENCY_FUND:
                return "Liquid Funds or Fixed Deposits for immediate access";
            case CHILD_EDUCATION:
                return "Child Education Plans or SIPs with moderate risk";
            case HOME_PURCHASE:
                return "Debt Funds or Recurring Deposits with predictable returns";
            default:
                return "General diversified investment";
        }
    }

    private String analyzeRiskAndDuration(InvestmentRecommendation.RiskLevel riskLevel, InvestmentRecommendation.InvestmentDuration duration) {
        if (riskLevel == InvestmentRecommendation.RiskLevel.HIGH && duration == InvestmentRecommendation.InvestmentDuration.LONG_TERM) {
            return "High-risk stocks or aggressive mutual funds";
        } else if (riskLevel == InvestmentRecommendation.RiskLevel.MEDIUM && duration == InvestmentRecommendation.InvestmentDuration.MEDIUM_TERM) {
            return "Balanced mutual funds or hybrid portfolios";
        } else if (riskLevel == InvestmentRecommendation.RiskLevel.LOW && duration == InvestmentRecommendation.InvestmentDuration.SHORT_TERM) {
            return "Debt funds or fixed income securities";
        } else {
            return "Balanced portfolio with diversified assets";
        }
    }

    private String analyzeLiquidityAndReturns(InvestmentRecommendation.LiquidityRequirement liquidity, InvestmentRecommendation.ReturnsPreference preference) {
        if (liquidity == InvestmentRecommendation.LiquidityRequirement.NEED_FUNDS_ANYTIME) {
            return preference == InvestmentRecommendation.ReturnsPreference.FIXED ? "Fixed Deposits or Liquid Funds" : "Short-term debt funds";
        } else {
            return preference == InvestmentRecommendation.ReturnsPreference.VARIABLE ? "Equity or Growth Funds" : "Lock-in Fixed Deposits";
        }
    }

    // Save a recommendation
    public InvestmentRecommendation saveRecommendation(InvestmentRecommendation recommendation) {
        return investmentRecommendationRepository.save(recommendation);
    }

    // Get all recommendations
    public List<InvestmentRecommendation> getAllRecommendations() {
        return investmentRecommendationRepository.findAll();
    }

    // Get recommendation by ID
    public Optional<InvestmentRecommendation> getRecommendationById(Long id) {
        return investmentRecommendationRepository.findById(id);
    }

    // Delete recommendation by ID
    public void deleteRecommendation(Long id) {
        investmentRecommendationRepository.deleteById(id);
    }
}
