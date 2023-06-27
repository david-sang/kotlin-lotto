package lotto.application.console

import lotto.domain.Lotto
import lotto.domain.LottoReceipt
import lotto.domain.LottoResult
import lotto.domain.LottoResultSummary

object ResultView {

    fun printLottoReceipt(lottoReceipt: LottoReceipt) {
        println("수동으로 ${lottoReceipt.manual.size}장, 자동으로 ${lottoReceipt.auto.size}개를 구매했습니다.")
        lottoReceipt.lottos.forEach { printLotto(it) }
        println()
    }

    fun printSummary(summary: LottoResultSummary) {
        println()
        println("당첨 통계")
        println("---------")
        summary.winResults().forEach { printLottoResult(it) }
        println("총 수익률은 ${summary.rateOfReturn()}입니다.(기준이 1이기 때문에 결과적으로 손해라는 의미임)")
    }

    private fun printLotto(lotto: Lotto) {
        println(lotto.joinToString(", ", "[", "]"))
    }

    private fun printLottoResult(lottoResult: Pair<LottoResult, Int>) {
        val (result, count) = lottoResult
        println("${result.matchCount}개 일치${matchBonusMessage(result.checkBonus)}(${result.winningAmount}원)- ${count}개")
    }

    private fun matchBonusMessage(matchBonus: Boolean): String {
        return if (matchBonus) ", 보너스 볼 일치"
        else " "
    }
}