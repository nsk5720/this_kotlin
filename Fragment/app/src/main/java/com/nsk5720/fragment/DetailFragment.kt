package com.nsk5720.fragment

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.nsk5720.fragment.databinding.FragmentDetailBinding

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class DetailFragment : Fragment() {
    lateinit var mainActivity: MainActivity

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mainActivity = context as MainActivity
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        // return inflater.inflate(R.layout.fragment_detail, container, false)
        val binding = FragmentDetailBinding.inflate(inflater, container, false)
        binding.btnBack.setOnClickListener{ mainActivity.goBack()}
        return binding.root

    }
}