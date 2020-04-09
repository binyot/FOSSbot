[app](../../index.md) / [com.miemdynamics.fossbot.ui.control](../index.md) / [ControlViewModel](./index.md)

# ControlViewModel

`class ControlViewModel : `[`ViewModel`](https://developer.android.com/reference/androidx/lifecycle/ViewModel.html) [(source)](https://github.com/binyot/fossbot/tree/master/app/src/main/java/com/miemdynamics/fossbot/ui/control/ControlViewModel.kt#L13)

### Constructors

| [&lt;init&gt;](-init-.md) | `ControlViewModel(robotService: `[`RobotService`](../../com.miemdynamics.fossbot.network.service/-robot-service/index.md)`, programRepository: `[`ProgramRepository`](../../com.miemdynamics.fossbot.data.repo/-program-repository/index.md)`)` |

### Properties

| [presets](presets.md) | `val presets: `[`ArrayList`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-array-list/index.html)`<`[`Preset`](../-preset/index.md)`>` |
| [selectedPreset](selected-preset.md) | `var selectedPreset: `[`Preset`](../-preset/index.md) |

### Functions

| [connectionStateLive](connection-state-live.md) | `fun connectionStateLive(): `[`LiveData`](https://developer.android.com/reference/androidx/lifecycle/LiveData.html)`<`[`State`](../../com.miemdynamics.fossbot.network.service/-robot-service/-state/index.md)`>` |
| [getPrograms](get-programs.md) | `fun getPrograms(): <ERROR CLASS>` |
| [getProgramsLive](get-programs-live.md) | `fun getProgramsLive(): `[`LiveData`](https://developer.android.com/reference/androidx/lifecycle/LiveData.html)`<`[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<`[`Program`](../../com.miemdynamics.fossbot.data.entity/-program/index.md)`>>` |
| [runProgram](run-program.md) | `fun runProgram(program: `[`Program`](../../com.miemdynamics.fossbot.data.entity/-program/index.md)`): Job` |

