# noRain

# Running a Windows Program with Wine

1. Update the package repository and install the necessary packages:
   ```bash
   apt-get update && apt-get install -y software-properties-common
   dpkg --add-architecture i386
   apt-get update && apt-get install -y wine
   apt-get install -y wine32
   ```

   ```bash
   apk add -f wine
   apk add -f wine32
   ```
   
2. Executable file (e.g., SII_Decrypt.exe) and any required files must be uploaded to the container.

3. Run the executable using the following command:
   ```
   /exec wine ./SII_Decrypt.exe ./game.sii
   ```
