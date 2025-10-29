public class Test{

    /**
     * Test and demonstration method
     */
    public static void main(String[] args) {
        DirectoryManipulation directory = new DirectoryManipulation();
        
        System.out.println("=== Directory Manipulation ===\n");
        
        // Test with current directory and tree
        String[] testDirectories = new String[]{ ".",  "./tree"};
        
        for (String directoryPath : testDirectories) {
            try {
                System.out.println("Analyzing directory: " + directoryPath);
                DirectoryStatistics stats = directory.analyzeDirectory(directoryPath);
                // Print detailed report
                directory.printDetailedReport(stats, directoryPath);

                // clean the directory
                System.out.println("    Cleaning the directory");
                System.out.println("═══════════════════════════════════════");
                if(!directory.cleanDirectory(directoryPath)){
                    System.out.println("No files deleted. " + directoryPath + " does have any empty files or directories");
                }

                // find a file in the directory
                System.out.println("\n═══════════════════════════════════════");
                System.out.println("    Finding a file in the directory");
                System.out.println("═══════════════════════════════════════");
                if(!directory.findFile(directoryPath, "Test.java")){
                    System.out.println("File Test.java not found in " + directoryPath);
                }

                if(!directory.findFile(directoryPath, "blahblah.java")){
                    System.out.println("File \"blahblah\" not found in " + directoryPath);
                }

                // find a word in the directory
                System.out.println("\n═══════════════════════════════════════");
                System.out.println("    Finding a word in the directory");
                System.out.println("═══════════════════════════════════════");
                if(!directory.findWord(directoryPath, "list")){
                    System.out.println("Word \"list\" not found in " + directoryPath);
                }
                Integer number = (int)(Math.random() * 1000_000);
                if(!directory.findWord(directoryPath, number.toString())){
                    System.out.println("Word \"" + number.toString() + "\" not found in " + directoryPath);
                }
                
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            } catch (SecurityException e) {
                System.out.println("Security Error: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("Unexpected Error: " + e.getMessage());
            }
            
            System.out.println();
        }
        // Demonstrate edge cases
        System.out.println("TESTING EDGE CASES:");
        System.out.println("===================");
        testEdgeCases(directory);
    }
    
    /**
     * Test various edge cases and error conditions
     */
    private static void testEdgeCases(DirectoryManipulation directory) {
        String[] edgeCases = {
            null,                           // Null path
            "",                            // Empty path
            "/nonexistent/path",           // Non-existent path
            "./Pair.java"                  // File instead of directory
        };
        
        for (String testCase : edgeCases) {
            try {
                System.out.print("Testing: " + (testCase == null ? "null" : "'" + testCase + "'") + " -> ");
                long size = directory.calculateDirectorySize(testCase);
                System.out.println("Size: " + DirectoryManipulation.formatBytes(size));
            } catch (Exception e) {
                System.out.println("Exception: " + e.getClass().getSimpleName() + " - " + e.getMessage());
            }
        }
    }
}