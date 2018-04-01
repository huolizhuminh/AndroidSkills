
package minhui.demo.designtype.state;

public class Context {
	IState state;

	public IState getState() {
		return state;
	}

	public void setState(IState state) {
		this.state = state;
	}

	public void back() {
		state.back();
	}

	public void forwade() {
		state.forward();
	}
}
