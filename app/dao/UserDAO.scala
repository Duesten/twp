package dao

import scala.concurrent.Future

import javax.inject.Inject
import models.User
import play.api.db.slick.DatabaseConfigProvider
import play.api.db.slick.HasDatabaseConfigProvider
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import slick.driver.PostgresDriver

class UserDAO @Inject()(val dbConfigProvider: DatabaseConfigProvider) extends HasDatabaseConfigProvider[PostgresDriver] {

  import driver.api._

  private val Users = TableQuery[UsersTable]

  def findById(id: Long) : Future[Option[User]] = db.run(Users.filter(_.id === id).result.headOption)
  def insert(user: User): Future[Unit] = db.run(Users += user).map { _ => () }
  def update(user:User): Future[Unit] = db.run(Users.filter(_.id === user.id).update(user)).map { _ => () }
  def delete(userId: Long): Future[Unit] = db.run(Users.filter(_.id === userId).delete).map { _ => () }
  def all(): Future[Seq[User]] = db.run(Users.result)
  def init() : Future[Int] = {
    db.run(sqlu"""CREATE TABLE IF NOT EXISTS users(
                id bigserial NOT NULL,
                username character varying,
                password character varying,
                CONSTRAINT pk2 PRIMARY KEY (id)
                )""")
  }

  private class UsersTable(tag: Tag) extends Table[User](tag, "users") {

    def id = column[Long]("id",O.PrimaryKey, O.AutoInc)
    def name = column[String]("username")
    def password = column[String]("password")

    def * = (id, name, password) <> (User.tupled, User.unapply _)
  }

}