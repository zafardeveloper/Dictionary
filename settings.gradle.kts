pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()

        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven {
            name = "GitHubPackages"

            // Replace GITHUB_USERID with your personal or organisation user ID and
            // REPOSITORY with the name of the repository on GitHub
            url = uri("https://maven.pkg.github.com/ilhom9045/Alif-Academy/")

            credentials {
                username = "baha5555"
                password ="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"
            }
        }
    }
}

rootProject.name = "bottomNavigation_Practise"
include(":app")
 