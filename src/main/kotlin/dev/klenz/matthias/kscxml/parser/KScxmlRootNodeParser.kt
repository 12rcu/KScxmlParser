package dev.klenz.matthias.kscxml.parser

import dev.klenz.matthias.kscxml.components.KScxmlRootNode
import dev.klenz.matthias.kscxml.parser.state.parseKScxmlState
import org.w3c.dom.Document

internal fun parseKScxmlRootNode(document: Document): KScxmlRootNode {
    val rootTag = document.getElementsByTagName("scxml").item(0)
    val childNodes = rootTag.childNodes.toList()

    return KScxmlRootNode(
        xmlns = rootTag.attributes.getNamedItem("xmlns")?.nodeValue,
        version = rootTag.attributes.getNamedItem("version")?.nodeValue,
        initial = rootTag.attributes.getNamedItem("initial")?.nodeValue,
        datamodel = rootTag.attributes.getNamedItem("datamodel")?.nodeValue,
        name = rootTag.attributes.getNamedItem("name")?.nodeValue,
        binding = rootTag.attributes.getNamedItem("binding")?.nodeValue,
        states = childNodes.filter { it.nodeName == "state" }.map { parseKScxmlState(it) }
    )
}
