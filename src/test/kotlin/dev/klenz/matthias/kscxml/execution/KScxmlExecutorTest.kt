package dev.klenz.matthias.kscxml.execution

import dev.klenz.matthias.kscxml.KScxml
import dev.klenz.matthias.kscxml.ResourceLoader
import dev.klenz.matthias.kscxml.execution.state.InternalScxmlState
import kotlin.test.Test

class KScxmlExecutorTest {
    @Test
    fun testStartPutsInAllActiveStatesIntoInternalState() {
        //Given
        val text = ResourceLoader
            .getResourceAsStream("calculator.xml")
            .readAllBytes()
            .decodeToString()
        val scxml = KScxml.load(text)
        val internalScxmlState = InternalScxmlState()
        val executor = KScxmlExecutor(scxml.rootNode!!, internalScxmlState)

        //Exec
        executor.start()

        //Check
        assert(internalScxmlState.activeStates.any { it.id == "on" })
        assert(internalScxmlState.activeStates.any { it.id == "ready" })
        assert(internalScxmlState.activeStates.any { it.id == "begin" })
    }

    @Test
    fun eventRemovesStatesAndPutsInNewState() {
        //Given
        val text = ResourceLoader
            .getResourceAsStream("calculator.xml")
            .readAllBytes()
            .decodeToString()
        val scxml = KScxml.load(text)
        val internalScxmlState = InternalScxmlState()
        val executor = KScxmlExecutor(scxml.rootNode!!, internalScxmlState)

        //Exec
        executor.start()
        executor.onEvent("OPER")

        //Check
        assert(internalScxmlState.activeStates.any { it.id == "on" })
        assert(internalScxmlState.activeStates.none { it.id == "ready" })
        assert(internalScxmlState.activeStates.none { it.id == "begin" })
        assert(internalScxmlState.activeStates.any { it.id == "opEntered" })
    }
}