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

    @Test
    fun loadFileHappyPathStatesArePresent() {
        val text = ResourceLoader
            .getResourceAsStream("calculator.xml")
            .readAllBytes()
            .decodeToString()
        val scxml = KScxml.load(text)
        assertNotNull(scxml.rootNode)
        assertNotNull(scxml.rootNode?.initial)
        assertEquals(scxml.rootNode!!.states.size, 1)
        assertEquals(scxml.rootNode!!.states.first().states.count(), 1)
    }

    @Test
    fun loadFileHappyPathStateNamesMatch() {
        val text = ResourceLoader
            .getResourceAsStream("calculator.xml")
            .readAllBytes()
            .decodeToString()
        val scxml = KScxml.load(text)
        assertEquals(scxml.rootNode!!.states.first().id, "wrapper")
        assertEquals(scxml.rootNode!!.states.first().states.first().id, "on")
        assertEquals(scxml.rootNode!!.states.first().states.first().states.count(), 6)
    }

    @Test
    fun loadFileHappyPathTransitionNamesMatch() {
        val text = ResourceLoader
            .getResourceAsStream("calculator.xml")
            .readAllBytes()
            .decodeToString()
        val scxml = KScxml.load(text)
        assertEquals(scxml.rootNode!!.states.first().id, "wrapper")
        assertEquals(scxml.rootNode!!.states.first().transitions.size, 4)
        assertEquals(scxml.rootNode!!.states.first().transitions[0].event, "CALC.DO")
        assertEquals(scxml.rootNode!!.states.first().transitions[1].event, "CALC.SUB")
        assertEquals(scxml.rootNode!!.states.first().transitions[2].event, "DISPLAY.UPDATE")
        assertEquals(scxml.rootNode!!.states.first().transitions[3].event, "OP.INSERT")
    }
}