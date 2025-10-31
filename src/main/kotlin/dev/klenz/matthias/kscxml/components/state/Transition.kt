package dev.klenz.matthias.kscxml.components.state

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty

class Transition {
    /**
     * A list of designators of events that trigger this transition.
     */
    @JacksonXmlProperty(isAttribute = true)
    var event: String? = null

    /**
     * The identifier(s) of the state or parallel region to transition to.
     */
    @JacksonXmlProperty(isAttribute = true)
    var target: String? = null

    /**
     * Determines whether the source state is exited in transitions whose target state is a descendant of the source state
     */
    @JacksonXmlProperty(isAttribute = true)
    var type: String? = null

    /**
     * The guard condition for this transition
     */
    @JacksonXmlProperty(isAttribute = true)
    var cond: String? = null
}