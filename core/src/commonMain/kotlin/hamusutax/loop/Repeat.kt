package hamusutax.loop

import kotlin.jvm.JvmName

/**
 * 重复执行块，直到得到一个非空值
 */
inline fun <T : Any> repeatUntilNotNull(block: () -> T?): T {
    while (true) {
        block()?.let { return it }
    }
}

/**
 * 重复执行块，直到得到一个结果复合要求的值
 */
inline fun <T : Any> repeatUntil(predicate: (T) -> Boolean, block: () -> T): T {
    while (true) {
        block().let {
            if (predicate(it))
                return it
        }
    }
}

/**
 * 重复执行块，直到得到一个结果复合要求的值
 */
@JvmName("repeatNullableUntil")
inline fun <T> repeatUntil(predicate: (T?) -> Boolean, block: () -> T?): T? {
    while (true) {
        block().let {
            if (predicate(it))
                return it
        }
    }
}
