package controllers

import javax.inject.{Inject, Singleton}

import dao.UserDAO
import models.{Item, UserData, User}
import play.api.data.Forms._
import play.api.data._
import play.api.i18n.{I18nSupport, MessagesApi, Messages}
import play.api.mvc.{Action, Controller}
import scala.concurrent.ExecutionContext.Implicits.global

@Singleton
class Users @Inject() (userDao: UserDAO, val messagesApi: MessagesApi) extends Controller with I18nSupport {

  val userForm = Form(
    mapping(
      "name" -> nonEmptyText,
      "password" -> nonEmptyText
    )(UserData.apply)(UserData.unapply)
  )

  def list = Action.async {
    val resultingUsers  = userDao.all()
    resultingUsers.map(users =>
      Ok(views.html.users.list(users,userForm))
    )
  }

  def create = Action { implicit request =>

    userForm.bindFromRequest.fold(
      error => TODO,
      userData => {
        userDao.insert(User(0,userData.name,userData.password))
      }
    )

    Redirect(routes.Users.list())
  }

  def show(id: Long) = Action.async {

    userDao.findById(id).map({
        case Some(u) => Ok(views.html.users.show(u))
        case None => NotFound
      }
    )

  }

  def edit(id:Long) = Action.async { implicit request =>
    userDao.findById(id).map({
      case Some(u) =>
        userForm.bindFromRequest.fold(
          error => TODO,
          userData => {
            userDao.update(User(u.id,userData.name,userData.password))
          }
        )
        Redirect(routes.Users.show(u.id))

      case None =>
        NotFound
    })
  }

  def delete(id: Long) = Action.async { implicit rs =>
    for {
     _ <- userDao.delete(id)
    } yield Redirect(routes.Users.list())
  }



}