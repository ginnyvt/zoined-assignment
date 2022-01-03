package entities

import play.api.libs.json._

case class SalesReport ( reports: Seq[Report])

object SalesReport {
  implicit val salesReportWrites = new Writes[SalesReport] {
    def writes(salesReport: SalesReport) = Json.obj(
      "salesReport" -> salesReport.reports
    )
  }
}
