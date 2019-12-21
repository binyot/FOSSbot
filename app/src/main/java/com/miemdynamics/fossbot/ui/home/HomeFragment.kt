package com.miemdynamics.fossbot.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.miemdynamics.fossbot.R
import com.miemdynamics.fossbot.internal.viewModel
import com.miemdynamics.fossbot.network.service.RobotService
import kotlinx.android.synthetic.main.fragment_home.*
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.closestKodein

/**
 * A home [Fragment].
 * Should be a Start Destination of navigation graph.
 */
class HomeFragment : Fragment(), KodeinAware {
    override val kodein: Kodein by closestKodein()
    private val viewModel: HomeViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
    }

    private fun setupUI() {
        val state = viewModel.connectionState
        state.observe(this, Observer {
            buttonConnect.isActivated = (it !is RobotService.State.Connecting)
            buttonConnect.text = when(it) {
                is RobotService.State.Connected -> "Disconnect"
                is RobotService.State.Disconnecting -> "Disconnecting..."
                is RobotService.State.Disconnected -> "Connect"
                is RobotService.State.Connecting -> "Connecting..."
            }
            textViewState.text = when(it) {
                is RobotService.State.Connected -> "Connected"
                is RobotService.State.Disconnecting -> "Disconnecting"
                is RobotService.State.Disconnected -> "Disconnected"
                is RobotService.State.Connecting -> "Connecting"
            }
        })
        buttonConnect.setOnClickListener {
            val target = viewModel.preferenceProvider.connectionTarget
            when(state.value) {
                is RobotService.State.Connected -> {
                    viewModel.disconnect()
                }
                is RobotService.State.Disconnected -> {
                    target?.let {
                        viewModel.connect()
                    } ?: Toast.makeText(context, "No device selected", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}