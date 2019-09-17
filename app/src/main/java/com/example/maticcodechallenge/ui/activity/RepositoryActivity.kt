package com.example.maticcodechallenge.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.maticcodechallenge.R
import com.example.maticcodechallenge.databinding.RepositoryActivityBinding
import com.example.maticcodechallenge.ui.adapter.RepositoriesAdapter
import com.example.maticcodechallenge.ui.fragment.dialog.NoInternetDialog
import com.example.maticcodechallenge.ui.viewmodel.RepositoryViewModel
import com.example.maticcodechallenge.ui.viewmodel.RepositoryViewModelFactory
import data.model.NetworkState
import org.kodein.di.KodeinAware
import org.kodein.di.android.closestKodein
import org.kodein.di.generic.instance

class RepositoryActivity : AppCompatActivity(), KodeinAware, NoInternetDialog.IInternetFragment {

    private lateinit var adapter: RepositoriesAdapter
    private lateinit var viewModel: RepositoryViewModel
    private lateinit var binding: RepositoryActivityBinding
    override val kodein by closestKodein()
    private val viewModelFactory: RepositoryViewModelFactory by instance()

    override fun onInternetFragmentClosed() {
        finish()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_repository)
        setUpRecyclerView()
        setUpViewModel()
    }

    private fun setUpRecyclerView() {
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = RepositoriesAdapter()
        binding.recyclerView.adapter = adapter
    }

    private fun setUpViewModel() {
        viewModel =
            ViewModelProviders.of(this, viewModelFactory).get(RepositoryViewModel::class.java)

        viewModel.apply {
            getRepositoryLiveData()?.observe(this@RepositoryActivity, Observer {
                adapter.submitList(it)
            })

            getInitialLoading()?.observe(this@RepositoryActivity, Observer {
                if (it.status == NetworkState.Status.FAILED) {
                    val noInternetFragment = NoInternetDialog()
                    noInternetFragment.isCancelable = false
                    noInternetFragment.setCallback(this@RepositoryActivity)
                    noInternetFragment.show(supportFragmentManager, null)
                }

            })

            getNetworkState()?.observe(this@RepositoryActivity, Observer {
                adapter.setNetworkState(it)
            })
        }

    }
}
