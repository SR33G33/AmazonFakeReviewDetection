# AmazonFakeReviewDetection

Main Description:
  This code looks through the review text and searches for a few different factors. For each factor, a number is added to the sum of a
  doubtability score. If this score is greater than 32, then the review is marked as fake and vice versa.

Main:
  parses the text document into different reviews then loops through and "grades" them
  finds the percentage of failures and successes

runTest(boolean output, runs from main):
  runs all of the doubtability methods for each of the reviews
  returns a boolean of if our code deems it true or fake
  
  
starRatingDoubtability(star number imput, no output, runs from runTest):
  adds to the doubtability based on the number of stars
  
lengthDoubtability(text input, no output, runs from runTest):
  
  adds a doubtability rating of based on how long it is, the longer it is the lower the doubtability rating
  
  
checkExclamation(text input, no output, runs from runTest):
  
  if there is only one exclamation mark, adds a doubtability rating of 10
  
  
checkBias(text input, no output, runs from runTest):

  adds doubtability rating of 10 if program detects that the reviewer was paid/got the product for free
  
  
countParagraphs(text input, no output, runs from runTest):

  if there is only 1 paragraph, adds a doubtability score of 3
  
  
helpfulScore:

  adds doubtability score for how many helpful things it got
  
compareWords(text input, no output, runs from runTest):

  if it is a negative review, searches the review for the negative key words
  
  if it is a positive review, searches the review for the positive key words
  
  adds doubtability score of 2 every time a word comes up
  
makeReviewListV2:
  parses the new data set
  
processLineV2:
  helper method for makeReviewListV2

makeReviewList:
  actual method that parses text file
  fun from main
  scanner, scans each line
  
processLine:
  helper method for makeReviewList
  changes values to expected data types

Review Class:

  this is a data transfer object that just holds the review text, star rating, etc.
