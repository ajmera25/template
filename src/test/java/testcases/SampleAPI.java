package testcases;

import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.annotations.Test;

import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import utilities.APIUtils;

public class SampleAPI{
	
	@Test
    public void getTestTweet() throws Exception{
		HashMap<String,List<String>> tweetDetails = new HashMap<>();
		APIUtils Utils = new APIUtils();
		List<String> reTwitterList = getRetweetersList(Utils.getMaxImpressionTweetId());
		for(String reTwitterId : reTwitterList) {
			tweetDetails.put(reTwitterId,getFollowerFollowingCount(reTwitterId));
		}
		tweetDetails.put("357443081",getFollowerFollowingCount("357443081"));
		JSONObject obj = new JSONObject(tweetDetails);
		System.out.println(obj);
		
		try (FileWriter file = new FileWriter(System.getProperty("user.dir") + "/src/test/resources/" + "twitterDetails.json")){
			file.write(obj.toString());
		}
    }
	
	public List<String> getRetweetersList(String tweetId) throws Exception {
		List<String> retwitterList;
		OkHttpClient client = new OkHttpClient();

		HttpUrl url = new HttpUrl.Builder().scheme("https").host("api.twitter.com")
				.addPathSegments("/1.1/statuses/retweeters/ids.json").addQueryParameter("id", tweetId)
				.addQueryParameter("stringify_ids", "true").build();

		Request request = new Request.Builder().url(url).build();

		Response response = client.newCall(request).execute();
		retwitterList = Arrays.asList(response.body().string().replace("[", "").replace("]", "").replace("\"", "").split(","));
		System.out.println(retwitterList);
		return retwitterList;
	}
	
	public List<String> getFollowerFollowingCount(String retweeterID) throws Exception {
		List<String> userProfile = new ArrayList<>();
		OkHttpClient client = new OkHttpClient();

		HttpUrl url = new HttpUrl.Builder().scheme("https").host("api.twitter.com").addPathSegments("/1.1/users/lookup.json")
				.addQueryParameter("user_id",retweeterID).build();

		Request request = new Request.Builder().url(url).build();
		 Response response = client.newCall(request).execute();
		 JSONArray array = new JSONArray(response.body().string());
		 //System.out.println(array);
			 JSONObject object = array.getJSONObject(0);
			 userProfile.add(object.get("followers_count").toString());
			 userProfile.add(object.get("friends_count").toString());
			userProfile.add(object.get("description").toString());
			return userProfile;
			 
		
	}
	
}
