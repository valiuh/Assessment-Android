package com.midtronics.ui.countries

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.midtronics.R
import com.midtronics.databinding.FragmentCountriesBinding
import com.midtronics.ui.base.BaseFragment
import com.midtronics.ui.country.CountryFragment
import org.koin.android.ext.android.inject

class CountriesFragment: BaseFragment<List<String>>() {

    private val countriesViewModel: CountriesViewModel by inject()

    private var _binding: FragmentCountriesBinding? = null
    private val binding get() = _binding!!

    private val adapter = CountriesAdapter{ country ->
        val bundle = Bundle().apply {
            putString(CountryFragment.COUNTRY, country)
        }
        findNavController().navigate(R.id.action_CountryFragment_to_CountriesFragment, bundle)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCountriesBinding.inflate(inflater, container, false)

        binding.countriesList.layoutManager = LinearLayoutManager(context)
        binding.countriesList.adapter = adapter

        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onResume() {
        super.onResume()
        countriesViewModel.getData()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun getViewModel() = countriesViewModel

    override fun getLoaderView() = binding.loader

    override fun getRootView() = binding.root

    override fun onSuccess(data: List<String>) {
        adapter.updateData(data)
    }

}