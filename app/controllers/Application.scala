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

    // MEDIUM     1
    // ARTIST     2
    // TITLE      3
    // PRODUCTION 4
    // YEAR       5
    // COUNTRY    6
    // LANGUAGE   7
    // WEBSITE    8
    // MORE:      9
    // DESC       10
    // GENRE      11

    CSVReader.open("resources/csv/HAJO_AND_MARIETTE.csv").foreach( f = record =>

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
    Ok(views.html.index())
  }

}