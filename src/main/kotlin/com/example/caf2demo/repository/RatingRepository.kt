package com.example.caf2demo.repository

import com.example.caf2demo.model.Rating
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface RatingRepository : JpaRepository<Rating, Long> {
    fun findByContractorId(contractorId: Long): List<Rating>
}
