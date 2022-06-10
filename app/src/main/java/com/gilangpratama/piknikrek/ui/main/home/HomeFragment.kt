package com.gilangpratama.piknikrek.ui.main.home

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.gilangpratama.piknikrek.R
import com.gilangpratama.piknikrek.data.local.entity.WisataEntity
import com.gilangpratama.piknikrek.data.remote.Result
import com.gilangpratama.piknikrek.databinding.FragmentHomeBinding
import com.gilangpratama.piknikrek.ui.adapters.WisataAdapter
import com.gilangpratama.piknikrek.ui.detail.DetailActivity
import com.gilangpratama.piknikrek.ui.main.MainViewModel
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment(), OnMapReadyCallback, WisataAdapter.OnItemClicked {

    private val mainViewModel: MainViewModel by viewModels()
    private lateinit var mMap: GoogleMap
    private lateinit var mAdapter: WisataAdapter
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity).setSupportActionBar(binding?.toolbar)
        setHasOptionsMenu(true)
        setUpView()
        val mapFragment = childFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    private fun setUpView() {
        binding?.apply {
            mAdapter = WisataAdapter(this@HomeFragment)
            rvHome.apply {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = mAdapter
            }
            mainViewModel.listWisata.observe(viewLifecycleOwner, homeObserver)
        }
    }

    private val homeObserver = Observer<Result<List<WisataEntity?>>> { result ->
        when (result) {
            is Result.Loading -> {}
            is Result.Success -> {
                mAdapter.submitList(result.data)
            }
            is Result.Error -> {
                Toast.makeText(requireActivity(), result.message, Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        mMap.uiSettings.setAllGesturesEnabled(false)
        val sydney = LatLng(-34.0, 151.0)

        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(sydney, 10f))
        mMap.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))
        //mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))
    }

    override fun onItemClicked(id: Int?) {
        val intent = Intent(requireActivity(), DetailActivity::class.java)
        intent.putExtra(DetailActivity.EXTRA_ID, id.toString())
        startActivity(intent )
    }
}