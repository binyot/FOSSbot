[app](../../index.md) / [com.miemdynamics.fossbot.ui.program](../index.md) / [ProgramAdapter](./index.md)

# ProgramAdapter

`class ProgramAdapter : `[`Adapter`](https://developer.android.com/reference/androidx/recyclerview/widget/RecyclerView/Adapter.html)`<`[`ViewHolder`](-view-holder/index.md)`>` [(source)](https://github.com/binyot/fossbot/tree/master/app/src/main/java/com/miemdynamics/fossbot/ui/program/ProgramAdapter.kt#L20)

A [RecyclerView.Adapter](https://developer.android.com/reference/androidx/recyclerview/widget/RecyclerView/Adapter.html) for [Program](../../com.miemdynamics.fossbot.data.entity/-program/index.md)
Supports selection if [tracker](tracker.md) is set

### Types

| [ItemIdKeyProvider](-item-id-key-provider/index.md) | `class ItemIdKeyProvider : `[`ItemKeyProvider`](https://developer.android.com/reference/androidx/recyclerview/selection/ItemKeyProvider.html)`<`[`Long`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html)`>` |
| [ItemLookup](-item-lookup/index.md) | `class ItemLookup : `[`ItemDetailsLookup`](https://developer.android.com/reference/androidx/recyclerview/selection/ItemDetailsLookup.html)`<`[`Long`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html)`>` |
| [ProgramDiffCallback](-program-diff-callback/index.md) | `class ProgramDiffCallback : `[`Callback`](https://developer.android.com/reference/androidx/recyclerview/widget/DiffUtil/Callback.html) |
| [ViewHolder](-view-holder/index.md) | `inner class ViewHolder : `[`ViewHolder`](https://developer.android.com/reference/androidx/recyclerview/widget/RecyclerView/ViewHolder.html) |

### Constructors

| [&lt;init&gt;](-init-.md) | `ProgramAdapter()`<br>A [RecyclerView.Adapter](https://developer.android.com/reference/androidx/recyclerview/widget/RecyclerView/Adapter.html) for [Program](../../com.miemdynamics.fossbot.data.entity/-program/index.md) Supports selection if [tracker](tracker.md) is set |

### Properties

| [onItemClick](on-item-click.md) | `var onItemClick: (`[`Program`](../../com.miemdynamics.fossbot.data.entity/-program/index.md)`) -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [programList](program-list.md) | `var programList: `[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<`[`Program`](../../com.miemdynamics.fossbot.data.entity/-program/index.md)`>` |
| [tracker](tracker.md) | `var tracker: `[`SelectionTracker`](https://developer.android.com/reference/androidx/recyclerview/selection/SelectionTracker.html)`<`[`Long`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html)`>?` |

### Functions

| [getItemCount](get-item-count.md) | `fun getItemCount(): `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [getItemId](get-item-id.md) | `fun getItemId(position: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`): `[`Long`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html) |
| [onBindViewHolder](on-bind-view-holder.md) | `fun onBindViewHolder(holder: `[`ViewHolder`](-view-holder/index.md)`, position: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [onCreateViewHolder](on-create-view-holder.md) | `fun onCreateViewHolder(parent: `[`ViewGroup`](https://developer.android.com/reference/android/view/ViewGroup.html)`, viewType: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`): `[`ViewHolder`](-view-holder/index.md) |

