import java.io.File;
import java.io.FileNotFoundException;
import java.text.DecimalFormat;
import java.util.*;
/**
 * File Manipulation
 * Calculates the total size of directories and all their contents recursively
 */
public class DirectoryManipulation {
    
    private static final DecimalFormat SIZE_FORMAT = new DecimalFormat("#,##0.##");
    private DirectoryStatistics stats;
    
    /**
     * Calculate the total size of a directory recursively
     * @param directoryPath Path to the directory
     * @return Total size in bytes
     * @throws IllegalArgumentException if path is invalid
     * @throws SecurityException if access is denied
     */
    /**
     * Time Complexity: O(N)
     * Where N is the total number of files and folders
     * We visit each file and folder exactly once
     */
    public long calculateDirectorySize(String directoryPath) throws IllegalArgumentException, SecurityException {
        if (directoryPath == null || directoryPath.isEmpty()){
            throw new IllegalArgumentException("Directory path cannot be null or empty");
        }
        File directory = new File(directoryPath); // convert string to File object

        return calculateDirectorySize(directory);
    }
    
    /**
     * Calculate the total size of a directory recursively
     * @param directory File object representing the directory
     * @return Total size in bytes
     */
    /**
     * Time Complexity: O(N)
     * Where N is the total number of files and folders
     * Delegates to recursive helper after validation
     */
    public long calculateDirectorySize(File directory) throws IllegalArgumentException, SecurityException {
        validateDirectory(directory); 
        return calculateDirectorySize(directory);
    }
    
    /**
     * Recursive method to calculate directory size with detailed statistics
     * @param file Current file or directory being processed
     * @param depth Current recursion depth
     * @return Size of the current file/directory and all its contents
     */
    /**
     * Time Complexity: O(N)
     * Where N is the total number of files and folders.
     * Recursively visits each file and folder once.
     */
    private long calculateDirectorySizeRecursive(File file) {
        // BASE CASE 1: Can't read it
        if (!file.canRead()) {
            return 0;
        }
        
        // BASE CASE 2: It's a file
        if (file.isFile()) {
            long size = file.length();
            
            // NEW: Update statistics if stats object exists
            if (stats != null) {
                stats.incrementFileCount();              // Count this file
                stats.addToTotalSize(size);              // Add to total
                stats.updateLargestFile(size, file.getAbsolutePath());  // Check if it's the biggest
                
                // Get extension and track it
                String extension = getFileExtension(file.getName());
                stats.addExtensionSize(extension, size);
            }
            
            return size;
        }
        
        // RECURSIVE CASE: It's a directory
        long totalSize = 0;
        
        // NEW: Count this directory
        if (stats != null) {
            stats.incrementDirectoryCount();
        }
        
        File[] files = file.listFiles();
        
        if (files == null) {
            return 0;
        }
        
        // Recurse through all items
        for (File f : files) {
            totalSize += calculateDirectorySizeRecursive(f);
        }
        
        return totalSize;
    }
    
    /**
     * Get detailed analysis of directory with statistics
     * @param directory name of the directory to analyze
     * @return DirectoryStatistics object with detailed information
     */
    /**
     * Time Complexity: O(N)
     * Where N is the total number of files and folders
     * Calls calculateDirectorySize which visits each file/folder once
     */
    public DirectoryStatistics analyzeDirectory(String directory) {
         if(directory == null || directory.isEmpty()){
            throw new IllegalArgumentException("Directory path cannot be null or empty");
        }

        File dir = new File(directory);
        return analyzeDirectory(dir);
    }

    /**
     * Get detailed analysis of directory with statistics
     * @param directory File object to analyze
     * @return DirectoryStatistics object with detailed information
     */
        /**
     * Time Complexity: O(N)
     * Where N is the total number of files and folders
     * Calls recursive method that visits each file/folder once
     */

    private DirectoryStatistics analyzeDirectory(File directory) {
        validateDirectory(directory);
        stats = new DirectoryStatistics();
        calculateDirectorySizeRecursive(directory);
        return stats;

    }
    
    /**
     * Validate that the given file is a valid directory
     * @param directory File to validate
     */
    /**
     * Time Complexity: O(1)
     * Constant time - just checking properties of one file object
     */
    private void validateDirectory(File directory) {
        //check if the path exist at all
        if (directory == null || !directory.exists()){
            throw new IllegalArgumentException("Directory path does not exist");
        }
        //checks if it actually a directory and not a file
        if (!directory.isDirectory()){
            throw new IllegalArgumentException("Path is not a directory: " + directory.getPath());
        }
        //can we read from it
        if (!directory.canExecute()){
            throw new SecurityException("Cannot read directory: " + directory.getPath());
        }
        
    }
    
    /**
     * Get file extension from filename
     * @param fileName Name of the file
     * @return File extension (without dot) or "no extension"
     */
    /**
     * Time Complexity: O(1)
     * Constant time - finding last occurrence of '.' in string
     */
    private String getFileExtension(String fileName) {
        int lastDotIndex = fileName.lastIndexOf('.'); //find the last dot in the filename
        // if there's no dot or the dot is at the start (like ".gitignore")
        if (lastDotIndex == -1 || lastDotIndex == 0){
            return "no extension";
        }
        return fileName.substring(lastDotIndex + 1); //return everything after the dot
    }
    
