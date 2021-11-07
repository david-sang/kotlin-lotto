package lotto.domain.model

class WinningNumbers(
    val numbers: List<LottoNumber>
) {
    init {
        require(numbers.size == WINNING_NUMBER_SIZE) { "당첨번호는 6개여야 합니다." }
    }

    fun contains(number: LottoNumber): Boolean {
        return numbers.contains(number)
    }

    override fun toString(): String {
        return numbers.toString()
    }

    companion object {
        private const val WINNING_NUMBER_SIZE = 6
    }
}