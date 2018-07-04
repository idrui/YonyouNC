package nc.bs.pu.est.rule;

import java.util.Set;

import nc.impl.pubapp.pattern.database.DataAccessUtils;
import nc.impl.pubapp.pattern.database.IDExQueryBuilder;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.itf.pu.reference.ia.IAForEstConfirmServices;
import nc.itf.pu.reference.pcia.PCIAForEstConfirmServices;
import nc.itf.scmpub.reference.uap.group.SysInitGroupQuery;
import nc.vo.pu.est.entity.EstVO;
import nc.vo.pu.est.entity.FeeEstDistVO;
import nc.vo.pu.est.entity.FeeEstVO;
import nc.vo.pu.est.util.EstVOUtil;
import nc.vo.pu.pub.constant.PUEntity;
import nc.vo.pu.pub.constant.PUTempTable;
import nc.vo.pu.pub.util.CirVOUtil;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.data.IRowSet;
import nc.vo.pubapp.pattern.pub.SqlBuilder;
import nc.vo.scmpub.res.billtype.ICBillType;

import org.apache.commons.lang.ArrayUtils;

/**
 * 
 * @description
 * 取消费用暂估的I9单据
 * @scene
 * 取消暂估-BP
 * @param
 * 无
 *
 * @since 6.3
 * @version 2014-10-23 上午9:47:49
 * @author zhangshqb
 */
public class FeeUnEstIARule implements IRule<EstVO> {

  @Override
  public void process(EstVO[] vos) {
    if (!SysInitGroupQuery.isIAEnabled()) {
      return;
    }
    FeeEstVO[] fees = (FeeEstVO[]) EstVOUtil.getFeeEstVOs(vos);
    if (ArrayUtils.isEmpty(fees)) {
      return;
    }
    String billtype = vos[0].getParentVO().getBillType();
    String[][] ids = this.getIDForDel(fees, billtype);
    if (null == ids) {
      return;
    }
    if (ICBillType.PurchaseIn.getCode().equals(billtype)) {
      IAForEstConfirmServices.feeUnEstimate(ids[0], ids[1]);
      // mengjian by 20141021 启用利润中心存货核算时
      if (SysInitGroupQuery.isPCIAEnabled()) {
        PCIAForEstConfirmServices.feeUnEstimate(ids[0], ids[1]);
      }
    }
    else if (ICBillType.VmiSum.getCode().equals(billtype)) {
      IAForEstConfirmServices.unVMIFeeEstimate(ids[0], ids[1]);
    }
  }

  private String getFeeEstDistTable(String billtype) {
    if (ICBillType.PurchaseIn.getCode().equals(billtype)) {
      return PUEntity.PurchaseinFI_FD_TAB;
    }
    else if (ICBillType.VmiSum.getCode().equals(billtype)) {
      return PUEntity.VMIFI_FD_TAB;
    }
    return null;
  }

  private String getFeeEstTable(FeeEstVO feeEstVo) {
    return feeEstVo.getMetaData().getPrimaryAttribute().getColumn().getTable()
        .getName();
  }

  private String[][] getIDForDel(FeeEstVO[] feeEstVos, String billtype) {
    Set<String> feeIDSet =
        CirVOUtil.getDistinctFieldSet(feeEstVos, FeeEstVO.PK_STOCKPS_FEE);
    String[] feeIDs = feeIDSet.toArray(new String[feeIDSet.size()]);
    String feeIDIn =
        new IDExQueryBuilder(PUTempTable.tmp_po_est_03.name()).buildSQL("fee."
            + FeeEstVO.PK_STOCKPS_FEE, feeIDs);
    SqlBuilder sql = new SqlBuilder();
    sql.append("select fee." + FeeEstVO.PK_STOCKPS + ",");
    sql.append("feedist." + FeeEstDistVO.PK_IASRC_B);
    sql.append(" from " + this.getFeeEstTable(feeEstVos[0]) + " fee");
    sql.append(" inner join  ");
    sql.append(this.getFeeEstDistTable(billtype) + " feedist");
    sql.append(" on fee." + FeeEstVO.PK_STOCKPS_FEE + "=");
    sql.append("feedist." + FeeEstDistVO.PK_STOCKPS_FEE);
    sql.append(" where " + feeIDIn + " and ");
    // 必须是暂估传过成本的才能删除
    sql.append(" fee." + FeeEstVO.BTOIA, UFBoolean.TRUE);
    sql.append(" group by fee." + FeeEstVO.PK_STOCKPS + ",");
    sql.append("feedist." + FeeEstDistVO.PK_IASRC_B);
    DataAccessUtils dau = new DataAccessUtils();
    IRowSet rs = dau.query(sql.toString());
    if (rs.size() == 0) {
      return null;
    }
    String[][] ids = new String[2][rs.size()];
    int i = 0;
    while (rs.next()) {
      ids[0][i] = rs.getString(0);
      ids[1][i] = rs.getString(1);
      i++;
    }
    return ids;
  }

}
