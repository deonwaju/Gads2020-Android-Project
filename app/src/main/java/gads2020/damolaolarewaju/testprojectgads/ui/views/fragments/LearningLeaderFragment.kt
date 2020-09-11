package gads2020.damolaolarewaju.testprojectgads.ui.views.fragments

import android.annotation.SuppressLint
import android.content.res.Configuration
import android.net.ConnectivityManager
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_learningleader.*
import gads2020.damolaolarewaju.testprojectgads.R
import gads2020.damolaolarewaju.testprojectgads.adapters.LearningLeaderAdapter
import gads2020.damolaolarewaju.testprojectgads.common.gone
import gads2020.damolaolarewaju.testprojectgads.common.visible
import gads2020.damolaolarewaju.testprojectgads.networking.NetworkStatus
import gads2020.damolaolarewaju.testprojectgads.ui.viewmodels.LearningLeaderViewModel
import timber.log.Timber

@SuppressLint("NewApi")
@AndroidEntryPoint
class LearningLeaderFragment : Fragment() {
    private val learningLeaderViewModel: LearningLeaderViewModel by viewModels()
    private val networkStatus: NetworkStatus by lazy {
        NetworkStatus(requireActivity().getSystemService(ConnectivityManager::class.java))
    }
    private val learningLeaderAdapter: LearningLeaderAdapter by lazy { LearningLeaderAdapter() }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_learningleader, container, false)
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        networkStatus.performIfConnectedToInternet(::displayNoNetworkMessage){
            learningLeaderViewModel.fetchLearningLeaders()
        }
        learningLeaderViewModel.toast.observe(viewLifecycleOwner, {message ->
            if (message != null){
                errorTitle.text = message
                showErrorView()
                hideLoading()
            }
        })
        learningLeaderViewModel.loadingState.observe(viewLifecycleOwner, {
            if (it == true){
                showLoadingDialog()
            }
            else {
                hideLoading()
            }
        })
        learningLeaderViewModel.learningLeadersModel.observe(viewLifecycleOwner, {
            Timber.d(it.size.toString())
            learningLeaderAdapter.initData(it)
        })
        initListeners()
    }
    private fun initListeners(){
        retryButton.setOnClickListener { loadData() }
    }
    private fun setupRecyclerView() = learningRecyclerView.apply {
        this.adapter = learningLeaderAdapter
        this.layoutManager = when (resources.configuration.orientation){
            Configuration.ORIENTATION_PORTRAIT ->{
                LinearLayoutManager(requireContext())
            }
            Configuration.ORIENTATION_LANDSCAPE -> {
                GridLayoutManager(requireContext(), 2, RecyclerView.VERTICAL, false)
            }
            else -> throw IllegalStateException(context.getString(R.string.state_error))
        }
    }
    private fun loadData(){
        networkStatus.performIfConnectedToInternet(::displayNoNetworkMessage){
            learningLeaderViewModel.learningLeadersModel
        }
    }
    /**
     * no network alert
     */
    private fun displayNoNetworkMessage(){
        view?.let {
            errorTitle.text = getText(R.string.no_connection)
            errorSubtitle.text = getText(R.string.no_connection_error)
            showErrorView()
        }
    }
    private fun showErrorView(){
        errorView.visible()
        learningRecyclerView.gone()
    }
    private fun showLoadingDialog(){
        leanerProgressBar.visible()
        learningRecyclerView.gone()
        errorView.gone()
    }
    private fun hideLoading(){
        leanerProgressBar.gone()
        learningRecyclerView.visible()
    }
}