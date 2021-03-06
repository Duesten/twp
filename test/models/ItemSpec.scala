package models

import scalikejdbc.specs2.mutable.AutoRollback
import org.specs2.mutable._
import scalikejdbc._


class ItemSpec extends Specification {

  "Item" should {

    val i = Item.syntax("i")

    "find by primary keys" in new AutoRollback {
      val maybeFound = Item.find(1L)
      maybeFound.isDefined should beTrue
    }
    "find by where clauses" in new AutoRollback {
      val maybeFound = Item.findBy(sqls.eq(i.id, 1L))
      maybeFound.isDefined should beTrue
    }
    "find all records" in new AutoRollback {
      val allResults = Item.findAll()
      allResults.size should be_>(0)
    }
    "count all records" in new AutoRollback {
      val count = Item.countAll()
      count should be_>(0L)
    }
    "find all by where clauses" in new AutoRollback {
      val results = Item.findAllBy(sqls.eq(i.id, 1L))
      results.size should be_>(0)
    }
    "count by where clauses" in new AutoRollback {
      val count = Item.countBy(sqls.eq(i.id, 1L))
      count should be_>(0L)
    }
    "create new record" in new AutoRollback {
      val created = Item.create()
      created should not beNull
    }
    "save a record" in new AutoRollback {
      val entity = Item.findAll().head
      // TODO modify something
      val modified = entity
      val updated = Item.save(modified)
      updated should not equalTo(entity)
    }
    "destroy a record" in new AutoRollback {
      val entity = Item.findAll().head
      Item.destroy(entity)
      val shouldBeNone = Item.find(1L)
      shouldBeNone.isDefined should beFalse
    }
    "perform batch insert" in new AutoRollback {
      val entities = Item.findAll()
      entities.foreach(e => Item.destroy(e))
      val batchInserted = Item.batchInsert(entities)
      batchInserted.size should be_>(0)
    }
  }

}
