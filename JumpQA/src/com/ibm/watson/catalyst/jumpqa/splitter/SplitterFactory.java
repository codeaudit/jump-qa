package com.ibm.watson.catalyst.jumpqa.splitter;

public class SplitterFactory {
  
  public static ISplitter createSplitter(AnswerSize answerSize) {
    switch(answerSize) {
      case SENTENCE:
        return new SentenceSplitter();
      case PARAGRAPH:
        return new ParagraphSplitter();
      default:
        throw new RuntimeException("Answer size not implemented: " + answerSize);
    }
  }
  
  public static ISplitter createSplitter(String answerSize) {
    return createSplitter(toAnswerSize(answerSize));
  }
  
  private enum AnswerSize { SENTENCE, PARAGRAPH, TREC }
  
  private static AnswerSize toAnswerSize(String aString) {
    switch(aString.toLowerCase()) {
      case "sentence":
        return AnswerSize.SENTENCE;
      case "paragraph":
        return AnswerSize.PARAGRAPH;
      case "trec":
        return AnswerSize.TREC;
      default:
        throw new RuntimeException("Unknown answer size: " + aString);
    }
  }
  
}
