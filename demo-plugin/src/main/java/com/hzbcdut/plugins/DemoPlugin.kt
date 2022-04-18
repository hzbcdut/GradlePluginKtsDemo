package com.hzbcdut.plugins

import com.android.build.gradle.AppExtension
import com.hzbcdut.plugins.transform.InjectImplTransform
import com.hzbcdut.plugins.transform.InjectTransform
import com.hzbcdut.plugins.transform.InjectsTransform
import com.hzbcdut.plugins.transform.process.ProcessingData
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.findByType

typealias DP = DemoPlugin

class DemoPlugin  :Plugin<Project>{
    override fun apply(project: Project) {
        println("Hello ==> 这里 自定义Gradle插件  111  \"${project.parent?.name}\" from task!")
        val appExtension = project.extensions.findByType<AppExtension>()
        data = ProcessingData()
        val injectTransform = arrayOf(InjectTransform(), InjectImplTransform(), InjectsTransform())
        injectTransform.forEach {
            appExtension?.registerTransform(it)
        }
//        appExtension?.registerTransform(AsmTransform(project))
    }

    companion object {
        var data = ProcessingData()
            private set
    }
}