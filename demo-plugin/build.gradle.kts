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
    implementation(gradleApi())
    implementation(localGroovy())
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