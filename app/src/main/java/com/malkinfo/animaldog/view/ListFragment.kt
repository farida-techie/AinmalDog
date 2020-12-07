package com.malkinfo.animaldog.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import com.malkinfo.animaldog.R
import com.malkinfo.animaldog.viewmodel.ListViewModel
import kotlinx.android.synthetic.main.fragment_list.*


class ListFragment : Fragment() {

    private lateinit var viewModel:ListViewModel
    private val animalListAdap = AnimalListAdapter(arrayListOf())
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(ListViewModel::class.java)
        viewModel.refresh()
        /*set Layout And Adapter*/
        ainmalRecycler.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = animalListAdap
        }
        swipL.setOnRefreshListener {
            ainmalRecycler.visibility = View.GONE
            textView.visibility = View.GONE
            progressBar.visibility = View.VISIBLE
            viewModel.refresh()
            swipL.isRefreshing = false

        }
        observeViewModel(view)
    }
    fun observeViewModel(v:View){
        viewModel.animal.observe(viewLifecycleOwner,{
            animal ->
            animal.let {
                ainmalRecycler.visibility = View.VISIBLE
                animalListAdap.updateAnimal(animal)
            }
        })
        viewModel.animalLoadError.observe(viewLifecycleOwner,{
            isError ->
            isError.let {
                textView.visibility = if (it) View.VISIBLE else View.GONE
            }
        })
        viewModel.loding.observe(viewLifecycleOwner,{
            isLoding ->
            isLoding.let {
                progressBar.visibility = if (it)View.VISIBLE else View.GONE
                if (it){
                    textView.visibility = View.GONE
                    ainmalRecycler.visibility = View.GONE
                }
            }
        })
    }

}