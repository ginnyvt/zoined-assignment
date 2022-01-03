package controllers

import entities.{Report, SalesReport}
import play.api.libs.json.Json

import javax.inject._
import play.api.mvc._
import repos.SalesRepo
import usecases.{ViewSalesByProductUseCase, ViewSalesByStoreUseCase}

@Singleton
class SalesReportController @Inject()(val controllerComponents: ControllerComponents, repo: SalesRepo, viewSalesByStoreUc: ViewSalesByStoreUseCase, viewSalesByProductUc: ViewSalesByProductUseCase) extends BaseController {
 def viewSalesByStore() = Action {
    val salesVectors = viewSalesByStoreUc.handle()
    var reportsByStore = Seq[Report]()
    salesVectors.map(vector => {
      reportsByStore = reportsByStore :+ Report(name = vector._1, amount = vector._2, period = vector._3, currency = vector._4)
    })
    val salesReportByStore = SalesReport(reportsByStore)
    val json = Json.toJson(salesReportByStore)
    Ok(json)
  }

  def viewSalesByProduct() = Action {
    val salesVectors = viewSalesByProductUc.handle()
    var reportsByProduct = Seq[Report]()
    salesVectors.map(vector => {
      reportsByProduct = reportsByProduct :+ Report(name = vector._1, amount = vector._2, period = vector._3, currency = vector._4)
    })
    val salesReportByProduct = SalesReport(reportsByProduct)
    val json = Json.toJson(salesReportByProduct)
    Ok(json)
  }
}
