package dev.klenz.matthias.kscxml

import dev.klenz.matthias.kscxml.components.KScxmlRootNode
import dev.klenz.matthias.kscxml.parser.parseKScxmlRootNode
import java.io.InputStream
import javax.xml.parsers.DocumentBuilderFactory

class KScxml(var rootNode: KScxmlRootNode? = null) {
    companion object {

        fun load(inputStream: InputStream): KScxml {
            val doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(inputStream).also {
                it.normalize()
            }
            return KScxml(parseKScxmlRootNode(doc))
        }

        /**
         * @param text a xml file in the format of scxml
         * @return a java/kotlin class with the content of the provided file
         */
        fun load(text: String): KScxml {
            return load(text.byteInputStream(Charsets.UTF_8))
        }
    }
}