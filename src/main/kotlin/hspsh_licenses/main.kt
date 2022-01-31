package hspsh_licenses

import org.eclipse.jgit.api.Git
import org.eclipse.jgit.transport.URIish
import org.kohsuke.github.GitHub
import org.kohsuke.github.GitHubBuilder
import org.kohsuke.github.HttpException
import java.nio.file.Path
import java.nio.file.Paths
import kotlin.io.path.pathString

val organizationLocalPath: Path = Paths.get("/Users/kuba/Projects/hspsh")
val licensePath: Path = Paths.get("/Users/kuba/Temporary/LICENSE.txt")

val licenseText = licensePath.toFile().readText()

val repositoryPaths = listOf(
    Paths.get("/Users/kuba/Projects/hspsh/GitSample"),
    Paths.get("/Users/kuba/Projects/hspsh/nextcloud"),
    Paths.get("/Users/kuba/Projects/hspsh/hevelhack-2019"),
    Paths.get("/Users/kuba/Projects/hspsh/finanse"),
    Paths.get("/Users/kuba/Projects/hspsh/hs-library-back"),
    Paths.get("/Users/kuba/Projects/hspsh/spotty-kiwi"),
    Paths.get("/Users/kuba/Projects/hspsh/hs3core"),
    Paths.get("/Users/kuba/Projects/hspsh/adeptus-elektronicus"),
    Paths.get("/Users/kuba/Projects/hspsh/nfckey"),
    Paths.get("/Users/kuba/Projects/hspsh/offensive-printer"),
    Paths.get("/Users/kuba/Projects/hspsh/zabello"),
    Paths.get("/Users/kuba/Projects/hspsh/LCD-whois"),
    Paths.get("/Users/kuba/Projects/hspsh/e3future"),
    Paths.get("/Users/kuba/Projects/hspsh/meta"),
    Paths.get("/Users/kuba/Projects/hspsh/modern-cal-embed"),
    Paths.get("/Users/kuba/Projects/hspsh/wiki"),
    Paths.get("/Users/kuba/Projects/hspsh/biblioteka"),
    Paths.get("/Users/kuba/Projects/hspsh/dcr"),
    Paths.get("/Users/kuba/Projects/hspsh/flaschentaschen-toys"),
    Paths.get("/Users/kuba/Projects/hspsh/linuxworkshopspreparation"),
    Paths.get("/Users/kuba/Projects/hspsh/hacker-light"),
    Paths.get("/Users/kuba/Projects/hspsh/hs3web"),
    Paths.get("/Users/kuba/Projects/hspsh/shittyKeyboardino"),
    Paths.get("/Users/kuba/Projects/hspsh/domki"),
    Paths.get("/Users/kuba/Projects/hspsh/ptako-ososiost--unity"),
    Paths.get("/Users/kuba/Projects/hspsh/Documents"),
    Paths.get("/Users/kuba/Projects/hspsh/short-of-inventory"),
    Paths.get("/Users/kuba/Projects/hspsh/hacker-creed"),
    Paths.get("/Users/kuba/Projects/hspsh/infrastructure"),
    Paths.get("/Users/kuba/Projects/hspsh/squire"),
    Paths.get("/Users/kuba/Projects/hspsh/ErgoCogs"),
    Paths.get("/Users/kuba/Projects/hspsh/hsp.sh-legacy"),
    Paths.get("/Users/kuba/Projects/hspsh/matrix-poc"),
    Paths.get("/Users/kuba/Projects/hspsh/Mikrowarsztaty"),
    Paths.get("/Users/kuba/Projects/hspsh/seaside-pool"),
)

const val githubPersonalToken = ""

val github: GitHub = GitHubBuilder().withOAuthToken(githubPersonalToken).build();

data class RepositoryStats(
    val hasLicense: Boolean,
)

val processedRepoNames = setOf(
    "GitSample",
    "nextcloud",
    "hevelhack-2019",
    "finanse",
    "hs-library-back",
    "spotty-kiwi",
    "hs3core",
    "adeptus-elektronicus",
    "nfckey",
    "offensive-printer",
    "zabello",
    "LCD-whois",
    "e3future",
    "meta",
    "modern-cal-embed",
    "wiki",
    "biblioteka",
    "dcr",
    "flaschentaschen-toys",
    "linuxworkshopspreparation",
    "hacker-light",
    "hs3web",
    "shittyKeyboardino",
    "domki",
    "ptako-ososiost--unity",
    "Documents",
    "short-of-inventory",
    "hacker-creed",
    "infrastructure",
    "squire",
    "ErgoCogs",
    "hsp.sh-legacy",
)

private fun processRepository(repoPath: Path) {

//    println("Processing repository: $repoPath")

    val repoName = repoPath.fileName.pathString

    if (processedRepoNames.contains(repoName)) return

    println("Repository without license: $repoName")

//    repoPath.resolve("LICENSE.txt").toFile().writeText(licenseText)
//
    val upstreamGithubRepo = github.getRepository("hspsh/$repoName")

    val repo = Git.open(repoPath.toFile())
//
//    repo.remoteAdd()
//        .setName("fork")
//        .setUri(URIish(githubRepo.sshUrl))
//        .call()

    val branchName = repo.repository.branch

    try {
        val pullRequest = upstreamGithubRepo.createPullRequest(
            "Add license",
            "cubuspl42:$branchName",
            branchName,
            "Add license\n",
        )

        println("Pull request: ${pullRequest.htmlUrl}")
    } catch (e: HttpException) {
        println("Error: $e")
    }

//    repo.push()
//        .setRemote("fork")
//        .call()

//    githubRepo.createPullRequest()

//    println("Full branch: ${repo.repository.fullBranch}")

//
//    repo.add()
//        .addFilepattern(".")
//        .call()
//
//    repo.commit()
//        .setAuthor("Jakub Trzebiatowski", "cubuspl42@gmail.com")
//        .setMessage("Add license")
//        .setAmend(true)
//        .call()

//

//    val myFork = githubRepo.fork()
//
//    println("Forked repository: ${myFork.url}")

//    repo.createPullRequest("Add license", )
}

fun main() {

//    val repositoryPaths = organizationLocalPath.listDirectoryEntries()

    repositoryPaths.forEach {
        processRepository(
            repoPath = it,
        )
    }
}
