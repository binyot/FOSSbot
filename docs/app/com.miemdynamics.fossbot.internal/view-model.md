[app](../index.md) / [com.miemdynamics.fossbot.internal](index.md) / [viewModel](./view-model.md)

# viewModel

`inline fun <reified VM : `[`ViewModel`](https://developer.android.com/reference/androidx/lifecycle/ViewModel.html)`, T> `[`T`](view-model.md#T)`.viewModel(): `[`Lazy`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-lazy/index.html)`<`[`VM`](view-model.md#VM)`> where T : KodeinAware, T : `[`FragmentActivity`](https://developer.android.com/reference/androidx/fragment/app/FragmentActivity.html) [(source)](https://github.com/binyot/fossbot/tree/master/app/src/main/java/com/miemdynamics/fossbot/internal/util.kt#L26)

Helper function for Kodein'ed [ViewModel](https://developer.android.com/reference/androidx/lifecycle/ViewModel.html)s

