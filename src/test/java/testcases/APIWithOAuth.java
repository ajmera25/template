package testcases;

import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.annotations.Test;

import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import se.akerfeldt.okhttp.signpost.OkHttpOAuthConsumer;
import se.akerfeldt.okhttp.signpost.SigningInterceptor;

public class APIWithOAuth{
	
	@Test
    public void getTestTweet() throws Exception{
		HashMap<String,List<String>> tweetDetails = new HashMap<>();
		List<String> reTwitterList = getRetweetersList(getMaxImpressionTweetId());
		for(String reTwitterId : reTwitterList) {
			tweetDetails.putAll(getFollowerFollowingCount(reTwitterId));
		}
		tweetDetails.putAll(getFollowerFollowingCount("357443081"));
		JSONObject obj = new JSONObject(tweetDetails);
		System.out.println(obj);
		
		try (FileWriter file = new FileWriter(System.getProperty("user.dir") + "/src/test/resources/results/" + "twitterDetails.js")){
			file.write("var tweetData="+obj.toString());
		}
    }
	
	public List<String> getRetweetersList(String tweetId) throws Exception {
		List<String> retwitterList = new ArrayList<>();
		OkHttpOAuthConsumer consumer = new OkHttpOAuthConsumer("rL76CAILFtAmv8lbrJ6sBc249", "tvw0utR7tP4YR8B2WBE7s3sooxGustT44nyrkxAXyRzJ7R2pt1");
		consumer.setTokenWithSecret("3684705912-EpWRUfjVapqfGC2Y9kJRU2Uil7sgtxlsF7xFsxo", "qIh8zC7xiFGgnQVAv8jYONU6CBT2bLm4OTj3mDEFJX0We");


		HttpUrl url = new HttpUrl.Builder().scheme("https").host("api.twitter.com")
				.addPathSegments("1.1/statuses/retweeters/ids.json").addQueryParameter("id", tweetId)
				.addQueryParameter("stringify_ids", "true").build();

		Request request = new Request.Builder().url(url).build();
		OkHttpClient client = new OkHttpClient.Builder()
	            .addInterceptor(new SigningInterceptor(consumer))
	            .build();
		Response response = client.newCall(request).execute();
		
		
		String a = response.body().string();
		JSONObject object = new JSONObject(a);
		JSONArray arrays = object.getJSONArray("ids");
		System.out.println("Id " + arrays);
		for(Object array : arrays) {
			retwitterList.add(array.toString());
		}
		
		System.out.println("retwitterList=" + retwitterList);
		return retwitterList;
	}
	
	public HashMap<String,List<String>> getFollowerFollowingCount(String retweeterID) throws Exception {
		HashMap<String,List<String>> userProfile = new HashMap<String,List<String>>();
		List<String> tweetProfile = new ArrayList<>();
		OkHttpOAuthConsumer consumer = new OkHttpOAuthConsumer("rL76CAILFtAmv8lbrJ6sBc249", "tvw0utR7tP4YR8B2WBE7s3sooxGustT44nyrkxAXyRzJ7R2pt1");
		consumer.setTokenWithSecret("3684705912-EpWRUfjVapqfGC2Y9kJRU2Uil7sgtxlsF7xFsxo", "qIh8zC7xiFGgnQVAv8jYONU6CBT2bLm4OTj3mDEFJX0We");

		HttpUrl url = new HttpUrl.Builder().scheme("https").host("api.twitter.com").addPathSegments("1.1/users/lookup.json")
				.addQueryParameter("user_id",retweeterID).build();

		Request request = new Request.Builder().url(url).build();
		OkHttpClient client = new OkHttpClient.Builder()
	            .addInterceptor(new SigningInterceptor(consumer))
	            .build();
		 Response response = client.newCall(request).execute();
		 JSONArray array = new JSONArray(response.body().string());
		 //System.out.println(array);
			 JSONObject object = array.getJSONObject(0);
			 
			 tweetProfile.add(object.get("followers_count").toString());
			 tweetProfile.add(object.get("friends_count").toString());
			 tweetProfile.add(object.get("description").toString());
			 userProfile.put(object.get("screen_name").toString(), tweetProfile);
			return userProfile;
	}
	
	public String getMaxImpressionTweetId() {
		int MaxImpression =0;
		String tweetId = null;
		try {
			OkHttpOAuthConsumer consumer = new OkHttpOAuthConsumer("rL76CAILFtAmv8lbrJ6sBc249", "tvw0utR7tP4YR8B2WBE7s3sooxGustT44nyrkxAXyRzJ7R2pt1");
			consumer.setTokenWithSecret("3684705912-EpWRUfjVapqfGC2Y9kJRU2Uil7sgtxlsF7xFsxo", "qIh8zC7xiFGgnQVAv8jYONU6CBT2bLm4OTj3mDEFJX0We");
			
			HttpUrl url = new HttpUrl.Builder()
					.scheme("https")
					.host("api.twitter.com")
					.addPathSegments("1.1/statuses/user_timeline.json")
					.addQueryParameter("screen_name","stepin_forum")
					.build();
			
			Request request = new Request.Builder()
				      .url(url)
				      .build();
			
			OkHttpClient client = new OkHttpClient.Builder()
		            .addInterceptor(new SigningInterceptor(consumer))
		            .build();
			 Response response = client.newCall(request).execute();
			 
			// JSONObject object = new JSONObject(response.body().string());
			 JSONArray array = new JSONArray(response.body().string());
			 HashMap<String,Integer> tweetImpressions = new HashMap<String,Integer>();
			 System.out.println(array);
			 for(int i=0;i<array.length();i++) {
				 JSONObject object = array.getJSONObject(i);
				 tweetImpressions.put(object.get("id").toString(), object.getInt("retweet_count") + object.getInt("favorite_count"));
			 }
			 System.out.println(tweetImpressions);
			 
			 for(String str :tweetImpressions.keySet()) {
				 int currentImpression = tweetImpressions.get(str);
				 if(MaxImpression<currentImpression) {
					 MaxImpression = currentImpression;
					 tweetId = str;
					 
				 }
			 }
			 System.out.println(tweetId  +  " " + MaxImpression);
	}catch(Exception e){
		e.printStackTrace();
	}
		return tweetId;
	}
	}
	
