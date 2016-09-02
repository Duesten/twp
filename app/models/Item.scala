package models

import scalikejdbc._

case class Item(
    id: Long,
    title: Option[String] = None,
    description: Option[String] = None,
    reserved: Option[Boolean] = None,
    medium: Option[String] = None,
    creators: Option[String] = None,
    production: Option[String] = None,
    year: Option[String] = None,
    country: Option[String] = None,
    lang: Option[String] = None,
    website: Option[String] = None,
    genre: Option[String] = None,
    extra: Option[String] = None
) {

  def save()(implicit session: DBSession = Item.autoSession): Item = Item.save(this)(session)

  def destroy()(implicit session: DBSession = Item.autoSession): Unit = Item.destroy(this)(session)

}

object Item extends SQLSyntaxSupport[Item] {

  override val tableName = "item"

  override val columns = Seq("id", "title", "description", "reserved", "medium", "creators", "production", "year", "country", "lang", "website", "genre", "extra")

  def apply(i: SyntaxProvider[Item])(rs: WrappedResultSet): Item = apply(i.resultName)(rs)
  def apply(i: ResultName[Item])(rs: WrappedResultSet): Item = new Item(
    id = rs.get(i.id),
    title = rs.get(i.title),
    description = rs.get(i.description),
    reserved = rs.get(i.reserved),
    medium = rs.get(i.medium),
    creators = rs.get(i.creators),
    production = rs.get(i.production),
    year = rs.get(i.year),
    country = rs.get(i.country),
    lang = rs.get(i.lang),
    website = rs.get(i.website),
    genre = rs.get(i.genre),
    extra = rs.get(i.extra)
  )

  val i = Item.syntax("i")

  override val autoSession = AutoSession

  def find(id: Long)(implicit session: DBSession = autoSession): Option[Item] = {
    withSQL {
      select.from(Item as i).where.eq(i.id, id)
    }.map(Item(i.resultName)).single.apply()
  }

  def findAll()(implicit session: DBSession = autoSession): List[Item] = {
    withSQL(select.from(Item as i)).map(Item(i.resultName)).list.apply()
  }

  def countAll()(implicit session: DBSession = autoSession): Long = {
    withSQL(select(sqls.count).from(Item as i)).map(rs => rs.long(1)).single.apply().get
  }

  def findBy(where: SQLSyntax)(implicit session: DBSession = autoSession): Option[Item] = {
    withSQL {
      select.from(Item as i).where.append(where)
    }.map(Item(i.resultName)).single.apply()
  }

  def findAllBy(where: SQLSyntax)(implicit session: DBSession = autoSession): List[Item] = {
    withSQL {
      select.from(Item as i).where.append(where)
    }.map(Item(i.resultName)).list.apply()
  }

  def countBy(where: SQLSyntax)(implicit session: DBSession = autoSession): Long = {
    withSQL {
      select(sqls.count).from(Item as i).where.append(where)
    }.map(_.long(1)).single.apply().get
  }

  def create(
    title: Option[String] = None,
    description: Option[String] = None,
    reserved: Option[Boolean] = None,
    medium: Option[String] = None,
    creators: Option[String] = None,
    production: Option[String] = None,
    year: Option[String] = None,
    country: Option[String] = None,
    lang: Option[String] = None,
    website: Option[String] = None,
    genre: Option[String] = None,
    extra: Option[String] = None
  )(implicit session: DBSession = autoSession): Item = {
    val generatedKey = withSQL {
      insert.into(Item).namedValues(
        column.title -> title,
        column.description -> description,
        column.reserved -> reserved,
        column.medium -> medium,
        column.creators -> creators,
        column.production -> production,
        column.year -> year,
        column.country -> country,
        column.lang -> lang,
        column.website -> website,
        column.genre -> genre,
        column.extra -> extra
      )
    }.updateAndReturnGeneratedKey.apply()

    Item(
      id = generatedKey,
      title = title,
      description = description,
      reserved = reserved,
      medium = medium,
      creators = creators,
      production = production,
      year = year,
      country = country,
      lang = lang,
      website = website,
      genre = genre,
      extra = extra
    )
  }

  def batchInsert(entities: Seq[Item])(implicit session: DBSession = autoSession): Seq[Int] = {
    val params: Seq[Seq[(Symbol, Any)]] = entities.map(entity =>
      Seq(
        'title -> entity.title,
        'description -> entity.description,
        'reserved -> entity.reserved,
        'medium -> entity.medium,
        'creators -> entity.creators,
        'production -> entity.production,
        'year -> entity.year,
        'country -> entity.country,
        'lang -> entity.lang,
        'website -> entity.website,
        'genre -> entity.genre,
        'extra -> entity.extra
      ))
    SQL("""insert into item(
        title,
        description,
        reserved,
        medium,
        creators,
        production,
        year,
        country,
        lang,
        website,
        genre,
        extra
      ) values (
        {title},
        {description},
        {reserved},
        {medium},
        {creators},
        {production},
        {year},
        {country},
        {lang},
        {website},
        {genre},
        {extra}
      )""").batchByName(params: _*).apply()
  }

  def save(entity: Item)(implicit session: DBSession = autoSession): Item = {
    withSQL {
      update(Item).set(
        column.id -> entity.id,
        column.title -> entity.title,
        column.description -> entity.description,
        column.reserved -> entity.reserved,
        column.medium -> entity.medium,
        column.creators -> entity.creators,
        column.production -> entity.production,
        column.year -> entity.year,
        column.country -> entity.country,
        column.lang -> entity.lang,
        column.website -> entity.website,
        column.genre -> entity.genre,
        column.extra -> entity.extra
      ).where.eq(column.id, entity.id)
    }.update.apply()
    entity
  }

  def destroy(entity: Item)(implicit session: DBSession = autoSession): Unit = {
    withSQL { delete.from(Item).where.eq(column.id, entity.id) }.update.apply()
  }

}
