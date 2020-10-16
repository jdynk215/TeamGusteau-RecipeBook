#!/bin/bash
FILE=TeamGusteau-RecipeBook
if [ -d "$FILE" ]; then
    echo "$FILE found, loading $FILE from local"
    cd TeamGusteau-RecipeBook
	javac main.java
	java main
    
else 
    echo "$FILE not found, cloning $FILE to local"
    git clone https://github.com/jdynk215/TeamGusteau-RecipeBook
	cd TeamGusteau-RecipeBook
	rm -rf basic_script.sh
	javac main.java
	java main
fi

