package entities


class Sales {
  // declare variables
  private var _id: String = _
  private var _date: String = _
  private var _store: String = _
  private var _category: String = _
  private var _product: String = _
  private var _amount: BigDecimal = _
  private var _currency: String = _

  // constructor
  def this(id: String, date: String, store: String, category: String, product: String, amount: BigDecimal, currency: String) = {
    this()
    this.setId(id)
    this.setDate(date)
    this.setStore(store)
    this.setCategory(category)
    this.setProduct(product)
    this.setAmount(amount)
    this.setCurrency(currency)
  }

  // setters
  def setId(id: String): Unit = _id = id

  def setDate(date: String): Unit = _date = date

  def setStore(store: String): Unit = _store = store

  def setCategory(category: String): Unit = _category = category

  def setProduct(product: String): Unit = _product = product

  def setAmount(amount: BigDecimal): Unit = {
    if (amount > 0) _amount = amount
    else throw new Exception("Amount must be a positive value")
  }

  def setCurrency(currency: String): Unit = _currency = currency

  // getters
  def getId: String = _id

  def getDate: String = _date

  def getStore: String = _store

  def getCategory: String = _category

  def getProduct: String = _product

  def getAmount: BigDecimal = _amount

  def getCurrency: String = _currency
}