@echo off

if /i %1 == DomainControllerDriver goto :DomainControllerDriver
if /i %1 == FoldersControllerDriver goto :FoldersControllerDriver
if /i %1 == SearchControllerDriver goto :SearchControllerDriver
if /i %1 == AuthorsControllerDriver goto :AuthorsControllerDriver
if /i %1 == FolderDriver goto :FolderDriver
if /i %1 == DocumentDriver goto :DocumentDriver
if /i %1 == ContentDriver goto :ContentDriver
if /i %1 == SentenceDriver goto :SentenceDriver
if /i %1 == BooleanExpressionDriver goto :BooleanExpressionDriver
if /i %1 == AuthorDriver goto :AuthorDriver

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

REM === Instructions to Compile the Project ===

REM === DomainControllerDriver ===
:DomainControllerDriver
javac FONTS/src/Domain/Controllers/Drivers/DomainControllerDriver.java -d .
jar cfe DomainControllerDriver.jar FONTS.src.Domain.Controllers.Drivers.DomainControllerDriver *

goto :EOF

REM === FoldersControllerDriver ===
:FoldersControllerDriver
javac FONTS/src/Domain/Controllers/Drivers/FoldersControllerDriver.java -d .
jar cfe FoldersControllerDriver.jar FONTS.src.Domain.Controllers.Drivers.FoldersControllerDriver *

goto :EOF

REM === AuthorsControllerDriver ===
:AuthorsControllerDriver
javac FONTS/src/Domain/Controllers/Drivers/AuthorsControllerDriver.java -d .
jar cfe AuthorsControllerDriver.jar FONTS.src.Domain.Controllers.Drivers.AuthorsControllerDriver *

goto :EOF

REM === SearchControllerDriver ===
:SearchControllerDriver
javac FONTS/src/Domain/Controllers/Drivers/SearchControllerDriver.java -d .
jar cfe SearchControllerDriver.jar FONTS.src.Domain.Controllers.Drivers.SearchControllerDriver *

goto :EOF

REM === FoldersDriver ===
:FoldersDriver
javac FONTS/src/Domain/Controllers/Drivers/FolderDriver.java -d .
jar cfe FolderDriver.jar FONTS.src.Domain.Controllers.Drivers.FolderDriver *

goto :EOF

REM === DocumentDriver ===
:DocumentDriver
javac FONTS/src/Domain/Controllers/Drivers/DocumentDriver.java -d .
jar cfe DocumentDriver.jar FONTS.src.Domain.Controllers.Drivers.DocumentDriver *

goto :EOF

REM === ContentDriver ===
:ContentDriver
javac FONTS/src/Domain/Controllers/Drivers/ContentDriver.java -d .
jar cfe ContentDriver.jar FONTS.src.Domain.Controllers.Drivers.ContentDriver *

goto :EOF

REM === SentenceDriver ===
:SentenceDriver
javac FONTS/src/Domain/Controllers/Drivers/SentenceDriver.java -d .
jar cfe SentenceDriver.jar FONTS.src.Domain.Controllers.Drivers.SentenceDriver *

goto :EOF

REM === BooleanExpression ===
:BooleanExpressionDriver
javac FONTS/src/Domain/Controllers/Drivers/BooleanExpressionDriver.java -d .
jar cfe BooleanExpressionDriver.jar FONTS.src.Domain.Controllers.Drivers.BooleanExpressionDriver *

goto :EOF

REM === AuthorDriver ===
:AuthorDriver
javac FONTS/src/Domain/Controllers/Drivers/AuthorDriver.java -d .
jar cfe AuthorDriver.jar FONTS.src.Domain.Controllers.Drivers.AuthorDriver *

goto :EOF
timeout 60