package com.hzbcdut.plugins

import java.io.File
import java.io.IOException
import java.util.jar.JarEntry
import java.util.jar.JarFile
import java.util.jar.JarOutputStream
import java.util.zip.ZipEntry


internal typealias InjectImplsMap = MutableMap<String, List<String>>

internal fun pluginLog(msg: String) = println("Demo-Plugin====> $msg")

internal fun String.toClassName() = replace('/', '.')
internal fun String.toInternalName() = replace('.', '/')

internal val String.isValidClassName: Boolean
    get() = endsWith(".class") &&
            !startsWith("BuildConfig") &&
            !startsWith("R$") &&
            this != "R.class"

internal fun printMappedInterfaceAndImpls(map: InjectImplsMap) {
    map.forEach { (k, v) -> pluginLog("$k : $v") }
}

internal fun File.writeToZip(filter: (ZipEntry) -> Boolean, bytesTransform: (ByteArray) -> ByteArray) {
    val tmpFile = File(parent, "catClass${System.currentTimeMillis()}.jar")
    tmpFile.outputStream().use { tos ->
        JarOutputStream(tos).use { jarOut ->
            JarFile(this).use { jarFile ->
                jarFile.entries().asSequence().forEach { jarEntry ->
                    val ins = jarFile.getInputStream(jarEntry)
                    jarOut.putNextEntry(JarEntry(jarEntry.name))
                    if (filter(jarEntry)) {
                        jarOut.write(bytesTransform(ins.readBytes()))
                    } else {
                        jarOut.write(ins.readBytes())
                    }
                    jarOut.closeEntry()
                    ins.close()
                }
            }
        }
    }
    if (!deleteRecursively()) throw IOException("Delete old file failed.")
    pluginLog("rename ${tmpFile.name} to $name")
    tmpFile.renameTo(this)
}

internal fun JarFile.hasEntry(str: String): Boolean = use {
    getEntry(str) != null
}