CLASSES = FONTS/src/Domain/Classes
CONTROLLERS = FONTS/src/Domain/Controllers
DRIVERS = FONTS/src/Domain/Controllers/Drivers
JUNITS = FONTS/src/Domain/Controllers/JUnits
STUBS = FONTS/src/Domain/Controllers/Stubs
INTERFACE = FONTS/src/Interface
DATA = FONTS/src/Data

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
	@java -cp $(CLASSPATH) org.junit.runner.JUnitCore FONTS.src.Domain.Controllers.JUnits.FolderTest
	@echo "Document JUnit test..."
	@java -cp $(CLASSPATH) org.junit.runner.JUnitCore FONTS.src.Domain.Controllers.JUnits.DocumentTest
	@echo "Content JUnit test..."
	@java -cp $(CLASSPATH) org.junit.runner.JUnitCore FONTS.src.Domain.Controllers.JUnits.ContentTest
	@echo "Sentence JUnit test..."
	@java -cp $(CLASSPATH) org.junit.runner.JUnitCore FONTS.src.Domain.Controllers.JUnits.SentenceTest
	@echo "Author JUnit test..."
	@java -cp $(CLASSPATH) org.junit.runner.JUnitCore FONTS.src.Domain.Controllers.JUnits.AuthorTest
	@echo "BooleanExpression JUnit test..."
	@java -cp $(CLASSPATH) org.junit.runner.JUnitCore FONTS.src.Domain.Controllers.JUnits.BooleanExpressionTest


