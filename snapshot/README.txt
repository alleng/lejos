To run lejos NXT Java test programs on Windows using this snapshot, you need to do the following:

- Install the Lego Mindstorms NXT software
- Install an SVN client for Windows.
- Checkout the snapshot project from SVN into <snapshot directory>
- Plug-in your NXT to a USB port
- Set NXJ_HOME to <snapshot directory>
- Add <snapshot directory>\bin to the PATH
- Type nxjflash to flash the firmware and menu
- Compile programs with nxjc
- Run programs with nxj

See below for how to compile and link your own program.

Install an SVN client for Windows
---------------------------------

Possibilities are TortoiseSVN or the Eclipse SVN plug-in.

For TortoiseSVN see http://tortoisesvn.net/downloads.

For the Eclipse plug-in, see http://sourceforge.net/docman?group id=178176

Check out the snapshot project
------------------------------

How you do this depends on which SVN Client you use. 

The URL for the repository is: https://lejos.svn.sourceforge.net/svnroot/lejos/trunk/snapshot

For Tortoise SVN for create the <snapshop directory> directory in Windows Explorer,
right click on it and select SVN Check out.

You will need to supply your sourceforge username and password.

Compiling and linking your own program
--------------------------------------

To compile, link and run Test.java, do:

- Type: nxjc Test.java
- Type: nxj Test


