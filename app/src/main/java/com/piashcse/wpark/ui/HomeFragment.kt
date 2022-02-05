package com.piashcse.wpark.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.core.view.doOnPreDraw
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.transition.MaterialSharedAxis
import com.piashcse.wpark.R
import com.piashcse.wpark.databinding.FragmentHomeBinding
import com.piashcse.wpark.ui.adapter.CityAdapter
import com.piashcse.wpark.ui.adapter.FoodAdapter
import com.piashcse.wpark.utils.*
import com.piashcse.wpark.utils.network.DataState
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

/**
 * HomeFragment for showing japanese main cities and popular in list
 */
@AndroidEntryPoint
class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = requireNotNull(_binding)
    private val homeViewModel: HomeViewModel by viewModels()
    private val cityAdapter: CityAdapter by lazy {
        CityAdapter()
    }
    private val foodAdapter: FoodAdapter by lazy {
        FoodAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initAnimation()
        homeViewModel.cityAndFood()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        postponeEnterTransition()
        view.doOnPreDraw { startPostponedEnterTransition() }
        initView()
        apiResponse()
    }

    /**
     * Init all view and bind recyclerview
     * Implemented adapter click event
     * @see[CityAdapter]
     * @see[FoodAdapter]
     */
    private fun initView() {
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

        cityAdapter.onItemClick = { item, imageView ->
            val extras = FragmentNavigatorExtras(
                imageView to resources.getString(R.string.item_detail_image_transition_name)
            )
            findNavController().navigate(
                R.id.detailFragment,
                bundleOf(
                    AppConstants.DATA_TRANSFER to item.toPrettyJson(),
                    AppConstants.IS_CITY to true
                ), null, extras
            )
        }
        foodAdapter.onItemClick = { item, imageView ->
            val extras = FragmentNavigatorExtras(
                imageView to resources.getString(R.string.item_detail_image_transition_name)
            )
            findNavController().navigate(
                R.id.detailFragment,
                bundleOf(
                    AppConstants.DATA_TRANSFER to item.toPrettyJson(),
                    AppConstants.IS_CITY to false
                ),
                null, extras
            )
        }
    }

    /**
     * Api response for food and city
     * after trigger the livedata
     */
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

    private fun initAnimation() {
        exitTransition = MaterialSharedAxis(MaterialSharedAxis.Z, false)
        reenterTransition = MaterialSharedAxis(MaterialSharedAxis.Z, true)
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
