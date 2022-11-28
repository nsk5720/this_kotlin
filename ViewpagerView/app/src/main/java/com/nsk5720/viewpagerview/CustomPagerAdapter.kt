package com.nsk5720.viewpagerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nsk5720.viewpagerview.databinding.ItemViewpagerBinding

class CustomPagerAdapter: RecyclerView.Adapter<Holder>() {
    var textList = listOf<String>()
    // onCreateViewHolder()메서드 바인딩을 생성한 후 holder에 전달
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding = ItemViewpagerBinding.inflate(
            LayoutInflater.from(parent.context), parent,
            false)
        return Holder(binding)

    }
    //
    override fun onBindViewHolder(holder: Holder, position: Int) {
        val text = textList[position]
        holder.setText(text)
    }
    // getItemCount 메서드는 몇 개의 페이지가 보일 건지 결정한다.
    override fun getItemCount(): Int {
        return textList.size
    }
}

class Holder(val binding: ItemViewpagerBinding): RecyclerView.ViewHolder(binding.root){



    fun setText(text:String){
        binding.textView.text = text
    }
}