package com.tatvasoft.nativepostkotlin.ui.post.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat.getColor
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.checkbox.MaterialCheckBox
import com.google.android.material.switchmaterial.SwitchMaterial
import com.google.android.material.textview.MaterialTextView
import com.tatvasoft.nativepostkotlin.R
import com.tatvasoft.nativepostkotlin.interfaces.RecyclerInterface
import com.tatvasoft.nativepostkotlin.ui.post.model.HitsItem
import kotlinx.android.synthetic.main.listitem_post.view.*

class PostAdapter(context: Context, recyclerInterface: RecyclerInterface) :
    PagedListAdapter<HitsItem, PostAdapter.PostViewHolder>(USER_COMPARATOR) {

    private var context: Context? = context
    private var recyclerViewItemClick: RecyclerInterface? = recyclerInterface
    private var checkNumber = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.listitem_post, parent, false)
        return PostViewHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val posts = getItem(position)
        val tvTitle = holder.tvTitle
        val tvCreated = holder.tvCreated
        val tvAuthor = holder.tvAuthor
        val tvStoryId = holder.tvStoryId
        val tvPageNumber = holder.tvPageNumber
        val clMain = holder.clMain
        val swActivation = holder.swActivation
        val chkSelected = holder.chkSelected

        tvTitle.text = "Title:" + posts?.title
        tvCreated.text = "Created At: " + posts?.createdAt
        tvAuthor.text = "Author: " + posts?.author
        tvStoryId.text = "Story Id: " + posts?.objectID
        tvPageNumber.text = "Points: " + posts?.points.toString()

        clMain.setOnClickListener {
            if (chkSelected.isChecked) {
                checkNumber--
                chkSelected.isChecked = false
                context?.let { it1 -> getColor(it1, R.color.colorWhite) }?.let { it2 ->
                    clMain.setBackgroundColor(
                        it2
                    )
                }
            } else {
                checkNumber++
                chkSelected.isChecked = true
                context?.let { it1 -> getColor(it1, R.color.design_default_color_error) }
                    ?.let { it2 ->
                        clMain.setBackgroundColor(
                            it2
                        )
                    }
            }
            recyclerViewItemClick?.onItemClick(checkNumber)
        }

        swActivation.setOnClickListener {
            if (swActivation.isChecked) {
                notifyDataSetChanged()
                swActivation.isChecked = false
                if (chkSelected.isChecked) {
                    checkNumber--
                    chkSelected.setChecked(false)
                    context?.let { it1 -> getColor(it1, R.color.colorWhite) }?.let { it2 ->
                        clMain.setBackgroundColor(
                            it2
                        )
                    }
                    recyclerViewItemClick?.onItemClick(checkNumber)
                }
            }
        }

//        posts?.let { holder.bind(it) }
    }


    class PostViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvTitle: MaterialTextView = view.tvTitle
        val tvPageNumber: MaterialTextView = view.tvPageNumber
        val tvCreated: MaterialTextView = view.tvCreated
        val tvAuthor: MaterialTextView = view.tvAuthor
        val tvStoryId: MaterialTextView = view.tvStoryId
        val swActivation: SwitchMaterial = view.swActivation
        val clMain: ConstraintLayout = view.clMain
        val chkSelected: MaterialCheckBox = view.chkSelected

    }

    companion object {
        private val USER_COMPARATOR = object : DiffUtil.ItemCallback<HitsItem>() {
            override fun areItemsTheSame(oldItem: HitsItem, newItem: HitsItem): Boolean =
                oldItem.objectID == newItem.objectID

            override fun areContentsTheSame(oldItem: HitsItem, newItem: HitsItem): Boolean =
                newItem == oldItem
        }
    }

}