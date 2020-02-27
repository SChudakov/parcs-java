all: run

clean:
	rm -f out/Bluck.jar out/DFS.jar

out/Bluck.jar: out/parcs.jar src/Bluck.java src/Graph.java
	@javac -cp out/parcs.jar src/Bluck.java src/Graph.java
	@jar cf out/Bluck.jar -C src Bluck.class -C src Graph.class
	@rm -f src/Bluck.class src/Graph.class

out/MC.jar: out/parcs.jar src/DFS.java src/Graph.java
	@javac -cp out/parcs.jar src/MaximumCliqueFinder.java src/Graph.java
	@jar cf out/MC.jar -C src MaximumCliqueFinder.class -C src Graph.class
	@rm -f src/MaximumCliqueFinder.class src/Graph.class

build: out/Bluck.jar out/DFS.jar

run: out/Bluck.jar out/MC.jar
	@cd out && java -cp 'parcs.jar:Bluck.jar' Bluck
