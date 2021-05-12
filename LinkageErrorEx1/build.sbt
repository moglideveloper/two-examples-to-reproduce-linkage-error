import org.jetbrains.sbtidea.Keys.IntelliJPlatform

name := "spray-linkage-error"

scalaVersion := "2.13.2"

intellijBuild in ThisBuild := "211.6693.111"
intellijPlatform in ThisBuild := IntelliJPlatform.IdeaCommunity
intellijPlugins += "org.intellij.scala:2021.1.17".toPlugin
intellijDownloadSources := false
bundleScalaLibrary in ThisBuild := false

// for rest http calls over the network
libraryDependencies += "org.scalaj" %% "scalaj-http" % "2.4.2"


lazy val sprayLinkageErrorProject = project.in(file(".")).enablePlugins(SbtIdeaPlugin)
