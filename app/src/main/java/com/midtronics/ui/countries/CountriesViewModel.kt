package com.midtronics.ui.countries

import com.midtronics.domain.CountriesUseCase
import com.midtronics.ui.base.BaseViewModel

class CountriesViewModel(useCase: CountriesUseCase):
    BaseViewModel<List<String>> (useCase)