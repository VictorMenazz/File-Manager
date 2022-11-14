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

JUnits: FolderJUnit DocumentJUnit ContentJUnit SentenceJUnit BooleanExpressionJUnit AuthorJUnit
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

FoldersController:
	@javac -cp . -d EXE/FoldersControllerDriver $(DRIVERS)/FoldersControllerDriver.java
	@java -cp EXE/FoldersControllerDriver Code.src.Domain.Controllers.Drivers.FoldersControllerDriver

Document:
	@javac -cp . -d EXE/DocumentDriver $(DRIVERS)/DocumentDriver.java
	@java -cp EXE/DocumentDriver Code.src.Domain.Controllers.Drivers.DocumentDriver


all: FoldersController

clean:
	rm -rf bin/
	rm -rf EXE/