[app](../../index.md) / [com.miemdynamics.fossbot.network.service](../index.md) / [RobotServiceImpl](./index.md)

# RobotServiceImpl

`class RobotServiceImpl : `[`RobotService`](../-robot-service/index.md) [(source)](https://github.com/binyot/fossbot/tree/master/app/src/main/java/com/miemdynamics/fossbot/network/service/RobotServiceImpl.kt#L18)

### Constructors

| [&lt;init&gt;](-init-.md) | `RobotServiceImpl(connection: `[`Connection`](../../com.miemdynamics.fossbot.network.connection/-connection/index.md)`, repository: `[`ProgramRepository`](../../com.miemdynamics.fossbot.data.repo/-program-repository/index.md)`)` |

### Properties

| [liveState](live-state.md) | `val liveState: `[`LiveData`](https://developer.android.com/reference/androidx/lifecycle/LiveData.html)`<`[`State`](../-robot-service/-state/index.md)`>`<br>Indicates current service connection state |

### Functions

| [connect](connect.md) | `suspend fun connect(target: `[`ConnectionTarget`](../../com.miemdynamics.fossbot.network.connection/-connection-target.md)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Connect to a service. Throws an [IllegalStateException](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-illegal-state-exception/index.html) unless the current state is [RobotService.State.Disconnected](../-robot-service/-state/-disconnected/index.md). |
| [disconnect](disconnect.md) | `suspend fun disconnect(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Disconnect from a service. Throws an [IllegalStateException](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-illegal-state-exception/index.html) unless the current state is [RobotService.State.Connected](../-robot-service/-state/-connected/index.md). |
| [downloadPrograms](download-programs.md) | `suspend fun downloadPrograms(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Sends a [program](#) list request, then adds programs from the list to the database |
| [runProgram](run-program.md) | `suspend fun runProgram(program: `[`Program`](../../com.miemdynamics.fossbot.data.entity/-program/index.md)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Sends a [program](../-robot-service/run-program.md#com.miemdynamics.fossbot.network.service.RobotService$runProgram(com.miemdynamics.fossbot.data.entity.Program)/program) run request. |
| [uploadProgram](upload-program.md) | `suspend fun uploadProgram(program: `[`Program`](../../com.miemdynamics.fossbot.data.entity/-program/index.md)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Sends a [program](../-robot-service/upload-program.md#com.miemdynamics.fossbot.network.service.RobotService$uploadProgram(com.miemdynamics.fossbot.data.entity.Program)/program) create request |
| [write](write.md) | `suspend fun write(string: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, appendNewLine: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Sends given [string](../-robot-service/write.md#com.miemdynamics.fossbot.network.service.RobotService$write(kotlin.String, kotlin.Boolean)/string) as-is. Should only be used for testing |

