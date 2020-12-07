package com.malkinfo.animaldog.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.malkinfo.animaldog.R
import com.malkinfo.animaldog.model.AnimalBreed
import com.malkinfo.animaldog.util.getProgressDrawable
import com.malkinfo.animaldog.util.loadImage
import kotlinx.android.synthetic.main.animal_item.view.*

class AnimalListAdapter(val animalList:ArrayList<AnimalBreed>):
    RecyclerView.Adapter<AnimalListAdapter.AnimalViewHolder>()
{
    class AnimalViewHolder(var view:View):RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnimalViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.animal_item,parent,false)
        return AnimalViewHolder(view)
    }

    override fun onBindViewHolder(holder: AnimalViewHolder, position: Int) {
       holder.view.titleHed.text = animalList[position].dogBreed
        holder.view.subTitle.text = animalList[position].lifeSpan
        holder.view.setOnClickListener {
            Navigation.findNavController(it).navigate(ListFragmentDirections.gotoDaitelFragment())
        }
        holder.view.animalImage.loadImage(animalList[position].imageUri!!, getProgressDrawable(holder.view.animalImage.context))
    }

    override fun getItemCount() = animalList.size

    fun updateAnimal(newAnimalList:List<AnimalBreed>){
        animalList.clear()
        animalList.addAll(newAnimalList)
        notifyDataSetChanged()
    }

}