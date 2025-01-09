package com.codegalaxy.testingpracticeimpl.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.codegalaxy.testingpracticeimpl.R
import com.codegalaxy.testingpracticeimpl.UserAdapter
import com.codegalaxy.testingpracticeimpl.databinding.FragmentHomeBinding
import com.codegalaxy.testingpracticeimpl.viewmodel.UserViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupClickListeners()
    }

    private fun setupClickListeners() {
        binding.btnFetchLocal.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.container, FetchLocalFragment())
                .addToBackStack(null)
                .commit()
        }

        binding.btnFetchRemote.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.container, FetchRemoteFragment())
                .addToBackStack(null)
                .commit()
        }
    }

}