package dev.klenz.matthias.kscxml.components.expression

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlText

class Script {
    @JacksonXmlText(value = true)
    var value: String? = null
}