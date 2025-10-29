/**
     * Statistics class to track directory analysis results
     */
    public class DirectoryStatistics {
        private long totalSize;
        private long fileCount;
        private long directoryCount;
        private long largestFileSize;
        private String largestFileName;
        private Pair[] extensionSizes;
        private long extensionCount;
        private String[] inaccessiblePaths;
        private long inaccessibleCount;
        /**
         * Default constructor
         */
        public DirectoryStatistics() {
            totalSize = 0;
            fileCount = 0;
            directoryCount = 0;
            largestFileSize = 0;
            largestFileName = "";
            extensionSizes = null;
            extensionCount = 0;
            inaccessiblePaths = null;    
            inaccessibleCount = 0; 
                    
        }
        
        /**
         * Getter for totalSize
         * @return the value of totalSize
         */
        public long getTotalSize() { return totalSize; }
        /**
         * Getter for fileCount
         * @return the value of fileCount
         */
        public long getFileCount() { return fileCount; }
        /**
         * Getter for directoryCount
         * @return the value of directoryCount
         */
        public long getDirectoryCount() { return directoryCount; }
        /**
         * Getter for largestFileSize
         * @return the value of largestFileSize
         */
        public long getLargestFileSize() { return largestFileSize; }
        /**
         * Getter for largestFileName
         * @return the value of largestFileName
         */
        public String getLargestFileName() { return largestFileName; }
        /**
         * Getter for extensionSizes
         * @return the reference to the array extensionSizes
         */
        public Pair[] getExtensionSizes() { return extensionSizes; }
        /**
         * Getter for extensionCount
         * @return the value of extensionCount
         */
        public long getExtensionCount() { return extensionCount; }

        /**
         * Increment fileCount
         */
        void incrementFileCount() { 
            fileCount++;
         }
        /**
         * Increment directoryCount
         */
        void incrementDirectoryCount() { 
            directoryCount++;
         }
        /**
         * adds a given value to totalSize
         * @param size the value to add to totalSize
         */
        void addToTotalSize(long size) { 
            totalSize += size;
         }
        /**
         * update the name and size of the largest file if 
         * the given size is greater that the current largest size
         * @param size the size of the given file
         * @param fileName the name of the given file
         */
        void updateLargestFile(long size, String fileName) {
            if (size > largestFileSize){
                largestFileSize = size;
                largestFileName = fileName;
            }
        }
        /**
         * adds a pair (extension, size) to the array extensionSizes
         * if no pair with the value extension is found
         * if a pair is found, add size to the current size of the pair
         * @param extension the name of the extension
         * @param size the given size
         */
        void addExtensionSize(String extension, long size) {
            // initialize the array (first time only)
            if (extensionSizes == null){
                extensionSizes = new Pair[100];
                extensionCount = 0;
            }
            for (int i =0; i< extensionCount; i++){
                if (extensionSizes[i].getType().equals(extension)){
                    long newSize = extensionSizes[i].getSize() + size;
                    extensionSizes[i].setSize(newSize);
                    return;
                }
            }

            extensionSizes[(int)extensionCount] = new Pair (extension, size);
            extensionCount++;
            
        }
    }