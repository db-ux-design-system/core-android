// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.com.android.library) apply false
    alias(libs.plugins.jetbrains.kotlin.android) apply false
    alias(libs.plugins.compose.compiler) apply false
}
task<Copy>("installGitHook") {
    delete(".git/hooks/pre-commit")

    fileMode = 0x777
    from(File(rootProject.rootDir, "scripts/pre-commit"))
    into(File(rootProject.rootDir, ".git/hooks"))
}

tasks.getByPath(":app:preBuild").dependsOn(":installGitHook")
