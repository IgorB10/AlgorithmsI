package week1

/**
 * Quick union
 * Complexity: MN
 * Space: N
 *
 * @param size of array
 */
class QuickFind(private val size: Int) {

    private var id: Array<Int>

    init {
        id = Array(size, init = {i -> i})
    }

    fun connected(p: Int, q: Int) : Boolean {
        return id[p] == id[q]
    }

    fun inion(p: Int, q: Int) {
        val pIndex = id[p]
        val qIndex = id[q]

        (0..size).filter { id[it] == pIndex }.forEach { id[it] = qIndex }
    }
}