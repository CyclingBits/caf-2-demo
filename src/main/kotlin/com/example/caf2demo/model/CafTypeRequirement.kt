package com.example.caf2demo.model

/**
 * Enum representing mapping between CAF types and requirements.
 */
enum class CafTypeRequirement(
    val cafType: CafType,
    val requirement: Requirement,
) {
    // INCREASE mappings
    INCREASE_IZP(CafType.INCREASE, Requirement.IZP),
    INCREASE_RATING(CafType.INCREASE, Requirement.RATING),
    INCREASE_CAUSE(CafType.INCREASE, Requirement.CAUSE),
    INCREASE_ANALYSIS(CafType.INCREASE, Requirement.ANALYSIS),
    INCREASE_APPROVAL(CafType.INCREASE, Requirement.APPROVAL),

    // EXTENSION mappings
    EXTENSION_IZP(CafType.EXTENSION, Requirement.IZP),
    EXTENSION_RATING(CafType.EXTENSION, Requirement.RATING),
    EXTENSION_CAUSE(CafType.EXTENSION, Requirement.CAUSE),
    EXTENSION_ANALYSIS(CafType.EXTENSION, Requirement.ANALYSIS),
    EXTENSION_APPROVAL(CafType.EXTENSION, Requirement.APPROVAL),

    // SUSPENSION mappings
    SUSPENSION_IZP(CafType.SUSPENSION, Requirement.IZP),
    SUSPENSION_RATING(CafType.SUSPENSION, Requirement.RATING),
    SUSPENSION_CAUSE(CafType.SUSPENSION, Requirement.CAUSE),
    SUSPENSION_ANALYSIS(CafType.SUSPENSION, Requirement.ANALYSIS),
    SUSPENSION_APPROVAL(CafType.SUSPENSION, Requirement.APPROVAL), ;

    companion object {
        /**
         * Get all requirements for a specific CAF type.
         */
        fun getRequirementsForCafType(cafType: CafType): List<Requirement> {
            return values().filter { it.cafType == cafType }.map { it.requirement }
        }

        /**
         * Get all CAF types requiring a specific requirement.
         */
        fun getCafTypesForRequirement(requirement: Requirement): List<CafType> {
            return values().filter { it.requirement == requirement }.map { it.cafType }
        }
    }
}
