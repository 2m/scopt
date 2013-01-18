import AssemblyKeys._

organization := "org.rogach"

name := "scallop"

scalaVersion := "2.10.0"

scalacOptions ++= Seq(
  "-deprecation", 
  "-unchecked", 
  "-feature",
  "-language:postfixOps",
  "-language:reflectiveCalls",
  "-language:existentials",
  "-language:implicitConversions",
  "-Xlint",
  "-Ywarn-all"
)

seq(Revolver.settings: _*)

seq(assemblySettings: _*)

resolvers += "Scala Tools Snapshots" at "http://scala-tools.org/repo-snapshots/"

libraryDependencies ++= Seq(
  "org.scalatest" % "scalatest_2.10.0" % "1.8" % "test"
)

crossScalaVersions := Seq("2.10.0")

unmanagedClasspath in Compile += file("dummy")

libraryDependencies <+= scalaVersion(sv => "org.scala-lang" % "scala-reflect" % sv)

publishTo <<= version { v: String =>
  val nexus = "https://oss.sonatype.org/"
  if (v.trim.endsWith("SNAPSHOT"))
    Some("snapshots" at nexus + "content/repositories/snapshots")
  else
    Some("releases" at nexus + "service/local/staging/deploy/maven2")
}

publishMavenStyle := true

publishArtifact in Test := false

pomIncludeRepository := { x => false }

pomExtra := (
  <url>https://github.com/Rogach/scallop</url>
  <licenses>
    <license>
      <name>MIT License</name>
      <url>http://www.opensource.org/licenses/mit-license.php</url>
    </license>
  </licenses>
  <scm>
    <url>git@github.com:Rogach/scallop.git</url>
    <connection>scm:git:git@github.com:Rogach/scallop.git</connection>
  </scm>
  <developers>
    <developer>
      <id>rogach</id>
      <name>Platon Pronko</name>
      <url>http://rogach.org</url>
    </developer>
  </developers>
)

scalacOptions in (Compile, doc) ++= Opts.doc.sourceUrl("https://github.com/Rogach/scallop/tree/master/€{FILE_PATH}.scala")

parallelExecution in Test := false

site.settings

site.includeScaladoc("")

ghpages.settings

git.remoteRepo := "git@github.com:Rogach/scallop.git"
