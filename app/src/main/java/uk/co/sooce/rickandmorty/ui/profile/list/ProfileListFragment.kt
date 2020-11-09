package uk.co.sooce.rickandmorty.ui.profile.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import uk.co.sooce.rickandmorty.R
import uk.co.sooce.rickandmorty.base.data.model.Resource
import uk.co.sooce.rickandmorty.databinding.FragmentProfileListBinding
import uk.co.sooce.rickandmorty.ui.profile.list.adapter.ProfileListAdapter
import uk.co.sooce.rickandmorty.util.autoCleared

@AndroidEntryPoint
class ProfileListFragment : Fragment(), ProfileListAdapter.ItemListener {

    private var binding: FragmentProfileListBinding by autoCleared()
    private val viewModel: ProfileListViewModel by viewModels()
    private lateinit var adapter: ProfileListAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentProfileListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        setupObservers()
    }

    private fun setupRecyclerView() {
        adapter = ProfileListAdapter(this)
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = adapter
    }

    private fun setupObservers() {
        viewModel.characters.observe(viewLifecycleOwner, Observer {
            when (it.status) {
                Resource.Status.SUCCESS -> {
                    binding.progressBar.visibility = View.GONE
                    if (!it.data.isNullOrEmpty()) adapter.setItems(ArrayList(it.data))
                }
                Resource.Status.ERROR ->
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()

                Resource.Status.LOADING ->
                    binding.progressBar.visibility = View.VISIBLE
            }
        })
    }

    override fun onClickedCharacter(characterId: Int) {
        findNavController().navigate(
            R.id.action_profileListFragment_to_profileDetailFragment,
            bundleOf("id" to characterId)
        )
    }
}