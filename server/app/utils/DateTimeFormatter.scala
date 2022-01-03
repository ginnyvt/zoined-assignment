package utils

object DateTimeFormatter {
  def format(date: String, hour: String): String = {
    if (date.length != 8) throw new Exception("Invalid date format!")
    else {
      val formattedDate = date.substring(0, 4) + "-" + date.substring(4, 6) + "-" + date.substring(date.length - 2)
      s"$formattedDate $hour:00:00"
    }
  }
}
