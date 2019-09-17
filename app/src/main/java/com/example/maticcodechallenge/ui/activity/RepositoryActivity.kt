package com.example.maticcodechallenge.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.maticcodechallenge.R
import com.example.maticcodechallenge.databinding.RepositoryActivityBinding
import com.example.maticcodechallenge.ui.adapter.RepositoriesAdapter
import com.example.maticcodechallenge.ui.viewmodel.RepositoryViewModel
import com.example.maticcodechallenge.ui.viewmodel.RepositoryViewModelFactory
import org.kodein.di.KodeinAware
import org.kodein.di.android.closestKodein
import org.kodein.di.generic.instance

class RepositoryActivity : AppCompatActivity(), KodeinAware {

    private lateinit var adapter: RepositoriesAdapter
    private lateinit var viewModel: RepositoryViewModel
    private lateinit var binding: RepositoryActivityBinding
    override val kodein by closestKodein()
    private val viewModelFactory: RepositoryViewModelFactory by instance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_repository)

    }
    
}
