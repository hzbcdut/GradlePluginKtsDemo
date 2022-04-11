// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.android.application") version "7.1.1" apply false
    id("com.android.library") version "7.1.1" apply false
    id("org.jetbrains.kotlin.android") version "1.6.10" apply false
}

buildscript {
    repositories {
        google()
        mavenCentral()
        // Local Maven仓库地址
        maven { url = uri("./repository/") }
    }

    dependencies {
        classpath("com.android.tools.build:gradle:7.0.3")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.6.10")
        // 引用本地Maven插件
        classpath("demo:demo-plugin:1.0.0")
    }
}
//翻一下 Gradle 的官方文档，看到现在创建任务的推荐使用register
// clean 就是Task的名称
tasks.register<Delete>("clean") {
    delete(rootProject.buildDir)
}