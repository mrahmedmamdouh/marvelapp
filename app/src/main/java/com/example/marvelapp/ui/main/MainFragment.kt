package com.example.marvelapp.ui.main

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log.d
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.marvelapp.R
import com.example.marvelapp.data.model.Character
import com.example.marvelapp.databinding.MainFragmentBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainFragment : Fragment(R.layout.main_fragment), CharacterCallBack {


    private lateinit var binding: MainFragmentBinding
    private val viewModel by activityViewModels<MainViewModel>()

    @SuppressLint("LogNotTimber")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = MainFragmentBinding.bind(view)
        (activity as AppCompatActivity).supportActionBar?.show()
        val characterAdapter = CharacterAdapter(this)

        binding.apply {
            binding.list.layoutManager = LinearLayoutManager(requireContext())
            binding.list.adapter = characterAdapter.withLoadStateFooter(CharacterLoadStateAdapter())
            binding.searchView.setOnSearchClickListener {
                binding.imageTitle.visibility = View.GONE
            }
            binding.searchView.setOnCloseListener {
                binding.imageTitle.visibility = View.VISIBLE
                viewModel.getData("")
                false
            }

            characterAdapter.addLoadStateListener {
                d(TAG, "itemCount: " + characterAdapter.itemCount)
            }

            binding.searchView.setOnQueryTextListener(object :
                androidx.appcompat.widget.SearchView.OnQueryTextListener {

                override fun onQueryTextChange(newText: String): Boolean {
                    MainScope().launch {
                        delay(200)
                        newText.let {
                            if (newText.isNotEmpty()) {
                                viewModel.getData(it)
                            }
                        }
                    }

                    return false
                }

                override fun onQueryTextSubmit(query: String): Boolean {
                    return false
                }

            })
        }

        viewModel.character.observe(viewLifecycleOwner, Observer { character ->
            characterAdapter.submitData(viewLifecycleOwner.lifecycle, character)
        })
    }

    companion object {
        private const val TAG = "MainFragment"
    }

    override fun onUserClick(character: Character) {
        val bundle = Bundle()
        bundle.putParcelable("character", character)
        Navigation.findNavController(binding.root)
            .navigate(R.id.action_mainFragment_to_detailsFragment, bundle)
    }
}