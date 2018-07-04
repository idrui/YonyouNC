package nc.vo.pu.pub.rule;

import java.util.ArrayList;
import java.util.List;

import nc.bs.ml.NCLangResOnserver;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.itf.scmpub.reference.uap.pf.PFMessage;
import nc.itf.scmpub.reference.uap.pf.PfServiceScmUtil;
import nc.itf.scmpub.reference.uap.rbac.UserManageQuery;
import nc.md.data.access.NCObject;
import nc.message.vo.MessageVO;
import nc.message.vo.NCMessage;
import nc.vo.pu.pub.enumeration.PuAttrNameEnum;
import nc.vo.pu.pub.util.AggVOUtil;
import nc.vo.pu.pub.util.ApproveFlowUtil;
import nc.vo.pub.lang.MultiLangText;
import nc.vo.pubapp.AppContext;
import nc.vo.pubapp.pattern.model.entity.bill.AbstractBill;
import nc.vo.pubapp.pattern.pub.MapList;
import nc.vo.sm.UserVO;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.ArrayUtils;

/**
 * @description
 *              采购订单支持审批中修改（修订）的单据，后台向所有已经处理过审批任务的人发送消息<br>
 *              审批中修改（修订）均可以使用此规则，但根据V55（V6延续）的需求，只有审批中修订发信息
 * @scene
 *        采购订单修订
 * @param 无
 * @since 6.3
 * @version 2014-10-22 上午10:38:08
 * @author luojw
 */
public class ApprovingEditSendMsgRule<E extends AbstractBill> implements
    IRule<E> {

  @Override
  public void process(E[] vos) {
    E[] filterVos = ApproveFlowUtil.filterApprovingEditReviseVO(vos);
    if (null == filterVos || filterVos.length == 0) {
      return;
    }
    // 所有已经处理过审批任务的人发送消息
    this.sendMsg(filterVos);
  }

  private NCMessage getMessageVO(E vo, List<String> userLst, String pk_user,
      String username) {
    MessageVO mvo = new MessageVO();
    mvo.setSender(pk_user);
    mvo.setSendtime(AppContext.getInstance().getServerTime());
    StringBuffer usersStr = new StringBuffer();
    for (String user : userLst) {
      usersStr.append(user + ",");
    }
    usersStr.deleteCharAt(usersStr.length() - 1);
    mvo.setReceiver(usersStr.toString());
    mvo.setMsgsourcetype("notice");
    String billcode =
        (String) vo.getParentVO().getAttributeValue(
            PuAttrNameEnum.vbillcode.name());
    mvo.setContent(NCLangResOnserver.getInstance().getStrByID("4004000_0",
        "04004000-0131", null, new String[] {
          username, billcode
        })/* [{0}]在审批中修订了单据[{1}]。 */);
    mvo.setSubject(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
        "4004000_0", "04004000-0132")/* @res "审批中修订通知" */);
    NCMessage ncm = new NCMessage();
    ncm.setMessage(mvo);
    return ncm;
  }

  private String getUserName(String pk_user) {
    UserVO[] usrvo = UserManageQuery.findUserByIDs(new String[] {
      pk_user
    });
    if (ArrayUtils.isEmpty(usrvo)) {
      return pk_user;
    }
    MultiLangText mlt =
        (MultiLangText) NCObject.newInstance(usrvo[0]).getAttributeValue(
            UserVO.USER_NAME);
    return mlt.toString();
  }

  private void sendMsg(E[] vos) {
    MapList<String, String> billApproverMap =
        PfServiceScmUtil.getApprovedUser(AggVOUtil.getPrimaryKeys(vos));
    List<NCMessage> msgLst = new ArrayList<NCMessage>();
    String pk_user = AppContext.getInstance().getPkUser();
    String username = this.getUserName(pk_user);
    for (E vo : vos) {
      List<String> approveLst = billApproverMap.get(vo.getPrimaryKey());
      if (CollectionUtils.isEmpty(approveLst)) {
        continue;
      }
      msgLst.add(this.getMessageVO(vo, approveLst, pk_user, username));
    }
    if (msgLst.size() > 0) {
      // 给用户发信息
      PFMessage.sendMessage(msgLst.toArray(new NCMessage[msgLst.size()]));
    }
  }

}
