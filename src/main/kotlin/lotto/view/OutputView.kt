package lotto.view

import lotto.domain.model.LottoStatistics
import lotto.domain.model.Lottos
import lotto.domain.Rank

class OutputView {

    fun printLotto(
        lottos: Lottos,
    ) {
        println("수동으로 ${lottos.getPassivityLottoSize()}장, 자동으로 ${lottos.getAutomaticLottoSize()}장을 구매했습니다.")

        (lottos.getLottos()).forEach { lotto ->
            println(lotto)
        }
    }

    fun printStatistics(statistics: LottoStatistics) {
        println("3개 일치 (5000원) - ${statistics.getCountByRank(Rank.FIFTH)}")
        println("4개 일치 (50000원) - ${statistics.getCountByRank(Rank.FOURTH)}")
        println("5개 일치 (1500000원) - ${statistics.getCountByRank(Rank.THIRD)}")
        println("5개 일치, 보너스 볼 일치(30000000원) - ${statistics.getCountByRank(Rank.SECOND)}")
        println("6개 일치 (2000000000원) - ${statistics.getCountByRank(Rank.FIRST)}")
        println("총 수익률은 ${statistics.getYield()}")
    }
}