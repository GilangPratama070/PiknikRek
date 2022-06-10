package com.gilangpratama.piknikrek.ui.search

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.gilangpratama.piknikrek.data.local.entity.WisataEntity
import com.gilangpratama.piknikrek.data.remote.Result
import com.gilangpratama.piknikrek.databinding.FragmentSearchBinding
import com.gilangpratama.piknikrek.ui.adapters.WisataAdapter
import com.gilangpratama.piknikrek.ui.result.ResultActivity
import com.gilangpratama.piknikrek.ui.result.ResultViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment : Fragment(), WisataAdapter.OnItemClicked {

    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding
    private val resultViewModel: ResultViewModel by viewModels()
    private lateinit var resultAdapter: WisataAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpView()
    }

    private fun setUpView() {
        binding?.apply {
            resultAdapter = WisataAdapter(this@SearchFragment)
            val query = arguments?.getString(ResultActivity.QUERY)
            Log.d("QUERYTES", "setUpView: $query")
            if (!query.isNullOrEmpty()) {
                resultViewModel.searchWisata(query).observe(viewLifecycleOwner, resultObserver)
            }
            rvResult.layoutManager = LinearLayoutManager(requireActivity())
            rvResult.setHasFixedSize(true)
            rvResult.adapter = resultAdapter
        }
    }

    private val resultObserver = Observer<Result<List<WisataEntity?>>> { result ->
        when (result) {
            is Result.Loading -> {}
            is Result.Success -> {
                if (!result.data.isNullOrEmpty()) {
                    resultAdapter.submitList(result.data)
                } else {
                    Toast.makeText(requireActivity(), "Data tidak ditemukan", Toast.LENGTH_SHORT).show()
                }
            }
            is Result.Error -> {
                Toast.makeText(requireActivity(), result.message, Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onItemClicked(id: Int?) {

    }
}