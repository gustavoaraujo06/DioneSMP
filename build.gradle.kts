import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

plugins {
    id("java")
    id("io.github.goooler.shadow") version "8.1.8"
}

group = "com.dione"
version = "1.0"

repositories {
    mavenCentral()
    maven("https://repo.papermc.io/repository/maven-public/")
    maven("https://repo.essentialsx.net/releases/")
    maven("https://jitpack.io")
}

dependencies {
    compileOnly("io.papermc.paper:paper-api:1.21-R0.1-SNAPSHOT")
    compileOnly("com.github.MilkBowl:VaultAPI:1.7")
    compileOnly("net.essentialsx:EssentialsX:2.20.1")
    implementation("org.mongodb:mongodb-driver-sync:5.1.2");
}

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(21))
}

tasks.withType<ShadowJar>{
    archiveBaseName.set(project.name);
    archiveVersion.set(project.version.toString());
    archiveClassifier.set("")
    relocate("org.mongodb", "com.dione.relocated.org.mongodb")
}

tasks.jar{
    enabled = false;
}
tasks.build{
    dependsOn(tasks.withType<ShadowJar>())
}

tasks.test {
    useJUnitPlatform()
}