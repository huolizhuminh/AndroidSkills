
package minhui.demo.designtype.dutychain;

public class WhiteIntercept implements Intercept {

	@Override
	public Response interceptResponse(Response response) {
		response.response=response.response+"  white ";
		return response;
	}

	@Override
	public Request interceptRequest(Request request) {
		request.request=request.request+" WHITE ";
		return request;
	}

}
