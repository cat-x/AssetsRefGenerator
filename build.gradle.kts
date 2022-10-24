plugins {
    id("java")
    id("org.jetbrains.intellij") version "1.9.0"
}

version = "1.1.0"

repositories {
    mavenCentral()
}

dependencies {
    implementation("net.coobird:thumbnailator:0.4.17")
}
// Configure Gradle IntelliJ Plugin - read more: https://github.com/JetBrains/gradle-intellij-plugin
intellij {
//    version.set("2022.1.1")
//    plugins.set(
//        listOf(
//            //因为要生成 dart 文件，需要使用到 dart 插件中的类，所以这里要引入 dart 插件
//            "Dart:223.7126.2", //https://plugins.jetbrains.com/plugin/6351-dart/versions
//            "io.flutter:70.2.6",//https://plugins.jetbrains.com/plugin/9212-flutter/versions
//            "org.jetbrains.plugins.yaml:223.6646.21", //https://plugins.jetbrains.com/plugin/13126-yaml/versions
//        )
//    )

    version.set("2021.3.1")//(2021.3.1) Patch 1
    type.set("IC") // Target IDE Platform

    plugins.set(
        listOf(
            //因为要生成 dart 文件，需要使用到 dart 插件中的类，所以这里要引入 dart 插件
            "Dart:213.5744.122", //https://plugins.jetbrains.com/plugin/6351-dart/versions
            "io.flutter:70.2.3",//https://plugins.jetbrains.com/plugin/9212-flutter/versions
            "org.jetbrains.plugins.yaml:213.6461.19", //https://plugins.jetbrains.com/plugin/13126-yaml/versions
        )
    )
}

tasks {

    buildSearchableOptions {
        enabled = false
    }

    // Set the JVM compatibility versions
    withType<JavaCompile> {
        sourceCompatibility = "11"
        targetCompatibility = "11"
    }

    patchPluginXml {
        sinceBuild.set("213.6461.79")
        untilBuild.set("223.*")
    }

    signPlugin {
        certificateChain.set(System.getenv("CERTIFICATE_CHAIN"))
        privateKey.set(System.getenv("PRIVATE_KEY"))
        password.set(System.getenv("PRIVATE_KEY_PASSWORD"))
    }

    publishPlugin {
        token.set(System.getenv("PUBLISH_TOKEN"))
    }
}
