package dev.klenz.matthias.kscxml.execution.search

import dev.klenz.matthias.kscxml.components.KScxmlRootNode
import dev.klenz.matthias.kscxml.components.state.KScxmlState

fun KScxmlRootNode.findInitialStates(): Set<KScxmlState> {
    if(initial == null) {
        return setOf()
    }
    val initialState = findState(initial!!)
    return initialState?.findInitialStates(this, mutableSetOf(initialState)) ?: setOf()
}

fun KScxmlState.findInitialStates(rootNode: KScxmlRootNode, foundState: MutableSet<KScxmlState> = mutableSetOf()): Set<KScxmlState> {
    if(initial == null) {
        return foundState
    }
    val initialState = rootNode.findState(initial!!)
    if(initialState != null && !foundState.contains(initialState)) {
        foundState.add(initialState)
        return initialState.findInitialStates(rootNode, foundState)
    }
    return foundState
}