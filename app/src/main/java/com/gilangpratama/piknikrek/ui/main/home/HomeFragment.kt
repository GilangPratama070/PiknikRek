package com.gilangpratama.piknikrek.ui.main.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.gilangpratama.piknikrek.R
import com.gilangpratama.piknikrek.data.local.WisataEntity
import com.gilangpratama.piknikrek.databinding.FragmentHomeBinding
import com.gilangpratama.piknikrek.ui.adapters.WisataAdapter
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class HomeFragment : Fragment(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
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
        val listWisata = ArrayList<WisataEntity>()
        listWisata.add(WisataEntity("2", "Candi Arjuna", "Wonosobo, Jawa Tengah", "https://ecs7.tokopedia.net/blog-tokopedia-com/uploads/2018/11/bromo-tengger-semeru.jpg"))
        listWisata.add(WisataEntity("3", "Candi Arjuna", "Wonosobo, Jawa Tengah", "https://ecs7.tokopedia.net/blog-tokopedia-com/uploads/2018/11/bromo-tengger-semeru.jpg"))
        listWisata.add(WisataEntity("2", "Candi Arjuna", "Wonosobo, Jawa Tengah", "https://ecs7.tokopedia.net/blog-tokopedia-com/uploads/2018/11/bromo-tengger-semeru.jpg"))
        listWisata.add(WisataEntity("2", "Candi Arjuna", "Wonosobo, Jawa Tengah", "https://ecs7.tokopedia.net/blog-tokopedia-com/uploads/2018/11/bromo-tengger-semeru.jpg"))
        listWisata.add(WisataEntity("2", "Candi Arjuna", "Wonosobo, Jawa Tengah", "https://ecs7.tokopedia.net/blog-tokopedia-com/uploads/2018/11/bromo-tengger-semeru.jpg"))
        listWisata.add(WisataEntity("2", "Candi Arjuna", "Wonosobo, Jawa Tengah", "https://ecs7.tokopedia.net/blog-tokopedia-com/uploads/2018/11/bromo-tengger-semeru.jpg"))
        listWisata.add(WisataEntity("2", "Candi Arjuna", "Wonosobo, Jawa Tengah", "https://ecs7.tokopedia.net/blog-tokopedia-com/uploads/2018/11/bromo-tengger-semeru.jpg"))
        listWisata.add(WisataEntity("2", "Candi Arjuna", "Wonosobo, Jawa Tengah", "https://ecs7.tokopedia.net/blog-tokopedia-com/uploads/2018/11/bromo-tengger-semeru.jpg"))
        listWisata.add(WisataEntity("2", "Candi Arjuna", "Wonosobo, Jawa Tengah", "https://ecs7.tokopedia.net/blog-tokopedia-com/uploads/2018/11/bromo-tengger-semeru.jpg"))
        listWisata.add(WisataEntity("2", "Candi Arjuna", "Wonosobo, Jawa Tengah", "https://ecs7.tokopedia.net/blog-tokopedia-com/uploads/2018/11/bromo-tengger-semeru.jpg"))
        listWisata.add(WisataEntity("2", "Candi Arjuna", "Wonosobo, Jawa Tengah", "https://ecs7.tokopedia.net/blog-tokopedia-com/uploads/2018/11/bromo-tengger-semeru.jpg"))
        listWisata.add(WisataEntity("2", "Candi Arjuna", "Wonosobo, Jawa Tengah", "https://ecs7.tokopedia.net/blog-tokopedia-com/uploads/2018/11/bromo-tengger-semeru.jpg"))
        listWisata.add(WisataEntity("2", "Candi Arjuna", "Wonosobo, Jawa Tengah", "https://ecs7.tokopedia.net/blog-tokopedia-com/uploads/2018/11/bromo-tengger-semeru.jpg"))
        listWisata.add(WisataEntity("2", "Candi Arjuna", "Wonosobo, Jawa Tengah", "https://ecs7.tokopedia.net/blog-tokopedia-com/uploads/2018/11/bromo-tengger-semeru.jpg"))
        listWisata.add(WisataEntity("2", "Candi Arjuna", "Wonosobo, Jawa Tengah", "https://ecs7.tokopedia.net/blog-tokopedia-com/uploads/2018/11/bromo-tengger-semeru.jpg"))
        listWisata.add(WisataEntity("2", "Candi Arjuna", "Wonosobo, Jawa Tengah", "https://ecs7.tokopedia.net/blog-tokopedia-com/uploads/2018/11/bromo-tengger-semeru.jpg"))

        binding?.apply {
            val mAdapter = WisataAdapter()
            rvHome.apply {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = mAdapter
            }
            mAdapter.submitList(listWisata)
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
}