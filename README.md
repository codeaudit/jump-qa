#JumpQA
_Automating ground truth generation._

JumpQA is a program which takes a corpus and generates questions which can be asked about it. More specifically, it takes a directory of TRECs (in .xml form) and outpus a .csv.

##How to Use
First, clone the repository.

    git clone https://hub.jazz.net/git/willbeason/JumpQA
    
It will ask for a username and password, use your IBM ID.

##Projects

###JumpQA
This is the main project. The others are dependencies.

###Corpus
Operations on corpora. Specifically, loading a corpus from a directory of TRECs and converting it into a .json file.

###Corpus TF-IDF
Calcaulates TFIDF of terms in a corpus for each document.

###NeuralNet
Library for neural networks; to be used in JumpQA heuristics.

###Random
A library which includes an ArrayList which iterates randomly.