package com.example.marvelapp.ui.detailsFragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.marvelapp.R
import com.example.marvelapp.data.model.Character
import com.example.marvelapp.databinding.CharacterDetailsFragmentBinding
import com.example.marvelapp.ui.main.MainViewModel

class DetailsFragment : Fragment(R.layout.character_details_fragment) {

    private lateinit var binding: CharacterDetailsFragmentBinding
    private val viewModel by activityViewModels<MainViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = CharacterDetailsFragmentBinding.bind(view)

        val character: Character = requireArguments().getParcelable("character")!!

        binding.apply {
            name.text = character.name
            description.text = character.description

            Glide.with(requireContext())
                .load(character.thumbnail.path + "/landscape_xlarge." + character.thumbnail.extension)
                .placeholder(R.drawable.image_placeholder)
                .error(R.drawable.image_placeholder)
                .transition(DrawableTransitionOptions.withCrossFade())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(image)

            binding.comicsList.layoutManager = LinearLayoutManager(requireContext())
            val comicsAdapter = DetailsAdapter("Comics")
            binding.comicsList.adapter = comicsAdapter

            binding.eventsList.layoutManager = LinearLayoutManager(requireContext())
            val eventsAdapter = DetailsAdapter("Events")
            binding.eventsList.adapter = eventsAdapter

            binding.seriesList.layoutManager = LinearLayoutManager(requireContext())
            val seriesAdapter = DetailsAdapter("Series")
            binding.seriesList.adapter = seriesAdapter

            binding.storiesList.layoutManager = LinearLayoutManager(requireContext())
            val storiesAdapter = DetailsAdapter("Stories")
            binding.storiesList.adapter = storiesAdapter

        }

//        viewModel.character.observe(viewLifecycleOwner, Observer {
//            comicsAdapter.submitData().submitData(viewLifecycleOwner.lifecycle, it)
//        })
    }
}