package plugin.builder

import com.intellij.ide.util.projectWizard.{ModuleBuilder, SdkSettingsStep, SettingsStep}
import com.intellij.openapi.projectRoots.SdkTypeId
import com.intellij.openapi.ui.Messages
import com.intellij.openapi.util.Condition

case class User(name : String)

class AppSdkSettingStep(settingsStep: SettingsStep,
                        moduleBuilder: ModuleBuilder,
                        sdkTypeIdFilter: Condition[_ >: SdkTypeId])
  extends SdkSettingsStep(settingsStep, moduleBuilder, sdkTypeIdFilter) with ValidateUserOverHttp {


  override def updateDataModel(): Unit = settingsStep.getContext.setProjectJdk(myJdkComboBox.getSelectedJdk)

  override def validate(): Boolean = {

    val user = validateUserOverHttp("Coder")
    Messages.showMessageDialog(user.toString, "IdeaMsg Title", Messages.getInformationIcon)


    super.validate()
  }


}


