#!/bin/bash
rm -f output 2>&1 >/dev/null
javac *.java
java EmergencyRoomManager > output.txt
javac Testing.java
java Testing 12 output.reference output.txt