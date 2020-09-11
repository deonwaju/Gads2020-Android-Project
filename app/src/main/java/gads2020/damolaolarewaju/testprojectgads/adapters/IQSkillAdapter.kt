package gads2020.damolaolarewaju.testprojectgads.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.iq_skill_learner_item.view.*
import gads2020.damolaolarewaju.testprojectgads.R
import gads2020.damolaolarewaju.testprojectgads.models.IQSkillLeaderModel

class IQSkillAdapter: RecyclerView.Adapter<HomeViewHolder>() {
    private var skillLeaderModels: List<IQSkillLeaderModel> = emptyList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        return HomeViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.iq_skill_learner_item,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        val skillIQLeader = skillLeaderModels[position]
        holder.itemView.apply {
            Glide.with(this).load(skillIQLeader.badgeUrl).into(topIQLearnerBadge)
            topIQLearnerName.text = skillIQLeader.name
            val iqAndCountry = "${skillIQLeader.score} skill IQ Score, ${skillIQLeader.country}"
            skillAndCountry.text = iqAndCountry
        }
    }

    override fun getItemCount(): Int = skillLeaderModels.size

    internal fun setData(IQSkillLeaderModels: List<IQSkillLeaderModel>){
        skillLeaderModels = IQSkillLeaderModels
        notifyDataSetChanged()
    }
}