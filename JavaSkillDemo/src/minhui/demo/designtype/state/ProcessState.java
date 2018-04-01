
package minhui.demo.designtype.state;

public class ProcessState extends IState {

	public ProcessState(Context context) {
		super(context);
	}

	@Override
	public void back() {
		System.out.println("ProcessState back");
		context.setState(new InitState(context));
	}

	@Override
	public void forward() {
		System.out.println("ProcessState forward");
		context.setState(new FinishState(context));
	}

}
