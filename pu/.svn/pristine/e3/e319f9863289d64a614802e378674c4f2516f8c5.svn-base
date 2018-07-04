package nc.bs.pu.m23.maintain.rule.update;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pu.m23.entity.ArriveHeaderVO;
import nc.vo.pu.m23.entity.ArriveItemVO;
import nc.vo.pu.m23.entity.ArriveVO;
import nc.vo.pu.m23.rule.NumAndMnySumWhenSave;
import nc.vo.pub.VOStatus;
import nc.vo.pub.lang.UFDate;
import nc.vo.pubapp.util.AuditInfoUtils;

/**
 * 
 * @description
 * 本类主要完成以下功能：
 * 修改时，填补数据，包括：修改人、修改时间
 * @scene
 * 到货单修改
 * @param
 * 无
 *
 * @since 6.3
 * @version 2010-1-26 下午04:00:52
 * @author hanbin
 */

public class UpdateFillDataRule implements IRule<ArriveVO> {

  @Override
  public void process(ArriveVO[] voArray) {
    AuditInfoUtils.setUpdateAuditInfo(voArray);

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
      if (item.getStatus() == VOStatus.DELETED) {
        continue;
      }
      item.setPk_org(pk_org);
      item.setPk_org_v(vo.getHVO().getPk_org_v());
      item.setPk_group(pk_group);
      item.setDbilldate(dbilldate);
    }
  }

  private void fillHeaderData(ArriveHeaderVO hvo) {
    // AuditInfoUtils.setUpdateAuditInfo(hvo);
    hvo.setStatus(VOStatus.UPDATED);
  }
}
