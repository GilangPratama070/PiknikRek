package com.gilangpratama.piknikrek.ui.onboard

import android.content.Intent
import android.graphics.drawable.Drawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import com.gilangpratama.piknikrek.R
import com.gilangpratama.piknikrek.databinding.FragmentItemBinding
import com.gilangpratama.piknikrek.ui.main.MainActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ItemFragment : Fragment() {

    private var _binding: FragmentItemBinding? = null
    private val binding get() = _binding
    private val onBoardingViewModel: OnBoardingViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentItemBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpView()
    }

    private fun setUpView() {
        binding?.apply {
            when (arguments?.getInt(ARG_SECTION_POSITION, 0)) {
                0 -> setUpOnFirstView()
                1 -> setUpOnSecView()
                2 -> setUpOnThirdView()
                3 -> setUpOnFourthView()
            }
        }
    }

    private fun setUpOnFirstView() {
        val image = ContextCompat.getDrawable(requireActivity(), R.drawable.onboard_1)
        val title = "Selamat Datang"
        val desc = "Mungkinkah menambah tinggi badan saat sudah dewasa? ada berberapa cara meninggikan badan orang dewasa"
        image?.triggerToView(title, desc)
        binding?.btnLewati?.setOnClickListener {
            onBoardingViewModel.setIsFirstTime(false)
        }
    }

    private fun setUpOnSecView() {
        binding?.apply {
            tvOnboardTitle.visibility = View.GONE
            tvOnboardDetail.visibility = View.GONE
            tvForSecThird.visibility = View.VISIBLE
            btnLewati.visibility = View.GONE
            val image = ContextCompat.getDrawable(requireActivity(), R.drawable.onboard_2)
            val title = "Ambil gambar dan dapatkan sejarahnya,"
            ivOnboard.setImageDrawable(image)
            tvForSecThird.text = title
        }
    }

    private fun setUpOnThirdView() {
        binding?.apply {
            tvOnboardTitle.visibility = View.GONE
            tvOnboardDetail.visibility = View.GONE
            tvForSecThird.visibility = View.VISIBLE
            btnLewati.visibility = View.GONE
            val image = ContextCompat.getDrawable(requireActivity(), R.drawable.onboard_3)
            val title = "Cepat dan mudah digunakan"
            ivOnboard.setImageDrawable(image)
            tvForSecThird.text = title
        }
    }

    private fun setUpOnFourthView() {
        binding?.apply {
            tvOnboardTitle.visibility = View.GONE
            tvOnboardDetail.visibility = View.GONE
            tvOnboard4th.visibility = View.VISIBLE
            tvOnboardDetail4th.visibility = View.VISIBLE
            btnMulai.visibility = View.VISIBLE
            btnLewati.visibility = View.GONE
            tvOnboardDetail4th.text = "Mari kita mulai perjalanannya"
            val image = ContextCompat.getDrawable(requireActivity(), R.drawable.onboard_4)
            val title = "Cepat dan mudah digunakan"
            ivOnboard.setImageDrawable(image)
            tvForSecThird.text = title
            btnMulai.setOnClickListener {
                onBoardingViewModel.setIsFirstTime(false)
            }
        }
    }

    private fun Drawable?.triggerToView(title: String, desc: String) = binding?.apply {
        ivOnboard.setImageDrawable(this@triggerToView)
        tvOnboardTitle.text = title
        tvOnboardDetail.text = desc
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        const val ARG_SECTION_POSITION = "position"
    }
}