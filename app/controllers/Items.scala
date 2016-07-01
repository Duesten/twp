package controllers

import javax.inject.{Inject, Singleton}

import dao.ItemDAO
import models.{Item, ItemData}
import play.api.data.Forms._
import play.api.data._
import play.api.i18n.{I18nSupport, MessagesApi}
import play.api.mvc.{Action, Controller}
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

@Singleton
class Items @Inject() (itemDao: ItemDAO, val messagesApi: MessagesApi) extends Controller with I18nSupport {

  val itemForm = Form(
    mapping(
      "title" -> nonEmptyText,
      "description" -> nonEmptyText,
      "reserved" -> boolean
    )(ItemData.apply)(ItemData.unapply)
  )

  def list = Action.async {
    val resultingItems  = itemDao.all()
    resultingItems.map(items =>
      Ok(views.html.items.list(items,itemForm))
    )
  }

  def create = Action { implicit request =>

    itemForm.bindFromRequest.fold(
      error => TODO,
      itemData => {
        itemDao.insert(Item(0,itemData.title,itemData.description,reserved = false))
      }
    )

    Redirect(routes.Items.list())
  }

  def edit(id:Long) = Action.async { implicit request =>
    itemDao.findById(id).map({
      case Some(i) =>
        itemForm.bindFromRequest.fold(
          error => TODO,
          itemData => {
            itemDao.update(Item(i.id,itemData.title,itemData.description,itemData.reserved))
          }
        )
        Redirect(routes.Items.show(i.id))

      case None =>
        NotFound
    })
  }

  def show(id: Long) = Action.async {

    itemDao.findById(id).map({
      case Some(i) => Ok(views.html.items.show(i))
      case None => NotFound
    })

  }

  def search = Action.async { implicit request =>

    case class Query(q : String,action : String)
    val itemSearchForm = Form(mapping("q" -> text,"searchType" -> text)(Query.apply)(Query.unapply)).bindFromRequest()

    itemSearchForm.value match {
      case Some(Query(q,"normal")) => itemDao.likeSearch(q).map({case seq => Ok(views.html.items.list(seq,itemForm)) ; case _ => NotFound})
      case Some(Query(q,"lucky")) => itemDao.luckySearch(q).map({case Some(i) => Ok(views.html.items.show(i)) ; case _ => NotFound})
      case _ => Future(NotFound)
      case None => Future(Redirect(routes.Items.list()))
    }

  }

  def reserve(id: Long) = Action.async { implicit request =>
    itemDao.findById(id).map({case Some(i) => itemDao.update(i.copy(reserved = true));Ok(views.html.items.show(i)) case None => NotFound})
  }

  def delete(id: Long) = Action.async { implicit rs =>
    for {
      _ <- itemDao.delete(id)
    } yield Redirect(routes.Items.list())
  }

}