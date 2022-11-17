CLASSES = FONTS/src/Domain/Classes
CONTROLLERS = FONTS/src/Domain/Controllers
DRIVERS = FONTS/src/Domain/Controllers/Drivers
JUNITS = FONTS/src/Domain/Controllers/JUnits
STUBS = FONTS/src/Domain/Controllers/Stubs
DOMAIN = FONTS/src/Domain
INTERFACE = FONTS/src/Interface
DATA = FONTS/src/Data
LIBS = FONTS/lib
BUILDDIR = EXE

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
CopyStopWords:
	mkdir -p $(BUILDDIR)/FONTS/src/Domain/Classes/StopWords
	cp -r ./FONTS/src/Domain/Classes/StopWords  $(BUILDDIR)/FONTS/src/Domain/Classes/

FoldersControllerDriver: CopyStopWords
	@javac -cp . -sourcepath . $(DRIVERS)/FoldersControllerDriver.java -d $(BUILDDIR)
	cd $(BUILDDIR) && find . -type f -name "*.class" | xargs -d '\n' jar -cef FONTS.src.Domain.Controllers.Drivers.FoldersControllerDriver ./FoldersControllerDriver.jar

DomainControllerDriver: CopyStopWords
	@javac -cp . -sourcepath . $(DRIVERS)/DomainControllerDriver.java -d $(BUILDDIR)
	cd $(BUILDDIR) && find . -type f -name "*.class" | xargs -d '\n' jar -cef FONTS.src.Domain.Controllers.Drivers.DomainControllerDriver ./DomainControllerDriver.jar

AuthorsControllerDriver: CopyStopWords
	@javac -cp . -sourcepath . $(DRIVERS)/AuthorsControllerDriver.java -d $(BUILDDIR)
	cd $(BUILDDIR) && find . -type f -name "*.class" | xargs -d '\n' jar -cef FONTS.src.Domain.Controllers.Drivers.AuthorsControllerDriver ./AuthorsControllerDriver.jar

SearchControllerDriver: CopyStopWords
	@javac -cp . -sourcepath . $(DRIVERS)/SearchControllerDriver.java -d $(BUILDDIR)
	cd $(BUILDDIR) && find . -type f -name "*.class" | xargs -d '\n' jar -cef FONTS.src.Domain.Controllers.Drivers.SearchControllerDriver ./SearchControllerDriver.jar

FolderDriver: CopyStopWords
	@javac -cp . -sourcepath . $(DRIVERS)/FolderDriver.java -d $(BUILDDIR)
	cd $(BUILDDIR) && find . -type f -name "*.class" | xargs -d '\n' jar -cef FONTS.src.Domain.Controllers.Drivers.FolderDriver ./FolderDriver.jar

AuthorDriver: CopyStopWords
	@javac -cp . -sourcepath . $(DRIVERS)/AuthorDriver.java -d $(BUILDDIR)
	cd $(BUILDDIR) && find . -type f -name "*.class" | xargs -d '\n' jar -cef FONTS.src.Domain.Controllers.Drivers.AuthorDriver ./AuthorDriver.jar

DocumentDriver: CopyStopWords
	@javac -cp . -sourcepath . $(DRIVERS)/DocumentDriver.java -d $(BUILDDIR)
	cd $(BUILDDIR) && find . -type f -name "*.class" | xargs -d '\n' jar -cef FONTS.src.Domain.Controllers.Drivers.DocumentDriver ./DocumentDriver.jar

ContentDriver: CopyStopWords
	@javac -cp . -sourcepath . $(DRIVERS)/ContentDriver.java -d $(BUILDDIR)
	cd $(BUILDDIR) && find . -type f -name "*.class" | xargs -d '\n' jar -cef FONTS.src.Domain.Controllers.Drivers.ContentDriver ./ContentDriver.jar

SentenceDriver: CopyStopWords
	@javac -cp . -sourcepath . $(DRIVERS)/SentenceDriver.java -d $(BUILDDIR)
	cd $(BUILDDIR) && find . -type f -name "*.class" | xargs -d '\n' jar -cef FONTS.src.Domain.Controllers.Drivers.SentenceDriver ./SentenceDriver.jar

