# KScxml Parser

This is a small library that provides Java/Kotlin classes that represent the SCXML standard. 
These classes can be used to parse and encode SCXML files.
No additional functionality is provided.

## Example

A provided scxml file
```xml
<scxml xmlns="http://www.w3.org/2005/07/scxml"
          version="1.0"
          initial="hello">

 <final id="hello">
  <onentry>
   <log expr="'hello world'" />
  </onentry>
 </final>
</scxml>
```
Can be loaded:

```kotlin
val scxml = KScxml.load("${scxmlText}")
println(scxml.rootNode.final?.onEntry?.log?.expr)
```
```
> 'hello world'
```