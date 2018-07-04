package nc.vo.pu.linkquery;

import java.util.Collection;

import nc.bs.pf.pub.PfDataCache;
import nc.bs.trade.billsource.IBillFlow;
import nc.impl.pubapp.linkquery.MultiSrcBillFinder;
import nc.impl.pubapp.linkquery.SingleSuportBillFlow;
import nc.jdbc.framework.DataSourceCenter;
import nc.jdbc.framework.util.DBConsts;
import nc.vo.pub.billtype.BilltypeVO;
import nc.vo.trade.billsource.LightBillVO;

import org.apache.commons.lang.StringUtils;

/**
 * 联查下游有修订情况时，过滤历史版本，只能联查出最新版本
 * 
 * @since 6.0
 * @version 2012-7-30 上午08:59:12
 * @author lixyp
 */
public class PUMultiSrcBillFinder extends MultiSrcBillFinder {

  private String srcBillType;

  @Override
  public LightBillVO[] getForwardBills(String srcBillType1, String curBillType,
      String... srcBillID) {
    this.srcBillType = srcBillType1;
    return super.getForwardBills(srcBillType1, curBillType, srcBillID);
  }

  @Override
  protected String createQueryFwdSql(IBillFlow billflow, String... srcBillID) {
    // 2013-5-27，平台修改方法，加了参数srcBillID
    String sql = super.createQueryFwdSql(billflow, srcBillID);

    if (billflow instanceof SingleSuportBillFlow
        && DBConsts.DB2 == DataSourceCenter.getInstance().getDatabaseType()) {
      String sourceTypeFromBeanid = billflow.getSourceTypeField();
      Collection<BilltypeVO> collbilltypes =
          PfDataCache.getBilltypes().values();
      BilltypeVO billTypeVO = null;
      for (BilltypeVO vo : collbilltypes) {
        if (vo != null
            && StringUtils.equalsIgnoreCase(vo.getPrimaryKey(),
                this.srcBillType)) {
          billTypeVO = vo;
          break;
        }
      }

      if (billTypeVO != null && !this.srcBillType.equals(sourceTypeFromBeanid)
          && !sourceTypeFromBeanid.equals(billTypeVO.getPk_billtypecode())) {
        sql = null;
      }
      else {
        billTypeVO = PfDataCache.getBillType(this.srcBillType);
        if (!this.srcBillType.equals(sourceTypeFromBeanid)
            && !sourceTypeFromBeanid.equals(billTypeVO.getPk_billtypeid())) {
          sql = null;
        }
      }
    }

    if (null != sql && sql.length() > 0) {
      // 增加过滤条件
      String hTbl = billflow.getMainTableName();
      sql += " and " + hTbl + ".bislatest='Y'";
    }
    return sql;
  }

}
