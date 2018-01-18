## Reorganize Files

This project is meant to reorganize parts of your file structure in a convenient way utilizing CSV files.

## Getting Started

To use this tool you need Windows (most likely, not tested on other systems) and JRE.
https://www.oracle.com/technetwork/java/javase/downloads/jre9-downloads-3848532.html

You need to download the ReorganizeFiles.jar file. 
https://github.com/LudwigBr/reorganize_files/releases

Start the executable file, located anywhere on your system.

## GUI explanation

The program has two parts. Firstly generation of an CSV file which shows the current structure and file names.
Once generated the structure and names may get changed (for example with Excel) to fit your needs. You should not touch the "Path" and "Filetype" column. Otherwise the files wont be found or may even break.

Secondly you can apply the specified structure and names and copy all files to a new location.

### Creating a CSV File

"Choose Location"-Button: Specify the path in which you want to search. All subfolders will get searched aswell.

"Choose CSV Destination"-Button: Specify the path where the generated CSV file should be saved.

"Create CSV File"-Button: Generate a CSV file.

### Copy and Rename the files according to CSV file

"Choose Destination"-Button: Specify the base path to which the (in the CSV specified) files will get copied.

"Choose Input CSV"-Button: Specify the path in which the CSV file to read from is located.

"Copy and Rename"-Button: Start the process which will apply the structure and file names specified in the CSV file.



