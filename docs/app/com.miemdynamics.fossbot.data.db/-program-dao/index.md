[app](../../index.md) / [com.miemdynamics.fossbot.data.db](../index.md) / [ProgramDao](./index.md)

# ProgramDao

`interface ProgramDao` [(source)](https://github.com/binyot/fossbot/tree/master/app/src/main/java/com/miemdynamics/fossbot/data/db/ProgramDao.kt#L16)

A DAO for [Program](../../com.miemdynamics.fossbot.data.entity/-program/index.md)
Methods that return LiveData are not [suspend](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/suspend.html)

### Functions

| [deleteAll](delete-all.md) | `abstract suspend fun deleteAll(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Delete all programs in the database |
| [deleteByName](delete-by-name.md) | `abstract suspend fun deleteByName(name: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Delete program with the [name](delete-by-name.md#com.miemdynamics.fossbot.data.db.ProgramDao$deleteByName(kotlin.String)/name) from the database |
| [getAll](get-all.md) | `abstract fun getAll(): `[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<`[`Program`](../../com.miemdynamics.fossbot.data.entity/-program/index.md)`>` |
| [getAllLive](get-all-live.md) | `abstract fun getAllLive(): `[`LiveData`](https://developer.android.com/reference/androidx/lifecycle/LiveData.html)`<`[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<`[`Program`](../../com.miemdynamics.fossbot.data.entity/-program/index.md)`>>` |
| [getByName](get-by-name.md) | `abstract fun getByName(name: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`LiveData`](https://developer.android.com/reference/androidx/lifecycle/LiveData.html)`<`[`Program`](../../com.miemdynamics.fossbot.data.entity/-program/index.md)`>` |
| [insert](insert.md) | `abstract suspend fun insert(program: `[`Program`](../../com.miemdynamics.fossbot.data.entity/-program/index.md)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Insert a [program](insert.md#com.miemdynamics.fossbot.data.db.ProgramDao$insert(com.miemdynamics.fossbot.data.entity.Program)/program) into the database |

