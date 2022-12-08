data class Tree(val position: Position, val height: Height)

data class Position(val x: Int, val y: Int)

@JvmInline
value class Height(val value: Int)

@JvmInline
value class ScenicScore(val value: Int)

fun List<Tree>.isVisible(tree: Tree): Boolean {
    val allTrees = this
    val maxX = allTrees.maxOf { it.position.x }
    val maxY = allTrees.maxOf { it.position.y }

    if(tree.position.x == 0 || tree.position.y == 0 || tree.position.x == maxX || tree.position.y == maxY) {
        return true
    }

    val treesToLeft = allTrees.filter { it.position.x < tree.position.x && it.position.y == tree.position.y }
    val treesToRight = allTrees.filter { it.position.x > tree.position.x && it.position.y == tree.position.y }
    val treesAbove = allTrees.filter { it.position.y < tree.position.y && it.position.x == tree.position.x }
    val treesBelow = allTrees.filter { it.position.y > tree.position.y && it.position.x == tree.position.x }

    return treesToLeft.maxOf { it.height.value } < tree.height.value ||
            treesToRight.maxOf { it.height.value } < tree.height.value ||
            treesAbove.maxOf { it.height.value } < tree.height.value ||
            treesBelow.maxOf { it.height.value } < tree.height.value
}

fun List<Tree>.scenicScore(tree: Tree): ScenicScore {
    val allTrees = this
    val treesToLeft = allTrees.filter { it.position.x < tree.position.x && it.position.y == tree.position.y }
        .sortedByDescending { it.position.x }
    val treesToRight = allTrees.filter { it.position.x > tree.position.x && it.position.y == tree.position.y }
        .sortedBy { it.position.x }
    val treesAbove = allTrees.filter { it.position.y < tree.position.y && it.position.x == tree.position.x }
        .sortedByDescending { it.position.y }
    val treesBelow = allTrees.filter { it.position.y > tree.position.y && it.position.x == tree.position.x }
        .sortedBy { it.position.y }

    val score = treesToLeft.getSeenTrees(tree) *
    treesToRight.getSeenTrees(tree) *
    treesAbove.getSeenTrees(tree) *
    treesBelow.getSeenTrees(tree)

    return ScenicScore(score)
}

fun List<Tree>.getSeenTrees(tree: Tree) =
    this.fold(0) { acc, treeToSee ->
        if (treeToSee.height.value >= tree.height.value) {
            return acc + 1
        } else {
            acc + 1
        }
    }