package com.gilangpratama.piknikrek.ui.result

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.gilangpratama.piknikrek.data.local.entity.WisataEntity
import com.gilangpratama.piknikrek.data.remote.Result
import com.gilangpratama.piknikrek.databinding.ActivityResultBinding
import com.gilangpratama.piknikrek.ui.adapters.WisataAdapter
import com.gilangpratama.piknikrek.ui.detail.DetailActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ResultActivity : AppCompatActivity(), WisataAdapter.OnItemClicked {

    private val resultViewModel: ResultViewModel by viewModels()
    private lateinit var binding: ActivityResultBinding
    private lateinit var resultAdapter: WisataAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpView()
    }

    private fun setUpView() {
        binding.apply {
            resultAdapter = WisataAdapter(this@ResultActivity)
            val query = intent.extras?.getString(QUERY)
            if (!query.isNullOrEmpty()) {
                resultViewModel.searchWisata(query).observe(this@ResultActivity, resultObserver)
            }
            rvResult.layoutManager = LinearLayoutManager(this@ResultActivity)
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
                    Toast.makeText(this, "Data tidak ditemukan", Toast.LENGTH_SHORT).show()
                }
            }
            is Result.Error -> {
                Toast.makeText(this, result.message, Toast.LENGTH_SHORT).show()
            }
        }
    }

    companion object {
        const val QUERY = "query"
    }

    override fun onItemClicked(id: Int?) {
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra(DetailActivity.EXTRA_ID, id.toString())
        startActivity(intent)
    }
}