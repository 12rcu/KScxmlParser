package dev.klenz.matthias.kscxml.parser.expression

import dev.klenz.matthias.kscxml.components.expression.Expressions
import dev.klenz.matthias.kscxml.components.expression.Log
import dev.klenz.matthias.kscxml.components.expression.Script
import dev.klenz.matthias.kscxml.components.expression.Send
import dev.klenz.matthias.kscxml.parser.toList
import org.w3c.dom.Node

internal fun parseExpressions(node: Node): Expressions {
    return Expressions(
        log = node.childNodes.toList().find { it.nodeName == "log" }?.let { parseLog(it) },
        script = node.childNodes.toList().find { it.nodeName == "script" }?.let { parseScript(it) },
        send = node.childNodes.toList().find { it.nodeName == "send" }?.let { parseSend(it) }
    )
}

private fun parseScript(node: Node): Script {
    return Script(node.textContent)
}

private fun parseLog(node: Node): Log {
    return Log(expr = node.attributes.getNamedItem("expr")?.nodeValue)
}

private fun parseSend(node: Node): Send {
    return Send(event = node.attributes.getNamedItem("event")?.nodeValue)
}