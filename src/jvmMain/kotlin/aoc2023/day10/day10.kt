package aoc2023.day10

data class Point(val x: Int, val y: Int)

fun countMaxSteps(grid: List<String>): Int {
    val n = grid.size
    val m = grid[0].length
    val steps = Array(n) { IntArray(m) { -1 } }
    val dx = intArrayOf(-1, 0, 1, 0)
    val dy = intArrayOf(0, 1, 0, -1)
    val dir = mapOf(
        'F' to setOf(0, 3),
        '7' to setOf(0, 1),
        'L' to setOf(1, 2),
        'J' to setOf(2, 3),
        '-' to setOf(1, 3),
        '|' to setOf(0, 2)
    )
    val q = java.util.ArrayDeque<Point>()
    for(i in 0 until n) {
        for(j in 0 until m) {
            if(grid[i][j] == 'S') {
                steps[i][j] = 0
                q.add(Point(i, j))
            }
        }
    }

    var maxSteps = 0
    while(q.isNotEmpty()) {
        val p = q.poll()
        val x = p.x
        val y = p.y
        val allowedDirs = dir[grid[x][y]] ?: emptySet<Int>()
        for(i in 0 until 4) {
            val nx = x + dx[i]
            val ny = y + dy[i]
            if(nx in 0 until n && ny in 0 until m && steps[nx][ny] == -1 && i in allowedDirs) {
                steps[nx][ny] = steps[x][y] + 1
                maxSteps = Math.max(maxSteps, steps[nx][ny])
                q.add(Point(nx, ny))
            }
        }
    }
    return maxSteps
}

fun main() {
    val example1 = listOf(
        ".....",
        ".S-7.",
        ".|.|.",
        ".L-J.",
        "....."
    )
    println("Example 1: " + countMaxSteps(example1))

    val example2 = listOf(
        "..F7.",
        ".FJ|.",
        "SJ.L7",
        "|F--J",
        "LJ..."
    )
    println("Example 2: " + countMaxSteps(example2))
}