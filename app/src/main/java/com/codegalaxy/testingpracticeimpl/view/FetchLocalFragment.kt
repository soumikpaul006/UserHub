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
import com.codegalaxy.testingpracticeimpl.databinding.FragmentFetchLocalBinding
import com.codegalaxy.testingpracticeimpl.viewmodel.UserViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FetchLocalFragment : Fragment() {

    private lateinit var binding: FragmentFetchLocalBinding
    private lateinit var viewModel: UserViewModel
    private lateinit var userAdapter: UserAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[UserViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFetchLocalBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        setupObservers()
    }

    private fun setupRecyclerView() {
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
    }

    private fun setupObservers() {

        viewModel.localUsers.observe(viewLifecycleOwner) { users ->
            if (users.isEmpty()) {
                viewModel.setError("No data found")
            } else {

                userAdapter = UserAdapter(users) { clickedUser ->

                    val bundle = Bundle().apply {
                        putInt("user_id", clickedUser.id)
                    }

                    parentFragmentManager.beginTransaction()
                        .replace(R.id.container, DetailScreenFragment().apply {
                            arguments = bundle
                        })
                        .addToBackStack(null)
                        .commit()
                }

                binding.recyclerView.adapter = userAdapter
            }
        }

        viewModel.loading.observe(viewLifecycleOwner) { isLoading ->
            binding.progressBar.isVisible = isLoading
        }

        viewModel.error.observe(viewLifecycleOwner) { error ->
            Toast.makeText(context, error, Toast.LENGTH_SHORT).show()
        }
    }

}