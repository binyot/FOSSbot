[app](../../index.md) / [com.miemdynamics.fossbot.network.service](../index.md) / [RobotService](./index.md)

# RobotService

`interface RobotService` [(source)](https://github.com/binyot/fossbot/tree/master/app/src/main/java/com/miemdynamics/fossbot/network/service/RobotService.kt#L10)

Used to interface with a robot's service

### Types

| [DisconnectedBy](-disconnected-by/index.md) | `enum class DisconnectedBy` |
| [State](-state/index.md) | `sealed class State` |

### Properties

| [liveState](live-state.md) | `abstract val liveState: `[`LiveData`](https://developer.android.com/reference/androidx/lifecycle/LiveData.html)`<`[`State`](-state/index.md)`>`<br>Indicates current service connection state |

### Functions

| [connect](connect.md) | `abstract suspend fun connect(target: `[`ConnectionTarget`](../../com.miemdynamics.fossbot.network.connection/-connection-target.md)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Connect to a service. Throws an [IllegalStateException](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-illegal-state-exception/index.html) unless the current state is [RobotService.State.Disconnected](-state/-disconnected/index.md). |
| [disconnect](disconnect.md) | `abstract suspend fun disconnect(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Disconnect from a service. Throws an [IllegalStateException](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-illegal-state-exception/index.html) unless the current state is [RobotService.State.Connected](-state/-connected/index.md). |
| [downloadPrograms](download-programs.md) | `abstract suspend fun downloadPrograms(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Sends a [program](#) list request, then adds programs from the list to the database |
| [runProgram](run-program.md) | `abstract suspend fun runProgram(program: `[`Program`](../../com.miemdynamics.fossbot.data.entity/-program/index.md)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Sends a [program](run-program.md#com.miemdynamics.fossbot.network.service.RobotService$runProgram(com.miemdynamics.fossbot.data.entity.Program)/program) run request. |
| [uploadProgram](upload-program.md) | `abstract suspend fun uploadProgram(program: `[`Program`](../../com.miemdynamics.fossbot.data.entity/-program/index.md)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Sends a [program](upload-program.md#com.miemdynamics.fossbot.network.service.RobotService$uploadProgram(com.miemdynamics.fossbot.data.entity.Program)/program) create request |
| [write](write.md) | `abstract suspend fun write(string: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, appendNewLine: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)` = true): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Sends given [string](write.md#com.miemdynamics.fossbot.network.service.RobotService$write(kotlin.String, kotlin.Boolean)/string) as-is. Should only be used for testing |

### Inheritors

| [RobotServiceImpl](../-robot-service-impl/index.md) | `class RobotServiceImpl : `[`RobotService`](./index.md) |

