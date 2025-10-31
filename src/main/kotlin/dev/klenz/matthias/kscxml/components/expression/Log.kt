package dev.klenz.matthias.kscxml.components.expression

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty

class Log {
    @JacksonXmlProperty(isAttribute = true)
    var expr: String? = null
}