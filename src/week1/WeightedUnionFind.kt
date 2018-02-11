package week1

/**
 * Union Find
 * Complexity: MlgN
 * Space: N
 *
 * @param size of array
 */
class WeightedUnionFind(private val size: Int) {

    private val id: Array<Int>
    private val sz: Array<Int>

    init {
        id = Array(size, {i -> i})
        sz = Array(size, { 1 })
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
        if (i == q) return
        if (sz[i] >= sz[j]) {
            id[i] = j
            sz[j] += sz[i]
        } else {
            id[j] = i
            sz[i] += sz[j]
        }
    }
}