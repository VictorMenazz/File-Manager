@echo off

SET CLASSES=FONTS\src\Domain\Classes
SET CONTROLLERS=FONTS\src\Domain\Controllers
SET DRIVERS=FONTS\src\Domain\Controllers\Drivers
SET JUNITS=FONTS\src\Domain\Controllers\JUnits
SET STUBS=FONTS\src\Domain\Controllers\Stubs
SET DOMAIN=FONTS\src\Domain
SET INTERFACE=FONTS\src\Interface
SET DATA=FONTS\src\Data
SET LIBS=FONTS\lib
SET BUILDDIR=EXE
SET CLASSPATH="bin;lib\junit-4.13.1.jar;lib\hamcrest-core-1.3.jar;\gson-2.10.jar"

IF /I "%1"=="default" GOTO default
IF /I "%1"=="JUnits" GOTO JUnits
IF /I "%1"=="FolderJUnit" GOTO FolderJUnit
IF /I "%1"=="DocumentJUnit" GOTO DocumentJUnit
IF /I "%1"=="ContentJUnit" GOTO ContentJUnit
IF /I "%1"=="SentenceJUnit" GOTO SentenceJUnit
IF /I "%1"=="BooleanExpressionJUnit" GOTO BooleanExpressionJUnit
IF /I "%1"=="AuthorJUnit" GOTO AuthorJUnit
IF /I "%1"=="FoldersControllerDriver" GOTO FoldersControllerDriver
IF /I "%1"=="DomainControllerDriver" GOTO DomainControllerDriver
IF /I "%1"=="AuthorsControllerDriver" GOTO AuthorsControllerDriver
IF /I "%1"=="SearchControllerDriver" GOTO SearchControllerDriver
IF /I "%1"=="FolderDriver" GOTO FolderDriver
IF /I "%1"=="AuthorDriver" GOTO AuthorDriver
IF /I "%1"=="DocumentDriver" GOTO DocumentDriver
IF /I "%1"=="ContentDriver" GOTO ContentDriver
IF /I "%1"=="SentenceDriver" GOTO SentenceDriver
IF /I "%1"=="BooleanExpressionDriver" GOTO BooleanExpressionDriver
IF /I "%1"=="Drivers" GOTO Drivers
IF /I "%1"=="runAutomaticDrivers" GOTO runAutomaticDrivers
IF /I "%1"=="runDrivers" GOTO runDrivers
IF /I "%1"=="runAutomaticSearchController" GOTO runAutomaticSearchController
IF /I "%1"=="runAutomaticAuthorsController" GOTO runAutomaticAuthorsController
IF /I "%1"=="runAutomaticFoldersController" GOTO runAutomaticFoldersController
IF /I "%1"=="runAutomaticDomainController" GOTO runAutomaticDomainController
IF /I "%1"=="runAutomaticFolder" GOTO runAutomaticFolder
IF /I "%1"=="runAutomaticDocument" GOTO runAutomaticDocument
IF /I "%1"=="runAutomaticContent" GOTO runAutomaticContent
IF /I "%1"=="runAutomaticSentence" GOTO runAutomaticSentence
IF /I "%1"=="runAutomaticBooleanExpression" GOTO runAutomaticBooleanExpression
IF /I "%1"=="runAutomaticAuthor" GOTO runAutomaticAuthor
IF /I "%1"=="clean" GOTO clean
IF /I "%1"=="help" GOTO help
GOTO error

:default
	CALL make.bat JUnits
	GOTO :EOF

:JUnits
	@javac -cp %CLASSPATH% %JUNITS%\*.java %CLASSES%\*.java %STUBS%\*.java -d bin\
	@echo "Folder JUnit test..."
	@java -cp %CLASSPATH% org.junit.runner.JUnitCore FONTS.src.Domain.Controllers.JUnits.FolderTest
	@echo "Document JUnit test..."
	@java -cp %CLASSPATH% org.junit.runner.JUnitCore FONTS.src.Domain.Controllers.JUnits.DocumentTest
	@echo "Content JUnit test..."
	@java -cp %CLASSPATH% org.junit.runner.JUnitCore FONTS.src.Domain.Controllers.JUnits.ContentTest
	@echo "Sentence JUnit test..."
	@java -cp %CLASSPATH% org.junit.runner.JUnitCore FONTS.src.Domain.Controllers.JUnits.SentenceTest
	@echo "Author JUnit test..."
	@java -cp %CLASSPATH% org.junit.runner.JUnitCore FONTS.src.Domain.Controllers.JUnits.AuthorTest
	@echo "BooleanExpression JUnit test..."
	@java -cp %CLASSPATH% org.junit.runner.JUnitCore FONTS.src.Domain.Controllers.JUnits.BooleanExpressionTest
	GOTO :EOF

