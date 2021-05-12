package plugin.wiz_factory

import com.intellij.ide.util.projectWizard.AbstractModuleBuilder
import com.intellij.openapi.ui.ValidationInfo
import com.intellij.platform.ProjectTemplate
import plugin.builder.AppBuilder

import javax.swing.Icon

class AppTemplate extends ProjectTemplate{
  override def getName: String = "template 1"

  override def getDescription: String = "linkage error with spray json classes"

  override def getIcon: Icon = null

  override def createModuleBuilder(): AbstractModuleBuilder = new AppBuilder

  override def validateSettings(): ValidationInfo = null
}
