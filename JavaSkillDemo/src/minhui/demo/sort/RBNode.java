
package minhui.demo.sort;

/**
 * 
 * 1.每个节点不是红色就是黑色的。 2.根节点总是黑色的。 3. 每个叶子节点（NULL）是黑色。（叶子节点是指空的叶子节点）。
 * 4.如果节点是红色的，则它的子节点必须是黑色的（反之不一定）； 5.从根节点到叶节点或空子节点的每条路径，必须包含相同数目的黑色节点（即相同的黑色高度）。
 * 
 * 
 * 
 */
public class RBNode {
	int value;
	RBNode leftNode;
	RBNode rightNode;
	RBNode formerNode;
	boolean isRight = false;
	boolean isBlack = false;

	private RBNode() {

	}

	private RBNode copyData() {
		RBNode copyNode = new RBNode();
		copyNode.value = value;
		copyNode.leftNode = leftNode;
		copyNode.rightNode = rightNode;
		copyNode.formerNode = formerNode;
		copyNode.isRight = isRight;
		copyNode.isBlack = isBlack;
		return copyNode;
	}

	public RBNode newRBTree(int value) {
		RBNode rbNode = new RBNode();
		rbNode.isBlack = true;
		rbNode.value = value;
		return this;
	}

	public void addNode(int addValue) {
		if (addValue <= value) {
			// 左树为空则新建左树，否则在左树上寻找合适的挂载点
			if (leftNode == null) {
				leftNode = new RBNode();
				leftNode.formerNode = this;
				leftNode.value = addValue;
				leftNode.isRight = false;
				leftNode.ajustTreeAfterInsert();
			} else {
				leftNode.addNode(addValue);
			}

		} else {
			// 右树为空则新建左树，否则在右树上寻找合适的挂载点
			if (rightNode == null) {
				rightNode = new RBNode();
				rightNode.formerNode = this;
				rightNode.value = addValue;
				rightNode.isRight = true;
				rightNode.ajustTreeAfterInsert();
			} else {
				rightNode.addNode(addValue);
			}
		}

	}

	public void deletNode(int deleteValue) {
		RBNode deleteNode = null;
		RBNode searchNode = this;
		for (;;) {
			if (deleteValue == searchNode.value) {
				deleteNode = searchNode;
				break;
			} else if (deleteValue < searchNode.value) {
				searchNode = searchNode.leftNode;

			} else {
				searchNode = searchNode.rightNode;
			}
			if (searchNode == null) {
				return;
			}
		}
		if (searchNode.leftNode == null && searchNode.rightNode == null) {
			deleteNoBrandNode(searchNode);
			return;
		}
		// 按照红黑色原理，单个子树只存在父为黑，子为红
		if (searchNode.leftNode == null) {
			searchNode.value = searchNode.rightNode.value;
			searchNode.rightNode.formerNode = null;
			searchNode.rightNode = null;
			return;
		}
		if (searchNode.rightNode == null) {
			searchNode.value = searchNode.leftNode.value;
			searchNode.leftNode.formerNode = null;
			searchNode.leftNode = null;
			return;
		}
		RBNode replaceMent = succssor(searchNode);
		if (replaceMent == null) {
			if (searchNode.isBlack) {
				searchNode.value = searchNode.leftNode.value;
				if (!searchNode.leftNode.isBlack) {
					searchNode.leftNode.formerNode = null;
					searchNode.leftNode = null;
					return;
				}
				deleteNoBrandNode(searchNode.leftNode);
			} else {
				searchNode.value = searchNode.leftNode.value;
				searchNode.leftNode.formerNode = null;
				searchNode.leftNode = null;
				searchNode.isBlack = true;
				searchNode.rightNode.isBlack = false;
			}
			return;
		}
		searchNode.value = replaceMent.value;

		// 按照红黑色原理，单个子树只存在父为黑，子为红
		if (!replaceMent.isRight && replaceMent.rightNode != null) {
			replaceMent.value = replaceMent.rightNode.value;
			replaceMent.rightNode.formerNode = null;
			searchNode.rightNode = null;
			return;
		}
		if (replaceMent.isRight && searchNode.leftNode != null) {
			searchNode.value = searchNode.leftNode.value;
			searchNode.leftNode.formerNode = null;
			searchNode.leftNode = null;
			return;
		}

		deleteNoBrandNode(replaceMent);
	}