:FolderJUnit
	@javac -cp %CLASSPATH% %JUNITS%\*.java %CLASSES%\*.java %STUBS%\*.java -d bin\
	@java -cp %CLASSPATH% org.junit.runner.JUnitCore FONTS.src.Domain.Controllers.JUnits.FolderTest
	GOTO :EOF

:DocumentJUnit
	@javac -cp %CLASSPATH% %JUNITS%\*.java %CLASSES%\*.java %STUBS%\*.java -d bin\
	@java -cp %CLASSPATH% org.junit.runner.JUnitCore FONTS.src.Domain.Controllers.JUnits.DocumentTest
	GOTO :EOF

:ContentJUnit
	@javac -cp %CLASSPATH% %JUNITS%\*.java %CLASSES%\*.java %STUBS%\*.java -d bin\
	@java -cp %CLASSPATH% org.junit.runner.JUnitCore FONTS.src.Domain.Controllers.JUnits.ContentTest
	GOTO :EOF

:SentenceJUnit
	@javac -cp %CLASSPATH% %JUNITS%\*.java %CLASSES%\*.java %STUBS%\*.java -d bin\
	@java -cp %CLASSPATH% org.junit.runner.JUnitCore FONTS.src.Domain.Controllers.JUnits.SentenceTest
	GOTO :EOF

:BooleanExpressionJUnit
	@javac -cp %CLASSPATH% %JUNITS%\*.java %CLASSES%\*.java %STUBS%\*.java -d bin\
	@java -cp %CLASSPATH% org.junit.runner.JUnitCore FONTS.src.Domain.Controllers.JUnits.BooleanExpressionTest
	GOTO :EOF

:AuthorJUnit
	@javac -cp %CLASSPATH% %JUNITS%\*.java %CLASSES%\*.java %STUBS%\*.java -d bin\
	@java -cp %CLASSPATH% org.junit.runner.JUnitCore FONTS.src.Domain.Controllers.JUnits.AuthorTest
	GOTO :EOF



:FoldersControllerDriver
    MKDIR %BUILDDIR%\FONTS\src\Domain\Classes\StopWords
    XCOPY /y /s FONTS\src\Domain\Classes\StopWords  %BUILDDIR%\FONTS\src\Domain\Classes\
	javac -cp . -sourcepath . %DRIVERS%\FoldersControllerDriver.java -d %BUILDDIR%
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
	jar -cef FONTS.src.Domain.Controllers.Drivers.FoldersControllerDriver .\FoldersControllerDriver.jar %file-list%
	POPD
	GOTO :EOF

:DomainControllerDriver
    MKDIR %BUILDDIR%\FONTS\src\Domain\Classes\StopWords
    XCOPY /y /s FONTS\src\Domain\Classes\StopWords  %BUILDDIR%\FONTS\src\Domain\Classes\
    javac -cp . -sourcepath . %DRIVERS%\DomainControllerDriver.java -d %BUILDDIR%
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
    jar -cef FONTS.src.Domain.Controllers.Drivers.DomainControllerDriver .\DomainControllerDriver.jar %file-list%
    POPD
    GOTO :EOF

:AuthorsControllerDriver
    MKDIR %BUILDDIR%\FONTS\src\Domain\Classes\StopWords
    XCOPY /y /s FONTS\src\Domain\Classes\StopWords  %BUILDDIR%\FONTS\src\Domain\Classes\
    javac -cp . -sourcepath . %DRIVERS%\AuthorsControllerDriver.java -d %BUILDDIR%
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
    jar -cef FONTS.src.Domain.Controllers.Drivers.AuthorsControllerDriver .\AuthorsControllerDriver.jar %file-list%
    POPD
    GOTO :EOF

:SearchControllerDriver
    MKDIR %BUILDDIR%\FONTS\src\Domain\Classes\StopWords
    XCOPY /y /s FONTS\src\Domain\Classes\StopWords  %BUILDDIR%\FONTS\src\Domain\Classes\
    javac -cp . -sourcepath . %DRIVERS%\SearchControllerDriver.java -d %BUILDDIR%
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
    jar -cef FONTS.src.Domain.Controllers.Drivers.SearchControllerDriver .\SearchControllerDriver.jar %file-list%
    POPD
    GOTO :EOF

