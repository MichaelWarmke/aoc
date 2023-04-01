
import khttp.*
import java.io.File
import com.google.gson.Gson

val gson = Gson()
val baseUrl = "https://bumble.com/mwebapi.phtml"
val headers = mapOf(
    "X-Desktop-web" to "1",
    "Origin" to "https://bumble.com",
    "Referer" to "https://bumble.com/get-started",
    "Sec-Fetch-Mode" to "cors",
    "User-Agent" to "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/77.0.3865.90 Safari/537.36",
    "x-use-session-cookie" to "1"
)

data class Login(
    var version: String,
    var message_type: String,
    var message_id: String,
    var is_background: String,
    var body: Array<Body>
) {
    data class Body(
        var message_type: String,
        var server_login_by_password: Password
        ) {
        data class Password(
            var remember_me: String,
            var phone: String,
            var password: String,
            var stats_data: String
        )
    }
}

fun login(t: String, p: String) {
    val postUrl = "$baseUrl?SERVER_LOGIN_BY_PASSWORD"
    val json = File("C:\\Users\\Mike-AMD\\IntelliJIDEAProjects\\AOC\\src\\jvmMain\\resources\\login.json").bufferedReader().readText()
    val model = gson.fromJson(json, Login::class.java)
    model.body[0].server_login_by_password.phone = t
    model.body[0].server_login_by_password.password = p

    val jsonString = gson.toJson(model)
    println(jsonString)

    val response = khttp.post(
        url = postUrl,
        headers = headers,
        data = model
    )

    println(response)
    println(response.jsonObject.toString(2))
}

fun main(args: Array<String>) {
    login("123131424", "helloword")
}