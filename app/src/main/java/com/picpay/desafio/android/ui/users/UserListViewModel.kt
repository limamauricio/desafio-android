package com.picpay.desafio.android.ui.users

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.picpay.desafio.android.data.repository.UserRepository

class UserListViewModel @ViewModelInject constructor(
    repository: UserRepository
) : ViewModel() {

    val users = repository.getUsers()
}