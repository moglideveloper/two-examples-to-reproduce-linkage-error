package plugin.builder

import com.intellij.ide.util.projectWizard.{ModuleWizardStep, SettingsStep}
import com.intellij.openapi.externalSystem.service.project.wizard.AbstractExternalModuleBuilder
import com.intellij.openapi.module.{JavaModuleType, ModuleType}
import com.intellij.openapi.projectRoots.{JavaSdk, SdkTypeId}
import org.jetbrains.sbt.project.SbtProjectSystem
import org.jetbrains.sbt.project.settings.SbtProjectSettings

class AppBuilder extends AbstractExternalModuleBuilder[SbtProjectSettings](
  SbtProjectSystem.Id,
  new SbtProjectSettings
)  {

  override def getModuleType: ModuleType[_] = JavaModuleType.getModuleType

  override def modifySettingsStep(settingsStep: SettingsStep): ModuleWizardStep = {

    //sdkSettingsStep(settingsStep)
    new AppSdkSettingStep( settingsStep, this, (_: SdkTypeId).isInstanceOf[JavaSdk])
  }
}
