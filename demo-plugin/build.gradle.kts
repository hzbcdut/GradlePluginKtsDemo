plugins {
    `kotlin-dsl`
    id("java")
    id("java-library")
    id("maven-publish")
    id("java-gradle-plugin")
}

repositories {
    google()
    mavenCentral()
}

dependencies {
    implementation(gradleApi()) //gradle sdk
    implementation(localGroovy())

    implementation("com.android.tools.build:gradle:7.0.3")
    implementation("com.android.tools.build:gradle-api:7.0.3")
    //ASM依赖
    implementation("org.ow2.asm:asm:8.0")
    implementation("org.ow2.asm:asm-util:8.0")
    implementation("org.ow2.asm:asm-commons:8.0")
}

// 发布插件
group = "demo"
version = "1.0.0"
publishing {
    repositories {
        maven(url = "../repository")
    }
}

// 注册插件
gradlePlugin{
    plugins {
        register("DemoPlugin") {
            id = "demo-plugin"
            implementationClass = "com.hzbcdut.plugins.DemoPlugin"
        }
    }
}