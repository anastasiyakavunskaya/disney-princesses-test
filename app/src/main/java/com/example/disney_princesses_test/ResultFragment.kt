package com.example.disney_princesses_test

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.databinding.DataBindingUtil
import androidx.navigation.ui.AppBarConfiguration
import com.example.disney_princesses_test.databinding.FragmentResultBinding
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController


class ResultFragment : Fragment() {

    private val listOfPrincesses: MutableList<Princess> = mutableListOf(
        Princess(name = "БЕЛОСНЕЖКА", info = R.string.princess01, img = R.drawable.princess01),
        Princess(name = "ПОКАХОНТАС", info = R.string.princess02, img = R.drawable.princess02),
        Princess(name = "БЕЛЛЬ", info = R.string.princess03, img = R.drawable.princess03),
        Princess(name = "ЖАСМИН", info = R.string.princess04, img = R.drawable.princess04),
        Princess(name = "ЗОЛУШКА", info = R.string.princess05, img = R.drawable.princess05),
        Princess(name = "АРИЭЛЬ", info = R.string.princess06, img =R.drawable.princess06)
    )

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {

        val binding: FragmentResultBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_result, container, false)
        val args = ResultFragmentArgs.fromBundle(arguments!!)


        binding.resultName.text = "ТЫ - " + listOfPrincesses[args.result].name
        binding.resultInfo.text = getString(listOfPrincesses[args.result].info)
        binding.resultImage.setImageResource(listOfPrincesses[args.result].img)
        (activity as AppCompatActivity).supportActionBar!!.title = listOfPrincesses[args.result].name

        binding.shareResultButton.setOnClickListener { view: View ->
            startActivity(getShareIntent())
        }

        binding.homeButton.setOnClickListener { view: View ->
            view.findNavController().navigate(ResultFragmentDirections.actionResultFragmentToTitleFragment())
        }
        return binding.root
    }
    private fun getShareIntent() : Intent {
        val args =  ResultFragmentArgs.fromBundle(arguments!!)
        val shareIntent = Intent(Intent.ACTION_SEND)
        shareIntent.setType("text/plain")
            .putExtra(Intent.EXTRA_TEXT,"Я "+listOfPrincesses[args.result].name)
        return shareIntent
    }
}

data class Princess(
    val name: String,
    val info: Int,
    val img: Int
)
