package com.finwise.backend.dao;

import com.finwise.backend.model.MutualFundSipAnalysis;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MutualFundSipAnalysisRepository extends JpaRepository<MutualFundSipAnalysis, Long> {
    List<MutualFundSipAnalysis> findByUserId(Long userId);
}
