package nc.ui.pu.m20.editor.org;

import java.util.List;

import nc.ui.pu.pub.editor.org.AbstractOrgChangedEventHandler;
import nc.ui.pu.pub.editor.org.IOrgChangedEventListener;
import nc.ui.pub.bill.BillEditEvent;
import nc.ui.pubapp.uif2app.event.OrgChangedEvent;
import nc.ui.pubapp.uif2app.event.card.CardHeadTailAfterEditEvent;
import nc.ui.trade.business.HYPubBO_Client;
import nc.uif.pub.exception.UifException;
import nc.vo.bd.psn.PsndocVO;
import nc.vo.pu.m20.entity.PraybillHeaderVO;
import nc.vo.pubapp.AppContext;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>处理主组织（库存组织）的变化事件：
 * <li>清空表头、表体中，相关字段的值，并把其置为修改的行状态
 * <li>处理精度
 * <li>设置参照范围
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author GGR
 * @time 2010-2-2 上午10:56:23
 */
public class OrgChgEventHandler extends AbstractOrgChangedEventHandler {

  @Override
  public void registerEventListener(List<IOrgChangedEventListener> listenerList) {
    listenerList.add(new StockOrg());
  }
  
  @Override
  public void handleAppEvent(OrgChangedEvent e) {
	  String userid = AppContext.getInstance().getPkUser();
	  StringBuffer buf = new StringBuffer();
	  buf.append(" code in");
	  buf.append(" (select u.user_code from sm_user u where u.cuserid = '");
	  buf.append(userid);
	  buf.append("'");
	  buf.append(" and nvl(u.dr,0)=0 )");
	  PsndocVO[] psndocVOs = null;
	  try {
		  psndocVOs = (PsndocVO[]) HYPubBO_Client.queryByCondition(PsndocVO.class, buf.toString());//根据当前登录用户查询人员主键
	  } catch (UifException e1) {
		  e1.printStackTrace();
		  ExceptionUtils.wrappBusinessException(e1.getMessage());
	  }
	  if(psndocVOs != null && psndocVOs.length>0){
		  getCardForm().getBillCardPanel().setHeadItem(PraybillHeaderVO.PK_PLANPSN, psndocVOs[0].getPk_psndoc());
		  BillEditEvent billEditEvent = new BillEditEvent(getCardForm().getBillCardPanel(), psndocVOs[0].getPk_psndoc(), PraybillHeaderVO.PK_PLANPSN);
		  CardHeadTailAfterEditEvent event = new CardHeadTailAfterEditEvent(getCardForm().getBillCardPanel(), billEditEvent, null);
		  getCardForm().getModel().fireEvent(event);
	  }
	  super.handleAppEvent(e);
  }
}
