[app](../index.md) / [com.miemdynamics.fossbot.internal](./index.md)

## Package com.miemdynamics.fossbot.internal

### Types

| [ViewModelFactory](-view-model-factory/index.md) | `class ViewModelFactory : `[`Factory`](https://developer.android.com/reference/androidx/lifecycle/ViewModelProvider/Factory.html)<br>Used to retrieve ViewModel defined by tag with Kodein |

### Extensions for External Classes

| [org.kodein.di.Kodein.Builder](org.kodein.di.-kodein.-builder/index.md) |  |

### Properties

| [BLUETOOTH_DEVICE](-b-l-u-e-t-o-o-t-h_-d-e-v-i-c-e.md) | `const val BLUETOOTH_DEVICE: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| [BLUETOOTH_DEVICE_ENABLE](-b-l-u-e-t-o-o-t-h_-d-e-v-i-c-e_-e-n-a-b-l-e.md) | `const val BLUETOOTH_DEVICE_ENABLE: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [IP_ADDRESS](-i-p_-a-d-d-r-e-s-s.md) | `const val IP_ADDRESS: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| [PORT](-p-o-r-t.md) | `const val PORT: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| [RUN_PROGRAM_CONFIRM](-r-u-n_-p-r-o-g-r-a-m_-c-o-n-f-i-r-m.md) | `const val RUN_PROGRAM_CONFIRM: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |

### Functions

| [lazyPromise](lazy-promise.md) | `fun <T> lazyPromise(scope: CoroutineScope, block: suspend CoroutineScope.() -> `[`T`](lazy-promise.md#T)`): `[`Lazy`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-lazy/index.html)`<Deferred<`[`T`](lazy-promise.md#T)`>>` |
| [toastNotImplemented](toast-not-implemented.md) | `fun toastNotImplemented(context: `[`Context`](https://developer.android.com/reference/android/content/Context.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Should be called whenever an unimplemented part of the UI is interacted with. |
| [viewModel](view-model.md) | `fun <VM : `[`ViewModel`](https://developer.android.com/reference/androidx/lifecycle/ViewModel.html)`, T> `[`T`](view-model.md#T)`.viewModel(): `[`Lazy`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-lazy/index.html)`<`[`VM`](view-model.md#VM)`> where T : KodeinAware, T : `[`FragmentActivity`](https://developer.android.com/reference/androidx/fragment/app/FragmentActivity.html)<br>Helper function for Kodein'ed [ViewModel](https://developer.android.com/reference/androidx/lifecycle/ViewModel.html)s |

