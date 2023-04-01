

val baseUrl = "https://bumble.com/mwebapi.phtml"

val headers = mapOf(
    "X-Desktop-web" to "1",
    "Origin" to "https://bumble.com",
    "Referer" to "https://bumble.com/get-started",
    "Sec-Fetch-Mode" to "cors",
    "User-Agent" to "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/77.0.3865.90 Safari/537.36",
    "x-use-session-cookie" to "1"
)


fun login(t: String, p: String) {
//    var json = js(File("C:\\Users\\Mike-AMD\\IntelliJIDEAProjects\\AOC\\src\\jvmMain\\resources\\login.json").bufferedReader().readLines())
//
//
//    val response = khttp.post(
//        url = baseUrl + "?SERVER_LOGIN_BY_PASSWORD",
//        headers = headers,
//        json = ""
//    )
//
//    println(response)
}

fun main(args: Array<String>) {
    login("123131424", "helloword")
}