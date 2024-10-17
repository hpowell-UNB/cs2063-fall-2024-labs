package mobiledev.unb.ca.threadinglab.model

/**
 * This makes use of the data class pattern
 * NOTES:
 *  id and name are private (set only); they are retrieved as a combination result
 *  description is public (get and set)
 */
data class Course(private val id: String?,
                  private val name: String?,
                  val description: String? = null) {
    // Only need to include getter for the course title
    val title: String
        get() = "$id: $name"
}
