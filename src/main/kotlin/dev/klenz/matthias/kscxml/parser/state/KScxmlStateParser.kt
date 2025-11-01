package dev.klenz.matthias.kscxml.parser.state

import dev.klenz.matthias.kscxml.components.state.KScxmlState
import dev.klenz.matthias.kscxml.parser.expression.parseExpressions
import dev.klenz.matthias.kscxml.parser.toList
import org.w3c.dom.Node

internal fun parseKScxmlState(node: Node): KScxmlState {
    return KScxmlState(
        id = node.attributes.getNamedItem("id")?.nodeValue,
        initial = node.attributes.getNamedItem("initial")?.nodeValue,
        states = node.childNodes.toList().filter { it.nodeName == "state" }.map { parseKScxmlState(it) },
        transitions = node.childNodes.toList().filter { it.nodeName == "transition" }.map { parseTransition(it) },
        onEntry = node.childNodes.toList().filter { it.nodeName == "onentry" }.map { parseExpressions(it) },
        onExit = node.childNodes.toList().filter { it.nodeName == "onexit" }.map { parseExpressions(it) }
    )
}