:FolderDriver
    MKDIR %BUILDDIR%\FONTS\src\Domain\Classes\StopWords
    XCOPY /y /s FONTS\src\Domain\Classes\StopWords  %BUILDDIR%\FONTS\src\Domain\Classes\
    javac -cp . -sourcepath . %DRIVERS%\FolderDriver.java -d %BUILDDIR%
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
    jar -cef FONTS.src.Domain.Controllers.Drivers.FolderDriver .\FolderDriver.jar %file-list%
    POPD
    GOTO :EOF

:AuthorDriver
    javac -cp . -sourcepath . %DRIVERS%\AuthorDriver.java -d %BUILDDIR%
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
    jar -cef FONTS.src.Domain.Controllers.Drivers.AuthorDriver .\AuthorDriver.jar %file-list%
    POPD
    GOTO :EOF

:DocumentDriver
    MKDIR %BUILDDIR%\FONTS\src\Domain\Classes\StopWords
    XCOPY /y /s FONTS\src\Domain\Classes\StopWords  %BUILDDIR%\FONTS\src\Domain\Classes\
    javac -cp . -sourcepath . %DRIVERS%\DocumentDriver.java -d %BUILDDIR%
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
    jar -cef FONTS.src.Domain.Controllers.Drivers.DocumentDriver .\DocumentDriver.jar %file-list%
    POPD
    GOTO :EOF

:ContentDriver
    MKDIR %BUILDDIR%\FONTS\src\Domain\Classes\StopWords
    XCOPY /y /s FONTS\src\Domain\Classes\StopWords  %BUILDDIR%\FONTS\src\Domain\Classes\
    javac -cp . -sourcepath . %DRIVERS%\ContentDriver.java -d %BUILDDIR%
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
    jar -cef FONTS.src.Domain.Controllers.Drivers.ContentDriver .\ContentDriver.jar %file-list%
    POPD
    GOTO :EOF

:SentenceDriver
    MKDIR %BUILDDIR%\FONTS\src\Domain\Classes\StopWords
    XCOPY /y /s FONTS\src\Domain\Classes\StopWords  %BUILDDIR%\FONTS\src\Domain\Classes\
    javac -cp . -sourcepath . %DRIVERS%\SentenceDriver.java -d %BUILDDIR%
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
    jar -cef FONTS.src.Domain.Controllers.Drivers.SentenceDriver .\SentenceDriver.jar %file-list%
    POPD
    GOTO :EOF

:BooleanExpressionDriver
    MKDIR %BUILDDIR%\FONTS\src\Domain\Classes\StopWords
    XCOPY /y /s FONTS\src\Domain\Classes\StopWords  %BUILDDIR%\FONTS\src\Domain\Classes\
    javac -cp . -sourcepath . %DRIVERS%\BooleanExpressionDriver.java -d %BUILDDIR%
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
    jar -cef FONTS.src.Domain.Controllers.Drivers.BooleanExpressionDriver .\BooleanExpressionDriver.jar %file-list%
    POPD
    GOTO :EOF

