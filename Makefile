CLASSES = Code/src/Domain/Classes
CONTROLLERS =Code/src/Domain/Controllers
DRIVERS = Code/src/Domain/Controllers/Drivers
JUNITS = Code/src/Domain/Controllers/JUnits
STUBS = Code/src/Domain/Controllers/Stubs
INTERFACE = Code/src/Interface
DATA = Code/src/Data

ifeq ($(OS), Windows_NT)
	CLASSPATH = "bin;lib/junit-4.13.1.jar;lib/hamcrest-core-1.3.jar"
else
	CLASSPATH = "bin:lib/junit-4.13.1.jar:lib/hamcrest-core-1.3.jar"
endif

default: JUnits

#COMPILING JUnits

JUnits:
	@javac -cp $(CLASSPATH) $(JUNITS)/*.java $(CLASSES)/*.java $(STUBS)/*.java -d bin/
	@echo "Folder JUnit test..."
	@java -cp $(CLASSPATH) org.junit.runner.JUnitCore Code.src.Domain.Controllers.JUnits.FolderTest
	@echo "Document JUnit test..."
	@java -cp $(CLASSPATH) org.junit.runner.JUnitCore Code.src.Domain.Controllers.JUnits.DocumentTest
	@echo "Content JUnit test..."
	@java -cp $(CLASSPATH) org.junit.runner.JUnitCore Code.src.Domain.Controllers.JUnits.ContentTest
	@echo "Sentence JUnit test..."
	@java -cp $(CLASSPATH) org.junit.runner.JUnitCore Code.src.Domain.Controllers.JUnits.SentenceTest
	@echo "Author JUnit test..."
	@java -cp $(CLASSPATH) org.junit.runner.JUnitCore Code.src.Domain.Controllers.JUnits.AuthorTest
	@echo "BooleanExpression JUnit test..."
	@java -cp $(CLASSPATH) org.junit.runner.JUnitCore Code.src.Domain.Controllers.JUnits.BooleanExpressionTest


FolderJUnit:
	@javac -cp $(CLASSPATH) $(JUNITS)/*.java $(CLASSES)/*.java $(STUBS)/*.java -d bin/
	@java -cp $(CLASSPATH) org.junit.runner.JUnitCore Code.src.Domain.Controllers.JUnits.FolderTest

DocumentJUnit:
	@javac -cp $(CLASSPATH) $(JUNITS)/*.java $(CLASSES)/*.java $(STUBS)/*.java -d bin/
	@java -cp $(CLASSPATH) org.junit.runner.JUnitCore Code.src.Domain.Controllers.JUnits.DocumentTest

ContentJUnit:
	@javac -cp $(CLASSPATH) $(JUNITS)/*.java $(CLASSES)/*.java $(STUBS)/*.java -d bin/
	@java -cp $(CLASSPATH) org.junit.runner.JUnitCore Code.src.Domain.Controllers.JUnits.ContentTest

SentenceJUnit:
	@javac -cp $(CLASSPATH) $(JUNITS)/*.java $(CLASSES)/*.java $(STUBS)/*.java -d bin/
	@java -cp $(CLASSPATH) org.junit.runner.JUnitCore Code.src.Domain.Controllers.JUnits.SentenceTest

BooleanExpressionJUnit:
	@javac -cp $(CLASSPATH) $(JUNITS)/*.java $(CLASSES)/*.java $(STUBS)/*.java -d bin/
	@java -cp $(CLASSPATH) org.junit.runner.JUnitCore Code.src.Domain.Controllers.JUnits.BooleanExpressionTest

AuthorJUnit:
	@javac -cp $(CLASSPATH) $(JUNITS)/*.java $(CLASSES)/*.java $(STUBS)/*.java -d bin/
	@java -cp $(CLASSPATH) org.junit.runner.JUnitCore Code.src.Domain.Controllers.JUnits.AuthorTest

#COMPILING DRIVERS

FoldersControllerDriver:
	@javac $(DRIVERS)/FoldersControllerDriver.java -d .
	@jar cfe FoldersControllerDriver.jar Code.src.Domain.Controllers.Drivers.FoldersControllerDriver *

DomainControllerDriver:
	@javac $(DRIVERS)/DomainControllerDriver.java -d .
	@jar cfe DomainControllerDriver.jar Code.src.Domain.Controllers.Drivers.DomainControllerDriver *

AuthorsControllerDriver:
	@javac $(DRIVERS)/AuthorsControllerDriver.java -d .
	@jar cfe AuthorsControllerDriver.jar Code.src.Domain.Controllers.Drivers.AuthorsControllerDriver *

SearchControllerDriver:
	@javac $(DRIVERS)/SearchControllerDriver.java -d .
	@jar cfe SearchControllerDriver.jar Code.src.Domain.Controllers.Drivers.SearchControllerDriver *


FolderDriver:
	@javac $(DRIVERS)/FolderDriver.java -d .
	@jar cfe FolderDriver.jar Code.src.Domain.Controllers.Drivers.FolderDriver *

AuthorDriver:
	@javac $(DRIVERS)/AuthorDriver.java -d .
	@jar cfe AuthorDriver.jar Code.src.Domain.Controllers.Drivers.AuthorDriver *

DocumentDriver:
	@javac $(DRIVERS)/DocumentDriver.java -d .
	@jar cfe DocumentDriver.jar Code.src.Domain.Controllers.Drivers.DocumentDriver *

ContentDriver:
	@javac $(DRIVERS)/ContentDriver.java -d .
	@jar cfe ContentDriver.jar Code.src.Domain.Controllers.Drivers.ContentDriver *

SentenceDriver:
	@javac $(DRIVERS)/SentenceDriver.java -d .
	@jar cfe SentenceDriver.jar Code.src.Domain.Controllers.Drivers.SentenceDriver *

BooleanExpressionDriver:
	@javac $(DRIVERS)/BooleanExpressionDriver.java -d .
	@jar cfe BooleanExpressionDriver.jar Code.src.Domain.Controllers.Drivers.BooleanExpressionDriver *

Drivers:
	@javac $(DRIVERS)/FoldersControllerDriver.java -d .
	@javac $(DRIVERS)/DomainControllerDriver.java -d .
	@javac $(DRIVERS)/AuthorsControllerDriver.java -d .
	@javac $(DRIVERS)/SearchControllerDriver.java -d .
	@javac $(DRIVERS)/FolderDriver.java -d .
	@javac $(DRIVERS)/AuthorDriver.java -d .
	@javac $(DRIVERS)/DocumentDriver.java -d .
	@javac $(DRIVERS)/ContentDriver.java -d .
	@javac $(DRIVERS)/SentenceDriver.java -d .
	@javac $(DRIVERS)/BooleanExpressionDriver.java -d .

	@jar cfe FoldersControllerDriver.jar Code.src.Domain.Controllers.Drivers.FoldersControllerDriver *
	@jar cfe DomainController.jar Code.src.Domain.Controllers.Drivers.DomainController *
	@jar cfe AuthorsControllerDriver.jar Code.src.Domain.Controllers.Drivers.AuthorsControllerDriver *
	@jar cfe SearchControllerDriver.jar Code.src.Domain.Controllers.Drivers.SearchControllerDriver *
	@jar cfe FolderDriver.jar Code.src.Domain.Controllers.Drivers.FolderDriver *
	@jar cfe AuthorDriver.jar Code.src.Domain.Controllers.Drivers.AuthorDriver *
	@jar cfe DocumentDriver.jar Code.src.Domain.Controllers.Drivers.DocumentDriver *
	@jar cfe ContentDriver.jar Code.src.Domain.Controllers.Drivers.ContentDriver *
	@jar cfe SentenceDriver.jar Code.src.Domain.Controllers.Drivers.SentenceDriver *
	@jar cfe BooleanExpressionDriver.jar Code.src.Domain.Controllers.Drivers.BooleanExpressionDriver *

runAutomaticDrivers:
	@java -jar SearchControllerDriver.jar < test/inputTest/inpSearchController.txt
	@java -jar AuthorsControllerDriver.jar < test/inputTest/inpAuthorsController.txt
	@java -jar FoldersControllerDriver.jar < test/inputTest/inpFoldersController.txt
	@java -jar DomainControllerDriver.jar < test/inputTest/inpDomainController.txt

	@java -jar FolderDriver.jar < test/inputTest/inpFolder.txt
	@java -jar DocumentDriver.jar < test/inputTest/inpDocument.txt
	@java -jar ContentDriver.jar < test/inputTest/inpContent.txt
	@java -jar SentenceDriver.jar < test/inputTest/inpSentence.txt
	@java -jar BooleanExpressionDriver.jar < test/inputTest/inpBooleanExpression.txt
	@java -jar AuthorDriver.jar < test/inputTest/inpAuthor.txt

runDrivers:
	@java -jar SearchControllerDriver.jar
	@java -jar AuthorsControllerDriver.jar
	@java -jar FoldersControllerDriver.jar
	@java -jar DomainControllerDriver.jar

	@java -jar FoldersDriver.jar
	@java -jar DocumentDriver.jar
	@java -jar ContentDriver.jar
	@java -jar SentenceDriver.jar
	@java -jar BooleanExpression.jar
	@java -jar AuthorDriver.jar

runAutomaticSearchController:
	@java -jar SearchControllerDriver.jar < test/inputTest/inpSearchController.txt

runAutomaticAuthorsController:
	@java -jar AuthorsControllerDriver.jar < test/inputTest/inpAuthorsController.txt

runAutomaticFoldersController:
	@java -jar FoldersControllerDriver.jar < test/inputTest/inpFoldersController.txt

runAutomaticDomainController:
	@java -jar DomainControllerDriver.jar < test/inputTest/inpDomainController.txt

runAutomaticFolder:
	@java -jar FolderDriver.jar < test/inputTest/inpFolder.txt

runAutomaticDocument:
	@java -jar DocumentDriver.jar < test/inputTest/inpDocument.txt

runAutomaticContent:
	@java -jar ContentDriver.jar < test/inputTest/inpContent.txt

runAutomaticSentence:
	@java -jar SentenceDriver.jar < test/inputTest/inpSentence.txt

runAutomaticBooleanExpression:
	@java -jar BooleanExpression.jar < test/inputTest/inpBooleanExpression.txt

runAutomaticAuthor:
	@java -jar AuthorDriver.jar < test/inputTest/inpAuthor.txt

clean:
	rm -rf bin/
	rm -rf EXE/