package controllers

import javax.inject._
import models.Item
import play.api.mvc.Controller
import play.api.mvc.Action
import scalikejdbc._
import models._

import scalikejdbc.SQLSyntax._

@Singleton
class Items extends Controller {

  def list = Action { implicit req =>
    val items: List[Item] = Item.findAll()
    Ok(views.html.items.list(items))
  }

  def show(id : Long) = Action { implicit req =>
    Ok(views.html.items.show(Item.find(id).get))
  }

  def showCategory(category : String) = Action { implicit req =>
    val i = Item.syntax("i")
    val items = Item.findAllBy(sqls.eq(i.medium,category))
    Ok(views.html.items.list(items))
  }
}