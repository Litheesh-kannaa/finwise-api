package com.finwise.backend.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "investment_recommendations")
@Data
public class InvestmentRecommendation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private InvestmentGoal investmentGoal;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private InvestmentType investmentType;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private RiskLevel riskLevel;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private InvestmentDuration investmentDuration;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private LiquidityRequirement liquidityRequirement;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ExpectedReturns expectedReturns;

    @Column(nullable = false)
    private Boolean taxBenefitsRequired;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private MarketKnowledge marketKnowledge;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ReturnsPreference returnsPreference;

    @Column(nullable = false)
    private Boolean inflationProtectionNeeded;

    // Inline Enums
    public enum InvestmentGoal {
        WEALTH_GROWTH, RETIREMENT, EMERGENCY_FUND, CHILD_EDUCATION, HOME_PURCHASE
    }

    public enum InvestmentType {
        MONTHLY_SIP, ONE_TIME_INVESTMENT, RECURRING_DEPOSITS
    }

    public enum RiskLevel {
        LOW, MEDIUM, HIGH
    }

    public enum InvestmentDuration {
        SHORT_TERM, MEDIUM_TERM, LONG_TERM
    }

    public enum LiquidityRequirement {
        NEED_FUNDS_ANYTIME, CAN_LOCK_IN
    }

    public enum ExpectedReturns {
        STABLE, MODERATE, HIGH
    }

    public enum MarketKnowledge {
        BEGINNER, INTERMEDIATE, EXPERT
    }

    public enum ReturnsPreference {
        FIXED, VARIABLE
    }
}
