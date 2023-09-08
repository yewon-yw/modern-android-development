package com.example.modernandroiddevelopment.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.modernandroiddevelopment.databinding.FragmentBookBinding
import com.example.modernandroiddevelopment.ui.viewmodel.BookSearchViewModel
import com.google.android.material.snackbar.Snackbar

class BookFragment : Fragment() {
    private var _binding: FragmentBookBinding? = null
    private val binding get() = _binding!!
    private val args by navArgs<BookFragmentArgs>()
    private lateinit var bookSearchViewModel: BookSearchViewModel

    // 프래그먼트의 UI를 생성하고 초기화하는 메서드
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentBookBinding.inflate(inflater, container, false)
        return binding.root
    }

    // onCreateView 이후에 호출되는 메서드
    // 프래그먼트의 뷰가 생성되고 연결된 후에 호출됨
    // onCreateView에서 생성한 뷰를 가지고 추가적인 작업을 수행할 때 사용
    // 뷰에 데이터를 채우거나 클릭 리스너를 설정하는 등의 작업 수행
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 이렇게 사용하려면 현재 액티비티가 MainActivity에서 호스팅되어야 함
        bookSearchViewModel = (activity as MainActivity).bookSearchViewModel

        val book = args.book
        binding.webview.apply {
            webViewClient = WebViewClient()
            settings.javaScriptEnabled = true
            loadUrl(book.url)
        }

        binding.fabFavorite.setOnClickListener {
            bookSearchViewModel.saveBook(book)
            Snackbar.make(view, "Book has saved", Snackbar.LENGTH_SHORT).show()
        }
    }

    // 현재 활동이 사용자와의 상호작용을 잃고 화면에서 가려질 때 호출
    // 사용자와의 상호작용을 중단하고 필요한 저장 작업이나 정리 작업 수행할 때 사용
    override fun onPause() {
        binding.webview.onPause()
        super.onPause()
    }

    // 현재 활동이 화면 상에 나타나 사용자와 상호작용이 가능한 상태로 진입할 때 호출됨
    // 사용자와의 상호작용을 재개하고 화면에 보이는 컴포넌트들을 업데이트하거나 초기화하는 데 사용
    override fun onResume() {
        super.onResume()
        binding.webview.onResume()
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}