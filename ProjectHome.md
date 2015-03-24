cppcheclipse is an Eclipse plugin which integrates cppcheck (http://sourceforge.net/projects/cppcheck/) with the CDT project. You can run/configure cppcheck from the Eclipse UI.

Click on [Installation](Installation.md) for help how to install this plugin.

## News ##
  * 2010/05/16 - Version 0.9.6 comes with a big internal change: Now cppcheck is only started once for all files to be checked ([issue 14](https://code.google.com/p/cppcheclipse/issues/detail?id=14)). Additionally the option --append is supported ([issue 16](https://code.google.com/p/cppcheclipse/issues/detail?id=16)), as well as passing include directories to cppcheck ([issue 17](https://code.google.com/p/cppcheclipse/issues/detail?id=17)). This version is again compatible with cppcheck 1.43 ([issue 15](https://code.google.com/p/cppcheclipse/issues/detail?id=15)). Another bug with cppcheclipse not appearing in the project properties ([issue 13](https://code.google.com/p/cppcheclipse/issues/detail?id=13)) was also fixed.
  * 2010/02/04 - Just one day later I have to release 0.9.5 since the previous one (and also 0.9.3 I guess) suffered  from a serious bug ([issue 10](https://code.google.com/p/cppcheclipse/issues/detail?id=10)), where some of the command line arguments where not correctly transmitted to cppcheck. Furthermore [issue 09](https://code.google.com/p/cppcheclipse/issues/detail?id=09) and [issue 11](https://code.google.com/p/cppcheclipse/issues/detail?id=11) were fixed.
  * 2010/02/03 - Release 0.9.4 available which fixes [issue 7](https://code.google.com/p/cppcheclipse/issues/detail?id=7) and [issue 8](https://code.google.com/p/cppcheclipse/issues/detail?id=8). Additionally it adds support for calling it via a shortcut (Ctrl+Shift+C). This release requires cppcheck in at least version 1.40, as it uses its new --enable-flags. Some other bugfixes regarding marker deletion, problem list handling and incremental builder were also included.
  * 2009/11/22 - New release 0.9.3 available which fixes [issue 4](https://code.google.com/p/cppcheclipse/issues/detail?id=4), [issue 5](https://code.google.com/p/cppcheclipse/issues/detail?id=5) and [issue 6](https://code.google.com/p/cppcheclipse/issues/detail?id=6). The latter could lead to a nasty error, where the error mark was at the wrong positon. Some bugfixes for suppressions were added.
  * 2009/10/31 - New release 0.9.2 is available which is compatible with Java 1.5 and Eclipse 3.4 and 3.5. It adds support for check suppressions, an automatic update check for cppcheck and lots of bugfixes which include [issue 2](https://code.google.com/p/cppcheclipse/issues/detail?id=2) and [issue 3](https://code.google.com/p/cppcheclipse/issues/detail?id=3).
  * 2009/10/22 - Bugfix release 0.9.1 available which fixes [issue 1](https://code.google.com/p/cppcheclipse/issues/detail?id=1). This bug caused Eclipse to hang under some circumstances.
  * 2009/10/18 - First public version 0.9 of cppcheclipse available

## Screenshot ##
![http://cppcheclipse.googlecode.com/svn/images/main.gif](http://cppcheclipse.googlecode.com/svn/images/main.gif)