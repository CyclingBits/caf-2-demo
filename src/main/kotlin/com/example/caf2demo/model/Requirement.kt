package com.example.caf2demo.model

import com.example.caf2demo.service.validator.AnalysisValidator
import com.example.caf2demo.service.validator.ApprovalValidator
import com.example.caf2demo.service.validator.CauseValidator
import com.example.caf2demo.service.validator.IzpValidator
import com.example.caf2demo.service.validator.RatingValidator
import com.example.caf2demo.service.validator.RequirementValidator
import kotlin.reflect.KClass

/**
 * Enum representing types of requirements.
 */
enum class Requirement(
    val id: Long,
    val symbol: String,
    val description: String,
    val validatorClass: KClass<out RequirementValidator>,
) {
    IZP(1, "IZP", "Aktualny IZP", IzpValidator::class),
    RATING(2, "Rating", "Aktualny Rating", RatingValidator::class),
    CAUSE(3, "Cause", "Powód zmian", CauseValidator::class),
    ANALYSIS(4, "Analysis", "Analiza I analityka", AnalysisValidator::class),
    APPROVAL(5, "Approval", "Akceptacja II analityka", ApprovalValidator::class),
}
