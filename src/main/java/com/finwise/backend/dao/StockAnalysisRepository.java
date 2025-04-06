package com.finwise.backend.dao;

import com.finwise.backend.model.StockAnalysis;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockAnalysisRepository extends JpaRepository<StockAnalysis, Long> {

}
