/* 
 * Adapted from the example code for Twitter4j by Yusuke Yamamoto,
 * available here 
 * https://github.com/yusuke/twitter4j/blob/master/twitter4j-examples/src/main/java/twitter4j/examples/tweets/UpdateStatus.java
 * Much content is using http://twitter4j.org/en/code-examples.html as a basis as I learn twitter4j 
 
 * Also making much use of http://rahular.com/twitter-sentiment-analysis/
 * 
 */

package twittering4j;

import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;

//import twitter4j.auth.AccessToken;
//import twitter4j.auth.RequestToken;



import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Adapted from https://github.com/yusuke/twitter4j/blob/master/twitter4j-examples/src/main/java/twitter4j/examples/search/SearchTweets.java
 * @param searchTerm
 * @return
 */
public class TwitterAccess {



	static Twitter twitter = TwitterFactory.getSingleton();
	static List<Status> retrievedTweets;
		
//	private static QueryResult getTweetsOf(Query query)  {
//		QueryResult result;
//		try {
//			result = twitter.search(query);
//			for (Status status : result.getTweets()) {
//				tweetList.add(tweet.getText());
//				System.out.println("@" + status.getUser().getScreenName() + ":" + status.getText());
//			}
//					} catch (TwitterException te) {
//			System.out.println("problem with searching for " + query.toString());
//			te.printStackTrace();
//			result = null;
//		}
//
//		return result;
//	}


	private static List<Status> printTweets(Query query) {
		QueryResult result;
		List<Status> tweetStatus;
		try {
			result = twitter.search(query);
			tweetStatus = result.getTweets();
		} catch (TwitterException te) {
			te.printStackTrace();
			tweetStatus = null;
			System.err.println("printTweets() threw exception");
		}
//		for (Status status : result.getTweets()) {
//			System.out.println("@" + status.getUser().getScreenName() + ":" + status.getText());
//		}
		return tweetStatus;
	}


	private static Query setUpQuery(String searchTerm) {
		Query query = new Query(searchTerm);
		query.setCount(100);
		return query;
	}
	
	private static int getOldestTweet()  {
		Date oldestDate = retrievedTweets.get(0).getCreatedAt(); 
		int indexOldestTweet = 0;
		for (int i = 1; i < retrievedTweets.size(); i++)  {   //already checked first one
			System.out.println(retrievedTweets.get(i).getCreatedAt());
			if (retrievedTweets.get(i).getCreatedAt().before(oldestDate)) {
				indexOldestTweet = i;
				oldestDate = retrievedTweets.get(i).getCreatedAt();
			}
		}
		return indexOldestTweet;
	}
	
	
	public static void main(String[] args)  {
		Query query = setUpQuery("from:annajordanous ");
		//QueryResult qr = getTweetsOf(query);
		List<Status> tweets = getNextRoundOfTweets(query);
		//for now
		retrievedTweets = tweets;
		System.out.println("get oldest tweet returns"+getOldestTweet());
	}


	private static List<Status> getNextRoundOfTweets(Query query) {
		List<Status> tweets = printTweets(query);
		for (Status tweet : tweets)  {
			System.out.println("date "+ tweet.getCreatedAt() + ", id = "+tweet.getId());
			//retrievedTweets.add(tweet);
		}
		return tweets;
	}

}
