[app](../../index.md) / [com.miemdynamics.fossbot.ui.home](../index.md) / [HomeViewModel](./index.md)

# HomeViewModel

`class HomeViewModel : `[`ViewModel`](https://developer.android.com/reference/androidx/lifecycle/ViewModel.html) [(source)](https://github.com/binyot/fossbot/tree/master/app/src/main/java/com/miemdynamics/fossbot/ui/home/HomeViewModel.kt#L16)

A [ViewModel](https://developer.android.com/reference/androidx/lifecycle/ViewModel.html) for [HomeFragment](../-home-fragment/index.md).

### Constructors

| [&lt;init&gt;](-init-.md) | `HomeViewModel(preferenceProvider: `[`PreferenceProvider`](../../com.miemdynamics.fossbot.data.provider/-preference-provider/index.md)`, robotService: `[`RobotService`](../../com.miemdynamics.fossbot.network.service/-robot-service/index.md)`)`<br>A [ViewModel](https://developer.android.com/reference/androidx/lifecycle/ViewModel.html) for [HomeFragment](../-home-fragment/index.md). |

### Properties

| [connectionState](connection-state.md) | `val connectionState: `[`LiveData`](https://developer.android.com/reference/androidx/lifecycle/LiveData.html)`<`[`State`](../../com.miemdynamics.fossbot.network.service/-robot-service/-state/index.md)`>` |
| [preferenceProvider](preference-provider.md) | `val preferenceProvider: `[`PreferenceProvider`](../../com.miemdynamics.fossbot.data.provider/-preference-provider/index.md) |
| [receivedText](received-text.md) | `val receivedText: `[`LiveData`](https://developer.android.com/reference/androidx/lifecycle/LiveData.html)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`>` |

### Functions

| [connect](connect.md) | `fun connect(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [disconnect](disconnect.md) | `fun disconnect(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [write](write.md) | `fun write(message: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |

