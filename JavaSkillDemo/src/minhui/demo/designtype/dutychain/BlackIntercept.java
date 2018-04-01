
package minhui.demo.designtype.dutychain;

public class BlackIntercept implements Intercept {

	@Override
	public Response interceptResponse(Response response) {
		response.response=response.response+"  black ";
		return response;
	}

	@Override
	public Request interceptRequest(Request request) {
		request.request=request.request+" BLACK ";
		return request;
	}

}
