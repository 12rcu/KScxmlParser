package dev.klenz.matthias.kscxml.execution

import dev.klenz.matthias.kscxml.components.KScxmlRootNode
import dev.klenz.matthias.kscxml.components.state.KScxmlState
import dev.klenz.matthias.kscxml.execution.search.findInitialStates
import dev.klenz.matthias.kscxml.execution.search.findState
import dev.klenz.matthias.kscxml.execution.state.InternalScxmlState
import org.graalvm.polyglot.Context
import org.graalvm.polyglot.Value

/**
 * @param rootNode the root node of the SCXML file
 * @param internalScxmlState an optional state that keeps track of all active states
 * @param context an optional Context that specifies the execution engine, this limited to graalvm, so JavaScript
 */
class KScxmlExecutor(
    private val rootNode: KScxmlRootNode,
    private val internalScxmlState: InternalScxmlState = InternalScxmlState(),
    private val context: Context = Context.newBuilder("js")
        .allowAllAccess(false)
        .out(System.out)
        .build()
) {
    /**
     * clears the internal state and puts in all initial states
     */
    fun start() {
        val activeStates = rootNode.findInitialStates()
        internalScxmlState.activeStates.clear()
        internalScxmlState.activeStates.addAll(activeStates)
    }

    fun onEvent(eventName: String) {
        val oldActiveStates = mutableSetOf<KScxmlState>().also { it.addAll(internalScxmlState.activeStates) }
        oldActiveStates.forEach { state ->
            val takeTransition = state
                .transitions
                .filter { it.event == eventName && evaluateCondition(it.cond) }
                .firstNotNullOfOrNull { rootNode.findState(it.target ?: "") }
            if (takeTransition != null) {
                transitionToState(state, takeTransition)
            }
        }
    }

    fun evaluateCondition(cond: String?): Boolean {
        if (cond == null) {
            return true
        }
        val result: Value = context.eval("js", cond)
        if (result.isBoolean) {
            val isTrue: Boolean = result.asBoolean()
            println("Kotlin received: $isTrue")
            return isTrue
        }
        return false
    }

    fun transitionToState(from: KScxmlState, to: KScxmlState) {
        internalScxmlState.activeStates.remove(from)
        removeActiveChildStates(from)
        internalScxmlState.activeStates.add(to)

        val initialStates = to.findInitialStates(rootNode)
        internalScxmlState.activeStates.addAll(initialStates)
    }

    fun removeActiveChildStates(state: KScxmlState) {
        state.states.forEach {
            internalScxmlState.activeStates.remove(it)
            removeActiveChildStates(it)
        }
    }
}