:Drivers
	MKDIR %BUILDDIR%\FONTS\src\Domain\Classes\StopWords
    XCOPY /y /s FONTS\src\Domain\Classes\StopWords  %BUILDDIR%\FONTS\src\Domain\Classes\
    javac -cp . -sourcepath . %DRIVERS%\DomainControllerDriver.java -d %BUILDDIR%
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
    jar -cef FONTS.src.Domain.Controllers.Drivers.DomainControllerDriver .\DomainControllerDriver.jar %file-list%
    POPD
    javac -cp . -sourcepath . %DRIVERS%\FoldersControllerDriver.java -d %BUILDDIR%
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
    jar -cef FONTS.src.Domain.Controllers.Drivers.FoldersControllerDriver .\FoldersControllerDriver.jar %file-list%
    POPD
    javac -cp . -sourcepath . %DRIVERS%\AuthorsControllerDriver.java -d %BUILDDIR%
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
    jar -cef FONTS.src.Domain.Controllers.Drivers.AuthorsControllerDriver .\AuthorsControllerDriver.jar %file-list%
    POPD
    javac -cp . -sourcepath . %DRIVERS%\SearchControllerDriver.java -d %BUILDDIR%
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
    jar -cef FONTS.src.Domain.Controllers.Drivers.SearchControllerDriver .\SearchControllerDriver.jar %file-list%
    POPD
    javac -cp . -sourcepath . %DRIVERS%\FolderDriver.java -d %BUILDDIR%
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
    jar -cef FONTS.src.Domain.Controllers.Drivers.FolderDriver .\FolderDriver.jar %file-list%
    POPD
    javac -cp . -sourcepath . %DRIVERS%\AuthorDriver.java -d %BUILDDIR%
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
    jar -cef FONTS.src.Domain.Controllers.Drivers.AuthorDriver .\AuthorDriver.jar %file-list%
    POPD
    javac -cp . -sourcepath . %DRIVERS%\DocumentDriver.java -d %BUILDDIR%
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
    jar -cef FONTS.src.Domain.Controllers.Drivers.DocumentDriver .\DocumentDriver.jar %file-list%
    POPD
    javac -cp . -sourcepath . %DRIVERS%\ContentDriver.java -d %BUILDDIR%
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
    jar -cef FONTS.src.Domain.Controllers.Drivers.ContentDriver .\ContentDriver.jar %file-list%
    POPD
    javac -cp . -sourcepath . %DRIVERS%\SentenceDriver.java -d %BUILDDIR%
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
    jar -cef FONTS.src.Domain.Controllers.Drivers.SentenceDriver .\SentenceDriver.jar %file-list%
    POPD
    javac -cp . -sourcepath . %DRIVERS%\BooleanExpressionDriver.java -d %BUILDDIR%
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
    jar -cef FONTS.src.Domain.Controllers.Drivers.BooleanExpressionDriver .\BooleanExpressionDriver.jar %file-list%
    POPD
    cd ..
	GOTO :EOF

:runAutomaticDrivers
	java -jar %BUILDDIR%\SearchControllerDriver.jar < test\inputTest\inpSearchController.txt
	java -jar %BUILDDIR%\AuthorsControllerDriver.jar < test\inputTest\inpAuthorsController.txt
	java -jar %BUILDDIR%\FoldersControllerDriver.jar < test\inputTest\inpFoldersController.txt
	java -jar %BUILDDIR%\DomainControllerDriver.jar < test\inputTest\inpDomainController.txt

	java -jar %BUILDDIR%\FolderDriver.jar < test\inputTest\inpFolder.txt
    java -jar %BUILDDIR%\DocumentDriver.jar < test\inputTest\inpDocument.txt
    java -jar %BUILDDIR%\ContentDriver.jar < test\inputTest\inpContent.txt
    java -jar %BUILDDIR%\SentenceDriver.jar < test\inputTest\inpSentence.txt
    java -jar %BUILDDIR%\BooleanExpressionDriver.jar < test\inputTest\inpBooleanExpression.txt
    java -jar %BUILDDIR%\AuthorDriver.jar < test\inputTest\inpAuthor.txt
	GOTO :EOF

:runDrivers
	java -jar %BUILDDIR%\SearchControllerDriver.jar
	java -jar %BUILDDIR%\AuthorsControllerDriver.jar
	java -jar %BUILDDIR%\FoldersControllerDriver.jar
	java -jar %BUILDDIR%\DomainControllerDriver.jar

    java -jar %BUILDDIR%\FolderDriver.jar
    java -jar %BUILDDIR%\DocumentDriver.jar
    java -jar %BUILDDIR%\ContentDriver.jar
    java -jar %BUILDDIR%\SentenceDriver.jar
    java -jar %BUILDDIR%\BooleanExpressionDriver.jar
    java -jar %BUILDDIR%\AuthorDriver.jar
	GOTO :EOF

:runAutomaticSearchController
	java -jar %BUILDDIR%\SearchControllerDriver.jar < test\inputTest\inpSearchController.txt
	GOTO :EOF

:runAutomaticAuthorsController
	java -jar %BUILDDIR%\AuthorsControllerDriver.jar < test\inputTest\inpAuthorsController.txt
	GOTO :EOF

:runAutomaticFoldersController
	java -jar %BUILDDIR%\FoldersControllerDriver.jar < test\inputTest\inpFoldersController.txt
	GOTO :EOF

