import com.android.build.gradle.internal.tasks.factory.dependsOn

// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.com.android.library) apply false
    alias(libs.plugins.compose.compiler) apply false
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.paparazzi) apply false
}
tasks.register("installGitHook") {
    group = "git"
    description = "Installs the Git pre-commit hook from scripts directory"

    doLast {
        delete(".git/hooks/pre-commit")
        copy {
            filePermissions {
                user {
                    read = true
                    execute = true
                }
                other.execute = false
            }

            from("${rootProject.rootDir}/scripts/pre-commit")
            into("${rootProject.rootDir}/.git/hooks")
        }
    }
}

tasks.getByPath("buildEnvironment").dependsOn(":installGitHook")
