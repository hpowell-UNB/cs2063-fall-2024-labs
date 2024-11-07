package mobiledev.unb.ca.labexam.model

/**
 * Data class used to hold Event information
 */
data class EventInfo(
    val year: String,
    val number: String,
    val hostCity: String,
    val dates: String,
    val wikipediaLink: String
) {
    // Also include a getter for the event title
    val eventTitle: String
        get() = "$number - $hostCity"
}