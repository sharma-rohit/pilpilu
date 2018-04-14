object Main {
  def main(args: Array[String]): Unit = {
    Setup.startClusterManager()
    Setup.startHttpServer()
  }
}