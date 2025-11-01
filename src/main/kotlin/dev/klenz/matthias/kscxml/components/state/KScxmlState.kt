package dev.klenz.matthias.kscxml.components.state

import dev.klenz.matthias.kscxml.components.expression.Expressions

/**
 * @param id The identifier for this state.
 * @param initial The id of the default initial state (or states) for this state.
 * @param states child states
 * @param transitions transitions to other states
 * @param onEntry A wrapper element containing executable content to be executed when the state is entered.
 * @param onExit A wrapper element containing executable content to be executed when the state is exited.
 * @param final the last child state
 */
data class KScxmlState(
    var id: String? = null,
    var initial: String? = null,
    var states: List<KScxmlState> = listOf(),
    var transitions: List<Transition> = listOf(),
    var onEntry: List<Expressions> = listOf(),
    var onExit: List<Expressions> = listOf(),
    var final: KScxmlState? = null
)