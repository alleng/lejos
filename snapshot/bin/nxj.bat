@echo off
if "%OS%" == "Windows_NT" setlocal

if "%NXJ_HOME%" == "" goto :home_unset
	set NXJ_BIN=%NXJ_HOME%\bin
	goto :home_endif
:home_unset
	if "%OS%" == "Windows_NT" goto :home_nt
	echo You have to set the NXJ_HOME Variable on Windows 9x/ME.
	goto :EOF
:home_nt
	set "NXJ_TEMP=%cd%"
	cd /d "%0\.."
	set "NXJ_BIN=%cd%"
	cd ".."
	set "NXJ_HOME=%cd%"
	cd /d "%NXJ_TEMP%"
:home_endif

set NXJ_LIBS=%NXJ_HOME%\lib
set NXJ_LIBS_3rd=%NXJ_HOME%\3rdparty\lib

set NXJ_JAR_BCEL=%NXJ_LIBS_3rd%\bcel-5.1.jar
set NXJ_JAR_BLUECOVE=%NXJ_LIBS_3rd%\bluecove.jar
set NXJ_JAR_BLUECOVE_GPL=%NXJ_LIBS_3rd%\bluecove-gpl.jar
set NXJ_JAR_COMMONS_CLI=%NXJ_LIBS_3rd%\commons-cli-1.0.jar

set NXJ_JAR_CLASSES=%NXJ_LIBS%\classes.jar
set NXJ_JAR_JTOOLS=%NXJ_LIBS%\jtools.jar
set NXJ_JAR_PCCOMM=%NXJ_LIBS%\pccomm.jar
set NXJ_JAR_PCTOOLS=%NXJ_LIBS%\pctools.jar

set NXJ_CP_BLUECOVE=%NXJ_JAR_BLUECOVE%

set NXJ_CP_BOOT=%NXJ_JAR_CLASSES%
set NXJ_CP_LINK=%NXJ_JAR_BCEL%;%NXJ_JAR_COMMONS_CLI%;%NXJ_JAR_JTOOLS%
set NXJ_CP_TOOL=%NXJ_CP_BLUECOVE%;%NXJ_CP_LINK%;%NXJ_JAR_PCCOMM%;%NXJ_JAR_PCTOOLS%


java -Dnxj.home="%NXJ_HOME%" -DCOMMAND_NAME="nxj" -Djava.library.path="%NXJ_BIN%" -classpath "%NXJ_CP_TOOL%" lejos.pc.tools.NXJLinkAndUpload --bootclasspath "%NXJ_CP_BOOT%" --writeorder "LE" --classpath "." %*
:EOF
