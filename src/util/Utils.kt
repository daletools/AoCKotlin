package util

import java.io.File
import java.math.BigInteger
import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse
import java.nio.file.Files
import java.nio.file.Paths
import java.security.MessageDigest
import kotlin.io.path.Path
import kotlin.io.path.readText
import kotlin.io.path.exists

/**
 * Reads lines from the given input txt file.
 */
fun readInput(name: String) = Path("src/$name.txt").readText().lines()

/**
 * Converts string to md5 hash.
 */
fun String.md5() = BigInteger(
    1,
    MessageDigest
        .getInstance("MD5")
        .digest(toByteArray())
)
  .toString(16)
  .padStart(32, '0')

fun Any?.println() = println(this)

fun getSessionCookie(): String {
  val env = File(".env")
  return if (env.exists()) {
    env.readLines().filter { it.startsWith("AOC_SESSION=") }[0].split("=")[1]
  } else {
    System.getenv("AOC_SESSION") ?: throw IllegalStateException(
      "AOC_SESSION environment variable not set"
    )
  }
}

fun fetchInput(year: Int, day: Int): String {
  val url = "https://adventofcode.com/$year/day/$day/input"
  val session = getSessionCookie()
  val client = HttpClient.newBuilder().build()
  val request = HttpRequest.newBuilder()
    .uri(URI.create(url))
    .header("Cookie", "session=$session")
    .header("User-Agent", "github.com/your-username/aoc-kotlin by your-email@example.com")
    .build()

  val response = client.send(request, HttpResponse.BodyHandlers.ofString())

  if (response.statusCode() != 200) {
    throw RuntimeException("Failed to fetch input: HTTP ${response.statusCode()}")
  }

  return response.body()
}

/**
 * Gets input for a given problem - checks if input exists, downloads if not,
 * and returns the saved input as List<String>
 */
fun getInput(year: Int, day: Int): List<String> {
  val dirPath = Paths.get("inputs", year.toString())
  Files.createDirectories(dirPath) // Ensure directory exists

  val filename = "day${day.toString().padStart(2, '0')}.txt"
  val testFileName = "day${day.toString().padStart(2, '0')}_test.txt"
  val filePath = dirPath.resolve(filename)
  val testFilePath = dirPath.resolve(testFileName)

  // Check if we need to fetch new input
  if (!filePath.exists() || Files.size(filePath) == 0L) {
    println("Downloading input for $year day $day...")
    val input = fetchInput(year, day)
    Files.write(filePath, input.toByteArray())
    println("Input saved to ${filePath.toAbsolutePath()}")
  }

  if (!testFilePath.exists()) {
    Files.createFile(testFilePath)
  }

  return readInputFile(filePath.toString())
}

/**
 * Helper function to read input from a file path
 */
private fun readInputFile(filePath: String): List<String> {
  return File(filePath).readLines().map { it }
}