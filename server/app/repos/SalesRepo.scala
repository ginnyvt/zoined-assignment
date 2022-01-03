package repos

import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}
import slick.jdbc.{ JdbcProfile}

import scala.concurrent.{Await, ExecutionContext, Future}
import javax.inject.{Inject, Singleton}
import scala.concurrent.duration.{Duration, DurationInt}
import scala.language.postfixOps

@Singleton
class SalesRepo @Inject()(protected val dbConfigProvider: DatabaseConfigProvider)(implicit ec: ExecutionContext)
  extends HasDatabaseConfigProvider[JdbcProfile] {

  import profile.api._


  def insert(id: String, date: String, store: String, category: String, product: String, amount: BigDecimal, currency: String) = {
    val query = sql"INSERT INTO sales (id, date, store, category, product, amount, currency) VALUES (${id}, ${date}, ${store}, ${category}, ${product}, ${amount}, ${currency})".as[Boolean]
    Await.result(db.run(query),1 seconds)
  }

  def viewSalesByStore() = {
    val query =
      sql"""
        SELECT store, SUM(amount), DATE_FORMAT(date, '%Y'), currency
        FROM sales
        GROUP BY store, DATE_FORMAT(date, '%Y'), currency
        ORDER BY DATE_FORMAT(date, '%Y');
        """.as[(String, BigDecimal, String, String)]
    val queryResult = Await.result(db.run(query), Duration.Inf)
    queryResult
  }

  def viewSalesByProduct() = {
    val query =
      sql"""
        SELECT product, SUM(amount), DATE_FORMAT(date, '%Y'), currency
        FROM sales
        GROUP BY product, DATE_FORMAT(date, '%Y'), currency
        ORDER BY DATE_FORMAT(date, '%Y');
      """.as[(String, BigDecimal, String, String)]

    val queryResult = Await.result(db.run(query), Duration.Inf)
    queryResult
  }
}


