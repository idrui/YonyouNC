package nc.vo.pu.pf;

import java.io.Serializable;

import nc.impl.pubapp.pattern.data.bill.BillQuery;
import nc.md.model.IBusinessEntity;
import nc.ui.pub.print.IMetaDataDataSource;
import nc.vo.pf.change.PfUtilBaseTools;
import nc.vo.pub.ISuperVO;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.model.entity.bill.IBill;
import nc.vo.pubapp.util.MetaUtils;

public class PrintDataSourceForMailAudit implements IMetaDataDataSource {

  /**
   * 邮件审批数据处理器，如精度等
   * 
   * @since 6.0
   * @version 2012-5-13 下午03:21:08
   * @author tianft
   */
  public interface IMailAuditDataProcessor extends Serializable {
    IBill[] processData(IBill[] datas);
  }

  private static final long serialVersionUID = -2860110606555004406L;

  // 单据主表ID
  private String billId;

  // 单据类型
  private String billType;

  private IMailAuditDataProcessor dataProcessor;

  /*
   * 构造子，初始化单据数据
   */
  public PrintDataSourceForMailAudit(String billType, String billId) {
    this.setBillType(billType);
    this.setBillId(billId);
  }

  @Override
  public String[] getAllDataItemExpress() {
    return null;
  }

  @Override
  public String[] getAllDataItemNames() {
    return null;
  }

  public String getBillId() {
    return this.billId;
  }

  public String getBillType() {
    return this.billType;
  }

  public IMailAuditDataProcessor getDataProcessor() {
    return this.dataProcessor;
  }

  @Override
  public String[] getDependentItemExpressByExpress(String itemExpress) {
    return null;
  }

  @Override
  public String[] getItemValuesByExpress(String itemExpress) {
    return null;
  }

  @SuppressWarnings("unchecked")
  @Override
  public Object[] getMDObjects() {
    Class<?> clazz = null;
    try {
      // by zhaoyha at 2011.12.23还是找到单据类型吧，如果流程中心传入的是交易类型，也不会有问题
      String realbtcode = PfUtilBaseTools.getRealBilltype(this.getBillType());
      IBusinessEntity bizbean =
          MetaUtils.getBusinessEntityByBillType(realbtcode);
      clazz =
          MetaUtils.getContainerClass((ISuperVO) Class.forName(
              bizbean.getFullClassName()).newInstance());

    }
    catch (Exception e) {
      ExceptionUtils.wrappException(e);
      return null;
    }
    IBill[] bills =
        new BillQuery<IBill>((Class<IBill>) clazz).query(new String[] {
          this.getBillId()
        });
    if (this.getDataProcessor() != null) {
      bills = this.getDataProcessor().processData(bills);
    }
    return bills;
  }

  /**
   * 按字段属性返回VO值 父类方法重写
   * 
   * @see nc.ui.pub.print.IDataSource#getItemValuesByExpress(java.lang.String)
   */

  @Override
  public String getModuleName() {
    return null;
  }

  @Override
  public boolean isNumber(String itemExpress) {
    return false;
  }

  public void setBillId(String billId) {
    this.billId = billId;
  }

  public void setBillType(String billType) {
    this.billType = billType;
  }

  public void setDataProcessor(IMailAuditDataProcessor dataProcessor) {
    this.dataProcessor = dataProcessor;
  }

}
