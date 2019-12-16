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
import com.miemdynamics.fossbot.network.BluetoothConnection
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
        val btConnection = viewModel.btConnection
        btConnection.liveState.observe(this, Observer {
            textViewReceived.text = it.toString()
            buttonConnect.isActivated = (it !is BluetoothConnection.State.Connecting)
            buttonConnect.text = when(it) {
                is BluetoothConnection.State.Connected -> "Disconnect"
                is BluetoothConnection.State.Disconnected -> "Connect"
                is BluetoothConnection.State.Connecting -> "Connecting..."
            }
        })
        buttonConnect.setOnClickListener {
            val device = viewModel.preferenceProvider.getBluetoothDevice()
            val state = btConnection.liveState.value
            when(state) {
                is BluetoothConnection.State.Connected -> {
                    viewModel.viewModelScope.launch {
                        btConnection.disconnect()
                    }
                }
                is BluetoothConnection.State.Disconnected -> {
                    device?.let {
                        viewModel.viewModelScope.launch {
                            btConnection.connect(device)
                        }
                    } ?: Toast.makeText(context, "No device selected", Toast.LENGTH_SHORT).show()
                }
            }
        }
        buttonSend.setOnClickListener {
            val state = btConnection.liveState.value
            when(state) {
                is BluetoothConnection.State.Connected -> {
                    viewModel.viewModelScope.launch {
                        val buffer = editTextSend.text
                            .toString()
                            .toByteArray(charset("ASCII"))
                        Log.d("spp", "writing $buffer")
                        btConnection.write(buffer)
                    }
                }
                else -> Toast.makeText(context, "Cannot send the message", Toast.LENGTH_SHORT).show()
            }
        }
    }
}