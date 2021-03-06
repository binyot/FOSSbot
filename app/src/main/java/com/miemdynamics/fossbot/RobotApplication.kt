package com.miemdynamics.fossbot

import android.app.Application
import androidx.lifecycle.ViewModelProvider
import com.miemdynamics.fossbot.data.db.ProgramDao
import com.miemdynamics.fossbot.data.db.ProgramDatabase
import com.miemdynamics.fossbot.data.provider.PreferenceProvider
import com.miemdynamics.fossbot.data.provider.PreferenceProviderImpl
import com.miemdynamics.fossbot.data.repo.ProgramRepository
import com.miemdynamics.fossbot.data.repo.ProgramRepositoryImpl
import com.miemdynamics.fossbot.internal.ViewModelFactory
import com.miemdynamics.fossbot.internal.bindViewModel
import com.miemdynamics.fossbot.network.connection.BluetoothConnection
import com.miemdynamics.fossbot.network.connection.Connection
import com.miemdynamics.fossbot.network.connection.TcpConnection
import com.miemdynamics.fossbot.network.service.RobotService
import com.miemdynamics.fossbot.network.service.RobotServiceImpl
import com.miemdynamics.fossbot.ui.home.HomeViewModel
import com.miemdynamics.fossbot.ui.program.ProgramViewModel
import com.miemdynamics.fossbot.ui.control.ControlViewModel
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.direct
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

const val APP_PREF_NAME = "RobotApplication"

/**
 * Used for dependency injection
 */
class RobotApplication: Application(), KodeinAware {
    /**
     * Kodein context.
     * Kodein should be imported here.
     */
    override val kodein by Kodein.lazy {
        import(androidXModule(this@RobotApplication))
        import(dataModule)
        import(networkModule)
        import(preferencesModule)
        import(viewModelModule)
    }

    private val preferencesModule = Kodein.Module(name="preferencesModule") {
        bind<PreferenceProvider>() with singleton { PreferenceProviderImpl(instance()) }
    }

    private val networkModule = Kodein.Module(name="networkModule") {
        bind<Connection>() with provider { TcpConnection() }
        bind<RobotService>() with singleton { RobotServiceImpl(instance(), instance()) }
    }

    private val dataModule = Kodein.Module(name = "dataModule") {
        bind<ProgramDatabase>() with singleton { ProgramDatabase(instance()) }
        bind<ProgramDao>() with singleton { instance<ProgramDatabase>().programDao() }
        bind<ProgramRepository>() with singleton { ProgramRepositoryImpl(instance()) }
    }

    private val viewModelModule = Kodein.Module(name = "viewModelModule") {
        bind<ViewModelProvider.Factory>() with singleton {
            ViewModelFactory(kodein.direct)
        }
        bindViewModel<HomeViewModel>() with provider {
            HomeViewModel(instance(), instance())
        }
        bindViewModel<ProgramViewModel>() with provider {
            ProgramViewModel(instance(), instance(), instance())
        }
        bindViewModel<ControlViewModel>() with provider {
            ControlViewModel(instance(), instance())
        }
    }
}