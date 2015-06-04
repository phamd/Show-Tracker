JC = javac
DIRS = gui show
CLASSFILES := $(foreach dir,$(DIRS),$(patsubst %,bin/$(dir)/%.class,$($(dir))))

all: $(CLASSFILES)

bin/%.class: ../%.java

clean:
        $(RM) *.class