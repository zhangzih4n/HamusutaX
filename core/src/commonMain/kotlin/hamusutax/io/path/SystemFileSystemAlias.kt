@file:Suppress("unused")
package hamusutax.io.path

import kotlinx.io.files.Path
import kotlinx.io.files.SystemFileSystem

fun Path.resolve() =
    SystemFileSystem.resolve(this)

fun Path.exists() =
    SystemFileSystem.exists(this)

fun Path.list() =
    SystemFileSystem.list(this)

fun Path.createDirectories(mustCreate: Boolean = false) =
    SystemFileSystem.createDirectories(this, mustCreate)

fun Path.atomicMove(destination: Path) =
    SystemFileSystem.atomicMove(this, destination)

fun Path.delete(mustExist: Boolean = true) =
    SystemFileSystem.delete(this, mustExist)

fun Path.deleteRecursively(mustExist: Boolean = true) =
    SystemFileSystem.deleteRecursively(this, mustExist)

fun Path.metadataOrNull() =
    SystemFileSystem.metadataOrNull(this)

val Path.size
    get() = SystemFileSystem.metadataOrNull(this)?.size

val Path.isRegularFile
    get() = SystemFileSystem.metadataOrNull(this)?.isRegularFile

val Path.isDirectory
    get() = SystemFileSystem.metadataOrNull(this)?.isDirectory

fun Path.rawSource() =
    SystemFileSystem.source(this)

fun Path.rawSink() =
    SystemFileSystem.sink(this)

fun Path.walk(vararg options: PathWalkOption) =
    SystemFileSystem.walk(this, *options)
