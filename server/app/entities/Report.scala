package entities

import play.api.libs.json._

case class Report(name: String, amount: BigDecimal, period: String, currency: String)

object Report {
  implicit val reportWrites = new Writes[Report] {
    def writes(report: Report) = Json.obj(
      "name" -> report.name,
      "amount" -> report.amount,
      "period" -> report.period,
      "currency" -> report.currency
    )
  }
}
