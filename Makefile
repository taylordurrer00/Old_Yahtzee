# -------------------------------------------------------------------------- #
# Makefile for old Yahtzee game.                                             #
# Author:  Taylor Durrer                                                     #
# Date:    August 10, 2015                                                   #
# -------------------------------------------------------------------------- #

# Java compiler
JCC = javac

# Display debugging information when compiling
JFLAGS = -g

# Source files to be compiled
J_SRCS = YahtzeeGame.java


default:
	$(JCC) $(JFLAGS) $(J_SRCS)
	echo 'java YahtzeeGame $$*' > YahtzeeGame
	chmod ug+rx YahtzeeGame

clean:
	rm -f *.class YahtzeeGame

new:
	make clean
	make

