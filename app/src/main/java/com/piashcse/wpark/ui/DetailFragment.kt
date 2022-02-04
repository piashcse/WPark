package com.piashcse.wpark.ui

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.doOnPreDraw
import androidx.transition.ArcMotion
import com.google.android.material.transition.MaterialContainerTransform
import com.google.android.material.transition.MaterialFadeThrough
import com.piashcse.wpark.R
import com.piashcse.wpark.data.model.CityItem
import com.piashcse.wpark.data.model.FoodItem
import com.piashcse.wpark.databinding.FragmentDetailBinding
import com.piashcse.wpark.utils.AppConstants
import com.piashcse.wpark.utils.fromPrettyJson
import com.piashcse.wpark.utils.loadImage

class DetailFragment : Fragment() {
    private lateinit var binding: FragmentDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enterTransition = MaterialFadeThrough().apply {
            removeTarget(R.id.image_view)
        }
        sharedElementEnterTransition = MaterialContainerTransform().apply {
            drawingViewId = R.id.homeFragment
            setPathMotion(ArcMotion())
            scrimColor = Color.TRANSPARENT
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        postponeEnterTransition()
        view.doOnPreDraw { startPostponedEnterTransition() }
        initView()
    }
    /**
     * Init all view and receiving data from [HomeFragment]
     */
    private fun initView() {
        val isCity = arguments?.getBoolean(AppConstants.IS_CITY) ?: false
        if (isCity) {
            arguments?.getString(AppConstants.DATA_TRANSFER)?.fromPrettyJson<CityItem>()?.let {
                binding.apply {
                    title.text = it.name
                    description.text = it.description
                    image.loadImage(it.image)
                }
            }
        } else {
            arguments?.getString(AppConstants.DATA_TRANSFER)?.fromPrettyJson<FoodItem>()?.let {
                binding.apply {
                    title.text = it.name
                    image.loadImage(it.image)
                }
            }
        }
    }
}