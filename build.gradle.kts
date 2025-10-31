plugins {
    kotlin("jvm") version "2.2.20"
    kotlin("plugin.serialization") version "2.2.20"
    `maven-publish`
    `java-library`
    id("org.jreleaser") version "1.20.0"
}

group = "dev.klenz.matthias.scxml"
version = System.getenv("GITHUB_REF")?.removePrefix("refs/tags/") ?: "0.1-local"

repositories {
    mavenCentral()
}

java {
    withJavadocJar()
    withSourcesJar()
}

dependencies {
    // https://mvnrepository.com/artifact/com.fasterxml.jackson.dataformat/jackson-dataformat-xml
    implementation("com.fasterxml.jackson.dataformat:jackson-dataformat-xml:2.20.0")

    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(23)
}

jreleaser {
    release {
        github {
            skipTag = true
            skipRelease = true
        }
    }
    signing {
        setActive("ALWAYS")
        armored = true
    }
    deploy {
        maven {
            //Portal Publisher API
            mavenCentral {
                register("sonatype") {
                    setActive("ALWAYS")
                    url = "https://central.sonatype.com/api/v1/publisher"
                    stagingRepository(layout.buildDirectory.dir("staging-deploy").get().asFile.path)
                }
            }
        }
    }
}

publishing {
    publications {
        register("release", MavenPublication::class) {
            from(components["java"])
            pom {
                name = "KScxmlParser"
                description = "This is a small library that provides Java/Kotlin classes that represent the SCXML standard."
                url = "https://github.com/12rcu/KScxmlParser"
                developers {
                    developer {
                        id = "12rcu"
                        name = "Matthias Klenz"
                    }
                }
                licenses {
                    license {
                        name = "Apache License, Version 2.0"
                        url = "https://www.apache.org/licenses/LICENSE-2.0"
                    }
                }
                scm {
                    connection.set("scm:git:git://github.com/12rcu/KScxmlParser.git")
                    developerConnection.set("scm:git:ssh://github.com/12rcu/KScxmlParser.git")
                    url.set("https://github.com/12rcu/KScxmlParser")
                }
            }
        }
    }
    repositories {
        maven {
            url = layout.buildDirectory.dir("staging-deploy").get().asFile.toURI()
        }
    }
}