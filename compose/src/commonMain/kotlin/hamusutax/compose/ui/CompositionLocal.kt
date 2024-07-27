@file:Suppress("unused")
package hamusutax.compose.ui

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.compositionLocalOf
import kotlinx.coroutines.CoroutineScope

val LocalCoroutineScope =
    compositionLocalOf<CoroutineScope> { error("no value") }

val LocalSnackbarHostState =
    compositionLocalOf<SnackbarHostState> { error("no value") }
