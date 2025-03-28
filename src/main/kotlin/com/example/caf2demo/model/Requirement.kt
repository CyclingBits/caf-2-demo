package com.example.caf2demo.model

import kotlin.reflect.KClass
import com.example.caf2demo.service.validator.*

/**
 * Enum representing types of requirements.
 */
enum class Requirement(
    val id: Long, 
    val symbol: String, 
    val description: String, 
    val validatorClass: KClass<out RequirementValidator>
) {
    IZP(1, "IZP", "Aktualny IZP", IzpValidator::class),
    RATING(2, "Rating", "Aktualny Rating", RatingValidator::class),
    CAUSE(3, "Cause", "Powód zmian", CauseValidator::class),
    ANALYSIS(4, "Analysis", "Analiza I analityka", AnalysisValidator::class),
    APPROVAL(5, "Approval", "Akceptacja II analityka", ApprovalValidator::class);
}