package nc.bs.pu.m23.fa.rule;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.pubitf.bdlayer.msg.FaBuziMsgSender;
import nc.pubitf.bdlayer.msg.ScmBuziMsgResEnum;
import nc.pubitf.bdlayer.msg.ScmSendBuziMsgPara;
import nc.vo.pu.m23.entity.ArriveHeaderVO;
import nc.vo.pu.m23.entity.ArriveItemVO;
import nc.vo.pu.m23.entity.ArriveVO;

/**
 * 
 * @description
 * 生成设备卡片后发送消息规则。
 * @scene
 * 生成资产卡片后
 * @param
 * 无
 *
 * @since 6.3
 * @version 2012-11-20 上午11:13:42
 * @author lixyp
 */
public class SendMsgForFARule implements IRule<ArriveVO> {

  private String msgResCode = null;

  public SendMsgForFARule(String msgResCode) {
    this.msgResCode = msgResCode;
  }

  @Override
  public void process(ArriveVO[] vos) {
    FaBuziMsgSender sender = new FaBuziMsgSender();
    for (ArriveVO vo : vos) {
      ArriveItemVO[] itemVos = vo.getBVO();
      ScmSendBuziMsgPara[] params = new ScmSendBuziMsgPara[itemVos.length];

      for (int i = 0; i < params.length; i++) {
        ScmSendBuziMsgPara param = new ScmSendBuziMsgPara();
        param.setMsgrescode(this.msgResCode);
        param.setBillType(vo.getHVO().getVtrantypecode());
        if (ScmBuziMsgResEnum.ARRIVAL_GENEQUIP.code().equals(this.msgResCode)) {
          param.setMsgSourceType("equipmsg");
        }
        else {
          param.setMsgSourceType("notice");
        }
        param.setContentType("text");
        param.setPk_detail(vo.getHVO().getPk_arriveorder());
        param.setApproverField(ArriveHeaderVO.APPROVER);
        param.setCtrantypeidField(ArriveHeaderVO.CTRANTYPEID);
        param.setPk_groupField(ArriveHeaderVO.PK_GROUP);
        param.setPk_materialField(ArriveItemVO.PK_MATERIAL);
        param.setPk_orgField(ArriveHeaderVO.PK_ORG);
        param.setVtrantypecodeField(ArriveHeaderVO.VTRANTYPECODE);
        param.putAttachment("ITEMVO", itemVos[i]);
        params[i] = param;
      }
      sender.add(vo, params);
    }
    sender.send();
  }
}
