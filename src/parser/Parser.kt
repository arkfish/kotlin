package parser

import java.io.File

// TODO: 12/7/2021 make it at least be able to parse singular lines and compile to a map, then display the output
class Parser {
    private lateinit var parsedFile: ParsedFile

    fun interpretLine(line: String) {
        val lineTree: MutableMap<String, Any> = mutableMapOf()

        if (!line.startsWith('[') && line.endsWith(']'))
            println("error when compiling line:\ncode must be between brackets: '[]' ")
        else {
            val split: List<String> = line.split(Regex("\\s.")) // splits between any whitespaces excluding newlines (\n)
            val token: TK = when(split[1]) {
                "vdec"   -> TK.VARIABLE
                "cdec"   -> TK.CONSTANT
                else     -> TK.NONE
            }
            lineTree["token"] = token
            val datatype: TK = when(split[2]) {
                "int"  -> TK.DT_INT
                "bool" -> TK.DT_BOOL
                "str"  -> TK.DT_STR
                else   -> TK.DT_NULL
            }
            lineTree["datatype"] = datatype
            lineTree["key"] = split[2]
            if (datatype == TK.DT_STR) lineTree["value"] = split[3].substring(split[3].indexOf("(\""), split[3].indexOf("\")"))
        }
    }

    fun parseFile(f: File) {
        
    }
}