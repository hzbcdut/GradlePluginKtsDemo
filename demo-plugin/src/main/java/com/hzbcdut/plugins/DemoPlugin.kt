package com.hzbcdut.plugins

import org.gradle.api.Plugin
import org.gradle.api.Project

class DemoPlugin  :Plugin<Project>{
    override fun apply(target: Project) {
        println("Hello ==> 这里 自定义Gradle插件  111111  \"${target.parent?.name}\" from task!")
    }
}