package dev.klenz.matthias.kscxml

import org.graalvm.polyglot.Context

class ScxmlExecutor {
    init {
        Context.newBuilder("js")
            .allowAllAccess(false)
            .out(System.out)
            .build().use { context ->

                // 1. Evaluation
                context.eval("js", "const x = 2;")

                // 2. Execute and get result as a GraalVM Value
                val result = context.eval("js", "x * 3;")
                println("Result: $result") // Prints 'Result: 6'

                // 3. Run a statement that outputs to the console (redirected above)
                context.eval("js", "console.log('The value of x is ' + x);")
            }
    }
}

fun main() {
    val executor = ScxmlExecutor()
}