package clientexample;

import cancerAPI.CancerClient;

public class Client extends CancerClient {

	protected void onMessageIncoming(String msg) {
		System.out.println(msg);

	}


}
