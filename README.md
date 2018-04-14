# Mellite-IDEA

## statement

Mellite-IDEA is an experimental project evaluating the feasibility to bring [Mellite](https://github.com/Sciss/Mellite/)
into IntelliJ IDEA, in order to use the Scala plugin for code editing.

It is (C)opyright 2018 by Hanns Holger Rutz. All rights reserved. Mellite-IDEA will be bundled with Mellite and will
is thus released under 
the [GNU General Public License](http://github.com/Sciss/Mellite/blob/master/licenses/Mellite-License.txt) v3+ and 
comes with absolutely no warranties. To contact the author, send an email to `contact at sciss.de`.

## building

This project builds with [sbt](http://scala-sbt.org/) and Scala 2.12. For preparation, you need to drop into
`sbt` and from its prompt run `ideaUpdate`. This will download the several-hundreds-megabyte IntelliJ into the
`idea` directory in the project directory, and thus take a while. Then you can open the project root in IntelliJ,
and you will have a suitable run configuration `IDEA` to launch an IDEA instance with the plugin enabled.

## contributing

Please see the file [CONTRIBUTING.md](CONTRIBUTING.md)

## running

The standalone jar, created via `sbt assembly` produces `Mellite.jar` which is double-clickable and can be run via:

    $ java -jar Mellite.jar

Runnable packages can be created via `sbt universal:packageBin` (all platforms) or `sbt debian:packageBin` (Debian).

## notes and to-do

- Mellite currently appears twice in the create-new-project dialogue, and I don't know why
  (one entry comes from project-template-factory, the other from module-builder).
  