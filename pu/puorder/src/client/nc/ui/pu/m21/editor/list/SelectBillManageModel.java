package nc.ui.pu.m21.editor.list;

import java.util.ArrayList;
import java.util.List;

import nc.ui.pubapp.uif2app.model.BillManageModel;
import nc.ui.uif2.model.ModelDataDescriptor;
import nc.vo.pub.ISuperVO;
import nc.vo.pubapp.pattern.model.entity.bill.IBill;

/**
 * 可记录页面选择记录的管理型模型
 * 
 * @since 6.0
 * @version 2013-1-8 下午04:30:57
 * @author liuyand
 */
public class SelectBillManageModel extends BillManageModel {

  /** 选中的单据行 */
  private List<IBill> selectedBills = new ArrayList<IBill>();

  /** 选中的表体行 */
  private List<ISuperVO> selectedBodyRows = new ArrayList<ISuperVO>();

  public List<IBill> getSelectedBills() {
    return this.selectedBills;
  }

  public List<ISuperVO> getSelectedBodyRows() {
    return this.selectedBodyRows;
  }

  @Override
  public void initModel(Object data, ModelDataDescriptor descriptor) {
    super.initModel(data, descriptor);
    this.selectedBills = new ArrayList<IBill>();
    this.selectedBodyRows = new ArrayList<ISuperVO>();
  }

  public void setSelectedBills(List<IBill> selectedBills) {
    if (selectedBills == null) {
      this.selectedBills = new ArrayList<IBill>();
    }
    else {
      this.selectedBills = selectedBills;
    }
  }

  public void setSelectedBodyRows(List<ISuperVO> selectedBodyRows) {
    if (selectedBodyRows == null) {
      this.selectedBodyRows = new ArrayList<ISuperVO>();
    }
    else {
      this.selectedBodyRows = selectedBodyRows;
    }
  }
}
