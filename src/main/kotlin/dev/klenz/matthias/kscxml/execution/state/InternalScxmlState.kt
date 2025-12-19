package dev.klenz.matthias.kscxml.execution.state

import dev.klenz.matthias.kscxml.components.state.KScxmlState

class InternalScxmlState {
    val activeStates = mutableSetOf<KScxmlState>()
}