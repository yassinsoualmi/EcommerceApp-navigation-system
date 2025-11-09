@echo off
REM Simple script to compile and run without corrupted Java
set JAVA_HOME=
cd src
javac com/electronicstore/models/*.java com/electronicstore/controllers/*.java com/electronicstore/ui/*.java Main.java && java Main
cd ..

