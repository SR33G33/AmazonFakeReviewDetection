# AmazonFakeReviewDetection

Main Description:
  This code looks through the review text and searches for a few different factors. For each factor, a number is added to the sum of a
  doubtability score. If this score is greater than 19, then the review is marked as fake and vice versa.

Main:
  
  parses the text document into different reviews then loops through and "grades" them
  
  finds the percentage of failures and successes
  
makeReviewList:
  
  actual method that parses text file
  
  fun from main
  
  scanner, scans each line
  
processLine:
  
  helper method for makeReviewList
  
  changes values to expected data types

runTest:
  
  run from main
  
  list of all the factors that the code runs through to evaluate if it is real or false
  
starRatingDoubtability:
  
  adds a doubtability rating of 6 or 3 based on how many stars there are
  
  2 and lower, 5 ----> 6
  
  3, 4 -------> 3
  
lengthDoubtability:
  
  adds a doubtability rating of 10, 6, 3, or -5 based on how long it is
  
  the longer it is the lower the doubtability rating
  
checkExclamation:
  
  if there is only one exclamation mark, adds a doubtability rating of 10
  
checkBias:

  adds doubtability rating of 10 if program detects that the reviewer was paid/got the product for free
  
countParagraphs:

  if there is only 1 paragraph, adds a doubtability score of 3
  
helpfulScore:

  adds doubtability score for how many helpful things it got
  
  0 ------------->3
  
  1 ------------->5
  
compareWords:

  if it is a negative review, searches the review for the negative key words
  
  if it is a positive review, searches the review for the positive key words
  
  adds doubtability score of 2 every time a word comes up
  
Review Class:

  this is a data transfer object that just holds the review text, star rating, etc.
