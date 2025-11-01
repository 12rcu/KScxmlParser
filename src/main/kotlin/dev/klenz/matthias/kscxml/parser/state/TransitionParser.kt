package dev.klenz.matthias.kscxml.parser.state

import dev.klenz.matthias.kscxml.components.state.Transition
import org.w3c.dom.Node

internal fun parseTransition(node: Node): Transition {
    return Transition(
        event = node.attributes.getNamedItem("event")?.nodeValue,
        target = node.attributes.getNamedItem("target")?.nodeValue,
        type = node.attributes.getNamedItem("type")?.nodeValue,
        cond = node.attributes.getNamedItem("cond")?.nodeValue,
    )
}