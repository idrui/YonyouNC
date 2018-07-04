/**
 * $文件说明$
 *
 * @author linsf
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-2-25 下午06:47:05
 */
package nc.bs.pu.m20.maintain.rule.approve;

import java.util.HashSet;

import nc.bs.ml.NCLangResOnserver;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.itf.scmpub.reference.uap.pf.PFMessage;
import nc.itf.scmpub.reference.uap.rbac.UserPubService;
import nc.message.vo.MessageVO;
import nc.message.vo.NCMessage;
import nc.vo.jcom.lang.StringUtil;
import nc.vo.pu.m20.entity.PraybillItemVO;
import nc.vo.pu.m20.entity.PraybillVO;
import nc.vo.pu.pub.util.ApproveFlowUtil;
import nc.vo.pu.pub.util.PUSysParamUtil;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.AppContext;
import nc.vo.sm.UserVO;

import org.apache.commons.lang.ArrayUtils;

/**
 * @description
 *              请购单
 * @scene
 *        请购单审批
 * @param 无
 * @since 6.3
 * @version 2014-10-21 上午8:58:12
 * @author yanxm5
 */
public class SendMsgToUserRule implements IRule<PraybillVO> {

  /**
   * 父类方法重写
   * 
   * @see nc.impl.pubapp.pattern.rule.IRule#process(E[])
   */
  @Override
  public void process(PraybillVO[] vos) {
    if (null == vos || vos.length == 0) {
      return;
    }
    // 得到审批通过的VO，审批流时可能会有审批中的VO过来
    Object[] approvedVos = ApproveFlowUtil.filterApprovedVO(vos);
    if (ArrayUtils.isEmpty(approvedVos)) {
      return;
    }
    this.sendmessage((PraybillVO[]) approvedVos);
  }

  private NCMessage getMessageVO(PraybillVO vo, String users) {
    MessageVO mvo = new MessageVO();
    mvo.setSender(vo.getHVO().getApprover());
    mvo.setSendtime(AppContext.getInstance().getServerTime());
    mvo.setReceiver(users);
    mvo.setMsgsourcetype("notice");
    String billcode = vo.getHVO().getVbillcode();
    mvo.setContent(NCLangResOnserver.getInstance().getStrByID("4004020_0",
        "04004020-0088", null, new String[] {
          billcode
        })/* 单据号为：{0}的请购单已经审批完成。 */);
    mvo.setSubject(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
        "4004020_0", "04004020-0025")/* @res "请购单审批完成通知" */);
    NCMessage ncm = new NCMessage();
    ncm.setMessage(mvo);
    return ncm;
  }

  private String getUser(HashSet<String> psns) {
    if (psns.size() == 0) {
      return null;
    }
    StringBuilder users = new StringBuilder();
    for (String psn : psns) {
      // 取得采购员对于的用户
      UserVO userVO = UserPubService.queryUserVOByPsnDocID(psn);
      if (null == userVO) {
        continue;
      }
      if (users.length() == 0) {
        users.append(userVO.getCuserid());
      }
      else {
        users.append(",").append(userVO.getCuserid());
      }
    }
    return users.toString();
  }

  private void sendmessage(PraybillVO[] vos) {

    for (PraybillVO vo : vos) {
      String pk_org = vo.getHVO().getPk_org();

      UFBoolean para = PUSysParamUtil.getPO43(pk_org);

      if (UFBoolean.TRUE.equals(para)) {
        PraybillItemVO[] items = vo.getBVO();
        // 取出采购员
        HashSet<String> psns = new HashSet<String>();
        for (PraybillItemVO item : items) {
          if (!StringUtil.isEmptyWithTrim(item.getPk_employee())) {
            psns.add(item.getPk_employee());
          }
        }
        // 取得采购员对于的用户
        String users = this.getUser(psns);
        if (null == users || users.length() == 0) {
          continue;
        }

        // 给用户发信息
        PFMessage.sendMessage(new NCMessage[] {
          this.getMessageVO(vo, users)
        });
      }

    }
  }
}
