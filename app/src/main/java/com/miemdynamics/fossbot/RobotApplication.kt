package com.miemdynamics.fossbot

import android.app.Application
import com.miemdynamics.fossbot.data.db.ProgramDao
import com.miemdynamics.fossbot.data.db.ProgramDatabase
import com.miemdynamics.fossbot.data.repo.ProgramRepository
import com.miemdynamics.fossbot.data.repo.ProgramRepositoryImpl
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton

class RobotApplication: Application(), KodeinAware {
    override val kodein by Kodein.lazy {
        import(androidXModule(this@RobotApplication))

        bind<ProgramDatabase>() with singleton { ProgramDatabase(instance()) }
        bind<ProgramDao>() with singleton { instance<ProgramDatabase>().programDao() }
        bind<ProgramRepository>() with singleton { ProgramRepositoryImpl(instance()) }
    }
}