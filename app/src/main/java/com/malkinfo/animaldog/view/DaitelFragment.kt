package com.malkinfo.animaldog.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import com.malkinfo.animaldog.R
import com.malkinfo.animaldog.viewmodel.DetailViewModel
import kotlinx.android.synthetic.main.fragment_daitel.*


class DaitelFragment : Fragment() {

    private lateinit var viewModel :DetailViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_daitel, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(DetailViewModel::class.java)
        viewModel.fetch()
        observeViewModel()
    }
    private fun observeViewModel()
    {
        viewModel.animalLiveData.observe(viewLifecycleOwner,{
            animal ->
            animal.let {
                tvName.text = animal.dogBreed
                tvpurose.text = animal.breedFor
                tvtemper.text = animal.temperament
                tvlife.text = animal.lifeSpan
            }
        })
    }

}