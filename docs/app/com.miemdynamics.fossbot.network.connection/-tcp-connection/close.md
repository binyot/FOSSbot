[app](../../index.md) / [com.miemdynamics.fossbot.network.connection](../index.md) / [TcpConnection](index.md) / [close](./close.md)

# close

`suspend fun close(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) [(source)](https://github.com/binyot/fossbot/tree/master/app/src/main/java/com/miemdynamics/fossbot/network/connection/TcpConnection.kt#L25)

Overrides [Connection.close](../-connection/close.md)

Closes connection if currently connected.
Throws [IllegalStateException](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-illegal-state-exception/index.html) unless currently connected

