[app](../../index.md) / [com.miemdynamics.fossbot.data.repo](../index.md) / [ProgramRepositoryImpl](./index.md)

# ProgramRepositoryImpl

`class ProgramRepositoryImpl : `[`ProgramRepository`](../-program-repository/index.md) [(source)](https://github.com/binyot/fossbot/tree/master/app/src/main/java/com/miemdynamics/fossbot/data/repo/ProgramRepositoryImpl.kt#L9)

### Constructors

| [&lt;init&gt;](-init-.md) | `ProgramRepositoryImpl(programDao: `[`ProgramDao`](../../com.miemdynamics.fossbot.data.db/-program-dao/index.md)`)` |

### Functions

| [delete](delete.md) | `suspend fun delete(program: `[`Program`](../../com.miemdynamics.fossbot.data.entity/-program/index.md)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Delete the [program](../-program-repository/delete.md#com.miemdynamics.fossbot.data.repo.ProgramRepository$delete(com.miemdynamics.fossbot.data.entity.Program)/program) |
| [getAll](get-all.md) | `suspend fun getAll(): `[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<`[`Program`](../../com.miemdynamics.fossbot.data.entity/-program/index.md)`>` |
| [getAllLive](get-all-live.md) | `fun getAllLive(): `[`LiveData`](https://developer.android.com/reference/androidx/lifecycle/LiveData.html)`<`[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<`[`Program`](../../com.miemdynamics.fossbot.data.entity/-program/index.md)`>>` |
| [insert](insert.md) | `suspend fun insert(program: `[`Program`](../../com.miemdynamics.fossbot.data.entity/-program/index.md)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Insert the [program](../-program-repository/insert.md#com.miemdynamics.fossbot.data.repo.ProgramRepository$insert(com.miemdynamics.fossbot.data.entity.Program)/program) in the database |

