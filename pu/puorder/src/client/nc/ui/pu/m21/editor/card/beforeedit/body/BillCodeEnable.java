/**
 * $文件说明$
 * 
 * @author chenlla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-7-14 上午09:44:25
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
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>对方确认编辑前事件
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author chenlla
 * @time 2010-7-14 上午09:44:25
 */
public class BillCodeEnable implements ICardBodyBeforeEditEventListener {

  /**
   * 父类方法重写
   * 
   * @see nc.ui.pu.pub.editor.card.listener.ICardBodyBeforeEditEventListener#beforeEdit(nc.ui.pubapp.uif2app.event.card.CardBodyBeforeEditEvent)
   */
  @Override
  public void beforeEdit(CardBodyBeforeEditEvent event) {
    String vTranType =
        (String) event.getBillCardPanel()
            .getHeadItem(OrderHeaderVO.VTRANTYPECODE).getValueObject();
    try {
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
        // 如果交易类型设置不能修改单据号
        if (vo != null) {
          event.setReturnValue(Boolean.valueOf(ValueUtils.getBoolean(vo
              .getBconfirmcode())));
        }
        else {
          event.setReturnValue(Boolean.FALSE);
        }
      }
    }
    catch (BusinessException e) {
      // 日志异常
      ExceptionUtils.wrappException(e);
    }
  }

}
