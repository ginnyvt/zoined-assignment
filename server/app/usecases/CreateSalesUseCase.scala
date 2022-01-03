package usecases

import entities.Sales
import repos.SalesRepo

import java.util.UUID.randomUUID
import javax.inject.{Inject, Singleton}

@Singleton
class CreateSalesUseCase @Inject()(repo: SalesRepo) {

  def handle(date: String, store: String, category: String, product: String, amount: BigDecimal, currency: String) = {
    val id = randomUUID.toString
    val newlyCreatedSales = new Sales(
      id, date, store, category, product, amount, currency
    )
    repo.insert(
      newlyCreatedSales.getId,
      newlyCreatedSales.getDate,
      newlyCreatedSales.getStore,
      newlyCreatedSales.getCategory,
      newlyCreatedSales.getProduct,
      newlyCreatedSales.getAmount,
      newlyCreatedSales.getCurrency,
    )
  }
}
