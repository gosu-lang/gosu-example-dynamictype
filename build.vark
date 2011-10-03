DefaultTarget = "build"

@Depends({"clean", "jar"})
function build() {
}

function deps() {
  Ivy.retrieve(:pattern = "lib/[conf]/[artifact].[ext]")
}

@Depends("deps")
function compile() {
  Ant.mkdir(:dir = file("target/classes"))
  Ant.javac(:srcdir = path(file("src")), :destdir = file("target/classes"), :classpath = path(file("lib/gosu-core-api.jar")))
}

@Depends("compile")
function jar() {
  Ant.jar(:destfile = file("target/dynamictype.jar"), :basedir = file("target/classes"), :manifest = file("src/META-INF/MANIFEST.MF"))
}

function clean() {
  Ant.delete(:dir = file("target"), :includeemptydirs = true)
}
