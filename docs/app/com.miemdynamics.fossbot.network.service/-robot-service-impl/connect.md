[app](../../index.md) / [com.miemdynamics.fossbot.network.service](../index.md) / [RobotServiceImpl](index.md) / [connect](./connect.md)

# connect

`suspend fun connect(target: `[`ConnectionTarget`](../../com.miemdynamics.fossbot.network.connection/-connection-target.md)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) [(source)](https://github.com/binyot/fossbot/tree/master/app/src/main/java/com/miemdynamics/fossbot/network/service/RobotServiceImpl.kt#L33)

Overrides [RobotService.connect](../-robot-service/connect.md)

Connect to a service.
Throws an [IllegalStateException](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-illegal-state-exception/index.html) unless the current state is [RobotService.State.Disconnected](../-robot-service/-state/-disconnected/index.md).

