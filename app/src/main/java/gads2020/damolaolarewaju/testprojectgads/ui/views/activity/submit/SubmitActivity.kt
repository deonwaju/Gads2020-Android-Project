package gads2020.damolaolarewaju.testprojectgads.ui.views.activity.submit

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.observe
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_submit.*
import gads2020.damolaolarewaju.testprojectgads.R
import gads2020.damolaolarewaju.testprojectgads.common.gone
import gads2020.damolaolarewaju.testprojectgads.common.toast
import gads2020.damolaolarewaju.testprojectgads.common.util.InputDataValidators
import gads2020.damolaolarewaju.testprojectgads.common.visible
import gads2020.damolaolarewaju.testprojectgads.ui.viewmodels.SubmitDataViewModel
import gads2020.damolaolarewaju.testprojectgads.ui.views.activity.home.HomeActivity
import gads2020.damolaolarewaju.testprojectgads.ui.views.fragments.dialog.ConfirmationDialogFragment
import gads2020.damolaolarewaju.testprojectgads.ui.views.fragments.dialog.ErrorDialogFragment
import gads2020.damolaolarewaju.testprojectgads.ui.views.fragments.dialog.SuccessDialogFragment

@AndroidEntryPoint
class SubmitActivity : AppCompatActivity(), ConfirmationDialogFragment.OnSubmitButtonClicked {
    private val inputValidator: InputDataValidators by lazy { InputDataValidators() }
    private val submitDataViewModel: SubmitDataViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_submit)
        //setSupportActionBar(submitToolbar)

        subscribeToLiveData()
        initListeners()
    }
    private fun initListeners() {
        actionBack.setOnClickListener { initUi() }
        confirmButton.setOnClickListener { initConfirmDialog() }
    }
    private fun subscribeToLiveData(){
        submitDataViewModel.submissionSuccess.observe(this, {
            if (it == true){
                initSuccessDialog()
            }
            else{
                initFailureDialog()
            }
        })
        submitDataViewModel.loadingState.observe(this, { loading ->
            if (loading == true){
                showLoading()
            }
            else
            {
                hideLoading()
            }
        })
        submitDataViewModel.toast.observe(this, {
            this.toast(it)
        })
    }
    //submit project details
    private fun submitProject(){
        val firstName = firstNameEditText.text.toString()
        val lastName = lastNameEditText.text.toString()
        val email = emailEditText.text.toString()
        val projectLink = githubLinkEditText.text.toString()
        submitDataViewModel.submitProject(
            firstName, lastName, email,projectLink
        )
    }

    override fun onSubmitButtonClicked(button: View) {
        button.setOnClickListener { submitProject() }
    }

    //show Success dialog
    private fun initSuccessDialog(){
        val dialog =
            SuccessDialogFragment()
        dialog.show(supportFragmentManager, dialog.tag)
    }
    //show Failure dialog
    private fun initFailureDialog() {
        val dialog =
            ErrorDialogFragment()
        dialog.show(supportFragmentManager, dialog.tag)
    }
    private fun initUi() {
        val intent = Intent(this, HomeActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
        startActivity(intent)
    }

    private fun validateSubmission(): Boolean{
        var areCredentialsValid = true
        val firstName = firstNameEditText.text.toString()
        val lastName = lastNameEditText.text.toString()
        val email = emailEditText.text.toString()
        val projectLink = githubLinkEditText.text.toString()
        inputValidator.setCredentials(
            firstName, lastName, email, projectLink
        )
        if (!inputValidator.isFirstNameValid()){
            areCredentialsValid = false
            firstNameEditText.error = getString(R.string.first_name_error)
            firstNameEditText.requestFocus()
        }
        if (!inputValidator.isLastNameValid()){
            areCredentialsValid = false
            lastNameEditText.error = getString(R.string.last_name_error)
            lastNameEditText.requestFocus()
        }
        if (!inputValidator.isEmailValid()){
            areCredentialsValid = false
            emailEditText.error = getString(R.string.email_error)
            emailEditText.requestFocus()
        }
        if (!inputValidator.isProjectLinkValid()){
            areCredentialsValid = false
            githubLinkEditText.error = getString(R.string.link_error)
            githubLinkEditText.requestFocus()
        }
        return areCredentialsValid
    }
    private fun initConfirmDialog(){
        if (validateSubmission()){
            val dialog =
                ConfirmationDialogFragment()
            dialog.setSubmitButtonClickListener(this)
            dialog.show(supportFragmentManager, dialog.tag)
        }
    }
    private fun showLoading(){
        submitProgressBar.visible()
    }
    private fun hideLoading(){
        submitProgressBar.gone()
    }
}