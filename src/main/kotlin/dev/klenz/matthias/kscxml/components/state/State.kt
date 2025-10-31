package dev.klenz.matthias.kscxml.components.state

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty

open class State {

    /**
     * The identifier for this state.
     */
    @JacksonXmlProperty(isAttribute = true)
    var id: String? = null

    /**
     * The id of the default initial state (or states) for this state.
     *
     * MUST NOT be specified in conjunction with the <initial> element. MUST NOT occur in atomic states.
     */
    @JacksonXmlProperty(isAttribute = true)
    var initial: String? = null

    @JacksonXmlElementWrapper(useWrapping = false, localName = "state")
    var states: List<State>? = null

    @JacksonXmlElementWrapper(useWrapping = false, localName = "transition")
    var transition: List<Transition>? = null

    /**
     * A wrapper element containing executable content to be executed when the state is entered.
     */
    @JacksonXmlElementWrapper(useWrapping = false, localName = "onentry")
    var onEntry: List<OnEntry>? = null

    /**
     * A wrapper element containing executable content to be executed when the state is exited.
     */
    @JacksonXmlElementWrapper(useWrapping = false, localName = "onexit")
    var onExit: List<OnExit>? = null

    var final: State? = null
}