package com.piashcse.wpark.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.piashcse.wpark.R
import com.piashcse.wpark.databinding.FragmentHomeBinding
import com.piashcse.wpark.ui.adapter.CityAdapter
import com.piashcse.wpark.ui.adapter.FoodAdapter
import com.piashcse.wpark.utils.*
import com.piashcse.wpark.utils.network.DataState
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private val homeViewModel: HomeViewModel by viewModels()
    private val cityAdapter: CityAdapter by lazy {
        CityAdapter()
    }
    private val foodAdapter: FoodAdapter by lazy {
        FoodAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        apiResponse()
    }

    private fun initView() {
        homeViewModel.cityAndFood()
        binding.apply {
            swipeToRefresh.setOnRefreshListener {
                binding.swipeToRefresh.isRefreshing = false
                homeViewModel.cityAndFood()
            }
            cityRecycler.apply {
                layoutManager = LinearLayoutManager(requireContext())
                adapter = cityAdapter
            }
            foodRecycler.apply {
                layoutManager = LinearLayoutManager(requireContext())
                adapter = foodAdapter
            }
        }

        cityAdapter.onItemClick = {
            findNavController().navigate(
                R.id.detailFragment,
                bundleOf(
                    AppConstants.DATA_TRANSFER to it.toPrettyJson(),
                    AppConstants.IS_CITY to true
                )
            )
        }
        foodAdapter.onItemClick = {
            findNavController().navigate(
                R.id.detailFragment,
                bundleOf(
                    AppConstants.DATA_TRANSFER to it.toPrettyJson(),
                    AppConstants.IS_CITY to false
                )
            )
        }
    }

    private fun apiResponse() {
        homeViewModel.foodResponse.observe(viewLifecycleOwner) {
            when (it) {
                is DataState.Loading -> {
                    binding.progressBar.show()
                }
                is DataState.Success -> {
                    binding.progressBar.hide()
                    foodAdapter.addItems(it.data.sortedBy { it.name }, true)
                }
                is DataState.Error -> {
                    binding.progressBar.hide()
                    requireContext().showAlert(it.exception.message.toString())
                    Timber.e("Error ${it.exception.message}")
                }
            }
        }

        homeViewModel.cityResponse.observe(viewLifecycleOwner) {
            when (it) {
                is DataState.Loading -> {
                    binding.progressBar.show()
                }
                is DataState.Success -> {
                    binding.progressBar.hide()
                    cityAdapter.addItems(it.data.sortedBy { it.name }, true)
                }
                is DataState.Error -> {
                    binding.progressBar.hide()
                    requireContext().showAlert(it.exception.message.toString())
                    Timber.e("Error ${it.exception.message}")
                }
            }
        }
    }
}
