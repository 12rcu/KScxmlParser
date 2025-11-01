@file:Suppress("unused")

package dev.klenz.matthias.kscxml.components

import dev.klenz.matthias.kscxml.components.state.KScxmlState

/**
 * @param xmlns URI, The value MUST be "http://www.w3.org/2005/07/scxml".
 * @param version The value MUST be "1.0"
 * @param initial The id of the initial state(s) for the document. If not specified, the default initial state is the first child state in document order.
 * @param datamodel "null", "ecmascript", "xpath" or other platform-defined values.
 * @param name the name of document
 * @param binding enum: "early", "late"; default early
 * @param states the states of the sm
 * @param final the last state of the sm
 */
data class KScxmlRootNode(
    var xmlns: String? = "http://www.w3.org/2005/07/scxml",
    var version: String? = "1.0",
    var initial: String? = null,
    var datamodel: String? = null,
    var name: String? = null,
    var binding: String? = null,
    var states: List<KScxmlState> = listOf(),
    var final: KScxmlState? = null
)