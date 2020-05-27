package com.tatvasoft.nativepostkotlin.ui.post.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.switchmaterial.SwitchMaterial
import com.google.android.material.textview.MaterialTextView
import com.tatvasoft.nativepostkotlin.R
import com.tatvasoft.nativepostkotlin.interfaces.RecyclerInterface
import com.tatvasoft.nativepostkotlin.ui.post.model.HitsItem
import kotlinx.android.synthetic.main.listitem_post.view.*
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

class PostAdapter(recyclerInterface: RecyclerInterface, number: Int) :
    PagedListAdapter<HitsItem, PostAdapter.PostViewHolder>(USER_COMPARATOR) {

    private var recyclerViewItemClick: RecyclerInterface? = recyclerInterface
    var checkNumber = number


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
        val swActivation = holder.swActivation
        @SuppressLint("SimpleDateFormat") val dateFormat =
            SimpleDateFormat("yyyy.MM.dd G 'at' HH:mm:ss z")
        var sourceDate: Date? = Date()
        try {
            sourceDate = dateFormat.parse(posts?.createdAt)
        } catch (e: ParseException) {
            e.printStackTrace()
        }

        @SuppressLint("SimpleDateFormat") val targetFormat =
            SimpleDateFormat("dd-MM-yyyy")
        tvTitle.text = "Title:" + posts?.title
        tvCreated.text = "Created At: " + targetFormat.format(sourceDate)
        tvAuthor.text = "Author: " + posts?.author
        tvStoryId.text = "Story Id: " + posts?.objectID
        tvPageNumber.text = "Points: " + posts?.points.toString()

        swActivation.setOnClickListener { view: View? ->
            if (swActivation.isChecked) {
                checkNumber++
                recyclerViewItemClick?.onItemClick(checkNumber)
                getItem(position)?.isCheck = true
            } else {
                checkNumber--
                recyclerViewItemClick?.onItemClick(checkNumber)
                getItem(position)?.isCheck = false
            }
        }
        swActivation.isChecked = getItem(position)!!.isCheck
    }

    class PostViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvTitle: MaterialTextView = view.tvTitle
        val tvPageNumber: MaterialTextView = view.tvPageNumber
        val tvCreated: MaterialTextView = view.tvCreated
        val tvAuthor: MaterialTextView = view.tvAuthor
        val tvStoryId: MaterialTextView = view.tvStoryId
        val swActivation: SwitchMaterial = view.swActivation
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