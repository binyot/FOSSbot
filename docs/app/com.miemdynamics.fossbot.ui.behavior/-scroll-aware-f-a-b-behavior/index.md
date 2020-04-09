[app](../../index.md) / [com.miemdynamics.fossbot.ui.behavior](../index.md) / [ScrollAwareFABBehavior](./index.md)

# ScrollAwareFABBehavior

`class ScrollAwareFABBehavior : Behavior` [(source)](https://github.com/binyot/fossbot/tree/master/app/src/main/java/com/miemdynamics/fossbot/ui/behavior/ScrollAwareFABBehavior.kt#L16)

Hides [FloatingActionButton](#) when its parent [RecyclerView](https://developer.android.com/reference/androidx/recyclerview/widget/RecyclerView.html) is being scrolled vertically

### Constructors

| [&lt;init&gt;](-init-.md) | `ScrollAwareFABBehavior(context: `[`Context`](https://developer.android.com/reference/android/content/Context.html)`, attrs: `[`AttributeSet`](https://developer.android.com/reference/android/util/AttributeSet.html)`)`<br>Hides [FloatingActionButton](#) when its parent [RecyclerView](https://developer.android.com/reference/androidx/recyclerview/widget/RecyclerView.html) is being scrolled vertically |

### Functions

| [layoutDependsOn](layout-depends-on.md) | `fun layoutDependsOn(parent: `[`CoordinatorLayout`](https://developer.android.com/reference/androidx/coordinatorlayout/widget/CoordinatorLayout.html)`, child: FloatingActionButton, dependency: `[`View`](https://developer.android.com/reference/android/view/View.html)`): `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [onNestedScroll](on-nested-scroll.md) | `fun onNestedScroll(coordinatorLayout: `[`CoordinatorLayout`](https://developer.android.com/reference/androidx/coordinatorlayout/widget/CoordinatorLayout.html)`, child: FloatingActionButton, target: `[`View`](https://developer.android.com/reference/android/view/View.html)`, dxConsumed: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`, dyConsumed: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`, dxUnconsumed: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`, dyUnconsumed: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`, type: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [onStartNestedScroll](on-start-nested-scroll.md) | `fun onStartNestedScroll(coordinatorLayout: `[`CoordinatorLayout`](https://developer.android.com/reference/androidx/coordinatorlayout/widget/CoordinatorLayout.html)`, child: FloatingActionButton, directTargetChild: `[`View`](https://developer.android.com/reference/android/view/View.html)`, target: `[`View`](https://developer.android.com/reference/android/view/View.html)`, axes: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`, type: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`): `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |

