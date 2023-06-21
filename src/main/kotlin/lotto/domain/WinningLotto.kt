package lotto.domain

/**
 * ### 한 주의 로또 당첨 번호를 표현하는 클래스 입니다.
 *
 * 6개의 당첨 번호와 1개의 보너스 번호를 가집니다.
 */
data class WinningLotto(
    val winningNumbers: Set<Int>,
    val bonusNumber: Int,
) {
    init {
        val numbers = winningNumbers + bonusNumber
        require(numbers.size == Lotto.MAX_LOTTO_NUMBER_SIZE + 1) {
            "winning lotto's number size must be ${Lotto.MAX_LOTTO_NUMBER_SIZE + 1}, actual size = ${numbers.size}"
        }
        require(numbers.all { it in (Lotto.MIN_LOTTO_NUMBER..Lotto.MAX_LOTTO_NUMBER) }) {
            "lotto number must be between ${Lotto.MIN_LOTTO_NUMBER} and ${Lotto.MAX_LOTTO_NUMBER}"
        }
    }
}