@echo off
echo Compiling Electronic Store Application...
cd src
javac com/electronicstore/models/*.java com/electronicstore/controllers/*.java com/electronicstore/ui/*.java Main.java
if %errorlevel% neq 0 (
    echo Compilation failed!
    pause
    exit /b 1
)
echo Compilation successful!
echo Starting application...
start "Electronic Store" java Main
cd ..

