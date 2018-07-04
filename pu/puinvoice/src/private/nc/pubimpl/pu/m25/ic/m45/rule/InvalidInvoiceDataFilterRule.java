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
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>��ⵥ�Ƶ��󣬹��˵���Ʒ�е�
 * <li>ͬʱ�������TS����֤�����ֲ�������ʽ������Բ���鲢����
 * <li>������ֻ��������ʽ����
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author zhaoyha
 * @time 2010-5-6 ����06:55:34
 */
public class InvalidInvoiceDataFilterRule implements IFilterRule<InvoiceVO> {

  @Override
  public InvoiceVO[] process(InvoiceVO[] vos) {
    String[] purinIds =
        (String[]) AggVOUtil.getDistinctItemFieldArray(vos,
            InvoiceItemVO.CSOURCEID, String.class);
    PurchaseInVO[] purinVos = this.getPurchaseinVO(purinIds);
    // ���˵���Դ����Ʒ�е���ⵥ�ķ�Ʊ��
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
        // ��Դ����Ʒ����ⵥ�����ɷ�Ʊ
        if (UFBoolean.TRUE.equals(purinItem.getFlargess())) {
          continue;
        }
        // ͬʱ�������TS����֤�����ֲ�������ʽ������Բ���鲢����
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
