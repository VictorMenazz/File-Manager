@echo off

SET MAIN=FONTS\src
SET CONTROLLERS=FONTS\src\Domain\Controllers
SET CLASSES=FONTS\src\Domain\Classes
SET INTERFACE=FONTS\src\Interface
SET DATA=FONTS\src\Data
SET LIBS=FONTS\lib
SET BUILDDIR=EXE
SET CLASSPATH="EXE;lib\gson-2.10.jar;lib\hamcrest-core-1.3.jar;lib\junit-4.13.1.jar;com\google\gson\Gson"

IF /I "%1"=="all" GOTO all
IF /I "%1"=="clean" GOTO clean
IF /I "%1"=="help" GOTO help
GOTO error

:all
    MKDIR %BUILDDIR%\FONTS\src\Domain\Classes\StopWords
    XCOPY /y /s FONTS\src\Domain\Classes\StopWords  %BUILDDIR%\FONTS\src\Domain\Classes\StopWords
    MKDIR %BUILDDIR%\FONTS\src\Interface\Utils
    XCOPY /y /s FONTS\src\Interface\Utils  %BUILDDIR%\FONTS\src\Interface\Utils

    javac -d %BUILDDIR% -encoding UTF-8 -cp %CLASSPATH% -sourcepath . %MAIN%\Main.java  %CLASSES%\*.java %CONTROLLERS%\*.java %DATA%\*.java %INTERFACE%\*.java

    jar xf lib/gson-2.10.jar

    PUSHD %BUILDDIR%
    forfiles /s /m *.class /c "cmd /c echo @relpath" > aux.txt

    setlocal enableextensions disabledelayedexpansion
    set search="
    set replace=
    set textFile=aux.txt
    for /f "delims=" %%i in ('type "%textFile%" ^& break ^> "%textFile%" ') do (
        set "line=%%i"
        setlocal enabledelayedexpansion
        >>"%textFile%" echo(!line:%search%=%replace%!
        endlocal
    )

    (SET file-list=)
    FOR /f "delims=" %%x IN (aux.txt) DO (
        CALL SET file-list=%%file-list%% %%x
    )
    jar -cef FONTS.src.Main .\Main.jar %file-list% ..\com\google\gson\*.class ..\com\google\gson\annotations\*.class ..\com\google\gson\internal\*.class ..\com\google\gson\internal\bind\*.class ..\com\google\gson\internal\reflect\*.class ..\com\google\gson\internal\sql\*.class ..\com\google\gson\reflect\*.class ..\com\google\gson\stream\*.class
    POPD

    java -classpath %CLASSPATH%  -jar %BUILDDIR%\Main.jar
    GOTO :EOF

:clean
	rmdir /S /Q EXE
    rmdir /S /Q bin
	GOTO :EOF

:help
	@echo ".\Make.bat all                           -->  compile and execute de application"
	@echo ".\Make.bat clean                             -->  clean bin\ and EXE\ directories"
	GOTO :EOF

:error
    IF "%1"=="" (
        ECHO make: *** No targets specified and no makefile found.  Stop.
    ) ELSE (
        ECHO make: *** No rule to make target '%1%'. Stop.
    )
    GOTO :EOF