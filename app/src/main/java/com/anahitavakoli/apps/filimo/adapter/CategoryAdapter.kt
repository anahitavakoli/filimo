package com.anahitavakoli.apps.filimo.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.recyclerview.widget.RecyclerView
import com.anahitavakoli.apps.filimo.R
import com.anahitavakoli.apps.filimo.model.Category
import com.squareup.picasso.Picasso

class CategoryAdapter(cContext: Context,data:List<Category>): RecyclerView.Adapter<CategoryAdapter.CategoryVH>() {

    var context = cContext
    var categoryList = data
    var inflater = LayoutInflater.from(context)

    class CategoryVH(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var imgCategory = itemView.findViewById<AppCompatImageView>(R.id.img_category)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryVH {
        var view = inflater.inflate(R.layout.category_home_raw,null)
        return CategoryVH(view)
    }

    override fun onBindViewHolder(holder: CategoryVH, position: Int) {
        var category: Category = categoryList.get(position)
        Picasso.get().load(category.categoryImage).into(holder.imgCategory)
    }

        override fun getItemCount(): Int {
        return categoryList.size
    }
}