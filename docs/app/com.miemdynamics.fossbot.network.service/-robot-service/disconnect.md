[app](../../index.md) / [com.miemdynamics.fossbot.network.service](../index.md) / [RobotService](index.md) / [disconnect](./disconnect.md)

# disconnect

`abstract suspend fun disconnect(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) [(source)](https://github.com/binyot/fossbot/tree/master/app/src/main/java/com/miemdynamics/fossbot/network/service/RobotService.kt#L26)

Disconnect from a service.
Throws an [IllegalStateException](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-illegal-state-exception/index.html) unless the current state is [RobotService.State.Connected](-state/-connected/index.md).

