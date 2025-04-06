package com.finwise.backend.dao;

import com.finwise.backend.model.InvestmentRecommendation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvestmentRecommendationRepository extends JpaRepository<InvestmentRecommendation, Long> {

}
