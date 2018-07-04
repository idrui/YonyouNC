package nc.ui.pu.est.editor.body.after.m45;

import java.util.Map;

import nc.ui.pu.est.editor.body.after.Crececountryid;
import nc.ui.pu.est.editor.body.after.Csendcountryid;
import nc.ui.pu.est.editor.body.after.Ctaxcodeid;
import nc.ui.pu.est.editor.body.after.Ctaxcountryid;
import nc.ui.pu.est.editor.body.after.Currency;
import nc.ui.pu.est.editor.body.after.Feematerial;
import nc.ui.pu.est.editor.body.after.Supplier;
import nc.ui.pu.pub.editor.card.handler.AbstractCardBodyAfterEditEventHandler;
import nc.ui.pu.pub.editor.card.listener.AbstractRelationCalculateListener;
import nc.ui.pu.pub.editor.card.listener.ICardBodyAfterEditEventListener;
import nc.vo.pu.est.entity.FeeEstVO;
import nc.vo.pu.est.util.FeeEstRelaItems;

/**
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>����༭���¼�����ַ���</li>
 * </ul>
 * <p>
 * </p>
 * 
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since
 * @time 2010-6-18 ����09:48:00
 */
public class CardBodyAfterEditEventHandler extends
    AbstractCardBodyAfterEditEventHandler {

  @Override
  public AbstractRelationCalculateListener getCalculateListener() {
    return null;
  }

  /*
   * (non-Javadoc)
   * @see
   * nc.ui.pu.pub.editor.card.handler.AbstractCardBodyAfterEditEventHandler#
   * registerEventListener(java.util.Map)
   */
  @Override
  public void registerEventListener(
      Map<String, ICardBodyAfterEditEventListener> listenerMap) {
    listenerMap.put(FeeEstVO.PK_SUPPLIER, new Supplier(new FeeEstRelaItems()));// ��Ӧ��
    listenerMap.put(FeeEstVO.PK_ESTCURRENCY,
        new Currency(new FeeEstRelaItems()));// ����
    // ������
    listenerMap.put(FeeEstVO.CSENDCOUNTRYID, new Csendcountryid(
        new FeeEstRelaItems()));
    listenerMap.put(FeeEstVO.CRECECOUNTRYID, new Crececountryid(
        new FeeEstRelaItems()));
    listenerMap.put(FeeEstVO.CTAXCOUNTRYID, new Ctaxcountryid(
        new FeeEstRelaItems()));
    listenerMap.put(FeeEstVO.CTAXCODEID, new Ctaxcodeid(new FeeEstRelaItems()));
    listenerMap.put(FeeEstVO.PK_FEEMATERIAL, new Feematerial(
        new FeeEstRelaItems()));

  }

}
