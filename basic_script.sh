#!/bin/bash
FILE=TeamGusteau-RecipeBook
if [ -d "$FILE" ]; then
    echo "$FILE exists."
    cd TeamGusteau-RecipeBook
	javac main.java
	java main
    
else 
    echo "$FILE does not exist."
    git clone https://github.com/jdynk215/TeamGusteau-RecipeBook
	cd TeamGusteau-RecipeBook
	rm -rf basic_script.sh
	javac main.java
	java main
fi

