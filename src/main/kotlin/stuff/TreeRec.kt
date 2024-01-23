package stuff


fun main() {
    val rec = TreeRec()

    val result = rec.buildTree(intArrayOf(1,2,3), intArrayOf(2,3,1))
    val a = 5
}
class TreeRec {

    class TreeNode(var `val`: Int) {
            var left: TreeNode? = null
             var right: TreeNode? = null
        }



    lateinit var preorder: IntArray
    lateinit var inorder: IntArray
    fun buildTree(preorder: IntArray, inorder: IntArray): TreeNode? {


        this.preorder = preorder
        this.inorder = inorder

        return rec(0, preorder.indices.last(), 0, inorder.indices.last())
    }

    private fun rec(pStart: Int, pEnd: Int, iStart: Int, iEnd: Int): TreeNode? {

        val root = preorder.getOrNull(pStart)
        var rootInorderIndex = -1
        for(i in iStart..iEnd) {
            if(inorder[i] == root) {
                rootInorderIndex = i
                break
            }
        }

        if(root == null || rootInorderIndex == -1) return null

        val relRootCount = rootInorderIndex - iStart

        val rootNode = TreeNode(root)
        rootNode.left = rec(pStart + 1, pStart + relRootCount, iStart, rootInorderIndex - 1)
        rootNode.right = rec(pStart + relRootCount + 1, pEnd, rootInorderIndex + 1, iEnd)

        return rootNode
    }
}