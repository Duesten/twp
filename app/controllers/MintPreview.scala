package controllers

import javax.inject._
import _root_.play.api.mvc.{Result, Controller, Action}

@Singleton
class MintPreview extends Controller {

  def index = Action { implicit request =>
    Ok(views.html.mintpreview())
  }

}