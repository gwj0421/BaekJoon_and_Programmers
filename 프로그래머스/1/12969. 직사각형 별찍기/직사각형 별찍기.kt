fun main(args: Array<String>) {
    val (a, b) = readLine()!!.split(' ').map(String::toInt)
    var content: String = ""
    for (i in 1..b) {
        for (j in 1..a) {
            content += "*"
        }
        content += "\n"
    }
    println(content)
}