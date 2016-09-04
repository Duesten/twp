package controllers

import javax.inject._
import models.Item
import org.joda.time.DateTime
import play.api.mvc.Controller
import play.api.mvc.Action

@Singleton
class Application extends Controller {

  def index = Action { implicit request =>
    Item.create(
      DateTime.now(),
      None,
      Some("Nano"),
      Some("Giga"),
      None,
      None,
      None,
      None,
      None,
      None,
      None,
      None,
      None,
      None
    ).save()
    Ok(views.html.index())
  }

}