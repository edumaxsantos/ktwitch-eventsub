plugins {
    kotlin("jvm") version "2.1.0"
    kotlin("plugin.serialization") version "2.1.0"
    `maven-publish`
}

group = "com.github.edumaxsantos.ktwitch"
version = "0.1.0"

val basicUrl = "github.com/edumaxsantos/ktwitch-eventsub"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.7.3")
    implementation("org.jetbrains.kotlin:kotlin-reflect:2.1.0")
    implementation("org.reflections:reflections:0.10.2")
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(22)
}

publishing {
    repositories {
        maven {
            name = "GitHubPackages"
            url = uri("https://maven.pkg.$basicUrl")
            credentials {
                username = System.getenv("GITHUB_ACTOR")
                password = System.getenv("GITHUB_TOKEN")
            }
        }
    }

    publications {
        register<MavenPublication>("gpr") {
            from(components["kotlin"])
            groupId = project.group as String
            artifactId = project.name
            version = project.version as String

            pom {
                name.set("KTwitch EventSub")
                description.set("A Kotlin library for Twitch EventSub integration.")
                url.set("https://$basicUrl")

                licenses {
                    license {
                        name.set("MIT License")
                        url.set("https://opensource.org/licenses/MIT")
                    }
                }

                developers {
                    developer {
                        id.set("edumaxsantos")
                        name.set("Eduardo Santos")
                        email.set("eduardosantos@hey.com")
                    }
                }

                scm {
                    connection.set("scm:git:git://$basicUrl.git")
                    developerConnection.set("scm:git:ssh://$basicUrl.git")
                    url.set("https://$basicUrl")
                }
            }
        }
    }
}