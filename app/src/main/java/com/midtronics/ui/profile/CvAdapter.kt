package com.midtronics.ui.profile

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.midtronics.R
import com.midtronics.data.Education
import com.midtronics.data.Experience

class CvAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>()  {

    private val VIEW_TYPE_EXPERIENCE = 1
    private val VIEW_TYPE_EDUCATION = 2

    private var experience: List<Experience> = emptyList()
    private var education: List<Education> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            VIEW_TYPE_EXPERIENCE -> {
                val view = LayoutInflater
                    .from(parent.context)
                    .inflate(R.layout.item_list_experience, parent, false)

                ExperienceViewHolder(view)
            }
            VIEW_TYPE_EDUCATION -> {
                val view = LayoutInflater
                    .from(parent.context)
                    .inflate(R.layout.item_list_education, parent, false)

                EducationViewHolder(view)
            }
            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (position < experience.size) {
            VIEW_TYPE_EXPERIENCE
        } else {
            VIEW_TYPE_EDUCATION
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder.itemViewType) {
            VIEW_TYPE_EXPERIENCE -> {
                val item = experience[position]
                (holder as ExperienceViewHolder).bind(item)
            }
            VIEW_TYPE_EDUCATION -> {
                val item = education[position - experience.size]
                (holder as EducationViewHolder).bind(item)
            }
        }
    }

    override fun getItemCount(): Int {
        return experience.size + education.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateData(experience: List<Experience>, education: List<Education>) {
        this.experience  = experience
        this.education  = education
        notifyDataSetChanged()
    }

    inner class ExperienceViewHolder(
        itemView: View
    ) : RecyclerView.ViewHolder(itemView) {
        private val companyName: TextView = itemView.findViewById(R.id.company_name)
        private val position: TextView = itemView.findViewById(R.id.position)
        private val startDate: TextView = itemView.findViewById(R.id.start_date)
        private val endDate: TextView = itemView.findViewById(R.id.end_date)

        fun bind(experience: Experience) {
            companyName.text = experience.companyName
            position.text = experience.position
            startDate.text = experience.startDate
            experience.endDate?.let { date ->
                endDate.text = date
            }
        }
    }

    inner class EducationViewHolder(
        itemView: View
    ) : RecyclerView.ViewHolder(itemView) {
        private val educationValue: TextView = itemView.findViewById(R.id.education)
        private val place: TextView = itemView.findViewById(R.id.place_name)
        private val startDate: TextView = itemView.findViewById(R.id.start_date)
        private val endDate: TextView = itemView.findViewById(R.id.end_date)

        fun bind(education: Education) {
            educationValue.text = education.education
            place.text = education.placeName
            startDate.text = education.startDate
            education.endDate?.let { date ->
                endDate.text = date
            }
        }
    }

}