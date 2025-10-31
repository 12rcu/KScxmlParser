package dev.klenz.matthias.kscxml

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.dataformat.xml.XmlMapper
import dev.klenz.matthias.kscxml.components.KScxmlRootNode

class KScxml(var rootNode: KScxmlRootNode? = null) {
    companion object {

        /**
         * @param text a xml file in the format of scxml
         * @return a java/kotlin class with the content of the provided file
         */
        fun load(text: String): KScxml {
            val mapper = XmlMapper()
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
            mapper.configure(DeserializationFeature.FAIL_ON_INVALID_SUBTYPE, false)
            mapper.configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, false)
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
            return KScxml(mapper.readValue(text, KScxmlRootNode::class.java))
        }
    }

    fun encode(): String {
        val mapper = XmlMapper()
        return mapper.writeValueAsString(rootNode)
    }
}