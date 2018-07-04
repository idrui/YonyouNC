/**
 * $�ļ�˵��$
 * 
 * @author wanghuid
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-9-1 ����04:34:45
 */
package nc.ui.pu.m21.editor.card.beforeedit.body;

import java.util.HashMap;

import nc.bs.framework.common.NCLocator;
import nc.pubitf.pu.m21transtype.IPoTransTypeQuery;
import nc.ui.pu.pub.editor.card.listener.ICardBodyBeforeEditEventListener;
import nc.ui.pubapp.uif2app.event.card.CardBodyBeforeEditEvent;
import nc.vo.pu.m21.entity.OrderHeaderVO;
import nc.vo.pu.m21transtype.entity.PoTransTypeVO;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.data.ValueUtils;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>���ݵ������������ж��Ƿ�����޸���;״̬�ֶε�ֵ
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wanghuid
 * @time 2010-9-1 ����04:34:45
 */
public class SetOnwayFieldEnable implements ICardBodyBeforeEditEventListener {

  private String field = "";

  public SetOnwayFieldEnable(String field) {
    this.field = field;
  }

  /**
   * ���෽����д
   * 
   * @see nc.ui.pu.pub.editor.card.listener.ICardBodyBeforeEditEventListener#beforeEdit(nc.ui.pubapp.uif2app.event.card.CardBodyBeforeEditEvent)
   */
  @Override
  public void beforeEdit(CardBodyBeforeEditEvent event) {

    String vTranType =
        (String) event.getBillCardPanel()
            .getHeadItem(OrderHeaderVO.VTRANTYPECODE).getValueObject();
    try {
      // ȡ�ý�������map
      HashMap<String, PoTransTypeVO> map =
          NCLocator.getInstance().lookup(IPoTransTypeQuery.class)
              .queryAttrByTypes(new String[] {
                vTranType
              }, null);
      if ((null == map) || (map.size() == 0)) {
        event.setReturnValue(Boolean.FALSE);
      }
      else {
        PoTransTypeVO vo = map.get(vTranType);
        // ���ݽ������������ж��Ƿ�����޸ĸ��ֶ�
        if (vo != null) {
          boolean btranType =
              ValueUtils.getBoolean(vo.getAttributeValue(this.field));
          event.setReturnValue(Boolean.valueOf(btranType));
        }
        else {
          event.setReturnValue(Boolean.FALSE);
        }
      }
    }
    catch (BusinessException e) {
      // ��־�쳣
      ExceptionUtils.wrappException(e);
    }
  }
}
