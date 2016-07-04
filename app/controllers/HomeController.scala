package controllers

import javax.inject._
import play.api.mvc.Controller
import play.api.mvc.Action
import slick.driver.PostgresDriver.api._


@Singleton
class HomeController extends Controller {

  def index = Action { implicit request =>

    var a: DBIO[Int] = sqlu"""
           CREATE TABLE users(
           id bigserial NOT NULL,
           name character varying,
           password character varying,
           CONSTRAINT pk PRIMARY KEY (id)
       );
      """

    var b : DBIO[Int] = sqlu"""
          CREATE TABLE item(
           id bigserial NOT NULL,
           title character varying,
           description character varying,
           reserved BOOLEAN DEFAULT FALSE,
           CONSTRAINT pk2 PRIMARY KEY (id)
       );
      """


    Ok(views.html.index())
  }

}
