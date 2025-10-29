#!/bin/bash
rm -f output 2>&1 >/dev/null
mkdir folder_3
touch ./tree/folder_1/empty.txt

javac *.java
java Test > output