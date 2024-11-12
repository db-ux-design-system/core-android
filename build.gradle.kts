// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.com.android.library) apply false
    alias(libs.plugins.jetbrains.kotlin.android) apply false
    alias(libs.plugins.compose.compiler) apply false
}
task("installGitHook") {
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

tasks.getByPath("buildEnvironment").dependsOn(":installGitHook")
