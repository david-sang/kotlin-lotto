package specific.lotto.controller

import specific.lotto.domain.Money
import specific.lotto.domain.Player
import specific.lotto.domain.Round
import specific.lotto.domain.TicketMachine
import specific.lotto.domain.WinningNumbers
import specific.lotto.view.InputView
import specific.lotto.view.OutputView

class LottoController {

    fun participateRound() {
        runCatching {
            val money = inputMoney()
            val manualTicketNumbers = inputManualTicketNumbers(inputManualTicketCount())
            val player = Player(money, manualTicketNumbers, TicketMachine())
            OutputView.printTickets(player.tickets)
            val winningNumbers = inputWinningNumbers()
            val round = Round(player, winningNumbers)
            OutputView.printWinningResult(round.winningResult)
            OutputView.printReturnOnInvestment(round.player.money.calculateReturnOnInvestment())
            round
        }.onFailure {
            println(it.message)
        }
    }

    private fun inputMoney(): Money {
        val moneyInput = InputView.getMoney()
        require(!moneyInput.isNullOrBlank()) { "'moneyInput' cannot be null or blank" }
        val principal = moneyInput.toLongOrThrow { "'moneyInput' should be convertible to Long" }
        return Money(principal)
    }

    private fun inputWinningNumbers(): WinningNumbers {
        val mainNumbersInput = InputView.getMainNumbers()
        val bonusNumberInput = InputView.getBonusNumber()
        require(!mainNumbersInput.isNullOrBlank()) { "'mainNumbersInput' cannot be null or blank" }
        require(!bonusNumberInput.isNullOrBlank()) { "'bonusNumberInput' cannot be null or blank" }
        val mainNumbers = mainNumbersInput
            .split(", ")
            .map { it.toIntOrThrow { "lotto number should be convertible to Int" } }
        val bonusNumber = bonusNumberInput.toIntOrThrow { "lotto number should be convertible to Int" }
        return WinningNumbers(mainNumbers, bonusNumber)
    }

    private fun inputManualTicketCount(): Int {
        val manualTicketCountInput = InputView.getManualLottoCount()
        require(!manualTicketCountInput.isNullOrBlank()) { "'manualTicketCountInput' cannot be null or blank" }
        return manualTicketCountInput.toIntOrThrow { "'manualTicketCountInput' should be convertible to Long" }
    }

    private fun inputManualTicketNumbers(manualLottoCount: Int): List<List<Int>> {
        return (1..manualLottoCount)
            .map {
                val manualNumbersInput = InputView.getManualNumbers()
                require(!manualNumbersInput.isNullOrBlank()) { "'manualNumbersInput' cannot be null or blank" }
                manualNumbersInput
                    .split(", ")
                    .map { it.toIntOrThrow { "lotto number should be convertible to Int" } }
            }
    }

    private fun String?.toIntOrThrow(lazyMessage: () -> Any): Int {
        require(!isNullOrBlank()) { lazyMessage }
        return this.toIntOrNull()
            ?: throw IllegalArgumentException(lazyMessage().toString())
    }

    private fun String?.toLongOrThrow(lazyMessage: () -> Any): Long {
        require(!isNullOrBlank()) { lazyMessage }
        return this.toLongOrNull()
            ?: throw IllegalArgumentException(lazyMessage().toString())
    }
}
