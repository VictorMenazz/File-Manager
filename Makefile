MAIN = FONTS/src
DOMAIN = FONTS/src/Domain
INTERFACE = FONTS/src/Interface
DATA = FONTS/src/Data
LIBS = FONTS/lib
BUILDDIR = EXE
OPTIONS_xargs = ""

ifeq ($(OS), Windows_NT)
	CLASSPATH = "bin;lib/gson-2.10.jar"
	OPTIONS_xargs = "-d '\n'"
else
	CLASSPATH = "bin:lib/gson-2.10.jar"
	OPTIONS_xargs = "-n1"
endif

default: all

#COMPILING JUnits

all: CopyStopWords
	@javac -cp $(CLASSPATH) -sourcepath . $(DRIVERS)/DomainControllerDriver.java -d $(BUILDDIR)
    cd $(BUILDDIR) && find . -type f -name "*.class" | xargs $(OPTIONS_xargs) jar -cef FONTS.src.Main ./Main.jar
	@java -jar $(BUILDDIR)/Main.jar

clean:
	rm -rf bin/
	rm -rf EXE/

help:
	@echo "make default or make all 			 -->  compile and execute the application"
	@echo "make clean                   		 -->  clean bin/ and EXE/ directories"
	@echo "make help							 -->  obtain help"