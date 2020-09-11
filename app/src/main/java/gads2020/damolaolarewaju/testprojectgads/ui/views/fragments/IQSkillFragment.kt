package gads2020.damolaolarewaju.testprojectgads.ui.views.fragments

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
import kotlinx.android.synthetic.main.fragment_iqskill.*
import gads2020.damolaolarewaju.testprojectgads.R
import gads2020.damolaolarewaju.testprojectgads.adapters.IQSkillAdapter
import gads2020.damolaolarewaju.testprojectgads.common.gone
import gads2020.damolaolarewaju.testprojectgads.common.visible
import gads2020.damolaolarewaju.testprojectgads.networking.NetworkStatus
import gads2020.damolaolarewaju.testprojectgads.ui.viewmodels.IQSkillLeaderViewModel

@RequiresApi(Build.VERSION_CODES.M)
@AndroidEntryPoint
class IQSkillFragment : Fragment() {
    private val iqSkillLeaderViewModel: IQSkillLeaderViewModel by viewModels()
    private val IQSkillAdapter: IQSkillAdapter by lazy { IQSkillAdapter() }
    private val networkStatus: NetworkStatus by lazy {
        NetworkStatus(requireActivity().getSystemService(ConnectivityManager::class.java))
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_iqskill, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        networkStatus.performIfConnectedToInternet(::displayNoNetworkMessage){
            iqSkillLeaderViewModel.fetchIQLeaders()
        }
        iqSkillLeaderViewModel.toast.observe(viewLifecycleOwner, { message ->
            if (message != null){
                errorTitle.text = message
                showErrorView()
            }
        })
        iqSkillLeaderViewModel.learningLeaders.observe(viewLifecycleOwner, {
            IQSkillAdapter.setData(it)
        })
    }
    private fun setupRecyclerView() = skillIQRecyclerView.apply {
        this.adapter = IQSkillAdapter
        this.layoutManager = when (resources.configuration.orientation){
            Configuration.ORIENTATION_PORTRAIT -> {
                LinearLayoutManager(requireContext())
            }
            Configuration.ORIENTATION_LANDSCAPE -> {
                GridLayoutManager(requireContext(), 2, RecyclerView.VERTICAL, false)
            }
            else -> throw IllegalStateException(context.getString(R.string.state_error))
        }
    }
    private fun displayNoNetworkMessage(){
        view?.let {
            errorTitle.text = getText(R.string.no_connection)
            errorSubtitle.text = getText(R.string.no_connection_error)
            showErrorView()
        }
    }
    private fun showErrorView(){
        skillIQRecyclerView.gone()
        errorView.visible()
    }
}