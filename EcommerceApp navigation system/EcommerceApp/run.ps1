Write-Host "========================================" -ForegroundColor Cyan
Write-Host "Electronic Store Application" -ForegroundColor Cyan
Write-Host "========================================" -ForegroundColor Cyan
Write-Host ""

# Clear JAVA_HOME to avoid using corrupted VS Code Java
$env:JAVA_HOME = ""

# Try to find Java
$javaCmd = $null
$javacCmd = $null

# Check if java is in PATH
try {
    $javaVersion = & java -version 2>&1
    if ($LASTEXITCODE -eq 0 -or $javaVersion -match "version") {
        Write-Host "Found Java in PATH" -ForegroundColor Green
        $javaCmd = "java"
        $javacCmd = "javac"
    }
} catch {
    # Java not in PATH
}

# If not found in PATH, search common locations
if (-not $javaCmd) {
    $javaLocations = @(
        "C:\Program Files\Java\jdk*",
        "C:\Program Files (x86)\Java\jdk*",
        "C:\Program Files\Eclipse Adoptium\jdk*",
        "C:\Program Files\Microsoft\jdk*"
    )
    
    foreach ($location in $javaLocations) {
        $jdkDirs = Get-ChildItem -Path $location -Directory -ErrorAction SilentlyContinue | Sort-Object Name -Descending
        foreach ($jdkDir in $jdkDirs) {
            $javaExe = Join-Path $jdkDir.FullName "bin\java.exe"
            $javacExe = Join-Path $jdkDir.FullName "bin\javac.exe"
            if (Test-Path $javaExe) {
                Write-Host "Found Java at: $($jdkDir.FullName)" -ForegroundColor Green
                $javaCmd = $javaExe
                $javacCmd = $javacExe
                break
            }
        }
        if ($javaCmd) { break }
    }
}

# If still not found, show error
if (-not $javaCmd) {
    Write-Host ""
    Write-Host "ERROR: Java JDK not found!" -ForegroundColor Red
    Write-Host ""
    Write-Host "Please install Java JDK from:" -ForegroundColor Yellow
    Write-Host "https://adoptium.net/temurin/releases/" -ForegroundColor Cyan
    Write-Host ""
    Write-Host "After installation, run this script again." -ForegroundColor Yellow
    Read-Host "Press Enter to exit"
    exit 1
}

# Compile
Write-Host ""
Write-Host "Compiling application..." -ForegroundColor Yellow
Set-Location src

& $javacCmd Main.java com/electronicstore/ui/*.java

if ($LASTEXITCODE -ne 0) {
    Write-Host ""
    Write-Host "ERROR: Compilation failed!" -ForegroundColor Red
    Set-Location ..
    Read-Host "Press Enter to exit"
    exit 1
}

Write-Host "Compilation successful!" -ForegroundColor Green
Write-Host ""
Write-Host "Starting application..." -ForegroundColor Yellow
Write-Host ""

& $javaCmd Main

Set-Location ..

