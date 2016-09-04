package controllers

import javax.inject._
import models.Item
import play.api.mvc.Controller
import play.api.mvc.Action

@Singleton
class Items extends Controller {

  def list = Action { implicit req =>

    val items: List[Item] = Item.findAll()

    Ok(views.html.items.list(items))
  }
}