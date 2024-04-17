package com.midtronics.ui.country

import com.midtronics.data.Country
import com.midtronics.domain.CountryUseCase
import com.midtronics.ui.base.BaseViewModel

class CountryViewModel(useCase: CountryUseCase):
    BaseViewModel<Country> (useCase)