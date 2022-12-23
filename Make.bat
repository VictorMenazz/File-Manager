@echo off

SET MAIN=FONTS\src
SET DOMAIN=FONTS\src\Domain
SET INTERFACE=FONTS\src\Interface
SET DATA=FONTS\src\Data
SET LIBS=FONTS\lib
SET BUILDDIR=EXE
SET CLASSPATH="bin;lib\gson-2.10.jar"

IF /I "%1"=="all" GOTO all
IF /I "%1"=="clean" GOTO clean
IF /I "%1"=="help" GOTO help
GOTO error

:all
    MKDIR %BUILDDIR%\FONTS\src\Domain\Classes\StopWords
    XCOPY /y /s FONTS\src\Domain\Classes\StopWords  %BUILDDIR%\FONTS\src\Domain\Classes\
    javac -cp %CLASSPATH% -sourcepath . %MAIN%\Main.java -d %BUILDDIR%
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
    jar -cef FONTS.src.Main .\Main.jar %file-list%
    POPD

    java -jar %BUILDDIR%\Main.jar
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