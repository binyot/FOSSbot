[app](../../index.md) / [com.miemdynamics.fossbot.data.repo](../index.md) / [ProgramRepository](./index.md)

# ProgramRepository

`interface ProgramRepository` [(source)](https://github.com/binyot/fossbot/tree/master/app/src/main/java/com/miemdynamics/fossbot/data/repo/ProgramRepository.kt#L9)

A repository for [Program](../../com.miemdynamics.fossbot.data.entity/-program/index.md)

### Functions

| [delete](delete.md) | `abstract suspend fun delete(program: `[`Program`](../../com.miemdynamics.fossbot.data.entity/-program/index.md)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Delete the [program](delete.md#com.miemdynamics.fossbot.data.repo.ProgramRepository$delete(com.miemdynamics.fossbot.data.entity.Program)/program) |
| [getAll](get-all.md) | `abstract suspend fun getAll(): `[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<`[`Program`](../../com.miemdynamics.fossbot.data.entity/-program/index.md)`>` |
| [getAllLive](get-all-live.md) | `abstract fun getAllLive(): `[`LiveData`](https://developer.android.com/reference/androidx/lifecycle/LiveData.html)`<`[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<`[`Program`](../../com.miemdynamics.fossbot.data.entity/-program/index.md)`>>` |
| [insert](insert.md) | `abstract suspend fun insert(program: `[`Program`](../../com.miemdynamics.fossbot.data.entity/-program/index.md)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Insert the [program](insert.md#com.miemdynamics.fossbot.data.repo.ProgramRepository$insert(com.miemdynamics.fossbot.data.entity.Program)/program) in the database |

### Inheritors

| [ProgramRepositoryImpl](../-program-repository-impl/index.md) | `class ProgramRepositoryImpl : `[`ProgramRepository`](./index.md) |

