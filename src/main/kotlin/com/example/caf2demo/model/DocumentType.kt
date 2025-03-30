package com.example.caf2demo.model

/**
 * Types of documents that can be attached to a contractor.
 */
enum class DocumentType(val displayName: String) {
    FINANCIAL("Dokumenty finansowe"),
    REGISTRY("Dokumenty rejestrowe"),
    BOOKKEEPING("Dokumenty księgowe"),
    AGREEMENT("Umowy"),
    ;

    companion object {
        /**
         * Returns display name for the given type.
         */
        fun getDisplayName(type: DocumentType): String {
            return type.displayName
        }
    }
}
