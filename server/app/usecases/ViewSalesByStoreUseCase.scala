package usecases


import repos.SalesRepo

import javax.inject.{Inject, Singleton}

@Singleton
class ViewSalesByStoreUseCase @Inject()(repo: SalesRepo) {
 def handle() = repo.viewSalesByStore()
}
