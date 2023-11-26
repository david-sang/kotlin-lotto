package specific.lotto.domain

class TicketMachine {
    fun sellTickets(money: Money, manualTicketNumbers: List<List<Int>>): Tickets {
        val manualTickets = sellManualTickets(money, manualTicketNumbers)
        val autoTickets = sellAutoTickets(money)
        return manualTickets + autoTickets
    }

    private fun sellManualTickets(money: Money, manualTicketNumbers: List<List<Int>>): Tickets {
        require(manualTicketNumbers.size <= TICKET_LIMIT) { "cannot purchase more than $TICKET_LIMIT" }
        val tickets: Tickets = manualTicketNumbers.map { issueTicket(it) }
        money.spend(Ticket.PRICE * tickets.size)
        return tickets
    }

    private fun sellAutoTickets(money: Money): Tickets {
        val count = money.remaining / Ticket.PRICE
        require(count <= TICKET_LIMIT) { "cannot purchase more than $TICKET_LIMIT" }
        val tickets: Tickets = List(count.toInt()) { issueTicket() }
        money.spend(Ticket.PRICE * tickets.size)
        return tickets
    }

    private fun issueTicket(
        numbers: Set<Number> = Number.getNumberPool().shuffled().take(Ticket.NUMBERS_SIZE).toSet(),
    ) = Ticket(numbers)

    private fun issueTicket(
        numbers: List<Int>,
    ) = Ticket(numbers)

    companion object {
        const val TICKET_LIMIT = 10000
    }
}
