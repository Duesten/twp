package controllers

import javax.inject._
import com.github.tototoshi.csv.CSVReader
import models.Item
import org.joda.time.DateTime
import play.api.mvc.Controller
import play.api.mvc.Action

@Singleton
class Application extends Controller {

  def index = Action { implicit request =>
    Ok(views.html.index())
  }

  def importCSV() = Action { implicit req =>

    println("Importing")

    CSVReader.open("resources/csv/CSV_Import.csv").foreach( record =>

      Item.create(
        createdTimestamp = DateTime.now(),
        deletedTimestamp = None,
        title = Some(record(3)),
        description = Some(record(10)),
        reserved = None,
        medium = Some(record(1)),
        creators = Some(record(2)),
        production = Some(record(4)),
        year = Some(record(5)),
        country = Some(record(6)),
        lang = Some(record(7)),
        website = Some(record(8)),
        genre = Some(record(11)),
        extra = record.headOption
      )

    )

    println("Done importing")

    Ok()
  }

}