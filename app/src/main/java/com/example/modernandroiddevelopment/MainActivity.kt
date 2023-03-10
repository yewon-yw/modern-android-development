package com.example.modernandroiddevelopment

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.modernandroiddevelopment.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(binding.root)
        val binding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)

//        var counter = 100
//        binding.textView.text = counter.toString()
//
//        binding.button.setOnClickListener{
//            counter += 1
//            binding.textView.text = counter.toString()
//        }

//        val myViewModel: MyViewModel = ViewModelProvider(this).get(MyViewModel::class.java)
//        myViewModel.counter = 100
//        binding.textView.text = myViewModel.counter.toString()
//
//        binding.button.setOnClickListener {
//            myViewModel.counter += 1
//            binding.textView.text = myViewModel.counter.toString()
//        }

        val myRepositoryImpl = MyRepositoryImpl(10)
        val factory = MyViewModelFactory(10, myRepositoryImpl,this)
//        val myViewModel = ViewModelProvider(this,factory).get(MyViewModel::class.java)
        val myViewModel by viewModels<MyViewModel>() { factory }
        // 팩토리를 적용해야하는 경우

        binding.lifecycleOwner = this
        binding.viewmodel = myViewModel

        binding.textView.text = myViewModel.counter.toString()

        binding.button.setOnClickListener {
//            myViewModel.counter += 1
//            binding.textView.text = myViewModel.counter.toString()
//            myViewModel.saveState()
//            myViewModel.liveCounter.value = myViewModel.liveCounter.value?.plus(1)
            myViewModel.increaseCounter()
        }
//        myViewModel.liveCounter.observe(this) { counter ->
//            binding.textView.text = counter.toString()
//        }

//        myViewModel.modifiedCounter.observe(this) { counter ->
//            binding.textView.text = counter.toString()
//        }
    }
}