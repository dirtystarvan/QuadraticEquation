import kotlin.reflect.KProperty

class CheckArgumentsDelegate(private var argument: Double = 0.0,
                             private val checkOp: (value: Double) -> Unit = {}) {

    companion object {
        const val aZeroErrorMessage = "[a] argument cannot be zero!!"
        const val nanErrorMessage = "argument must be finite number"
    }

    operator fun getValue(thisRef: Any?, property: KProperty<*>): Double {
        return argument
    }

    operator fun setValue(thisRef: Any?, property: KProperty<*>, value: Double) {
        if (value.isFinite()) {
            argument = value
        } else {
            throw IllegalArgumentException("[${property.name}] $nanErrorMessage")
        }

        checkOp(value)
    }
}