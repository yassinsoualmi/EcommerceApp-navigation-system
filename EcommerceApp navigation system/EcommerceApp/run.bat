@echo off
echo ========================================
echo Electronic Store Application
echo ========================================
echo.

REM Clear JAVA_HOME to avoid using corrupted VS Code Java
set JAVA_HOME=

REM Try to find Java in common locations
set JAVA_CMD=

REM Check if java is in PATH
where java >nul 2>&1
if %ERRORLEVEL% EQU 0 (
    echo Found Java in PATH
    set JAVA_CMD=java
    set JAVAC_CMD=javac
    goto :compile
)

REM Check Program Files
if exist "C:\Program Files\Java" (
    for /d %%i in ("C:\Program Files\Java\jdk*") do (
        if exist "%%i\bin\java.exe" (
            echo Found Java at: %%i
            set JAVA_CMD=%%i\bin\java.exe
            set JAVAC_CMD=%%i\bin\javac.exe
            goto :compile
        )
    )
)

REM Check Program Files (x86)
if exist "C:\Program Files (x86)\Java" (
    for /d %%i in ("C:\Program Files (x86)\Java\jdk*") do (
        if exist "%%i\bin\java.exe" (
            echo Found Java at: %%i
            set JAVA_CMD=%%i\bin\java.exe
            set JAVAC_CMD=%%i\bin\javac.exe
            goto :compile
        )
    )
)

REM If no Java found, show error
echo.
echo ERROR: Java JDK not found!
echo.
echo Please install Java JDK from:
echo https://adoptium.net/temurin/releases/
echo.
echo After installation, run this script again.
pause
exit /b 1

:compile
echo.
echo Compiling application...
cd src
"%JAVAC_CMD%" Main.java com/electronicstore/ui/*.java
if %ERRORLEVEL% NEQ 0 (
    echo.
    echo ERROR: Compilation failed!
    pause
    exit /b 1
)

echo Compilation successful!
echo.
echo Starting application...
echo.
"%JAVA_CMD%" Main

cd ..

