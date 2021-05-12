package plugin.wiz_factory

import com.intellij.ide.util.projectWizard.WizardContext
import com.intellij.platform.{ProjectTemplate, ProjectTemplatesFactory}

class AppWizardFactory extends ProjectTemplatesFactory{
  override def getGroups: Array[String] = Array("Sample")

  override def createTemplates(s: String, wizardContext: WizardContext): Array[ProjectTemplate] = {
    if (wizardContext.isCreatingNewProject) {
      Array( new AppTemplate )
    } else {
      Array.empty
    }
  }
}