BooleanExpressionDriver: CopyStopWords
	@javac $(DRIVERS)/BooleanExpressionDriver.java -d $(BUILDDIR)
	cd $(BUILDDIR) && find . -type f -name "*.class" | xargs -d '\n' jar -cef FONTS.src.Domain.Controllers.Drivers.BooleanExpressionDriver ./BooleanExpressionDriver.jar

Drivers: FoldersControllerDriver DomainControllerDriver SearchControllerDriver AuthorsControllerDriver FolderDriver AuthorDriver DocumentDriver ContentDriver SentenceDriver BooleanExpressionDriver

runAutomaticDrivers:
	@java -jar $(BUILDDIR)/SearchControllerDriver.jar < test/inputTest/inpSearchController.txt
	@java -jar $(BUILDDIR)/AuthorsControllerDriver.jar < test/inputTest/inpAuthorsController.txt
	@java -jar $(BUILDDIR)/FoldersControllerDriver.jar < test/inputTest/inpFoldersController.txt
	@java -jar $(BUILDDIR)/DomainControllerDriver.jar < test/inputTest/inpDomainController.txt

	@java -jar $(BUILDDIR)/FolderDriver.jar < test/inputTest/inpFolder.txt
	@java -jar $(BUILDDIR)/DocumentDriver.jar < test/inputTest/inpDocument.txt
	@java -jar $(BUILDDIR)/ContentDriver.jar < test/inputTest/inpContent.txt
	@java -jar $(BUILDDIR)/SentenceDriver.jar < test/inputTest/inpSentence.txt
	@java -jar $(BUILDDIR)/BooleanExpressionDriver.jar < test/inputTest/inpBooleanExpression.txt
	@java -jar $(BUILDDIR)/AuthorDriver.jar < test/inputTest/inpAuthor.txt

runDrivers:
	@java -jar $(BUILDDIR)/SearchControllerDriver.jar
	@java -jar $(BUILDDIR)/AuthorsControllerDriver.jar
	@java -jar $(BUILDDIR)/FoldersControllerDriver.jar
	@java -jar $(BUILDDIR)/DomainControllerDriver.jar

	@java -jar $(BUILDDIR)/FoldersDriver.jar
	@java -jar $(BUILDDIR)/DocumentDriver.jar
	@java -jar $(BUILDDIR)/ContentDriver.jar
	@java -jar $(BUILDDIR)/SentenceDriver.jar
	@java -jar $(BUILDDIR)/BooleanExpressionDriver.jar
	@java -jar $(BUILDDIR)/AuthorDriver.jar

runAutomaticSearchController:
	@java -jar $(BUILDDIR)/SearchControllerDriver.jar < test/inputTest/inpSearchController.txt

runAutomaticAuthorsController:
	@java -jar $(BUILDDIR)/AuthorsControllerDriver.jar < test/inputTest/inpAuthorsController.txt

runAutomaticFoldersController:
	@java -jar $(BUILDDIR)/FoldersControllerDriver.jar < test/inputTest/inpFoldersController.txt

runAutomaticDomainController:
	@java -jar $(BUILDDIR)/DomainControllerDriver.jar < test/inputTest/inpDomainController.txt

runAutomaticFolder:
	@java -jar $(BUILDDIR)/FolderDriver.jar < test/inputTest/inpFolder.txt

runAutomaticDocument:
	@java -jar $(BUILDDIR)/DocumentDriver.jar < test/inputTest/inpDocument.txt

runAutomaticContent:
	@java -jar $(BUILDDIR)/ContentDriver.jar < test/inputTest/inpContent.txt

runAutomaticSentence:
	@java -jar $(BUILDDIR)/SentenceDriver.jar < test/inputTest/inpSentence.txt

runAutomaticBooleanExpression:
	@java -jar $(BUILDDIR)/BooleanExpressionDriver.jar < test/inputTest/inpBooleanExpression.txt

runAutomaticAuthor:
	@java -jar $(BUILDDIR)/AuthorDriver.jar < test/inputTest/inpAuthor.txt

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