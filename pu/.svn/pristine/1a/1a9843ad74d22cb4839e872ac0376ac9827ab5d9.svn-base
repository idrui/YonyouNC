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
 * 新增时，填补数据，包括：制单人、制单时间、集团
 * @scene
 * 
 * @param
 * 无
 *
 * @since 6.3
 * @version 2010-1-26 下午04:00:52
 * @author hanbin
 */
public class InsertFillDataRule implements IRule<ArriveVO> {

  @Override
  public void process(ArriveVO[] voArray) {
    AuditInfoUtils.setAddAuditInfo(voArray);
    ApproveFlowUtil.setBillMakeInfo(voArray);
    NumAndMnySumWhenSave numAndMnySum = new NumAndMnySumWhenSave();
    for (ArriveVO aggVO : voArray) {
      // 填补表头数据
      this.fillHeaderData(aggVO.getHVO());

      // 计算表头合计
      numAndMnySum.numAndMnySum(aggVO);

      this.fillBodyData(aggVO);
    }
  }

  private void fillBodyData(ArriveVO vo) {
    // 同步冗余数据
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
      hvo.setPk_group(proxy.getGroupId()); // 集团
    }
  }
}
