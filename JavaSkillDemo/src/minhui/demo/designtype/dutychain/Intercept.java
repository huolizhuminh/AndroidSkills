
package minhui.demo.designtype.dutychain;

public interface Intercept {
	Response interceptResponse(Response response);

	Request interceptRequest(Request request);
}
