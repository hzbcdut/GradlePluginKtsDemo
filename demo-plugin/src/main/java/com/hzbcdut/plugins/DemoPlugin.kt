package com.hzbcdut.plugins

import com.android.build.gradle.AppExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.findByType

class DemoPlugin  :Plugin<Project>{
    override fun apply(project: Project) {
        println("Hello ==> 这里 自定义Gradle插件  111111  \"${project.parent?.name}\" from task!")
        val appExtension = project.extensions.findByType<AppExtension>()
        appExtension?.registerTransform(AsmTransform(project))
    }
}