data class Elf(val lowerBound: Bound, val upperBound: Bound) {
    fun isOverlappingCompletelyWithElf(elf: Elf) =
        (this.lowerBound.isOverlappingWithElf(elf) &&
            this.upperBound.isOverlappingWithElf(elf)) ||
        (elf.lowerBound.isOverlappingWithElf(this) &&
            elf.upperBound.isOverlappingWithElf(this))

    fun isOverlappingPartiallyWithElf(elf: Elf) =
        (this.lowerBound.isOverlappingWithElf(elf) ||
            this.upperBound.isOverlappingWithElf(elf)) ||
        (elf.lowerBound.isOverlappingWithElf(this) ||
            elf.upperBound.isOverlappingWithElf(this))
}

@JvmInline
value class Bound(private val value: Int) {
    fun isOverlappingWithElf(elf: Elf) =
        value >= elf.lowerBound.value && value <= elf.upperBound.value
}