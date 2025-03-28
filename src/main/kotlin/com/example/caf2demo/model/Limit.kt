package com.example.caf2demo.model

import jakarta.persistence.*
import java.math.BigDecimal
import java.time.LocalDate

/**
 * Entity representing a contractor limit.
 */
@Entity
@Table(name = "limits")
data class Limit(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    
    @ManyToOne
    @JoinColumn(name = "contractor_id", nullable = false)
    val contractor: Contractor,
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    val type: LimitType,
    
    @Column(name = "limit_value", nullable = false, precision = 19, scale = 2)
    val value: BigDecimal,
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    val currency: Currency,
    
    @Column(name = "date_from", nullable = false)
    val dateFrom: LocalDate,
    
    @Column(name = "date_to", nullable = false)
    val dateTo: LocalDate,
    
    @Column(nullable = false, precision = 19, scale = 2)
    val used: BigDecimal = BigDecimal.ZERO
)