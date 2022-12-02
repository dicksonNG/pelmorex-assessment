package com.example.pelmorexassessment.ui.weather

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.viewModels
import com.example.pelmorexassessment.BaseFragment
import com.example.pelmorexassessment.R
import com.example.pelmorexassessment.base.BaseViewModel
import com.example.pelmorexassessment.databinding.FragmentWeatherBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WeatherFragment : BaseFragment() {


    private var _binding: FragmentWeatherBinding? = null
    val weatherViewModel by viewModels<WeatherViewModel>()

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    override fun provideBaseViewModel()=weatherViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWeatherBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        weatherViewModel.getWeatherC(weatherViewModel.getSelectedCity().cityCode)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setRefreshAdapter()
        initListener()
        initObserver()
    }

    fun initObserver() {
        weatherViewModel.weatherData.observe(viewLifecycleOwner) {
            binding.viewModel = it
        }
    }

    fun initListener() {
        binding.swTemp.setOnCheckedChangeListener { view, isCheck ->
            if (!isCheck) {
                weatherViewModel.setSelectedScale(WeatherViewModel.TemperatureScale.CELCIUS)
            } else {
                weatherViewModel.setSelectedScale(WeatherViewModel.TemperatureScale.FAHRENHEIT)
            }
        }
    }

    fun setRefreshAdapter() {
        val cityList = weatherViewModel.getCityList()
        val selectedTime = cityList.indexOf(weatherViewModel.getSelectedCity())
        val spinnerArrayAdapter: ArrayAdapter<WeatherViewModel.City> =
            ArrayAdapter<WeatherViewModel.City>(
                requireContext(),
                R.layout.item_city_text,
                cityList
            )
        spinnerArrayAdapter.setDropDownViewResource(R.layout.item_city_text) // The drop down view
        binding.spCityList.adapter = spinnerArrayAdapter
        binding.spCityList.setSelection(selectedTime, false)
        binding.spCityList.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View?,
                position: Int,
                id: Long
            ) {
                weatherViewModel.setSelectedCity(cityList[position])
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {

            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}