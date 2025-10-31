package dev.klenz.matthias.kscxml

import java.io.FileNotFoundException
import java.io.InputStream

object ResourceLoader {
    fun getResourceAsStream(filePath: String): InputStream {
        return this::class.java.classLoader.getResourceAsStream(filePath)
            ?: throw FileNotFoundException("could not find $filePath")
    }
}