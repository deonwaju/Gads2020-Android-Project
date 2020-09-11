package gads2020.damolaolarewaju.testprojectgads.ui.views.fragments.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.DialogFragment
import gads2020.damolaolarewaju.testprojectgads.R
class SuccessDialogFragment : DialogFragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_dialog_success, container, false)
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(WindowManager.LayoutParams.MATCH_PARENT,
        WindowManager.LayoutParams.WRAP_CONTENT)
    }
}