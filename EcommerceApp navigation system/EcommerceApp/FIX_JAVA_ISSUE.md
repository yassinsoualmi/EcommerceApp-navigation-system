# How to Fix the Java Runtime Issue

## Problem
Your VS Code Java extension has a corrupted Java runtime that causes this error:
```
Invalid layout of well-known class
Error occurred during initialization of VM
```

## Solutions (Choose One)

### ✅ Solution 1: Use the Run Scripts (Easiest)

I've created scripts that bypass the corrupted Java:

**Option A - Batch Script (Windows):**
```
run.bat
```
Double-click `run.bat` or run it from command prompt.

**Option B - PowerShell Script:**
```
powershell -ExecutionPolicy Bypass -File run.ps1
```

These scripts will:
1. Find a working Java installation
2. Compile your code
3. Run the application

---

### ✅ Solution 2: Install Java JDK (Recommended for Long-term)

1. **Download Java JDK:**
   - Go to: https://adoptium.net/temurin/releases/
   - Download: **Windows x64 JDK** (latest version)
   - Choose the `.msi` installer

2. **Install Java:**
   - Run the downloaded `.msi` file
   - **IMPORTANT:** Check the box "Set JAVA_HOME variable"
   - Complete the installation

3. **Restart VS Code:**
   - Close VS Code completely
   - Reopen it
   - The Java extension will now use the new installation

4. **Test:**
   - Run your application from VS Code

---

### ✅ Solution 3: Clean VS Code Java Extension

1. **Close VS Code completely**

2. **Delete the corrupted Java folder:**
   - Open File Explorer
   - Navigate to: `C:\Users\LENOVO\AppData\Roaming\Code\User\globalStorage\`
   - Delete the folder: `pleiades.java-extension-pack-jdk`

3. **Reopen VS Code:**
   - The Java extension will download a fresh Java runtime
   - Wait for it to complete

4. **Test:**
   - Run your application

---

## Quick Test

After applying any solution, test if Java works:

```bash
java -version
```

If you see version information without errors, Java is fixed!

---

## Running the Application

### From Command Line:
```bash
cd src
javac Main.java com/electronicstore/ui/*.java
java Main
```

### From VS Code:
- Open `src/Main.java`
- Click the "Run" button above the `main` method

### Using Scripts:
- Double-click `run.bat`
- Or run: `powershell -ExecutionPolicy Bypass -File run.ps1`

---

## Application Features

Your Electronic Store application has:

- **Navigation System:** Home, Products, Cart, Checkout buttons
- **Product Page:** Grid of 4 products with "Add to Cart" buttons
- **Cart Page:** Shopping cart with items and total
- **Login Page:** Login form for checkout

---

## Need Help?

If none of these solutions work, you may need to:
1. Completely uninstall all Java versions
2. Restart your computer
3. Install a fresh Java JDK from Adoptium
4. Restart VS Code

