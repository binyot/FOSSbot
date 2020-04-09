[app](../../index.md) / [com.miemdynamics.fossbot.data.provider](../index.md) / [PreferenceProvider](./index.md)

# PreferenceProvider

`interface PreferenceProvider` [(source)](https://github.com/binyot/fossbot/tree/master/app/src/main/java/com/miemdynamics/fossbot/data/provider/PreferenceProvider.kt#L9)

User preference provider.

### Properties

| [connectionTarget](connection-target.md) | `abstract val connectionTarget: `[`ConnectionTarget`](../../com.miemdynamics.fossbot.network.connection/-connection-target.md)`?` |
| [preferences](preferences.md) | `abstract val preferences: `[`SharedPreferences`](https://developer.android.com/reference/android/content/SharedPreferences.html) |
| [runProgramConfirmEnabled](run-program-confirm-enabled.md) | `abstract val runProgramConfirmEnabled: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |

### Inheritors

| [PreferenceProviderImpl](../-preference-provider-impl/index.md) | `class PreferenceProviderImpl : `[`PreferenceProvider`](./index.md) |

