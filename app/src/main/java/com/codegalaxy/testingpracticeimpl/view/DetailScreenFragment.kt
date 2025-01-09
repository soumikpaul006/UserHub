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
import com.codegalaxy.testingpracticeimpl.CommentAdapter
import com.codegalaxy.testingpracticeimpl.R
import com.codegalaxy.testingpracticeimpl.databinding.FragmentDetailScreenBinding
import com.codegalaxy.testingpracticeimpl.viewmodel.UserViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class DetailScreenFragment : Fragment() {

    private lateinit var binding: FragmentDetailScreenBinding
    private lateinit var viewModel: UserViewModel
    private lateinit var commentsAdapter: CommentAdapter
    private var userId: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[UserViewModel::class.java]
        userId = arguments?.getInt("user_id") ?: -1
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailScreenBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        setupObservers()
        viewModel.fetchComments(userId)
    }

    private fun setupRecyclerView() {
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
    }

    private fun setupObservers() {
        viewModel.comments.observe(viewLifecycleOwner) { comments ->

            if (comments.isEmpty()) {
                viewModel.setError("No comments found")
            } else {
                commentsAdapter = CommentAdapter(comments)
                binding.recyclerView.adapter = commentsAdapter
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