package nc.bs.pu.m23.maintain.rule.insert;

import nc.bs.framework.common.InvocationInfoProxy;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pu.m23.entity.ArriveHeaderVO;
import nc.vo.pu.m23.entity.ArriveItemVO;
import nc.vo.pu.m23.entity.ArriveVO;
import nc.vo.pu.m23.rule.NumAndMnySumWhenSave;
import nc.vo.pu.pub.util.ApproveFlowUtil;
import nc.vo.pub.lang.UFDate;
import nc.vo.pubapp.util.AuditInfoUtils;

import org.apache.commons.lang.StringUtils;

/**
 * 
 * @description
 * ����ʱ������ݣ��������Ƶ��ˡ��Ƶ�ʱ�䡢����
 * @scene
 * 
 * @param
 * ��
 *
 * @since 6.3
 * @version 2010-1-26 ����04:00:52
 * @author hanbin
 */
public class InsertFillDataRule implements IRule<ArriveVO> {

  @Override
  public void process(ArriveVO[] voArray) {
    AuditInfoUtils.setAddAuditInfo(voArray);
    ApproveFlowUtil.setBillMakeInfo(voArray);
    NumAndMnySumWhenSave numAndMnySum = new NumAndMnySumWhenSave();
    for (ArriveVO aggVO : voArray) {
      // ���ͷ����
      this.fillHeaderData(aggVO.getHVO());

      // �����ͷ�ϼ�
      numAndMnySum.numAndMnySum(aggVO);

      this.fillBodyData(aggVO);
    }
  }

  private void fillBodyData(ArriveVO vo) {
    // ͬ����������
    String pk_org = vo.getHVO().getPk_org();
    String pk_group = vo.getHVO().getPk_group();
    UFDate dbilldate = vo.getHVO().getDbilldate();
    for (ArriveItemVO item : vo.getBVO()) {
      item.setPk_org(pk_org);
      item.setPk_org_v(vo.getHVO().getPk_org_v());
      item.setPk_group(pk_group);
      item.setDbilldate(dbilldate);
    }
  }

  private void fillHeaderData(ArriveHeaderVO hvo) {
    InvocationInfoProxy proxy = InvocationInfoProxy.getInstance();
    // AuditInfoUtils.setAddAuditInfo(hvo);
    // AuditInfoUtils.setUpdateAuditInfo(hvo);
    if (StringUtils.isEmpty(hvo.getPk_group())) {
      hvo.setPk_group(proxy.getGroupId()); // ����
    }
  }
}
