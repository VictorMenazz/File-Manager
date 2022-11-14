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
	@javac -cp . -d EXE/FoldersControllerDriver $(DRIVERS)/FoldersControllerDriver.java
	@java -cp EXE/FoldersControllerDriver Code.src.Domain.Controllers.Drivers.FoldersControllerDriver

all: default

clean:
	rm -rf bin/