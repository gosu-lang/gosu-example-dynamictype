uses gw.vark.antlibs.Ivy

function deps() {
  Ivy.retrieve(:pattern = "lib/[conf]/[artifact].[ext]")
}
