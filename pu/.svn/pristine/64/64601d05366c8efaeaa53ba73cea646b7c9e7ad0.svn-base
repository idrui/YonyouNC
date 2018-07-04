/**
 * $文件说明$
 * 
 * @author zhyhang
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-3-24 下午08:51:57
 */
package nc.bs.pu.m25.maintain.rule.save;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import nc.impl.pubapp.pattern.rule.ICompareRule;
import nc.itf.scmpub.reference.uap.bd.material.MaterialPubService;
import nc.vo.bd.material.MaterialVO;
import nc.vo.pu.m25.entity.InvoiceItemVO;
import nc.vo.pu.m25.entity.InvoiceVO;
import nc.vo.pu.m25.enumeration.InvoiceRowType;
import nc.vo.pu.pub.util.AggVOUtil;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.pub.MathTool;

import org.apache.commons.lang.ArrayUtils;

/**
 * 
 * @description
 * 生成发票行的类型(普通货物行,折扣行,劳务行,零数量行)
 * @scene
 * 保存的BP
 * @param
 * 无
 *
 * @since 6.3
 * @version 2014-10-22 下午3:21:25
 * @author zhangshqb
 */
public class InvoiceRowTypeFillRule implements ICompareRule<InvoiceVO> {

  @Override
  public void process(InvoiceVO[] vos, InvoiceVO[] originVOs) {
    if (ArrayUtils.isEmpty(vos)) {
      return;
    }
    Map<String, InvoiceItemVO> orgItemMap =
        AggVOUtil
            .createItemMap(ArrayUtils.isEmpty(originVOs) ? vos : originVOs);
    List<InvoiceItemVO> noFillItems = new ArrayList<InvoiceItemVO>();
    for (InvoiceVO vo : vos) {

      for (InvoiceItemVO item : vo.getChildrenVO()) {
        String mpk = item.getPk_material();
        // 根据物料或者表体主键判断是否已存在该行
        boolean noExist =
            !orgItemMap.containsKey(item.getPk_invoice_b())
                || !mpk.equals(orgItemMap.get(item.getPk_invoice_b())
                    .getPk_material());
        if (0 == MathTool.compareTo(item.getNastnum(), UFDouble.ZERO_DBL)
            && (item.getFrowtype() == null || noExist)) {
          item.setFrowtype(Integer.valueOf(InvoiceRowType.ZERONUM_ROW));
        }
        if (ArrayUtils.isEmpty(originVOs) || noExist) {
          noFillItems.add(item);
        }
      }
    }
    this.fillRowType(noFillItems);
  }

  private void fillRowType(List<InvoiceItemVO> items) {
    if (0 == items.size()) {
      return;
    }
    Set<String> mpks = new HashSet<String>();
    for (InvoiceItemVO item : items) {
      mpks.add(item.getPk_material());
    }
    MaterialVO[] mvos = null;
    mvos =
        MaterialPubService.queryMaterialBaseInfoByPks(
            mpks.toArray(new String[mpks.size()]), new String[] {
              MaterialVO.FEE, MaterialVO.DISCOUNTFLAG
            });
    Map<String, MaterialVO> materialMap = new HashMap<String, MaterialVO>();
    if (mvos != null) {

      for (MaterialVO mvo : mvos) {
        materialMap.put(mvo.getPk_material(), mvo);
      }
    }
    for (InvoiceItemVO item : items) {
      String mpk = item.getPk_material();
      MaterialVO mvo = materialMap.get(mpk);
      if (null == mvo) {
        item.setFrowtype(Integer.valueOf(InvoiceRowType.GOODS_ROW));
      }
      else if (mvo.getDiscountflag().booleanValue()) {
        item.setFrowtype(Integer.valueOf(InvoiceRowType.DISCOUNT_ROW));
      }
      else if (mvo.getFee().booleanValue()) {
        item.setFrowtype(Integer.valueOf(InvoiceRowType.FEE_ROW));
      }
      else {
        item.setFrowtype(Integer.valueOf(InvoiceRowType.GOODS_ROW));
      }
    }
  }
}
