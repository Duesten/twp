package controllers

import javax.inject._
import play.api.data._
import play.api.data.Forms._
import models.Item
import _root_.play.api.mvc.{Result, Controller, Action}
import scalikejdbc._
import models._

import scalikejdbc.SQLSyntax._
import views.html.helper.form

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

  def showCategories = Action { implicit req =>
    Ok(views.html.items.list(List.empty))
  }

  def textSearch = Action { implicit req =>

    val itemForm = Form("q" -> text)
    val result = itemForm.bindFromRequest().apply("q").value.getOrElse("fuck off")

    val items = Item.findAll().filter { item =>
      (item.description + item.title + item.year + item.creators + item.production + item.year + item.genre + item.extra + item.medium).toLowerCase() contains queryString.getOrElse("asdasd").toLowerCase()
    }

    Ok(views.html.items.list(items))
  }

}