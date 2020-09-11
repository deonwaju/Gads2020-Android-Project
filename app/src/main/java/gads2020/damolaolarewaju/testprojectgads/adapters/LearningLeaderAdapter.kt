package gads2020.damolaolarewaju.testprojectgads.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.leader_learner_item.view.*
import gads2020.damolaolarewaju.testprojectgads.R
import gads2020.damolaolarewaju.testprojectgads.models.LearningLeaderModel

class LearningLeaderAdapter : RecyclerView.Adapter<HomeViewHolder>() {
    private var leadersList: List<LearningLeaderModel> = emptyList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        return HomeViewHolder(LayoutInflater.from(parent.context).inflate(
            R.layout.leader_learner_item,
            parent,
            false
        ))
    }
    override fun getItemCount(): Int = leadersList.size
    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        val learningLeader = leadersList[position]
        holder.itemView.apply {
            Glide.with(this).load(learningLeader.badgeUrl).into(topLearnerBadge)
            topLearnerName.text = learningLeader.name
            val hours = "${learningLeader.hours} learning hours, ${learningLeader.country}"
            hoursAndCountry.text = hours
        }
    }
    internal fun initData(leaderModels: List<LearningLeaderModel>) {
        this.leadersList = leaderModels
        notifyDataSetChanged()
    }
}