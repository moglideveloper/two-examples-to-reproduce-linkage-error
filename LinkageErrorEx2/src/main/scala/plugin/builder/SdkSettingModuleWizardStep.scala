package plugin.builder

import com.intellij.ide.util.projectWizard.{ModuleBuilder, SdkSettingsStep, SettingsStep}
import com.intellij.openapi.options.ConfigurationException
import com.intellij.openapi.projectRoots.{JavaSdk, JavaSdkVersion, Sdk, SdkTypeId}
import com.intellij.openapi.util.Condition
import org.jetbrains.plugins.scala.project.{ScalaLanguageLevel, Version}
import org.jetbrains.sbt.{Sbt, SbtBundle}

class SdkSettingModuleWizardStep(settingsStep: SettingsStep,
                                 moduleBuilder: ModuleBuilder,
                                 sdkTypeIdFilter: Condition[_ >: SdkTypeId])
  extends SdkSettingsStep(settingsStep, moduleBuilder, sdkTypeIdFilter) {


  override def updateDataModel(): Unit = {
    settingsStep.getContext.setProjectJdk(myJdkComboBox.getSelectedJdk)
  }

  override def validate(): Boolean = super.validate() && {

    //here build.properties and build.sbt will be fetched first
    //to compute scala version and sbt version

    //extract below piece as a separate method, with scala version, sbt version as parameters
    //and then write test cases
    for {
      sdk <- Option(myJdkComboBox.getSelectedJdk)
      version <- Option("2.12.7")

      languageLevel <- ScalaLanguageLevel.findByVersion(version)
    } validateLanguageLevel(languageLevel, sdk)

    //here other rest apis will be invoked, if result is successful,
    //then first onLoadComplete will be called and then true will be returned
    true
  }

  private def validateLanguageLevel(languageLevel: ScalaLanguageLevel, sdk: Sdk): Unit = {

    def reportMisconfiguration(libraryName: String,
                               libraryVersion: String) =
      throw new ConfigurationException(
        SbtBundle.message("scala.version.requires.library.version", languageLevel.getVersion, libraryName, libraryVersion),
        SbtBundle.message("wrong.library.version", libraryName)
      )

    import JavaSdkVersion.JDK_1_8
    import Sbt.{Latest_1_0, Name}
    import ScalaLanguageLevel._

    languageLevel match {
      case Scala_3_0 if Option("1.2.8").exists(Version(_) >= Latest_1_0) => ()
      case Scala_3_0 => reportMisconfiguration(Name, Latest_1_0.presentation)
      case _ if languageLevel >= Scala_2_12 && !JavaSdk.getInstance().getVersion(sdk).isAtLeast(JDK_1_8) =>
        reportMisconfiguration("JDK", JDK_1_8.getDescription)
      case _ =>
    }
  }
}

