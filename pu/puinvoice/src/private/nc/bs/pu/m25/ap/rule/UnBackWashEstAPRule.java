/**
 * $文件说明$
 * 
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-7-28 下午01:21:50
 */
package nc.bs.pu.m25.ap.rule;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import nc.itf.scmpub.reference.arap.ArapServicesForPUUtil;
import nc.vo.pu.est.entity.EstVO;
import nc.vo.pu.m25.entity.InvoiceVO;
import nc.vo.pu.m27.query.SettleBillInfo;
import nc.vo.pubapp.pattern.pub.MapList;

/**
 * 
 * @description
 * 取消传应付时回冲的暂估应付
 * @scene
 * 取消传应付
 * @param
 * estVOMap 发票对象的暂估VO<br>
 * sttlInfoMap 发票对象的结算明细 
 * 
 * @since 6.3
 * @version 2014-10-22 下午3:00:24
 * @author zhangshqb
 */
public class UnBackWashEstAPRule extends BackWashEstAPRule {

  /**
   * UnBackWashEstAPRule 的构造子
   * 
   * @param estVOMap
   * @param sttlInfoMap
   */
  public UnBackWashEstAPRule(Map<String, EstVO[]> estVOMap,
      MapList<String, SettleBillInfo> sttlInfoMap) {
    super(estVOMap, sttlInfoMap);
  }

  @Override
  protected void procInvoiceVOs(InvoiceVO[] vos) {
    List<String> clbhs = new ArrayList<String>();
    for (InvoiceVO vo : vos) {
      clbhs.add(vo.getParentVO().getPk_invoice());// 对应发票表头id
    }
    if (clbhs.size() == 0) {
      return;
    }
    ArapServicesForPUUtil.unWashEstPayable(clbhs.toArray(new String[clbhs
        .size()]));

  }

}
