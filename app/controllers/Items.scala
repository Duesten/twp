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

<<<<<<< Updated upstream
    val singleForm = Form(
      single(
        "q" -> text
      )
    )

    val result = singleForm.bindFromRequest.get

    val items = Item.findAll().filter { item =>
      (item.description + item.title + item.year + item.creators + item.production + item.year + item.genre + item.extra + item.medium).toLowerCase() contains result.toLowerCase()
    }


    Ok(views.html.items.list(items))
=======
    val searchedTextForm = Form(single( "q" -> text))
    val searchedText = searchedTextForm.bindFromRequest()
    val i = Item.syntax("i")

    val result = searchedText.apply("q").value.map{
      queryString =>  {
        val ilikeString = "%"+queryString+"%"

        val dbresult = Item.findAllBy(
            sqls"i.description ilike ${ilikeString}"
              .or(sqls"i.title ilike ${ilikeString}")
              .or(sqls"i.medium ilike ${ilikeString}")
              .or(sqls"i.creators ilike ${ilikeString}")
              .or(sqls"i.production ilike ${ilikeString}")
              .or(sqls"i.year ilike ${ilikeString}")
              .or(sqls"i.country ilike ${ilikeString}")
              .or(sqls"i.website ilike ${ilikeString}")
              .or(sqls"i.genre ilike ${ilikeString}")
              .or(sqls"i.extra ilike ${ilikeString}")
              .or(sqls"i.title ilike ${ilikeString}")
              .or(sqls"i.title ilike ${ilikeString}")
              .or(sqls"i.title ilike ${ilikeString}")
        )
        dbresult
      }
    }.getOrElse(List.empty)

    Ok(views.html.items.list(result))
>>>>>>> Stashed changes
  }

}