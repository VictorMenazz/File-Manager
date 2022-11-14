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

default:
	@javac -d bin -encoding UTF-8 -cp $(CLASSPATH) -sourcepath Code/src Code/src/Main.java $(CLASSES)/*.java $(CONTROLLERS)/*.java $(DRIVERS)/*.java $(JUNITS)/*.java $(STUBS)/*.java $(INTERFACE)/*.java $(DATA)/*.java

#	@java -c ../EXE/DomainControllerDriver.jar

	@jar cfe EXE/Driver FolderDriver.jar bin/Code/src/Domain/Controllers/Drivers/FolderDriver.class
	#@jar cfe ../EXE/DriverCtrlDomini.jar drivers.DriverCtrlDomini drivers/DriverCtrlDomini.class controladors/*.class classes/*.class
	#@jar cfe ../EXE/DriverCtrlDocument.jar drivers.DriverCtrlDocument drivers/DriverCtrlDocument.class controladors/*.class classes/*.class
	#@jar cfe ../EXE/DriverFuncions.jar drivers.DriverFuncions drivers/DriverFuncions.class controladors/*.class classes/*.class
	#@jar cfe ../EXE/DriverDocument.jar drivers.DriverDocument drivers/DriverDocument.class controladors/*.class classes/*.class
	#@jar cfe ../EXE/DriverFull.jar drivers.DriverFull drivers/DriverFull.class controladors/*.class classes/*.class
	#@jar cfe ../EXE/DriverCela.jar drivers.DriverCela drivers/DriverCela.class controladors/*.class classes/*.class

	#@javac -sourcepath . -classpath "lib/*" test/*.java -d .
	#@jar cfe ../EXE/Tests/FuncionsTest.jar test.FuncionsTest classes/*.class
	#@jar cfe ../EXE/Tests/DocumentsTest.jar test.CelaTest classes/*.class
	#@jar cfe ../EXE/Tests/FullTest.jar test.FulTest classes/*.class
	#@jar cfe ../EXE/Tests/CelaTest.jar test.CelaTest classes/*.class

all: default

clean:
	rm -rf bin/

DomainControllerDriver: default
	@java drivers.DomainControllerDriver

AuthorsControllerDriver: default
	@java drivers.AuthorsControllerDriver

FoldersControllerDriver: default
	@java drivers.FoldersControllerDriver

SearchControllerDriver: default
	@java drivers.SearchControllerDriver