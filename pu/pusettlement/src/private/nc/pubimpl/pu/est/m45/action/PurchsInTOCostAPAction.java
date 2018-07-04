/**
 * $文件说明$
 * 
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-6-7 下午03:31:32
 */
package nc.pubimpl.pu.est.m45.action;

import org.apache.commons.lang.ArrayUtils;

import nc.vo.ic.general.define.MetaNameConst;
import nc.vo.ic.m45.entity.PurchaseInVO;
import nc.vo.pu.m4201.entity.PurchaseinFIVO;
import nc.vo.pu.pub.util.AggVOUtil;

import nc.bs.pu.est.m45.rule.AutoConfirmFlagFillRule;
import nc.bs.pu.est.m45.rule.ConfirmTOAPRule;
import nc.bs.pu.est.m45.rule.ConfirmTOIARule;
import nc.bs.pu.est.plugin.PurchsInEstPluginPoint;

import nc.impl.pubapp.pattern.data.bill.BillQuery;
import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>发票自动传应付和成本组件的服务
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author zhaoyha
 * @time 2010-6-7 下午03:31:32
 */
public class PurchsInTOCostAPAction {

  public void confirm(PurchaseInVO[] vos) {
    String[] ids =
        (String[]) AggVOUtil.getDistinctItemFieldArray(vos,
            MetaNameConst.CGENERALHID, String.class);
    BillQuery<PurchaseinFIVO> bquery =
        new BillQuery<PurchaseinFIVO>(PurchaseinFIVO.class);
    PurchaseinFIVO[] fiVos = bquery.query(ids);
    if (ArrayUtils.isEmpty(fiVos)) {
      return; // 未查询到可直接传应付和成本数据
    }
    PurchaseinFIVO[] origFiVos = this.cloneFIVos(fiVos);
    AroundProcesser<PurchaseinFIVO> prcr =
        new AroundProcesser<PurchaseinFIVO>(
            PurchsInEstPluginPoint.AUTO_TO_COSTAP);
    this.addRule(prcr);
    prcr.before(fiVos);
    BillUpdate<PurchaseinFIVO> bupdate = new BillUpdate<PurchaseinFIVO>();
    bupdate.update(fiVos, origFiVos);
    prcr.after(fiVos);
  }

  private void addRule(AroundProcesser<PurchaseinFIVO> prcr) {
    prcr.addBeforeFinalRule(new AutoConfirmFlagFillRule());// 更新表头自动传财务标志
    prcr.addBeforeFinalRule(new ConfirmTOAPRule());// 确认应付规则
    // change by wandl 不支持入库确认成本传利润中心
    // prcr.addBeforeFinalRule(new ConfirmTOPCIARule());// 确认利润中心成本规则
    prcr.addBeforeFinalRule(new ConfirmTOIARule());// 确认成本规则
  }

  private PurchaseinFIVO[] cloneFIVos(PurchaseinFIVO[] fiVos) {
    PurchaseinFIVO[] cloned = new PurchaseinFIVO[fiVos.length];
    for (int i = 0; i < cloned.length; i++) {
      cloned[i] = (PurchaseinFIVO) fiVos[i].clone();
    }
    return cloned;
  }

}
