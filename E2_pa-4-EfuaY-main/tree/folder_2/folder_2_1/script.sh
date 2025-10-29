#!/bin/bash
rm -f output 2>&1 >/dev/null
javac CalendarManager.java
java CalendarManager > output.Test
javac Testing.java
java Testing 14 output.reference output.Test