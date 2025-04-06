package com.finwise.backend.model;

import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "mutual_fund_sip_analysis")
@Data
public class MutualFundSipAnalysis {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private Long userId; // Reference to the user

    @Column(nullable = false)
    private String fundName; // Mutual Fund or SIP name

    private BigDecimal expenseRatio; // Expense Ratio (Operational costs)
    private BigDecimal aum; // Assets Under Management (AUM)
    private BigDecimal pastReturns; // Fund Performance (Returns in %)

    // Risk analysis
    private BigDecimal standardDeviation; // Measures volatility
    private BigDecimal beta; // Measures sensitivity to market movements

    private Integer investmentHorizon; // Number of years recommended for investment
    private Integer lockInPeriod; // Lock-in period (if applicable, e.g., ELSS funds)

    @Column(nullable = false, updatable = false)
    private Instant createdAt = Instant.now();

}

