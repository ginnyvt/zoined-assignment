package controllers

import javax.inject._
import play.api.mvc._
import repos.SalesRepo

@Singleton
class HomeController @Inject()(val controllerComponents: ControllerComponents, repo: SalesRepo) extends BaseController {
  def index() = Action { implicit request: Request[AnyContent] =>
    Ok("HELLO FROM PLAY!")
  }
}
