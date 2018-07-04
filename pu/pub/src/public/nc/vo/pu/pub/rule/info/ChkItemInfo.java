package nc.vo.pu.pub.rule.info;

public class ChkItemInfo {

	private String itemCode;

	private String itemName;

	public ChkItemInfo(String itemCode, String itemName) {
		this.itemCode = itemCode;
		this.itemName = itemName;
	}

	public String getItemCode() {
		return this.itemCode;
	}

	public String getItemName() {
		return this.itemName;
	}

	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
}
