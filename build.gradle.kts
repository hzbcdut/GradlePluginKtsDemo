// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.android.application") version "7.1.1" apply false
    id("com.android.library") version "7.1.1" apply false
    id("org.jetbrains.kotlin.android") version "1.6.10" apply false
}

//翻一下 Gradle 的官方文档，看到现在创建任务的推荐使用register
// clean 就是Task的名称
tasks.register<Delete>("clean") {
    delete(rootProject.buildDir)
}