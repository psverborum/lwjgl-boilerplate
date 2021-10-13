create-jar-linux:
	mkdir -p jars
	mvn clean compile assembly:single -P lwjgl-natives-linux-amd64
	mv ./target/*.jar ./jars/mines-linux.jar

create-jar-macos:
	mkdir -p jars
	mvn clean compile assembly:single -P lwjgl-natives-macos-amd64
	mv ./target/*.jar ./jars/mines-macos.jar

create-jar-windows:
	mkdir -p jars
	mvn clean compile assembly:single -P lwjgl-natives-windows-amd64
	mv ./target/*.jar ./jars/mines-windows.jar

create-jar-all:
	make create-jar-linux
	make create-jar-macos
	make create-jar-windows