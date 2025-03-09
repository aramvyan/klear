package app.dbalava.klear.di

import eu.terrainc.ui.MainViewModel
import eu.terrainc.ui.screens.auth.LoginViewModel
import eu.terrainc.ui.screens.profile.PhotoViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel { LoginViewModel() }
    viewModel { MainViewModel() }
    viewModel { PhotoViewModel() }
}