package com.gilangpratama.piknikrek.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.navArgs
import com.gilangpratama.piknikrek.R
import com.gilangpratama.piknikrek.data.local.DetailEntity
import com.gilangpratama.piknikrek.data.local.WisataEntity
import com.gilangpratama.piknikrek.data.remote.Result
import com.gilangpratama.piknikrek.databinding.ActivityDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailActivity : AppCompatActivity() {

    private val detailViewModel: DetailViewModel by viewModels()
    private val detailArgs: DetailActivityArgs by navArgs()
    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpView()
    }

    private fun setUpView() {
        binding.apply {
            val id = detailArgs.id?.toInt()
            id?.let {
                detailViewModel.getDetailWisata(it).observe(this@DetailActivity, detailObserver)
            }
        }
    }

    private val detailObserver = Observer<Result<List<WisataEntity?>>> { result ->
        when (result) {
            is Result.Loading -> {}
            is Result.Success -> {
                populateDetail(result.data?.get(0))
            }
            is Result.Error -> {
                Toast.makeText(this, result.message, Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun populateDetail(data: WisataEntity?) {
        binding.apply {
            tvName.text = data?.name
            tvAlamat.text = data?.address
            tvDesc.text = data?.description
        }
    }
}