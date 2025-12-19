package dev.klenz.matthias.kscxml.execution.search

import dev.klenz.matthias.kscxml.components.KScxmlRootNode
import dev.klenz.matthias.kscxml.components.state.KScxmlState

fun KScxmlRootNode.findState(id: String): KScxmlState? {
    states.forEach {
        val state = it.iterate(id)
        if(state != null) return state
    }
    return null
}

private fun KScxmlState.iterate(id: String): KScxmlState? {
    if (this.id == id)
        return this
    states.forEach {
        val state = it.iterate(id)
        if(state != null) return state
    }
    return null
}