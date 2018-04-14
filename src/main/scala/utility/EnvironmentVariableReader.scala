package utility

object EnvironmentVariableReader {
  def read[A](envVariableName: String)(f: String => A): Option[A] = sys.env.get(envVariableName).map(f)
}
