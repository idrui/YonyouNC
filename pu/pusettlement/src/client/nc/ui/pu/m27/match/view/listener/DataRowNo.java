package nc.ui.pu.m27.match.view.listener;

public class DataRowNo {
  Object data;

  int rowNo;

  public DataRowNo(Object data, int rowNo) {
    super();
    this.data = data;
    this.rowNo = rowNo;
  }

  public Object getData() {
    return this.data;
  }

  public int getRowNo() {
    return this.rowNo;
  }

  public void setData(Object data) {
    this.data = data;
  }

  public void setRowNo(int rowNo) {
    this.rowNo = rowNo;
  }

}
