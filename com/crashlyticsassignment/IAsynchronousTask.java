package activities.twitter.com.crashlyticsassignment;

public interface IAsynchronousTask {
	void showProgressLoader();

	void hideProgressLoader();
	
	String doBackgroundPorcess();

	void processDataAfterDownload(String data);

}