	private RBNode succssor(RBNode searchNode) {
		RBNode replaceMent = null;
		if (searchNode.leftNode != null && searchNode.leftNode.rightNode != null) {
			RBNode lastRight = searchNode.leftNode.rightNode;
			for (;;) {
				replaceMent = lastRight;
				lastRight = lastRight.rightNode;
				if (lastRight == null) {
					break;
				}
			}
		} else if (searchNode.rightNode != null && searchNode.rightNode.leftNode != null) {
			RBNode lastLeft = searchNode.rightNode.leftNode;
			for (;;) {
				replaceMent = lastLeft;
				lastLeft = lastLeft.leftNode;
				if (lastLeft == null) {
					break;
				}
			}
		}
		return replaceMent;
	}

	private void deleteNoBrandNode(RBNode delteNode) {
		RBNode changedNode=delteNode;
		while (changedNode.formerNode != null && changedNode.isBlack) {
			//黑红黑结构直接
			if (!changedNode.formerNode.isBlack) {
				if(changedNode.isRight){
					changedNode.formerNode.rightTurn();
				}else{
					changedNode.formerNode.leftTurn();
				}
				changedNode=changedNode.formerNode;
				continue;
			}
			RBNode sildeNode = changedNode.isRight ? changedNode.formerNode.leftNode : changedNode.formerNode.rightNode;
			
		}
	}

	// 1. 插入节点的父节点和其叔叔节点（祖父节点的另一个子节点）均为红色的则将当前节点的父节点和叔叔节点涂黑，将祖父节点涂红
	//
	// 2. 插入节点的父节点是红色，叔叔节点是黑色，且插入节点是其父节点的右子节点则将当前节点的父节点作为新的节点，以新的当前节点为支点做左旋操作。
	//
	// 3. 插入节点的父节点是红色，叔叔节点是黑色，且插入节点是其父节点的左子节点，将当前节点的父节点涂黑，将祖父节点涂红，在祖父节点为支点做右旋操作。
	// 按照1 2 3的步骤循环肯定可以调整完 整个调整过程是向上调整，向中间调整，一直调整到根结点 有点类似于魔方和拼图游戏
	private void ajustTreeAfterInsert() {
		if (formerNode == null) {
			isBlack = true;
			return;
		}
		// 如果父节点是黑色 直接返回
		if (formerNode.isBlack) {
			return;
		}
		// 插入节点的父节点和其叔叔节点（祖父节点的另一个子节点）均为红色的
		RBNode grandFather = formerNode.formerNode;

		RBNode uncle = formerNode.isRight ? grandFather.leftNode : grandFather.rightNode;
		// 祖父节点左旋或者右旋，祖父节点成为父节点的子树
		if (uncle == null) {
			RBNode lastFormer = formerNode;
			if (formerNode.isRight) {
				grandFather.leftTurn();
			} else {
				grandFather.rightTurn();
			}

			// 将原父节点变成黑色，原祖父结点变成红色
			lastFormer.isBlack = true;
			grandFather.isBlack = false;
			return;
		}
		// 将当前节点的父节点和叔叔节点涂黑，将祖父节点涂红。再将当前节点指向其祖父节点，再次从新的当前节点开始算法
		if (!uncle.isBlack) {
			formerNode.isBlack = true;
			uncle.isBlack = true;
			grandFather.isBlack = false;
			grandFather.ajustTreeAfterInsert();

		} else {
			if (isRight) {
				formerNode.leftTurn();
				formerNode.ajustTreeAfterInsert();
			} else {
				formerNode.isBlack = true;
				grandFather.isBlack = false;
				grandFather.rightTurn();
				if (grandFather.rightNode.isBlack = false) {
					grandFather.rightNode.ajustTreeAfterInsert();
				}
			}
		}

	}

	private void rightTurn() {
		// 无左树不能右旋
		if (leftNode == null) {
			return;
		}
		RBNode lastLeftNode = leftNode;
		if (formerNode != null) {
			if (isRight) {
				formerNode.rightNode = leftNode;
			} else {
				formerNode.leftNode = leftNode;
			}
			leftNode.formerNode = formerNode;
			leftNode.isRight = isRight;
		}
		leftNode = leftNode.rightNode;
		if (leftNode != null) {
			leftNode.formerNode = this;
			leftNode.isRight = false;
		}
		lastLeftNode.rightNode = this;
		formerNode = lastLeftNode;
		isRight = true;
	}

	private void leftTurn() {
		if (rightNode == null) {
			return;
		}
		RBNode lastRightNode = rightNode;
		if (formerNode != null) {
			if (isRight) {
				formerNode.rightNode = rightNode;
			} else {
				formerNode.leftNode = rightNode;
			}
			rightNode.formerNode = formerNode;
			rightNode.isRight = isRight;
		}
		rightNode = rightNode.leftNode;
		if (rightNode != null) {
			rightNode.formerNode = this;
			rightNode.isBlack = true;
		}
		lastRightNode.leftNode = this;
		this.formerNode = lastRightNode;
		isRight = false;

	}

}
