## Programming Assignment 4
# Recursion 

### Activity Objectives
At the end of this assignment, students should be able to:
- Write recursive methods to solve a given problem
- Analyze the time complexity of recursive methods
- Test the recursive methods

* Useful APIs from the `File` class
- `boolean isFile()` returns true if the File object is associated with a text file
- `boolean isDirectory()` returns true if the filename associated with the File object is a directory (aka folder)
- `long length()` returns the size of the file (as a long) in bytes 
- `File[] listFiles()` returns an array that contains the list of files and subfolders in the directory
- `String getName()` returns the name of the file or directory associated with the File object
- `String getAbsolutePath()` returns the path to the file or directory associated with the File object
Details about these methods can be found in the Java 11 Documentation for java.io.File

### Description
In the file `Recursion.java`, write the definitions of the five recursive methods described below:

- `public static long size(File file)` returns the size of `file` in bytes. Return -1 if `file` is not found. The method should return the size of the file in bytes if `file` is a file. If `file` is a folder, the method should recursively traverse the contents of `file` to return the sum of the sizes of all the files/folders under `file`

- `public static void listContents(File file)` prints the file and its size if `file` is a file, or the list of files and folders under `file` with their corresponding size if `file` is a folder

- `public static boolean find(File file, String filename)` prints the absolute path of all the locations where the file `filename` was found

- `public static void findWord(File file, String word)` prints the number of occurrences of `word` in all the files under `file`, which can be a single file or a directory. The method should look for `word` recursively in all the files in file. For each file, only print if the word is found at least once

- `public static void delete(File file)` removes all the empty files and folders under `file`

Define the following method to be used by listContents to print the size of files/folders as a formatted string with the appropriate unit

- `public static String formatSize(long size)` accepts a size in bytes and returns a formatted string with the appropriate unit suffix. See the example below:

`formatSize(52500)` returns `"52.40 Kbytes"`
`formatSize(2999000)` returns  `"2.99 Mbytes"`
`formatSize(9072000000)` returns `"9.07 Gbytes"`


Using Big-O notation, determine the time complexity of the five recursive methods using 
- `N` the number of files and folders under the input directory and 
- `M` the size of the largest file under the input directory
