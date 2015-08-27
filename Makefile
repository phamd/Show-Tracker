JC = javac
DIRS = gui show
CLASS := $(foreach dir, $(DIRS), $(patsubst %.java, %.class, $(wildcard $(dir)/*.java)))

run: all
	java gui.MainGui

all: $(CLASS)

%.class: %.java
	$(JC) $<
