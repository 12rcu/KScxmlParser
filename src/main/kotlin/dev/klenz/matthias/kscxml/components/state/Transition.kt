package dev.klenz.matthias.kscxml.components.state

/**
 * @param event A list of designators of events that trigger this transition.
 * @param target The identifier(s) of the state or parallel region to transition to.
 * @param type Determines whether the source state is exited in transitions whose target state is a descendant of the source state
 * @param cond The guard condition for this transition
 */
data class Transition(
    var event: String? = null,
    var target: String? = null,
    var type: String? = null,
    var cond: String? = null
)