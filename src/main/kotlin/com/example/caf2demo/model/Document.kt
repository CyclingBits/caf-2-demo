package com.example.caf2demo.model

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.Lob
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table
import java.time.LocalDate

@Entity
@Table(name = "document")
data class Document(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "contractor_id", nullable = false)
    val contractor: Contractor,
    val date: LocalDate,
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    val type: DocumentType,
    @Lob
    @Column(nullable = false)
    val file: ByteArray,
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Document

        if (id != other.id) return false
        if (contractor != other.contractor) return false
        if (date != other.date) return false
        if (type != other.type) return false
        if (!file.contentEquals(other.file)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id?.hashCode() ?: 0
        result = 31 * result + contractor.hashCode()
        result = 31 * result + date.hashCode()
        result = 31 * result + type.hashCode()
        result = 31 * result + file.contentHashCode()
        return result
    }
}
