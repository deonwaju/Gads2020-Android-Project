package gads2020.damolaolarewaju.testprojectgads.models
/**
 * Represents the Success, Loading and Error cases from the Remote API.
 */
sealed class ResourceModel<T>(
    val data: T? = null,
    val message: String? = null
) {
    class Success<T>(data: T) : ResourceModel<T>(data)
    class Error<T>(message: String, data: T? = null) : ResourceModel<T>(data, message)
    class Loading<T> : ResourceModel<T>()
}