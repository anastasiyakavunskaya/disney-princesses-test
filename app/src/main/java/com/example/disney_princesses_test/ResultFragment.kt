package com.example.disney_princesses_test

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.disney_princesses_test.databinding.FragmentResultBinding



class ResultFragment : Fragment() {

    private val listOfPrincesses: MutableList<Princess> = mutableListOf(
        Princess(name = "Белоснежка", info = R.string.princess01, img = R.drawable.princess01),
        Princess(name = "Покахонтас", info = R.string.princess02, img = R.drawable.princess02),
        Princess(name = "Белль", info = R.string.princess03, img = R.drawable.princess03),
        Princess(name = "Жасмин", info = R.string.princess04, img = R.drawable.princess04),
        Princess(name = "Аврора", info = R.string.princess05, img = R.drawable.princess05),
        Princess(name = "Ариэль", info = R.string.princess06, img =R.drawable.princess06)
    )

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {

        val binding: FragmentResultBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_result, container, false)
        val args = ResultFragmentArgs.fromBundle(arguments!!)


        binding.resultName.text = listOfPrincesses[args.result].name
        binding.resultInfo.text = getString(listOfPrincesses[args.result].info)
        binding.resultImage.setImageResource(listOfPrincesses[args.result].img)

        return binding.root
    }
}

data class Princess(
    val name: String,
    val info: Int,
    val img: Int
)