    /**
     * Format bytes into human-readable format
     * @param bytes Number of bytes
     * @return Formatted string (e.g., "1.5 GB", "234.7 MB")
     */
    /**
     * Time Complexity: O(1)
     * Constant time - just arithmetic and string formatting operations
     */
    public static String formatBytes(long bytes) {
        return SIZE_FORMAT.format(bytes) + "B";
    }
    
    /**
     * Print detailed directory analysis report
     * @param stats DirectoryStatistics object
     * @param directoryPath Original directory path
     */
    public void printDetailedReport(DirectoryStatistics stats, String directoryPath) {
        System.out.println("═══════════════════════════════════════");
        System.out.println("    Directory Analysis Results");
        System.out.println("═══════════════════════════════════════");
        System.out.println("Directory: " + directoryPath);
        System.out.println("Analysis Date: " + new Date());
        System.out.println();
        
        System.out.println("SUMMARY:");
        System.out.println("--------");
        System.out.println("Total Size: " + formatBytes(stats.getTotalSize()) + " (" + SIZE_FORMAT.format(stats.getTotalSize()) + " bytes)");
        System.out.println("Files: " + SIZE_FORMAT.format(stats.getFileCount()));
        System.out.println("Directories: " + SIZE_FORMAT.format(stats.getDirectoryCount()));
        System.out.println();
        
        if (stats.getLargestFileSize() > 0) {
            System.out.println("LARGEST FILE:");
            System.out.println("-------------");
            System.out.println("Size: " + formatBytes(stats.getLargestFileSize()));
            System.out.println("File: " + stats.getLargestFileName());
            System.out.println();
        }
        
        if (stats.getExtensionCount() > 0) {
            System.out.println("SIZE BY FILE TYPE:");
            System.out.println("------------------");
            
            // Sort extensions by size (descending)
            sortExtension();
            Pair[] extensions = stats.getExtensionSizes();
            long extCount = stats.getExtensionCount();
            for (int i=0; i< (int)extCount ; i++) {
                double percentage = (double) extensions[i].getSize() / stats.getTotalSize() * 100;
                System.out.printf("%-15s: %15s (%5.1f%%)%n", 
                    extensions[i].getType(), 
                    formatBytes(extensions[i].getSize()), 
                    percentage);
            }
            System.out.println();
        }
        
        System.out.println("═══════════════════════════════════════");
    }
    /**
     * sorts the array etxensionSizes by size
     */
    /**
     * Time Complexity: O(E^2)
     * Where E is the number of different file extensions
     * Selection sort has quadratic complexity
     */
    private void sortExtension() {
        if (stats == null || stats.getExtensionSizes() == null || stats.getExtensionCount() == 0) {
            return;
        }
        
        Pair[] extensions = stats.getExtensionSizes();
        int count = (int) stats.getExtensionCount();
        
        // Selection Sort - find the largest and move it to the front
        for (int i = 0; i < count - 1; i++) {
            // Assume current position has the largest
            int largestIndex = i;
            
            // Search the rest of the array for something larger
            for (int j = i + 1; j < count; j++) {
                if (extensions[j].getSize() < extensions[largestIndex].getSize()) {
                    largestIndex = j;
                }
            }
            
            // Swap the largest with position i
            if (largestIndex != i) {
                Pair temp = extensions[i];
                extensions[i] = extensions[largestIndex];
                extensions[largestIndex] = temp;
            }
        }
    }

    /**
     * 
     * @param directory the name of the file or directory
     * @param filename the name of the file the method searches for
     * @return true if the file was found anywhere in the hierarchy, false if the file was not found
     * prints the absolute path of all the locations where filename was found
     */
    /**
     * Time Complexity: O(N)
     * Where N is the total number of files and folders
     * In worst case, we visit every file and folder to find matches
     */
   public boolean findFile(String directory, String filename) {
        // Validate inputs
        if (directory == null || directory.isEmpty()) {
            throw new IllegalArgumentException("Directory path cannot be null or empty");
        }
        if (filename == null || filename.isEmpty()) {
            throw new IllegalArgumentException("Filename cannot be null or empty");
        }
        
        // Convert to File object
        File dir = new File(directory);
        
        // Validate it exists
        if (!dir.exists()) {
            return false;
        }
        
        // Call the recursive helper
        return findFileRecursive(dir, filename);
    }

/**
 * Recursive helper to search for a file
 */
/**
 * Time Complexity: O(N)
 * Where N is the total number of files and folders
 * Recursively visits each file/folder to search for filename
 */
private boolean findFileRecursive(File file, String filename) {
    // BASE CASE 1: Can't read it
    if (!file.canRead()) {
        return false;
    }
    
    // BASE CASE 2: It's a file - check if the name matches
    if (file.isFile()) {
        if (file.getName().equals(filename)) {
            // Found it! Print the absolute path
            System.out.println("File \"" + filename + "\" found in " + file.getAbsolutePath());
            return true;
        }
        return false;
    }
    
    // RECURSIVE CASE: It's a directory - search inside it
    boolean found = false;
    File[] files = file.listFiles();
    
    if (files == null) {
        return false;
    }
    
    // Search through all items in this directory
    for (File f : files) {
        // Recursively search this item
        if (findFileRecursive(f, filename)) {
            found = true;
            // DON'T return yet - keep searching for more matches
        }
    }
    
    return found;
}
    
