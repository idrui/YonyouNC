/**
 * $�ļ�˵��$
 * 
 * @author zhyhang
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-1-22 ����09:50:25
 */
package nc.bs.pu.m21.writeback.pu.rule;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pu.m21.entity.OrderViewVO;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>��Ʊ�����ݲ���
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author zhyhang
 * @time 2010-1-22 ����09:50:25
 */
public class InvoicePriceChkRule implements IRule<OrderViewVO> {

  @Override
  public void process(OrderViewVO[] vos) {
    // MathTool calc=MathTool;
    // ExceptionUtils eu=ExceptionUtils;
    // Map<String, IOrderWriteBackPara> wbMap = OrderVOUtil.getInsance()
    // .createWBMap(wbVos);
    // for(OrderViewVO view:vos){
    // String PO04 = PUSysParamUtil.getPO04(view.getPk_org());
    // UFDouble PO05 = PUSysParamUtil.getPO05(view.getPk_org());
    // if (ctrltype.NOT_CONTROL.equals(PO04))
    // continue;
    //
    // IOrderWriteBackParaFor25 wbVo = (IOrderWriteBackParaFor25) wbMap.get(view
    // .getPk_order_b());
    // //ɾ���򲻽��е����ݲ���
    // if(wbVo.isDiscard()) continue;
    // UFDouble orderPrice=view.getNnetprice();//��������˰ ��
    // UFDouble invoicePrice=wbVo.getPrice();
    // UFDouble tolerancePrice=orderPrice.multiply(PO05.add(100)).div(100);;
    // boolean isUserComfirm=wbVo.isPriceUserConfirm();
    // if(calc.compareTo(invoicePrice, tolerancePrice)>0){
    // if(ctrltype.NOT_SAVE.equals(PO04))
    // eu.wrappBusinessException(
    // "���ڷ�Ʊ������˰���۳�������������˰�����ݲ���Ƶ��У����飡");
    // else if(ctrltype.ASK.equals(PO04) && !isUserComfirm)
    // eu.wrappBusinessException(
    // "���ڷ�Ʊ������˰���۳�������������˰�����ݲ���Ƶ��У��Ƿ������");
    // }
    // }

  }

}
