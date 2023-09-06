package com.example.modernandroiddevelopment.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.modernandroiddevelopment.data.model.Book
import com.example.modernandroiddevelopment.databinding.ItemBookPreviewBinding

class BookSearchAdapter: ListAdapter<Book, BookSearchViewHolder>(BookDiffCallback) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookSearchViewHolder {
        return BookSearchViewHolder(
            ItemBookPreviewBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        )

    }

    override fun onBindViewHolder(holder: BookSearchViewHolder, position: Int) {
        val book = currentList[position]
        holder.bind(book)
        holder.itemView.setOnClickListener {
            onItemClickListener?.let { it(book) }
            // onItemClickListener가 설정되어있으면 해당 클릭 리스너를 호출함
            // 클릭 리스너는 람다식으로 '(Book) -> Unit' 형태의 함수로 설정
            // 클릭된 아이템의 book 객체를 전달해 클릭 이벤트에 대한 동작을 수행함
        }
    }

    private var onItemClickListener: ((Book) -> Unit)? = null

    // 외부에서 클릭 리스너를 설정할 수 있도록 해줌
    fun setOnItemClickListener(listener: (Book) -> Unit) {
        onItemClickListener = listener
    }

    companion object {
        private val BookDiffCallback = object : DiffUtil.ItemCallback<Book>() {
            override fun areItemsTheSame(oldItem: Book, newItem: Book): Boolean {
                return oldItem.isbn == newItem.isbn
            }

            override fun areContentsTheSame(oldItem: Book, newItem: Book): Boolean {
                return oldItem == newItem
            }
        }
    }
}