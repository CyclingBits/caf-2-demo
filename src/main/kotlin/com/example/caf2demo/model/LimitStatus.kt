package com.example.caf2demo.model

/**
 * Enum representing statuses of limits.
 */
enum class LimitStatus {
    ACTIVE, // Limit is active and can be used
    USED_UP, // Limit has been fully used
    EXPIRED, // Limit has expired (date has passed)
}
