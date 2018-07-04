/**
 * $�ļ�˵��$
 *
 * @author linsf
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-2-25 ����06:47:05
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
 *              �빺��
 * @scene
 *        �빺������
 * @param ��
 * @since 6.3
 * @version 2014-10-21 ����8:58:12
 * @author yanxm5
 */
public class SendMsgToUserRule implements IRule<PraybillVO> {

  /**
   * ���෽����д
   * 
   * @see nc.impl.pubapp.pattern.rule.IRule#process(E[])
   */
  @Override
  public void process(PraybillVO[] vos) {
    if (null == vos || vos.length == 0) {
      return;
    }
    // �õ�����ͨ����VO��������ʱ���ܻ��������е�VO����
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
        })/* ���ݺ�Ϊ��{0}���빺���Ѿ�������ɡ� */);
    mvo.setSubject(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
        "4004020_0", "04004020-0025")/* @res "�빺���������֪ͨ" */);
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
      // ȡ�òɹ�Ա���ڵ��û�
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
        // ȡ���ɹ�Ա
        HashSet<String> psns = new HashSet<String>();
        for (PraybillItemVO item : items) {
          if (!StringUtil.isEmptyWithTrim(item.getPk_employee())) {
            psns.add(item.getPk_employee());
          }
        }
        // ȡ�òɹ�Ա���ڵ��û�
        String users = this.getUser(psns);
        if (null == users || users.length() == 0) {
          continue;
        }

        // ���û�����Ϣ
        PFMessage.sendMessage(new NCMessage[] {
          this.getMessageVO(vo, users)
        });
      }

    }
  }
}
