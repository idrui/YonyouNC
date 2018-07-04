package nc.pubitf.pu.m27.ec;

import nc.vo.pu.m27.entity.SettleBillHeaderVO;
import nc.vo.pu.m27.entity.SettleBillItemVO;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.model.entity.view.AbstractDataView;
import nc.vo.pubapp.pattern.model.meta.entity.view.DataViewMeta;
import nc.vo.pubapp.pattern.model.meta.entity.view.DataViewMetaFactory;
import nc.vo.pubapp.pattern.model.meta.entity.view.IDataViewMeta;

/**
 * @since 6.0
 * @version 2011-5-16 下午12:16:58
 * @author wuxla
 */

public class SettleBillViewECVO extends AbstractDataView {
  public static class SettleBillViewECVOMeta extends DataViewMeta {
    public SettleBillViewECVOMeta() {
      this.init();
    }

    private void init() {
      // 结算单号、结算日期、入库单号、发票号、单位id、结算数量、单价、
      // 金额
      this.add(SettleBillItemVO.class, new String[] {
        SettleBillItemVO.PK_STOCKORDER_B, SettleBillItemVO.VSTOCKCODE,
        SettleBillItemVO.VINVOICECODE, SettleBillItemVO.CUNITID,
        SettleBillItemVO.NSETTLENUM, SettleBillItemVO.NPRICE,
        SettleBillItemVO.NMONEY
      });
      this.add(SettleBillHeaderVO.class, new String[] {
        SettleBillHeaderVO.VBILLCODE, SettleBillHeaderVO.DBILLDATE
      });
      this.addRelation(SettleBillItemVO.class, SettleBillItemVO.PK_SETTLEBILL,
          SettleBillHeaderVO.class, SettleBillHeaderVO.PK_SETTLEBILL);
    }
  }

  private static final long serialVersionUID = -7575084489897849592L;

  /** 主单位 getter 方法 */
  public String getCunitid() {
    return (String) this.getAttributeValue(SettleBillItemVO.CUNITID);
  }

  /** 结算日期 **/
  public UFDate getDbilldate() {
    return (UFDate) this.getAttributeValue(SettleBillHeaderVO.DBILLDATE);
  }

  @Override
  public IDataViewMeta getMetaData() {
    return DataViewMetaFactory.getInstance().getDataViewMeta(
        SettleBillViewECVOMeta.class);
  }

  /** 金额 getter 方法 */
  public UFDouble getNmoney() {
    return (UFDouble) this.getAttributeValue(SettleBillItemVO.NMONEY);
  }

  /** 单价 getter 方法 */
  public UFDouble getNprice() {
    return (UFDouble) this.getAttributeValue(SettleBillItemVO.NPRICE);
  }

  /** 结算数量 getter 方法 */
  public UFDouble getNsettlenum() {
    return (UFDouble) this.getAttributeValue(SettleBillItemVO.NSETTLENUM);
  }

  /** 订单表体pk getter 方法 */
  public String getPk_stockorder_b() {
    return (String) this.getAttributeValue(SettleBillItemVO.PK_STOCKORDER_B);
  }

  /** 结算单号 **/
  public String getVbillcode() {
    return (String) this.getAttributeValue(SettleBillHeaderVO.VBILLCODE);
  }

  /** 发票号 getter 方法 */
  public String getVinvoicecode() {
    return (String) this.getAttributeValue(SettleBillItemVO.VINVOICECODE);
  }

  /** 库存单据号 getter 方法 */
  public String getVstockcode() {
    return (String) this.getAttributeValue(SettleBillItemVO.VSTOCKCODE);
  }
}
