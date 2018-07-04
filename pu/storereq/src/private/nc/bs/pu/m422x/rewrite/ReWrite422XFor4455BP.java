package nc.bs.pu.m422x.rewrite;

import java.util.HashSet;
import java.util.Set;

import nc.bs.pu.m422x.plugin.StoreReqAppPluginPoint;
import nc.bs.pu.m422x.rewrite.rule.AccuOutreqNumCalcRule;
import nc.bs.pu.m422x.rewrite.rule.AccuOutreqNumChkRule;
import nc.impl.pubapp.pattern.data.view.ViewQuery;
import nc.impl.pubapp.pattern.data.view.ViewUpdate;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.vo.pu.m422x.entity.StoreReqAppItemVO;
import nc.vo.pu.m422x.entity.StoreReqAppViewVO;
import nc.vo.pu.m422x.entity.WriteBack422XVO;

import org.apache.commons.lang.ArrayUtils;

/**
 * �������뵥��дBP
 * 
 * @since 6.0
 * @version 2010-12-16 ����02:15:11
 * @author wuxla
 */

public class ReWrite422XFor4455BP {

  public void backWriteNumFor4455(WriteBack422XVO[] vos) {
    if (ArrayUtils.isEmpty(vos)) {
      return;
    }
    String[] bids = this.getBidsFromWBVos(vos);
    // ��ѯ����ǰ������������������
    StoreReqAppViewVO[] views =
        new ViewQuery<StoreReqAppViewVO>(StoreReqAppViewVO.class).query(bids);
    if (ArrayUtils.isEmpty(views)) {
      return;
    }

    AroundProcesser<StoreReqAppViewVO> processer =
        new AroundProcesser<StoreReqAppViewVO>(
            StoreReqAppPluginPoint.WRITEBACK_4455);

    this.addRule(processer, vos);
    processer.before(views);
    String[] wbNames = new String[] {
      StoreReqAppItemVO.NACCUOUTREQNUM, StoreReqAppItemVO.BCLOSE
    };
    ViewUpdate<StoreReqAppViewVO> bo = new ViewUpdate<StoreReqAppViewVO>();
    views = bo.update(views, StoreReqAppItemVO.class, wbNames);
    processer.after(views);
  }

  private void addRule(AroundProcesser<StoreReqAppViewVO> processer,
      WriteBack422XVO[] vos) {
    // �ۼ���������
    processer.addBeforeRule(new AccuOutreqNumCalcRule(vos));
    // �ۼ������ݲ�У��
    processer.addBeforeRule(new AccuOutreqNumChkRule());
  }

  private String[] getBidsFromWBVos(WriteBack422XVO[] vos) {
    Set<String> retVal = new HashSet<String>();
    for (WriteBack422XVO vo : vos) {
      retVal.add(vo.getPk_storereq_b());
    }
    return retVal.toArray(new String[retVal.size()]);
  }

}
