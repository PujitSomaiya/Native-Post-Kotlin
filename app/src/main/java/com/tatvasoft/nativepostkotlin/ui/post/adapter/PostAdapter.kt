package com.tatvasoft.nativepostkotlin.ui.post.adapter

import android.annotation.SuppressLint
import android.content.res.Resources
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.tatvasoft.nativepostkotlin.R
import com.tatvasoft.nativepostkotlin.interfaces.RecyclerInterface
import com.tatvasoft.nativepostkotlin.ui.post.model.HitsItem
import kotlinx.android.synthetic.main.listitem_post.view.*

class PostAdapter ():
    PagedListAdapter<HitsItem, PostAdapter.PostViewHolder>(USER_COMPARATOR) {
//developer

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.listitem_post, parent, false)
        return PostViewHolder(view)
    }


    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val posts = getItem(position)
        posts?.let { holder.bind(it) }
    }

    var recyclerViewItemClick: RecyclerInterface? = null
    constructor(recyclerInterface: RecyclerInterface) : this() {
        this.recyclerViewItemClick=recyclerInterface
    }

    class PostViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var checkNumber = 0
        var res: Resources = view.context.resources
        private val tvTitle = view.tvTitle
        private val tvPageNumber = view.tvPageNumber
        private val tvCreated = view.tvCreated
        private val tvAuthor = view.tvAuthor
        private val tvStoryId = view.tvStoryId
        private val swActivation = view.swActivation
        private val clMain = view.clMain
        private val chkSelected = view.chkSelected

        @SuppressLint("SetTextI18n")
        fun bind(hitsItems: HitsItem) {
            tvTitle?.text = "Title:" + hitsItems.title
            tvCreated?.text = "Created At: " + hitsItems.createdAt
            tvAuthor?.text = "Author: " + hitsItems.author
            tvStoryId?.text = "Story Id: " + hitsItems.objectID
            tvPageNumber?.text = "Points: " + hitsItems.points.toString()

            clMain?.setOnClickListener {
                if (chkSelected.isChecked) {
                    checkNumber--
                    chkSelected.isChecked = false
                    clMain.setBackgroundColor(res.getColor(R.color.colorWhite))
                } else {
                    checkNumber++
                    chkSelected.isChecked = true
                    clMain.setBackgroundColor(res.getColor(R.color.design_default_color_error))
                }

            }

        }

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