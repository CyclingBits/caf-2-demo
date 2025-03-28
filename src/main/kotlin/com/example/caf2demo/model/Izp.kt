package com.example.caf2demo.model

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table
import java.time.LocalDate

/**
 * Entity representing an IZP record with points
 */
@Entity
@Table(name = "izp")
data class Izp(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    val points: Int,
    @ManyToOne
    @JoinColumn(name = "contractor_id", nullable = false)
    val contractor: Contractor,
    val date: LocalDate,
)
