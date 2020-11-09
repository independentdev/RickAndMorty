package uk.co.sooce.rickandmorty.ui.profile.list.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import uk.co.sooce.rickandmorty.data.model.MovieCharacter
import uk.co.sooce.rickandmorty.databinding.ItemProfileListBinding

class ProfileListAdapter(private val listener: ItemListener) : RecyclerView.Adapter<ProfileListItemViewHolder>() {

    interface ItemListener {
        fun onClickedCharacter(characterId: Int)
    }

    private val items = ArrayList<MovieCharacter>()

    fun setItems(items: ArrayList<MovieCharacter>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProfileListItemViewHolder {
        val binding: ItemProfileListBinding = ItemProfileListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProfileListItemViewHolder(binding, listener)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ProfileListItemViewHolder, position: Int) = holder.bind(items[position])
}