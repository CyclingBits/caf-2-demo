package com.example.caf2demo.model

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table
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
    val used: BigDecimal = BigDecimal.ZERO,
    @Column(nullable = false)
    val suspended: Boolean = false,
) {
    val status: LimitStatus
        get() {
            return when {
                suspended -> LimitStatus.SUSPENDED
                LocalDate.now().isAfter(dateTo) -> LimitStatus.EXPIRED
                used >= value -> LimitStatus.USED_UP
                else -> LimitStatus.ACTIVE
            }
        }
}
