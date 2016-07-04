package dao

import scala.concurrent.Future

import javax.inject.Inject
import models.Item
import play.api.db.slick.DatabaseConfigProvider
import play.api.db.slick.HasDatabaseConfigProvider
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import slick.driver.PostgresDriver

class ItemDAO @Inject()(val dbConfigProvider: DatabaseConfigProvider) extends HasDatabaseConfigProvider[PostgresDriver] {

  import driver.api._

  private val Items = TableQuery[ItemsTable]

  def findById(id: Long) : Future[Option[Item]] = db.run(Items.filter(_.id === id).result.headOption)
  def insert(item: Item): Future[Unit] = db.run(Items += item).map { _ => () }
  def update(item:Item): Future[Unit] = db.run(Items.filter(_.id === item.id).update(item)).map { _ => () }
  def delete(itemId: Long): Future[Unit] = db.run(Items.filter(_.id === itemId).delete).map { _ => () }
  def all(): Future[Seq[Item]] = db.run(Items.result)

  def likeSearch(query : String) : Future[Seq[Item]] = db.run(Items.filter(i => i.description.toLowerCase.like('%' + query.toLowerCase + '%') || i.title.toLowerCase.like('%' + query.toLowerCase + '%')).result)
  def luckySearch(query : String) : Future[Option[Item]] = db.run(Items.filter(_.title.toLowerCase like ("%" + query.toLowerCase + "%")).result.headOption)

  private class ItemsTable(tag: Tag) extends Table[Item](tag, "item") {

    def id = column[Long]("id",O.PrimaryKey, O.AutoInc)
    def title = column[String]("title")
    def description = column[String]("description")
    def reserved = column[Boolean]("reserved")

    def * = (id, title, description,reserved) <> (Item.tupled, Item.unapply _)
  }

}