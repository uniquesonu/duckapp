package com.example.instagram.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.instagram.Models.Reel
import com.example.instagram.adapters.ReelAdapter
import com.example.instagram.databinding.FragmentReelBinding
import com.example.instagram.utils.REEL
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.toObject
import com.google.firebase.ktx.Firebase


class reelFragment : Fragment() {
    private lateinit var binding: FragmentReelBinding
    lateinit var adapter : ReelAdapter
    var reelList=ArrayList<Reel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
       binding=FragmentReelBinding.inflate(inflater, container, false)
        adapter=ReelAdapter(requireContext(),reelList)
        binding.viewPager.adapter=adapter
        Firebase.firestore.collection(REEL).get().addOnSuccessListener {
            var tempList=ArrayList<Reel>()
            reelList.clear()
            for (i in it.documents)
            {
                var reel=i.toObject<Reel>()!!
                tempList.add(reel)!!

            }
            reelList.addAll(tempList)
            reelList.reverse()
            adapter.notifyDataSetChanged()
        }



        return binding.root
    }

    companion object {


            }
    }
