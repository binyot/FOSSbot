[app](../../index.md) / [com.miemdynamics.fossbot.ui.program](../index.md) / [ProgramViewModel](./index.md)

# ProgramViewModel

`class ProgramViewModel : `[`ViewModel`](https://developer.android.com/reference/androidx/lifecycle/ViewModel.html) [(source)](https://github.com/binyot/fossbot/tree/master/app/src/main/java/com/miemdynamics/fossbot/ui/program/ProgramViewModel.kt#L16)

A [ViewModel](https://developer.android.com/reference/androidx/lifecycle/ViewModel.html) for [ProgramFragment](../-program-fragment/index.md).

### Constructors

| [&lt;init&gt;](-init-.md) | `ProgramViewModel(programRepository: `[`ProgramRepository`](../../com.miemdynamics.fossbot.data.repo/-program-repository/index.md)`, robotService: `[`RobotService`](../../com.miemdynamics.fossbot.network.service/-robot-service/index.md)`, preferenceProvider: `[`PreferenceProvider`](../../com.miemdynamics.fossbot.data.provider/-preference-provider/index.md)`)`<br>A [ViewModel](https://developer.android.com/reference/androidx/lifecycle/ViewModel.html) for [ProgramFragment](../-program-fragment/index.md). |

### Properties

| [runProgramConfirmEnabled](run-program-confirm-enabled.md) | `val runProgramConfirmEnabled: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |

### Functions

| [addProgram](add-program.md) | `fun addProgram(program: `[`Program`](../../com.miemdynamics.fossbot.data.entity/-program/index.md)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [connectionStateLive](connection-state-live.md) | `fun connectionStateLive(): `[`LiveData`](https://developer.android.com/reference/androidx/lifecycle/LiveData.html)`<`[`State`](../../com.miemdynamics.fossbot.network.service/-robot-service/-state/index.md)`>` |
| [createProgram](create-program.md) | `fun createProgram(program: `[`Program`](../../com.miemdynamics.fossbot.data.entity/-program/index.md)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [deletePrograms](delete-programs.md) | `fun deletePrograms(programs: `[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<`[`Program`](../../com.miemdynamics.fossbot.data.entity/-program/index.md)`>): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [downloadPrograms](download-programs.md) | `fun downloadPrograms(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [getPrograms](get-programs.md) | `fun getPrograms(): <ERROR CLASS>` |
| [getProgramsLive](get-programs-live.md) | `fun getProgramsLive(): `[`LiveData`](https://developer.android.com/reference/androidx/lifecycle/LiveData.html)`<`[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<`[`Program`](../../com.miemdynamics.fossbot.data.entity/-program/index.md)`>>` |
| [runProgram](run-program.md) | `fun runProgram(program: `[`Program`](../../com.miemdynamics.fossbot.data.entity/-program/index.md)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [uploadAllPrograms](upload-all-programs.md) | `fun uploadAllPrograms(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [uploadProgram](upload-program.md) | `fun uploadProgram(program: `[`Program`](../../com.miemdynamics.fossbot.data.entity/-program/index.md)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |

