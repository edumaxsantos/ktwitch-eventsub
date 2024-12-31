plugins {
    kotlin("jvm") version "2.1.0"
    kotlin("plugin.serialization") version "2.1.0"
    signing
    `maven-publish`
}

group = "io.github.edumaxsantos.ktwitch"
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

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(22))
    }
}

kotlin {
    jvmToolchain(22)
}

publishing {
    publications {
        register<MavenPublication>("mavenJava") {
            from(components["kotlin"])
            groupId = project.group.toString()
            artifactId = project.name
            version = project.version.toString()

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
                    developerConnection.set("scm:git:ssh://git@$basicUrl.git")
                    url.set("https://$basicUrl")
                }
            }
        }
    }

    repositories {
        maven {
            name = "OSSRH"
            url = uri("https://s01.oss.sonatype.org/service/local/staging/deploy/maven2/")
            credentials {
                username = System.getenv("OSSRH_USERNAME")
                password = System.getenv("OSSRH_TOKEN")
            }
        }
    }
}

signing {
    useGpgCmd()
    sign(publishing.publications["gpr"])
}