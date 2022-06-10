package com.gilangpratama.piknikrek.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel
import com.gilangpratama.piknikrek.data.local.entity.WisataEntity
import com.gilangpratama.piknikrek.data.remote.Result
import com.gilangpratama.piknikrek.databinding.ActivityDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailActivity : AppCompatActivity() {

    private val detailViewModel: DetailViewModel by viewModels()
    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpView()
    }

    private fun setUpView() {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding.apply {
            val id = intent.extras?.getString(EXTRA_ID)
            id?.let {
                detailViewModel.getDetailWisata(it.toInt()).observe(this@DetailActivity, detailObserver)
            }
        }
    }

    private val detailObserver = Observer<Result<List<WisataEntity?>>> { result ->
        when (result) {
            is Result.Loading -> {}
            is Result.Success -> {
                val data = result.data?.get(0)
                populateDetail(data)
                val slideModel : ArrayList<SlideModel> = arrayListOf()
                data?.images?.forEach {
                    slideModel.add(SlideModel(it))
                }
                binding.imageSlider.setImageList(slideModel , ScaleTypes.CENTER_CROP)
                binding.imageSlider.startSliding()
            }
            is Result.Error -> {
                Toast.makeText(this, result.message, Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun populateDetail(data: WisataEntity?) {
        binding.apply {
            supportActionBar?.title = data?.name
            tvAlamat.text = data?.address
            tvDesc.text = data?.description
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        onBackPressed()
        return super.onOptionsItemSelected(item)
    }

    companion object {
        const val EXTRA_ID = "id"
    }
}