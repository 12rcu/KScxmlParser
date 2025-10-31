package dev.klenz.matthias.kscxml

import org.junit.jupiter.api.Assertions.*
import kotlin.test.Test

class KScxmlTest {
    @Test
    fun loadFileHappyPath() {
        val text = ResourceLoader
            .getResourceAsStream("calculator.xml")
            .readAllBytes()
            .decodeToString()
        assertDoesNotThrow {
            val scxml = KScxml.load(text)
            assertNotNull(scxml.rootNode)
            assertNotNull(scxml.rootNode?.initial)
        }
    }
}