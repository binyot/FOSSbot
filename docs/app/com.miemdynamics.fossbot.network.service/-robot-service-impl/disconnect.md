[app](../../index.md) / [com.miemdynamics.fossbot.network.service](../index.md) / [RobotServiceImpl](index.md) / [disconnect](./disconnect.md)

# disconnect

`suspend fun disconnect(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) [(source)](https://github.com/binyot/fossbot/tree/master/app/src/main/java/com/miemdynamics/fossbot/network/service/RobotServiceImpl.kt#L55)

Overrides [RobotService.disconnect](../-robot-service/disconnect.md)

Disconnect from a service.
Throws an [IllegalStateException](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-illegal-state-exception/index.html) unless the current state is [RobotService.State.Connected](../-robot-service/-state/-connected/index.md).

