package controllers

import play.api.mvc._
import usecases.CreateSalesUseCase
import utils.DateTimeFormatter

import javax.inject.{Inject, Singleton}
import scala.concurrent.Await
import scala.concurrent.duration.{Duration, DurationInt}
import scala.io.Source
import scala.language.postfixOps

@Singleton
class UploadFileController @Inject()(val controllerComponents: ControllerComponents, createSalesUc: CreateSalesUseCase) extends BaseController {
  // specific controller to handle data from a clothing company
  def clothingData() = Action(parse.multipartFormData) { request => {
    request.body.file("file").map { file =>
      val filePath = file.ref.getPath
      val bufferedSource = Source.fromFile(s"$filePath")
      for (lines <- bufferedSource.getLines.drop(1)) {
        try {
          val cols = lines.split(";").map(_.trim)
          val date = DateTimeFormatter.format(cols(0), cols(1))
          val store = cols(2)
          val category = cols(3)
          val product = cols(4)
          val amount = BigDecimal(cols(5))
          if (amount > 0) {
            createSalesUc.handle(date, store, category, product, amount, "EUR")
          }
        } catch {
          case x: Exception => println(lines)
        }
      }
      bufferedSource.close()
      Ok("File saved")
    }
  }.getOrElse {
    BadRequest("No File Founded!")
  }
  }


  def textFile() = Action {
    Ok("OK")
  }
}
