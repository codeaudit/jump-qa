#JumpQA
_Automating ground truth generation._

JumpQA is a program which takes a corpus and generates questions which can be asked about it. More specifically, it takes a directory of TRECs (in .xml form) and outpus a .csv.

##Installation
1. Clone the repository. You'll need your IBM ID and password.

    ```
    git clone https://hub.jazz.net/git/willbeason/JumpQA
    cd JumpQA/
    ```

2. Use [Maven](https://maven.apache.org/download.cgi) to install the necessary projects.

    ``` 
    find . -name "pom.xml" -exec mvn install package -f '{}' \;
    ```

If desired, you could instead `cd` into each subproject's directory and run `mvn install`.

3. In each subproject's directory, run

    ```
    mvn package
    ```

##Usage

###Converting Corpus to JSON
Before JumpQA can run on a corpus, the corpus must be converted to a .json file. This vastly speeds up how quickly JumpQA can process a corpus.

1. Edit corpus2json.properties.  
```
input=sample/
output=sample/output.json
```
The `input` is a directory holding a list of `.xml` trecs.
The `output` is the output `.json` file. 

2. 

###Using JumpQA
2. Use JumpQA ...



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