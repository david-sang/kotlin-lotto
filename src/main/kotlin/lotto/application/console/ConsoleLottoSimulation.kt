package lotto.application.console

import lotto.domain.LottoGame
import lotto.domain.LottoMachine
import lotto.domain.RandomLottoFactory

fun main() {

    val cost = InputView.inputCost()
    val lottoMachine = LottoMachine(RandomLottoFactory())
    val lottos = lottoMachine.buyAuto(cost)

    ResultView.printLottos(lottos)

    val winningNumbers = InputView.inputWinningNumbers()
    val lottoGame = LottoGame(winningNumbers)

    val summary = lottoGame.match(lottos)
    ResultView.printSummary(summary)
}