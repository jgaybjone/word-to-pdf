import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("java")
    id("maven")
    kotlin("jvm") version "1.3.40"
    kotlin("plugin.spring") version "1.3.40"
}

group = "com.example"
version = "1.0-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_11
java.targetCompatibility = JavaVersion.VERSION_11

repositories {

    maven(url = "https://maven.aliyun.com/repository/public")
    mavenCentral()

}

dependencies {
    implementation("org.apache.poi:poi:3.10-FINAL")
    implementation("org.apache.commons:commons-collections4:4.4")
    implementation("fr.opensagres.xdocreport:org.apache.poi.xwpf.converter.core:1.0.6")
    implementation("org.eclipse.birt.runtime.3_7_1:com.lowagie.text:2.1.7")
    implementation("fr.opensagres.xdocreport:fr.opensagres.xdocreport.itext.extension:2.0.2")
    implementation("fr.opensagres.xdocreport:org.apache.poi.xwpf.converter.pdf:1.0.6")
    implementation("cn.afterturn:easypoi-base:4.1.0")
    implementation("cn.afterturn:easypoi-annotation:4.1.0")
    implementation("org.apache.poi:ooxml-schemas:1.4")
    implementation(kotlin("stdlib-jdk8"))
    implementation("org.slf4j:slf4j-api:1.7.26")
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "1.8"
    }
}
val compileKotlin: KotlinCompile by tasks
compileKotlin.kotlinOptions {
    jvmTarget = "1.8"
}
val compileTestKotlin: KotlinCompile by tasks
compileTestKotlin.kotlinOptions {
    jvmTarget = "1.8"
}