package week1

/**
 * Union Find
 * Complexity: MN
 * Space: N
 *
 * @param size of array
 */
class UnionFind(private val size: Int) {

    private val id: Array<Int>

    init {
        id = Array(size, {i -> i})
    }

    fun root(p: Int): Int {
        if (id[p] == p) return p
        return root(id[p])
    }

    fun connected(p: Int, q: Int) : Boolean {
        return root(p) == root(q)
    }

    fun union(p: Int, q: Int) {
        val i = root(p)
        val j = root(q)

        id[i] = j
    }
}