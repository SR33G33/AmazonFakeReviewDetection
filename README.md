# AmazonFakeReviewDetection

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
  gives a doubtability rating of 6 or 3 based on how many stars there are
  2 and lower, 5 ----> 6
  3, 4 -------> 3
  
lengthDoubtability
