/**
 * $文件说明$
 * 
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-4-29 上午10:10:54
 */
package nc.vo.pu.m25.rule.maintain.save;

import java.util.Map;

import nc.impl.pubapp.pattern.rule.ICompareRule;
import nc.vo.pu.m25.entity.InvoiceHeaderVO;
import nc.vo.pu.m25.entity.InvoiceItemVO;
import nc.vo.pu.m25.entity.InvoiceVO;
import nc.vo.pu.pub.util.AggVOUtil;
import nc.vo.pub.VOStatus;
import nc.vo.pub.lang.UFDate;

import org.apache.commons.lang.ObjectUtils;

/**
 * @description
 *              同步表体冗余信息规则
 * @scene
 *        保存的BP
 * @param 无
 * @since 6.3
 * @version 2014-10-22 下午3:27:07
 * @author zhangshqb
 */
public class SynchronizeDupDataRule implements ICompareRule<InvoiceVO> {

  @Override
  public void process(InvoiceVO[] vos, InvoiceVO[] originVOs) {
    Map<String, InvoiceVO> map = AggVOUtil.createVOMap(originVOs);
    for (InvoiceVO vo : vos) {
      InvoiceHeaderVO head = vo.getParentVO();
      String pk_org = head.getPk_org();
      String pk_org_v = head.getPk_org_v();
      String pk_group = head.getPk_group();
      UFDate date = head.getDbilldate();
      String pk_supplier = head.getPk_supplier();

      boolean change = false;
      if (map != null) {
        InvoiceVO orgVO = map.get(head.getPk_invoice());
        if (orgVO != null && orgVO.getParentVO() != null) {
          InvoiceHeaderVO orgHead = orgVO.getParentVO();
          if (!ObjectUtils.equals(date, orgHead.getDbilldate())
              || !ObjectUtils.equals(pk_supplier, orgHead.getPk_supplier())) {
            change = true;
          }
        }
      }

      for (InvoiceItemVO item : vo.getChildrenVO()) {
        if (VOStatus.UNCHANGED == item.getStatus() && change) {
          item.setStatus(VOStatus.UPDATED);
        }
        item.setPk_org(pk_org);
        item.setPk_org_v(pk_org_v);
        item.setPk_group(pk_group);
        item.setDbilldate(date);
        item.setPk_supplier(pk_supplier);
      }
    }
  }

}
