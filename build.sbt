lazy val baseName  = "Mellite-IDEA"
lazy val baseNameL = baseName.toLowerCase

lazy val projectVersion     = "0.1.0-SNAPSHOT"
lazy val ideaVersion        = "181.4445.78"
lazy val scalaPluginVersion = "2018.1.8"
lazy val scalaPluginUpdate  = 44474

// somehow this only works when we set it globally
ideaBuild in ThisBuild := ideaVersion

lazy val commonSettings = Seq(
  version       := projectVersion,
  organization  := "de.sciss",
  scalaVersion  := "2.12.5"
)

lazy val mellitePlugin = project.withId(baseNameL).in(file("."))
  .enablePlugins(SbtIdeaPlugin)
  .settings(commonSettings)
  .settings(
    name      := baseName,
    // ideaBuild := ideaVersion,
    ideaExternalPlugins +=
      IdeaPlugin.Zip("scala-plugin",
        url(s"https://plugins.jetbrains.com/files/1347/$scalaPluginUpdate/scala-intellij-bin-$scalaPluginVersion.zip")),
    assemblyOption in assembly := (assemblyOption in assembly).value.copy(includeScala = false),
    aggregate in updateIdea := false,
    assemblyExcludedJars in assembly := ideaFullJars.value
 )

lazy val ideaRunner = project.in(file("ideaRunner"))
  .dependsOn(mellitePlugin % Provided)
  .settings(commonSettings)
  .settings(
    name := "ideaRunner",
    autoScalaLibrary := false,
    unmanagedJars in Compile := ideaMainJars.in(mellitePlugin).value,
    unmanagedJars in Compile += file(System.getProperty("java.home")).getParentFile / "lib" / "tools.jar"
  )

lazy val packagePlugin = TaskKey[File]("package-plugin", "Create plugin's zip file ready to load into IDEA")

packagePlugin in mellitePlugin := {
  val ideaJar = (assembly in mellitePlugin).value
  val tgt     = (target   in mellitePlugin).value
  // val paths   = ivyPaths.value

  val pluginName = "sbt-idea-example"
  // val ivyLocal = paths.ivyHome.getOrElse(file(System.getProperty("user.home")) / ".ivy2") / "local"
  val sources = Seq(
    ideaJar -> s"$pluginName/lib/${ideaJar.getName}"
  )
  val out = tgt / s"$pluginName-plugin.zip"
  IO.zip(sources, out)
  out
}
