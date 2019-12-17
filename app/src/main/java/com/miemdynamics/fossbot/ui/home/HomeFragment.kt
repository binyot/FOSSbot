package com.miemdynamics.fossbot.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.viewModelScope
import com.miemdynamics.fossbot.R
import com.miemdynamics.fossbot.internal.viewModel
import com.miemdynamics.fossbot.network.connection.BluetoothConnection
import com.miemdynamics.fossbot.network.connection.BluetoothTarget
import com.miemdynamics.fossbot.network.service.RobotService
import com.miemdynamics.fossbot.network.service.RobotServiceImpl
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.coroutines.launch
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
        val service = viewModel.robotService
        service.liveState.observe(this, Observer {
            buttonConnect.isActivated = (it !is RobotService.State.Connecting)
            buttonConnect.text = when(it) {
                is RobotService.State.Connected -> "Disconnect"
                is RobotService.State.Disconnecting -> "Disconnecting..."
                is RobotService.State.Disconnected -> "Connect"
                is RobotService.State.Connecting -> "Connecting..."
            }
        })
        buttonConnect.setOnClickListener {
            val device = viewModel.preferenceProvider.getBluetoothDevice()
            val state = service.liveState.value
            when(state) {
                is RobotService.State.Connected -> {
                    viewModel.viewModelScope.launch {
                        service.disconnect()
                    }
                }
                is RobotService.State.Disconnected -> {
                    device?.let {
                        viewModel.viewModelScope.launch {
                            service.connect(BluetoothTarget(device))
                        }
                    } ?: Toast.makeText(context, "No device selected", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}