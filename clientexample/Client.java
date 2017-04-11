package clientexample;

import cancerApi.CancerClient;
import cancerApi.Message;

public class Client extends CancerClient {

	protected void onMessageIncoming(Message msg) {
		System.out.println(msg.text);
	}


}
