@file:Suppress("unused")

package dev.klenz.matthias.kscxml.components

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement
import dev.klenz.matthias.kscxml.components.state.State

@JacksonXmlRootElement(localName = "scxml")
class KScxmlRootNode {

    /**
     * URI
     * The value MUST be "http://www.w3.org/2005/07/scxml".
     */
    @JacksonXmlProperty(isAttribute = true)
    var xmlns: String? = "http://www.w3.org/2005/07/scxml"

    /**
     * The value MUST be "1.0"
     */
    @JacksonXmlProperty(isAttribute = true)
    var version: String? = "1.0"

    /**
     * The id of the initial state(s) for the document. If not specified, the default initial state is the first child
     * state in document order.
     */
    @JacksonXmlProperty(isAttribute = true)
    var initial: String? = null

    /**
     * "null", "ecmascript", "xpath" or other platform-defined values.
     */
    @JacksonXmlProperty(isAttribute = true)
    var datamodel: String? = null

    /**
     * enum
     * default "early",
     * other: "early", "late"
     */
    @JacksonXmlProperty(isAttribute = true)
    var binding: String? = null

    @JacksonXmlElementWrapper(useWrapping = false, localName = "state")
    var states: List<State>? = null

    var final: State? = null
}