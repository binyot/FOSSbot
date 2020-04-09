[app](../../index.md) / [com.miemdynamics.fossbot.internal](../index.md) / [ViewModelFactory](./index.md)

# ViewModelFactory

`class ViewModelFactory : `[`Factory`](https://developer.android.com/reference/androidx/lifecycle/ViewModelProvider/Factory.html) [(source)](https://github.com/binyot/fossbot/tree/master/app/src/main/java/com/miemdynamics/fossbot/internal/ViewModelFactory.kt#L11)

Used to retrieve ViewModel defined by tag with Kodein

### Constructors

| [&lt;init&gt;](-init-.md) | `ViewModelFactory(injector: DKodein)`<br>Used to retrieve ViewModel defined by tag with Kodein |

### Functions

| [create](create.md) | `fun <T : `[`ViewModel`](https://developer.android.com/reference/androidx/lifecycle/ViewModel.html)`?> create(modelClass: `[`Class`](https://developer.android.com/reference/java/lang/Class.html)`<`[`T`](create.md#T)`>): `[`T`](create.md#T) |

