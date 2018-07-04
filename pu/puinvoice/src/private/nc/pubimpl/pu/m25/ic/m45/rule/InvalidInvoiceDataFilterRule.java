package nc.pubimpl.pu.m25.ic.m45.rule;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import nc.impl.pubapp.pattern.data.bill.BillQuery;
import nc.impl.pubapp.pattern.rule.IFilterRule;
import nc.vo.ic.m45.entity.PurchaseInBodyVO;
import nc.vo.ic.m45.entity.PurchaseInVO;
import nc.vo.pu.m25.entity.InvoiceItemVO;
import nc.vo.pu.m25.entity.InvoiceVO;
import nc.vo.pu.pub.util.AggVOUtil;
import nc.vo.pub.lang.UFBoolean;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>入库单推单后，过滤掉赠品行等
 * <li>同时清空上游TS，保证不出现并发（推式保存可以不检查并发）
 * <li>本规则只能用于推式保存
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author zhaoyha
 * @time 2010-5-6 下午06:55:34
 */
public class InvalidInvoiceDataFilterRule implements IFilterRule<InvoiceVO> {

  @Override
  public InvoiceVO[] process(InvoiceVO[] vos) {
    String[] purinIds =
        (String[]) AggVOUtil.getDistinctItemFieldArray(vos,
            InvoiceItemVO.CSOURCEID, String.class);
    PurchaseInVO[] purinVos = this.getPurchaseinVO(purinIds);
    // 过滤掉来源于赠品行的入库单的发票行
    return this.filter(vos, purinVos);
  }

  private InvoiceVO[] filter(InvoiceVO[] vos, PurchaseInVO[] purinVos) {
    Map<String, PurchaseInBodyVO> purinItemMap =
        AggVOUtil.createItemMap(purinVos);
    List<InvoiceVO> newVoLst = new ArrayList<InvoiceVO>();
    for (InvoiceVO vo : vos) {
      List<InvoiceItemVO> newItemLst = new ArrayList<InvoiceItemVO>();
      for (InvoiceItemVO item : vo.getChildrenVO()) {
        String pk_purin_b = item.getCsourcebid();
        PurchaseInBodyVO purinItem = purinItemMap.get(pk_purin_b);
        // 来源于赠品的入库单不生成发票
        if (UFBoolean.TRUE.equals(purinItem.getFlargess())) {
          continue;
        }
        // 同时清空上游TS，保证不出现并发（推式保存可以不检查并发）
        item.setFirstbts(null);
        item.setFirstts(null);
        item.setSourcebts(null);
        item.setSourcets(null);
        newItemLst.add(item);
      }
      if (newItemLst.size() == 0) {
        continue;
      }
      InvoiceVO newVo = vo;
      if (newItemLst.size() != vo.getChildrenVO().length) {
        newVo = new InvoiceVO();
        newVo.setParentVO(vo.getParentVO());
        newVo.setChildrenVO(newItemLst.toArray(new InvoiceItemVO[newItemLst
            .size()]));
      }
      newVoLst.add(newVo);
    }
    return newVoLst.toArray(new InvoiceVO[newVoLst.size()]);
  }

  private PurchaseInVO[] getPurchaseinVO(String[] purinIds) {
    return new BillQuery<PurchaseInVO>(PurchaseInVO.class).query(purinIds);
  }

}
