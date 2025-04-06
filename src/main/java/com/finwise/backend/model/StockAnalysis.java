package com.finwise.backend.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "stock_analysis")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class StockAnalysis {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double peRatio;
    private Double eps;
    private Double pbRatio;
    private Double deRatio;
    private Double roe;
    private Double dividendYield;
    private Double marketCap;
    private Double week52High;
    private Double week52Low;
    private Double rsi;
    private Long volume;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }
}
