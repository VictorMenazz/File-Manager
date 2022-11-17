@echo off
set SOURCEDIR=FONTS

if /I %1 == DomainControllerDriver goto :DomainControllerDriver
if /I %1 == FoldersControllerDriver goto :FoldersControllerDriver
if /I %1 == SearchControllerDriver goto :SearchControllerDriver
if /I %1 == AuthorsControllerDriver goto :AuthorsControllerDriver
if /I %1 == FolderDriver goto :FolderDriver
if /I %1 == DocumentDriver goto :DocumentDriver
if /I %1 == ContentDriver goto :ContentDriver
if /I %1 == SentenceDriver goto :SentenceDriver
if /I %1 == BooleanExpressionDriver goto :BooleanExpressionDriver
if /I %1 == AuthorDriver goto :AuthorDriver
if /I %1 == clean goto :clean

goto :bad

REM == Instructions to compile the project ===

REM === DomainControllerDriver ===
:DomainControllerDriver
javac -sourcepath %SOURCEDIR% .\FONTS\src\Domain\Controllers\Drivers\DomainControllerDriver.java -d .\EXE
jar cfe DomainControllerDriver.jar FONTS.src.Domain.Controllers.Drivers.DomainControllerDriver .\EXE\*
goto :EOF

REM === FoldersControllerDriver ===
:FoldersControllerDriver
javac -sourcepath %SOURCEDIR% .\FONTS\src\Domain\Controllers\Drivers\FoldersControllerDriver.java -d .\EXE
jar cfe FoldersControllerDriver.jar FONTS.src.Domain.Controllers.Drivers.FoldersControllerDriver .\EXE\*
goto :EOF

REM === AuthorsControllerDriver ===
:AuthorsControllerDriver
javac -sourcepath %SOURCEDIR% .\FONTS\src\Domain\Controllers\Drivers\AuthorsControllerDriver.java
jar cfe AuthorsControllerDriver.jar FONTS.src.Domain.Controllers.Drivers.AuthorsControllerDriver .\EXE\*
goto :EOF

REM === SearchControllerDriver ===
:SearchControllerDriver
javac -sourcepath %SOURCEDIR% .\FONTS\src\Domain\Controllers\Drivers\SearchControllerDriver.java
jar cfe SearchControllerDriver.jar FONTS.src.Domain.Controllers.Drivers.SearchControllerDriver .\EXE\*
goto :EOF

REM === FolderDriver ===
:FolderDriver
javac -sourcepath %SOURCEDIR% .\FONTS\src\Domain\Controllers\Drivers\FolderDriver.java
jar cfe FolderDriver.jar FONTS.src.Domain.Controllers.Drivers.FolderDriver .\EXE\*
goto :EOF

REM === DocumentDriver ===
:DocumentDriver
javac -sourcepath %SOURCEDIR% .\FONTS\src\Domain\Controllers\Drivers\DocumentDriver.java
jar cfe DocumentDriver.jar FONTS.src.Domain.Controllers.Drivers.DocumentDriver .\EXE\*
goto :EOF

REM === ContentDriver ===
:ContentDriver
javac -sourcepath %SOURCEDIR% .\FONTS\src\Domain\Controllers\Drivers\ContentDriver.java
jar cfe ContentDriver.jar FONTS.src.Domain.Controllers.Drivers.ContentDriver .\EXE\*
goto :EOF

REM === SentenceDriver ===
:SentenceDriver
javac -sourcepath %SOURCEDIR% .\FONTS\src\Domain\Controllers\Drivers\SentenceDriver.java
jar cfe SentenceDriver.jar FONTS.src.Domain.Controllers.Drivers.SentenceDriver .\EXE\*
goto :EOF

REM === BooleanExpression ===
:BooleanExpressionDriver
javac -sourcepath %SOURCEDIR% .\FONTS\src\Domain\Controllers\Drivers\BooleanExpressionDriver.java
jar cfe BooleanExpressionDriver.jar FONTS.src.Domain.Controllers.Drivers.BooleanExpressionDriver .\EXE\*
goto :EOF

REM === AuthorDriver ===
:AuthorDriver
javac -sourcepath %SOURCEDIR% .\FONTS\src\Domain\Controllers\Drivers\AuthorDriver.java
jar cfe AuthorDriver.jar FONTS.src.Domain.Controllers.Drivers.AuthorDriver .\EXE\*
goto :EOF

:clean
del /s /q *.class
goto :EOF

:bad
echo USAGE
echo Compile.bat testToExecute
echo Options:
echo DomainControllerDriver
echo FoldersControllerDriver
echo SearchControllerDriver
echo AuthorsControllerDriver
echo FolderDriver
echo DocumentDriver
echo ContentDriver
echo SentenceDriver
echo BooleanExpressionDriver
echo AuthorDriver
goto :EOF