package gads2020.damolaolarewaju.testprojectgads.common.util

interface Validators {
    fun setCredentials(firstName: String, lastName: String, email: String, projectLink: String)
    fun isFirstNameValid(): Boolean
    fun isLastNameValid(): Boolean
    fun isEmailValid(): Boolean
    fun isProjectLinkValid(): Boolean
}