FolderJUnit:
	@javac -cp $(CLASSPATH) $(JUNITS)/*.java $(CLASSES)/*.java $(STUBS)/*.java -d bin/
	@java -cp $(CLASSPATH) org.junit.runner.JUnitCore FONTS.src.Domain.Controllers.JUnits.FolderTest

DocumentJUnit:
	@javac -cp $(CLASSPATH) $(JUNITS)/*.java $(CLASSES)/*.java $(STUBS)/*.java -d bin/
	@java -cp $(CLASSPATH) org.junit.runner.JUnitCore FONTS.src.Domain.Controllers.JUnits.DocumentTest

ContentJUnit:
	@javac -cp $(CLASSPATH) $(JUNITS)/*.java $(CLASSES)/*.java $(STUBS)/*.java -d bin/
	@java -cp $(CLASSPATH) org.junit.runner.JUnitCore FONTS.src.Domain.Controllers.JUnits.ContentTest

SentenceJUnit:
	@javac -cp $(CLASSPATH) $(JUNITS)/*.java $(CLASSES)/*.java $(STUBS)/*.java -d bin/
	@java -cp $(CLASSPATH) org.junit.runner.JUnitCore FONTS.src.Domain.Controllers.JUnits.SentenceTest

BooleanExpressionJUnit:
	@javac -cp $(CLASSPATH) $(JUNITS)/*.java $(CLASSES)/*.java $(STUBS)/*.java -d bin/
	@java -cp $(CLASSPATH) org.junit.runner.JUnitCore FONTS.src.Domain.Controllers.JUnits.BooleanExpressionTest

AuthorJUnit:
	@javac -cp $(CLASSPATH) $(JUNITS)/*.java $(CLASSES)/*.java $(STUBS)/*.java -d bin/
	@java -cp $(CLASSPATH) org.junit.runner.JUnitCore FONTS.src.Domain.Controllers.JUnits.AuthorTest

#COMPILING DRIVERS

FoldersControllerDriver:
	@javac $(DRIVERS)/FoldersControllerDriver.java -d .
	@jar cfe FoldersControllerDriver.jar FONTS.src.Domain.Controllers.Drivers.FoldersControllerDriver *

DomainControllerDriver:
	@javac $(DRIVERS)/DomainControllerDriver.java -d .
	@jar cfe DomainControllerDriver.jar FONTS.src.Domain.Controllers.Drivers.DomainControllerDriver *

AuthorsControllerDriver:
	@javac $(DRIVERS)/AuthorsControllerDriver.java -d .
	@jar cfe AuthorsControllerDriver.jar FONTS.src.Domain.Controllers.Drivers.AuthorsControllerDriver *

SearchControllerDriver:
	@javac $(DRIVERS)/SearchControllerDriver.java -d .
	@jar cfe SearchControllerDriver.jar FONTS.src.Domain.Controllers.Drivers.SearchControllerDriver *


FolderDriver:
	@javac $(DRIVERS)/FolderDriver.java -d .
	@jar cfe FolderDriver.jar FONTS.src.Domain.Controllers.Drivers.FolderDriver *

AuthorDriver:
	@javac $(DRIVERS)/AuthorDriver.java -d .
	@jar cfe AuthorDriver.jar FONTS.src.Domain.Controllers.Drivers.AuthorDriver *

DocumentDriver:
	@javac $(DRIVERS)/DocumentDriver.java -d .
	@jar cfe DocumentDriver.jar FONTS.src.Domain.Controllers.Drivers.DocumentDriver *

ContentDriver:
	@javac $(DRIVERS)/ContentDriver.java -d .
	@jar cfe ContentDriver.jar FONTS.src.Domain.Controllers.Drivers.ContentDriver *

SentenceDriver:
	@javac $(DRIVERS)/SentenceDriver.java -d .
	@jar cfe SentenceDriver.jar FONTS.src.Domain.Controllers.Drivers.SentenceDriver *

BooleanExpressionDriver:
	@javac $(DRIVERS)/BooleanExpressionDriver.java -d .
	@jar cfe BooleanExpressionDriver.jar FONTS.src.Domain.Controllers.Drivers.BooleanExpressionDriver *

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

	@jar cfe FoldersControllerDriver.jar FONTS.src.Domain.Controllers.Drivers.FoldersControllerDriver *
	@jar cfe DomainControllerDriver.jar FONTS.src.Domain.Controllers.Drivers.DomainControllerDriver *
	@jar cfe AuthorsControllerDriver.jar FONTS.src.Domain.Controllers.Drivers.AuthorsControllerDriver *
	@jar cfe SearchControllerDriver.jar FONTS.src.Domain.Controllers.Drivers.SearchControllerDriver *
	@jar cfe FolderDriver.jar FONTS.src.Domain.Controllers.Drivers.FolderDriver *
	@jar cfe AuthorDriver.jar FONTS.src.Domain.Controllers.Drivers.AuthorDriver *
	@jar cfe DocumentDriver.jar FONTS.src.Domain.Controllers.Drivers.DocumentDriver *
	@jar cfe ContentDriver.jar FONTS.src.Domain.Controllers.Drivers.ContentDriver *
	@jar cfe SentenceDriver.jar FONTS.src.Domain.Controllers.Drivers.SentenceDriver *
	@jar cfe BooleanExpressionDriver.jar FONTS.src.Domain.Controllers.Drivers.BooleanExpressionDriver *

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
	@java -jar BooleanExpressionDriver.jar
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
	@java -jar BooleanExpressionDriver.jar < test/inputTest/inpBooleanExpression.txt

runAutomaticAuthor:
	@java -jar AuthorDriver.jar < test/inputTest/inpAuthor.txt

clean:
	rm -rf bin/
	rm -rf EXE/

help:
	@echo "make default or make JUnits 			 -->  compile all JUnits"
	@echo "make FolderJUnit     				 -->  compile Folder JUnit"
	@echo "make DocumentJUnit   				 -->  compile Document JUnit"
	@echo "make ContentJUnit    				 -->  compile Content JUnit"
	@echo "make SentenceJUnit   				 -->  compile Sentence JUnit"
	@echo "make BooleanExpressionJUnit  		 -->  compile BooleanExpression JUnit"
	@echo "make AuthorJUnit						 -->  compile Author JUnit"
	@echo "make FoldersControllerDriver 		 -->  compile FoldersController Driver"
	@echo "make DomainControllerDriver  		 -->  compile DomainController Driver"
	@echo "make AuthorsControllerDriver 		 -->  compile AuthorsController Driver"
	@echo "make SearchControllerDriver 			 -->  compile FoldersController Driver"
	@echo "make FolderDriver 					 -->  compile Folder Driver"
	@echo "make AuthorDriver 					 -->  compile Author Driver"
	@echo "make DocumentDriver 					 -->  compile Document Driver"
	@echo "make ContentDriver 					 -->  compile Content Driver"
	@echo "make SentenceDriver 					 -->  compile Sentence Driver"
	@echo "make BooleanExpressionDriver 		 -->  compile Boolean Expression Driver"
	@echo "make Drivers							 -->  compile All Drivers"
	@echo "make runAutomaticDrivers     		 -->  run drivers automatically with input files redesigned"
	@echo "make runDrivers              		 -->  execute drivers"
	@echo "make runAutomaticSearchController     -->  run driver automatically with input files redesigned"
	@echo "make runAutomaticAuthorsController    -->  run driver automatically with input files redesigned"
	@echo "make runAutomaticFoldersController    -->  run driver automatically with input files redesigned"
	@echo "make runAutomaticDomainController     -->  run driver automatically with input files redesigned"
	@echo "make runAutomaticFolder			     -->  run driver automatically with input files redesigned"
	@echo "make runAutomaticDocument		     -->  run driver automatically with input files redesigned"
	@echo "make runAutomaticContent				 -->  run driver automatically with input files redesigned"
	@echo "make runAutomaticSentence			 -->  run driver automatically with input files redesigned"
	@echo "make runAutomaticBooleanExpression	 -->  run driver automatically with input files redesigned"
	@echo "make runAutomaticAuthor				 -->  run driver automatically with input files redesigned"
	@echo "make clean                   		 -->  clean bin/ and EXE/ directories"
	@echo "make help							 -->  obtain help"