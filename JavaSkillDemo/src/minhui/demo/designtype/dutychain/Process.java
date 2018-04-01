
package minhui.demo.designtype.dutychain;

import java.util.ArrayList;
import java.util.List;

public class Process {
	private List<Intercept> intercepts = new ArrayList<>();

	public void addIntercept(Intercept intercept) {
		intercepts.add(intercept);
	}

	public void process(Request request) {
		Object[] interceptArrays =  intercepts.toArray();
		for (Object intercept : interceptArrays) {
			request =((Intercept)intercept) .interceptRequest(request);
		}
		System.out.println("current request " + request.request);
		Response response = new Response();
		response.response = request.request.toUpperCase();
		for (int i = interceptArrays.length - 1; i >= 0; i--) {
			response = ((Intercept)interceptArrays[i]).interceptResponse(response);
		}
		System.out.println("current response "+response.response);
	}
}
