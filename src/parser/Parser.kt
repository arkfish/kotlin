package parser

import parser.dataholder.Variable
import parser.token.DT
import parser.token.TK
import java.io.File

// TODO: 12/7/2021 make it at least be able to parse singular lines and compile to a map, then display the output
class Parser {
    private lateinit var parsedFile: ParsedFile

    private fun interpretLine(fullLine: String) {
        val lineTree: MutableMap<String, Any> = mutableMapOf()
        val line = fullLine.split(Regex("(//.*)"))[0] // filters out all of the comments

        if (!line.startsWith('[') && line.endsWith(']'))
            println("error when compiling line:\ncode must be between brackets: '[]' ")
        else {
            val split: List<String> = line.split(Regex("\\s.")) // splits between any whitespaces excluding newlines (\n)
            val token: TK = when(split[1]) {
                "vdec" -> TK.VARIABLE
                "cdec" -> TK.CONSTANT
                else   -> TK.NONE
            }
            val datatype: DT = when(split[2]) {
                "int"  -> DT.INT
                "bool" -> DT.BOOL
                "str"  -> DT.STR
                else   -> DT.NULL
            }

            if (token == TK.CONSTANT || token == TK.VARIABLE) {
                // if the operation is variable declaration
                val variable = Variable(datatype, split[2], null, token == TK.CONSTANT)
                lineTree["token"] = token
                variable.value = when(datatype) {
                    DT.STR  -> split[3].substring(split[3].indexOf("(\""), split[3].indexOf("\")"))
                    DT.INT  -> split[3].toInt()
                    DT.BOOL -> split[3].toBoolean()
                    DT.NULL -> null
                }

                println(variable.toString())
            }
        }
    }

    fun parseFile(pathname: String) {
        val f = File(pathname)
        for(line in f.readLines()) interpretLine(line)
    }
}