:runAutomaticDomainController
	java -jar %BUILDDIR%\DomainControllerDriver.jar < test\inputTest\inpDomainController.txt
	GOTO :EOF

:runAutomaticFolder
	java -jar %BUILDDIR%\FolderDriver.jar < test\inputTest\inpFolder.txt
	GOTO :EOF

:runAutomaticDocument
	java -jar %BUILDDIR%\DocumentDriver.jar < test\inputTest\inpDocument.txt
	GOTO :EOF

:runAutomaticContent
	java -jar %BUILDDIR%\ContentDriver.jar < test\inputTest\inpContent.txt
	GOTO :EOF

:runAutomaticSentence
	java -jar %BUILDDIR%\SentenceDriver.jar < test\inputTest\inpSentence.txt
	GOTO :EOF

:runAutomaticBooleanExpression
	java -jar %BUILDDIR%\BooleanExpressionDriver.jar < test\inputTest\inpBooleanExpression.txt
	GOTO :EOF

:runAutomaticAuthor
	java -jar %BUILDDIR%\AuthorDriver.jar < test\inputTest\inpAuthor.txt
	GOTO :EOF

:clean
	rmdir /S /Q EXE
    rmdir /S /Q bin
	GOTO :EOF

:help
	@echo ".\Make.bat default or .\Make.bat JUnits      -->  compile all JUnits"
	@echo ".\Make.bat FolderJUnit                       -->  compile Folder JUnit"
	@echo ".\Make.bat DocumentJUnit                     -->  compile Document JUnit"
	@echo ".\Make.bat ContentJUnit                      -->  compile Content JUnit"
	@echo ".\Make.bat SentenceJUnit                     -->  compile Sentence JUnit"
	@echo ".\Make.bat BooleanExpressionJUnit            -->  compile BooleanExpression JUnit"
	@echo ".\Make.bat AuthorJUnit                       -->  compile Author JUnit"
	@echo ".\Make.bat FoldersControllerDriver           -->  compile FoldersController Driver"
	@echo ".\Make.bat DomainControllerDriver            -->  compile DomainController Driver"
	@echo ".\Make.bat AuthorsControllerDriver           -->  compile AuthorsController Driver"
	@echo ".\Make.bat SearchControllerDriver            -->  compile FoldersController Driver"
	@echo ".\Make.bat FolderDriver                      -->  compile Folder Driver"
	@echo ".\Make.bat AuthorDriver                      -->  compile Author Driver"
	@echo ".\Make.bat DocumentDriver                    -->  compile Document Driver"
	@echo ".\Make.bat ContentDriver                     -->  compile Content Driver"
	@echo ".\Make.bat SentenceDriver                    -->  compile Sentence Driver"
	@echo ".\Make.bat BooleanExpressionDriver           -->  compile Boolean Expression Driver"
	@echo ".\Make.bat Drivers                           -->  compile All Drivers"
	@echo ".\Make.bat runAutomaticDrivers               -->  run drivers automatically with input files redesigned"
	@echo ".\Make.bat runDrivers                        -->  execute drivers"
	@echo ".\Make.bat runAutomaticSearchController      -->  run driver automatically with input files redesigned"
	@echo ".\Make.bat runAutomaticAuthorsController     -->  run driver automatically with input files redesigned"
	@echo ".\Make.bat runAutomaticFoldersController     -->  run driver automatically with input files redesigned"
	@echo ".\Make.bat runAutomaticDomainController      -->  run driver automatically with input files redesigned"
	@echo ".\Make.bat runAutomaticFolder                -->  run driver automatically with input files redesigned"
	@echo ".\Make.bat runAutomaticDocument              -->  run driver automatically with input files redesigned"
	@echo ".\Make.bat runAutomaticContent               -->  run driver automatically with input files redesigned"
	@echo ".\Make.bat runAutomaticSentence              -->  run driver automatically with input files redesigned"
	@echo ".\Make.bat runAutomaticBooleanExpression     -->  run driver automatically with input files redesigned"
	@echo ".\Make.bat runAutomaticAuthor                -->  run driver automatically with input files redesigned"
	@echo ".\Make.bat clean                             -->  clean bin\ and EXE\ directories"
	GOTO :EOF

:error
    IF "%1"=="" (
        ECHO make: *** No targets specified and no makefile found.  Stop.
    ) ELSE (
        ECHO make: *** No rule to make target '%1%'. Stop.
    )
    GOTO :EOF