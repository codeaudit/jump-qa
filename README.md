#JumpQA
_Automating ground truth generation._

JumpQA is a projects for generating questions about a corpus. More specifically, it takes a directory of TRECs (in .xml form) and outpus a .csv.

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

##Basic Usage
Before JumpQA can run on a corpus, the corpus must be converted to a .json file. This vastly speeds up how quickly JumpQA can process a corpus. Many of the fields in the TRECs are currently removed. If you wish to keep non-default ones, you will need to edit `corpus2json`. Eventually, it will be possible to specify which fields to keep in the `.properties` file.

Once the corpus is in a JSON, JumpQA can process the corpus and generate ground truth. 

###Converting Corpus to JSON
1. Edit `corpus2json.properties`. The file should look something like:
```
input=sample/
output=sample/output.json
```
The `input` is a directory holding a list of `.xml` trecs. Change it to the directory holding your corpus XMLs.
The `output` is the output `.json` file. 

2. Run `java -jar target/corpus2json-0.1.0.jar`. If you have a different version, replace `0.1.0` with the one you are using. 

###Using JumpQA
1. Edit `jumpqa.properties`. The file should look something like:
```
templates=templates.csv
trecs=health-corpus.json
output=health.csv
```
`templates` is the templates file to process the TRECs with.
`trecs` is the inputs JSON file.
`output` is the output CSV file.
2. Run `java -jar target/jumpqa-0.1.0.jar`


##Projects

###[JumpQA](JumpQA/README.md)
This is the main project.

JumpQA takes a corpus in JSON form and creates a set of ground truth. The others are related projects and dependencies.

###[Corpus](Corpus/README.md)
This converts a directory of TRECs to a JSON file that JumpQA can use.

###Corpus TF-IDF
Calcaulates TFIDF of terms in a corpus for each document.

###NeuralNet
Library for neural networks; to be used in JumpQA heuristics.

###Random
A library which includes an ArrayList which iterates randomly.