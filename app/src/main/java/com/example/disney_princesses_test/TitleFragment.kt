package com.example.disney_princesses_test

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.disney_princesses_test.databinding.FragmentTitleBinding


class TitleFragment : Fragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
    {
        val binding: FragmentTitleBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_title, container, false)
        binding.playButton.setOnClickListener { view:View ->
            view.findNavController().navigate(R.id.action_titleFragment_to_questionFragment)}
        setHasOptionsMenu(true)
        return binding.root
    }

}
