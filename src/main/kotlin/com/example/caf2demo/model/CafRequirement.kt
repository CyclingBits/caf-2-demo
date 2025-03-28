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
 * Entity representing a CAF requirement.
 */
@Entity
@Table(name = "caf_requirement")
data class CafRequirement(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    @ManyToOne
    @JoinColumn(name = "caf_id", nullable = false)
    val caf: Caf,
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    val requirement: Requirement,
    @Column(nullable = false)
    val fulfilled: Boolean = false,
)
