import java.io.File

fun File.readAsList(): List<String> {
    val t = mutableListOf<String>()
    for (c in this.readText()) {
        t.add(c.toString())
    }
    return t
}

val f = File("parser/parser-input.ktp")