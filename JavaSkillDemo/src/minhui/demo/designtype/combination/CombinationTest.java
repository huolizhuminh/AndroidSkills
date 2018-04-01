
package minhui.demo.designtype.combination;

import com.sun.javafx.sg.prism.web.NGWebView;

import minhui.demo.DemoInterface;

public class CombinationTest implements DemoInterface {

	@Override
	public void startRun() {
      CombinationNode combinationNode = new CombinationNode("zhangsan ","000");
      LeafNode lisi = new LeafNode("lissi", "002");
      CombinationNode wanger = new CombinationNode("wanger", "003");
      CombinationNode mazi = new CombinationNode("mazi","004");
      LeafNode suwuk = new LeafNode("suwuk", "005");
      wanger.addNode(mazi);
      wanger.addNode(suwuk);
      combinationNode.addNode(lisi);
      combinationNode.addNode(wanger);
      combinationNode.printInfo();
	}

}
