package com.miemdynamics.fossbot.internal

import android.content.Context
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.direct
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance

/**
 * Helper function for Kodein'ed [ViewModel]s
 */
inline fun <reified T : ViewModel> Kodein.Builder.bindViewModel(overrides: Boolean? = null): Kodein.Builder.TypeBinder<T> {
    return bind<T>(T::class.java.simpleName, overrides)
}

/**
 * Helper function for Kodein'ed [ViewModel]s
 */
inline fun <reified VM : ViewModel, T> T.viewModel(): Lazy<VM> where T : KodeinAware, T : FragmentActivity {
    return lazy { ViewModelProviders.of(this, direct.instance()).get(VM::class.java) }
}

/**
 * Helper function for Kodein'ed [ViewModel]s
 */
inline fun <reified VM : ViewModel, T> T.viewModel(): Lazy<VM> where T : KodeinAware, T : Fragment {
    return lazy { ViewModelProviders.of(this, direct.instance()).get(VM::class.java) }
}

/**
 * Should be called whenever an unimplemented part of the UI is interacted with.
 */
fun toastNotImplemented(context: Context) {
    Toast.makeText(context, "Not implemented", Toast.LENGTH_SHORT).show()
}