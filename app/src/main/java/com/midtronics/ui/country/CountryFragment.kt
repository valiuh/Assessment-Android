package com.midtronics.ui.country

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.midtronics.data.Country
import com.midtronics.databinding.FragmentCountryBinding
import com.midtronics.ui.base.BaseFragment
import org.koin.android.ext.android.inject

class CountryFragment : BaseFragment<Country>() {

    companion object {
        const val COUNTRY = "COUNTRY"
    }

    private val countryViewModel: CountryViewModel by inject()

    private var countryName: String? = null

    private var _binding: FragmentCountryBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let { bundle ->
            countryName = bundle.getString(COUNTRY)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCountryBinding.inflate(inflater, container, false)

        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onResume() {
        super.onResume()
        countryName?.let { countryName ->
            countryViewModel.getData(countryName)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun getViewModel() = countryViewModel

    override fun getRootView() = binding.root

    override fun getLoaderView() = binding.loader

    override fun onSuccess(data: Country) {
        binding.loader.visibility = View.GONE

        binding.countryName.text = data.name.common
        binding.countryCapital.text = data.capital?.first()
        binding.countryPopulation.text = data.population
        binding.countryArea.text = data.area
        binding.countryRegion.text = data.region
        binding.countrySubregion.text = data.subregion
    }
}