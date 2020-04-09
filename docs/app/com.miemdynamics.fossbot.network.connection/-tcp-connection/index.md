[app](../../index.md) / [com.miemdynamics.fossbot.network.connection](../index.md) / [TcpConnection](./index.md)

# TcpConnection

`class TcpConnection : `[`Connection`](../-connection/index.md) [(source)](https://github.com/binyot/fossbot/tree/master/app/src/main/java/com/miemdynamics/fossbot/network/connection/TcpConnection.kt#L14)

### Constructors

| [&lt;init&gt;](-init-.md) | `TcpConnection()` |

### Properties

| [inputStream](input-stream.md) | `val inputStream: `[`InputStream`](https://developer.android.com/reference/java/io/InputStream.html)<br>Returns implementation's input stream. Throws [IllegalStateException](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-illegal-state-exception/index.html) if currently not connected. |
| [outputStream](output-stream.md) | `val outputStream: `[`OutputStream`](https://developer.android.com/reference/java/io/OutputStream.html)<br>Returns implementation's output stream. Throws [IllegalStateException](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-illegal-state-exception/index.html) if currently not connected. |

### Functions

| [close](close.md) | `suspend fun close(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Closes connection if currently connected. Throws [IllegalStateException](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-illegal-state-exception/index.html) unless currently connected |
| [connect](connect.md) | `suspend fun connect(target: `[`ConnectionTarget`](../-connection-target.md)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Attempts to connect to a specified [target](../-connection/connect.md#com.miemdynamics.fossbot.network.connection.Connection$connect(com.miemdynamics.fossbot.network.connection.ConnectionTarget)/target). |

