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

/**
 * Entity representing a CAF operation.
 */
@Entity
@Table(name = "caf")
data class Caf(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    @ManyToOne
    @JoinColumn(name = "limit_id", nullable = false)
    val limit: Limit,
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    val type: CafType,
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    val status: CafStatus,
)
