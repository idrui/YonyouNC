package nc.bs.pu.m422x.pf;

import nc.bs.pub.pf.IMessagePriorityCallback;
import nc.impl.pubapp.pattern.data.vo.VOQuery;
import nc.message.vo.MessageVO;
import nc.vo.pu.m422x.entity.StoreReqAppHeaderVO;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * 根据物资需求申请单ID查询物资需求申请单表头紧急状态,
 * 根据不同的状态返回审批消息优先级1或10,10表示紧急
 * 
 * @since 6.3
 * @version 2012-12-3 下午03:52:16
 * @author fanly3
 */
public class MessagePriorityCallbackImpl implements IMessagePriorityCallback {

  @Override
  public Integer getMessagePriority(String billid) throws BusinessException {
    try {
      VOQuery<StoreReqAppHeaderVO> query =
          new VOQuery<StoreReqAppHeaderVO>(StoreReqAppHeaderVO.class,
              new String[] {
                StoreReqAppHeaderVO.BURGENCY
              });
      StoreReqAppHeaderVO[] headers = query.query(new String[] {
        billid
      });

      if (headers[0].getBurgency().booleanValue()) {
        return Integer.valueOf(MessageVO.MAX_PRIORITY);
      }
      return Integer.valueOf(MessageVO.MIN_PRIORITY);
    }
    catch (Exception e) {
      ExceptionUtils.wrappException(e);
    }
    return null;
  }
}
