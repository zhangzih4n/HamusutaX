package hamusutax.compose.navigation

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.DialogProperties
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavDeepLink
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.Navigator
import androidx.navigation.compose.composable
import androidx.navigation.compose.dialog
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.InternalSerializationApi
import kotlinx.serialization.serializer
import kotlin.jvm.JvmSuppressWildcards

@OptIn(InternalSerializationApi::class, ExperimentalSerializationApi::class)
@Composable
inline fun <reified T : Any> NavHost(
    navController: NavHostController,
    startDestination: T,
    modifier: Modifier = Modifier,
    contentAlignment: Alignment = Alignment.Center,
    route: String? = null,
    noinline enterTransition: AnimatedContentTransitionScope<NavBackStackEntry>.() -> EnterTransition = { fadeIn(animationSpec = tween(700)) },
    noinline exitTransition: AnimatedContentTransitionScope<NavBackStackEntry>.() -> ExitTransition = { fadeOut(animationSpec = tween(700)) },
    noinline popEnterTransition: AnimatedContentTransitionScope<NavBackStackEntry>.() -> EnterTransition = enterTransition,
    noinline popExitTransition: AnimatedContentTransitionScope<NavBackStackEntry>.() -> ExitTransition = exitTransition,
    noinline builder: NavGraphBuilder.() -> Unit
) {
    androidx.navigation.compose.NavHost(
        navController = navController,
        startDestination = T::class.serializer().descriptor.serialName,
        modifier = modifier,
        contentAlignment = contentAlignment,
        route = route,
        enterTransition = enterTransition,
        exitTransition = exitTransition,
        popEnterTransition = popEnterTransition,
        popExitTransition = popExitTransition,
        builder = builder
    )
}

@OptIn(InternalSerializationApi::class, ExperimentalSerializationApi::class)
inline fun <reified T : Any> NavGraphBuilder.composable(
    arguments: List<NamedNavArgument> = emptyList(),
    deepLinks: List<NavDeepLink> = emptyList(),
    noinline enterTransition: @JvmSuppressWildcards (AnimatedContentTransitionScope<NavBackStackEntry>.() -> EnterTransition?)? = null,
    noinline exitTransition: @JvmSuppressWildcards (AnimatedContentTransitionScope<NavBackStackEntry>.() -> ExitTransition?)? = null,
    noinline popEnterTransition: @JvmSuppressWildcards (AnimatedContentTransitionScope<NavBackStackEntry>.() -> EnterTransition?)? = enterTransition,
    noinline popExitTransition: @JvmSuppressWildcards (AnimatedContentTransitionScope<NavBackStackEntry>.() -> ExitTransition?)? = exitTransition,
    noinline content: @Composable (AnimatedContentScope.(NavBackStackEntry) -> Unit)
) {
    composable(
        route = T::class.serializer().descriptor.serialName,
        arguments = arguments,
        deepLinks = deepLinks,
        enterTransition = enterTransition,
        exitTransition = exitTransition,
        popEnterTransition = popEnterTransition,
        popExitTransition = popExitTransition,
        content = content
    )
}

@OptIn(InternalSerializationApi::class, ExperimentalSerializationApi::class)
inline fun <reified T : Any> NavGraphBuilder.dialog(
    arguments: List<NamedNavArgument> = emptyList(),
    deepLinks: List<NavDeepLink> = emptyList(),
    dialogProperties: DialogProperties = DialogProperties(),
    noinline content: @Composable (NavBackStackEntry) -> Unit
) {
    dialog(
        route = T::class.serializer().descriptor.serialName,
        arguments = arguments,
        deepLinks = deepLinks,
        dialogProperties = dialogProperties,
        content = content
    )
}

@OptIn(InternalSerializationApi::class, ExperimentalSerializationApi::class)
inline fun <reified T : Any> NavHostController.navigate(
    route: T,
    navOptions: NavOptions? = null,
    navigatorExtras: Navigator.Extras? = null
) =
    navigate(
        route = T::class.serializer().descriptor.serialName,
        navOptions = navOptions,
        navigatorExtras = navigatorExtras
    )