    /**
     * deletes all the empty files or folders in the hierarchy of name
     * @param name of the file or directory 
     * prints the list of empty files deleted
     */
    /**
     * Time Complexity: O(N)
     * Where N is the total number of files and folders
     * We visit each file and folder once to check if empty and delete
     */
   public boolean cleanDirectory(String name) {
    // Validate input
    if (name == null || name.isEmpty()) {
        throw new IllegalArgumentException("Path cannot be null or empty");
    }
    
    // Convert to File object
    File file = new File(name);
    
    // Check if it exists
    if (!file.exists()) {
        return false;
    }
    
    // Call recursive helper
    return cleanDirectoryRecursive(file);
}

/**
 * Recursive helper to clean directory
 */
/**
 * Time Complexity: O(N)
 * Where N is the total number of files and folders
 * Recursively visits each file/folder to check if empty
 */
private boolean cleanDirectoryRecursive(File file) {
    // BASE CASE 1: Can't read it
    if (!file.canRead()) {
        return false;
    }
    
    boolean deletedSomething = false;
    
    // CASE 1: It's a directory
    if (file.isDirectory()) {
        File[] files = file.listFiles();
        
        if (files == null) {
            return false;
        }
        
        // FIRST: Recursively clean all subdirectories and files
        for (File f : files) {
            if (cleanDirectoryRecursive(f)) {
                deletedSomething = true;
            }
        }
        
        // after cleaning children, check if 'this' directory is now empty
        files = file.listFiles(); // Re-check contents
        if (files != null && files.length == 0) {
            // Directory is empty - delete it!
            System.out.println("Deleting empty directory: " + file.getAbsolutePath());
            if (file.delete()) {
                deletedSomething = true;
            }
        }
    }
    // CASE 2: It's a file
    else if (file.isFile()) {
        // Check if it's empty (0 bytes)
        if (file.length() == 0) {
            System.out.println("Deleting empty file: " + file.getAbsolutePath());
            if (file.delete()) {
                deletedSomething = true;
            }
        }
    }
    
    return deletedSomething;
}
    /**
     * Searches for the given word in all the files in the hierarchy of files/folder under directory
     * @param directory the name of the file or directory
     * @param word the word being looked up
     * prints the files where the word was found and 
     * the number of occurences of the word in each file where the word was found
     */
    /**
     * Time Complexity: O(N * S)
     * Where N is the number of files/folders and S is the size of the largest file
     * We visit N files, and for each text file we read its contents (up to S bytes)
     */
    public boolean findWord(String directory, String word) {
    // Validate inputs
    if (directory == null || directory.isEmpty()) {
        throw new IllegalArgumentException("Directory path cannot be null or empty");
    }
    if (word == null || word.isEmpty()) {
        throw new IllegalArgumentException("Word cannot be null or empty");
    }
    
    // Convert to File object
    File dir = new File(directory);
    
    // Check if exists
    if (!dir.exists()) {
        return false;
    }
    
    // Call recursive helper
    return findWordRecursive(dir, word);
}

/**
 * Recursive helper to search for a word in files
 */
/**
 * Time Complexity: O(N * S)
 * Where N is the number of files/folders and S is the size of the largest file
 * Visits N items and reads file contents (up to S bytes) for each file
 */
private boolean findWordRecursive(File file, String word) {
    if (!file.canRead()) {
        return false;
    }
    
    if (file.isFile()) {
        try {
            Scanner scanner = new Scanner(file);
            int count = 0;
            
            // Read line by line and count occurrences
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().toLowerCase();
                String searchWord = word.toLowerCase();
                
                // Count occurrences of word in this line
                int index = 0;
                while ((index = line.indexOf(searchWord, index)) != -1) {
                    count++;
                    index += searchWord.length();
                }
            }
            scanner.close();
            
            if (count > 0) {
                System.out.println("\"" + word + "\" found " + count + 
                                   " times in " + file.getAbsolutePath());
                return true;
            }
            
        } catch (FileNotFoundException e) {
            return false;
        }
        
        return false;
    }
    
    // Recursive case: directory
    boolean found = false;
    File[] files = file.listFiles();
    
    if (files == null) {
        return false;
    }
    
    for (File f : files) {
        if (findWordRecursive(f, word)) {
            found = true;
        }
    }
    
    return found;
}


  //helper method to count how many times a word appears in text
 
    private int countOccurrences(String text, String word) {
        int count = 0;
        int index = 0;

        String lowerText = text.toLowerCase();
        String lowerWord = word.toLowerCase();
        
        while ((index = lowerText.indexOf(lowerWord, index)) != -1) {
            count++;
            index += lowerWord.length(); // movse past this occurrence
        }
        
        return count;
    }
}