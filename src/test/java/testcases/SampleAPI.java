package testcases;

import org.testng.annotations.Test;

import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import utilities.APIUtils;

public class SampleAPI{
	
	
	@Test
    public void getTestTweet() throws Exception{
		APIUtils Utils = new APIUtils();
		getRetweetersList(Utils.getMaxImpressionTweetId());

    }
	
	public void getRetweetersList(String tweetId) throws Exception {
		//tweetId = "327473909412814850";
		OkHttpClient client = new OkHttpClient();

		HttpUrl url = new HttpUrl.Builder().scheme("https").host("api.twitter.com")
				.addPathSegments("/1.1/statuses/retweeters/ids.json").addQueryParameter("id", tweetId)
				.addQueryParameter("stringify_ids", "true").build();

		Request request = new Request.Builder().url(url).build();

		Response response = client.newCall(request).execute();
		System.out.println(response.body().string());
	}
	
}
