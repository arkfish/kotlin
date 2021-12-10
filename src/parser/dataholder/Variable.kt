package parser.dataholder

import parser.token.DT

data class Variable(val datatype: DT, val key: String, var value: Any?, val constant: Boolean) {
    override fun toString(): String {
        // ex "Variable(DT.INT, "something", 2, true)" would return "const something: int = 2"
        return "${if (constant) "const" else "var"} ${this.key}: ${datatype.name.lowercase()} = ${value.toString()}"
    }
}