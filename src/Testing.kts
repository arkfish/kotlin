import parser.Parser

//fun File.readAsList(): List<String> {
//    val t = mutableListOf<String>()
//    for (c in this.readText()) {
//        t.add(c.toString())
//    }
//    return t
//}


// TODO: 12/7/2021 make this pass in the input to the parser
Parser().parseFile("parser/basic-input.txt")