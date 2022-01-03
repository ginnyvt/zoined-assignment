package usecases


import repos.SalesRepo

import javax.inject.{Inject, Singleton}

@Singleton
class ViewSalesByProductUseCase @Inject()(repo: SalesRepo) {
 def handle() = repo.viewSalesByProduct()
}
