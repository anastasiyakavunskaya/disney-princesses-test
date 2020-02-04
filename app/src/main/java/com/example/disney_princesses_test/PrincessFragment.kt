package com.example.disney_princesses_test


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.disney_princesses_test.databinding.FragmentPrincessBinding


class PrincessFragment : Fragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val binding: FragmentPrincessBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_princess, container, false)
        return binding.root
    }

}
