package com.midtronics.ui.profile

import com.midtronics.data.Profile
import com.midtronics.domain.ProfileUseCase
import com.midtronics.ui.base.BaseViewModel

class ProfileViewModel(
    useCase: ProfileUseCase
) : BaseViewModel<Profile>(useCase)

