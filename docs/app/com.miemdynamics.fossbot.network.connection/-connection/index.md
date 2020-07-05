[app](../../index.md) / [com.miemdynamics.fossbot.network.connection](../index.md) / [Connection](./index.md)

# Connection

`interface Connection` [(source)](https://github.com/binyot/fossbot/tree/master/app/src/main/java/com/miemdynamics/fossbot/network/connection/Connection.kt#L9)

A wrapper for socket connections.

### Properties

| [inputStream](input-stream.md) | `abstract val inputStream: `[`InputStream`](https://developer.android.com/reference/java/io/InputStream.html)<br>Returns implementation's input stream. Throws [IllegalStateException](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-illegal-state-exception/index.html) if currently not connected. |
| [outputStream](output-stream.md) | `abstract val outputStream: `[`OutputStream`](https://developer.android.com/reference/java/io/OutputStream.html)<br>Returns implementation's output stream. Throws [IllegalStateException](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-illegal-state-exception/index.html) if currently not connected. |

### Functions

| [close](close.md) | `abstract suspend fun close(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Closes connection if currently connected. Throws [IllegalStateException](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-illegal-state-exception/index.html) unless currently connected |
| [connect](connect.md) | `abstract suspend fun connect(target: `[`ConnectionTarget`](../-connection-target.md)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Attempts to connect to a specified [target](connect.md#com.miemdynamics.fossbot.network.connection.Connection$connect(com.miemdynamics.fossbot.network.connection.ConnectionTarget)/target). |

### Inheritors

| [BluetoothConnection](../-bluetooth-connection/index.md) | `class BluetoothConnection : `[`Connection`](./index.md) |
| [TcpConnection](../-tcp-connection/index.md) | `class TcpConnection : `[`Connection`](./index.md) |

