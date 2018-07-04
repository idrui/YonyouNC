/**
 * $�ļ�˵��$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-7-20 ����10:32:28
 */
package nc.bs.pu.m422x.maintain;

import nc.bs.pu.m422x.maintain.rule.WriteBackSourceRule;
import nc.bs.pu.m422x.maintain.rule.save.BillCodeUniqueRule;
import nc.bs.pu.m422x.maintain.rule.save.CodeProceRule;
import nc.bs.pu.m422x.maintain.rule.save.NastumGtZeroRule;
import nc.bs.pu.m422x.maintain.rule.save.NecessaryFillRule;
import nc.bs.pu.m422x.maintain.rule.save.RowNoRule;
import nc.bs.pu.m422x.maintain.rule.save.SaveAfterEventRule;
import nc.bs.pu.m422x.maintain.rule.save.SaveBeforeEventRule;
import nc.bs.pu.m422x.plugin.StoreReqAppPluginPoint;
import nc.impl.pubapp.bd.userdef.UserDefSaveRule;
import nc.impl.pubapp.pattern.data.bill.BillInsert;
import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.vo.pu.m422x.entity.StoreReqAppHeaderVO;
import nc.vo.pu.m422x.entity.StoreReqAppItemVO;
import nc.vo.pu.m422x.entity.StoreReqAppVO;
import nc.vo.pu.m422x.rule.ItemEmptyRule;
import nc.vo.pu.pub.rule.ApprovingEditCheckRule;
import nc.vo.pu.pub.rule.NewestOrgVersionFillRule;
import nc.vo.pu.pub.rule.WriteBack422xRule;
import nc.vo.pu.pub.rule.WriteBackPIMRule;
import nc.vo.pu.pub.rule.pf.NoPassUpdateRule;
import nc.vo.pu.pub.util.ArrayUtil;
import nc.vo.scmpub.res.billtype.POBillType;
import nc.vo.scmpub.rule.StockOrgEnableCheckRule;

import org.apache.commons.lang.ArrayUtils;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-7-20 ����10:32:28
 */
public class StoreReqAppSaveBP {

  public StoreReqAppVO[] save(StoreReqAppVO[] insertVOs,
      StoreReqAppVO[] updateVOs, StoreReqAppVO[] updateOriginVOs) {
    CompareAroundProcesser<StoreReqAppVO> processer =
        new CompareAroundProcesser<StoreReqAppVO>(
            StoreReqAppPluginPoint.SAVE_BP);
    this.addRule(processer);

    StoreReqAppVO[] insertedVOs = null;
    if (!ArrayUtils.isEmpty(insertVOs)) {
      processer.before(insertVOs, null);
      insertedVOs = new BillInsert<StoreReqAppVO>().insert(insertVOs);
      processer.after(insertedVOs, null);
    }

    StoreReqAppVO[] updatedVOs = null;
    if (!ArrayUtils.isEmpty(updateVOs)) {
      processer.before(updateVOs, updateOriginVOs);
      updatedVOs =
          new BillUpdate<StoreReqAppVO>().update(updateVOs, updateOriginVOs);
      processer.after(updatedVOs, updateOriginVOs);
    }

    return ArrayUtil.combinArrays(insertedVOs, updatedVOs);
  }

  private void addRule(CompareAroundProcesser<StoreReqAppVO> processer) {
    processer.addBeforeRule(new RowNoRule());
    processer.addBeforeRule(new ItemEmptyRule());
    // ��������֯���°�
    processer.addBeforeFinalRule(new NewestOrgVersionFillRule<StoreReqAppVO>(
        StoreReqAppHeaderVO.PK_STOREREQ));
    // ����֯ͣ�ü��
    processer.addBeforeRule(new StockOrgEnableCheckRule<StoreReqAppVO>());
    processer.addBeforeRule(new CodeProceRule());
    processer.addBeforeRule(new NecessaryFillRule());
    processer.addBeforeRule(new NastumGtZeroRule());
    // ������ͨ��ʱ�޸ĺ��״̬���������������ڵȴ���
    processer.addBeforeFinalRule(new NoPassUpdateRule<StoreReqAppVO>());
    // ��������еĵ����Ƿ���ɵ�ǰ�û��޸�
    processer.addBeforeFinalRule(new ApprovingEditCheckRule<StoreReqAppVO>(
        POBillType.MRBill));
    // ��������
    processer.addBeforeRule(new UserDefSaveRule<StoreReqAppVO>(new Class[] {
      StoreReqAppHeaderVO.class, StoreReqAppItemVO.class
    }));
    // ��������ǰ���޸�ǰ�¼�
    processer.addBeforeRule(new SaveBeforeEventRule());
    // ���ɸ�������У��
    // MarAssistantSaveRule<StoreReqAppVO> marRule =
    // new MarAssistantSaveRule<StoreReqAppVO>();
    // marRule.setNotNullValidate(StoreReqAppItemVO.PK_MATERIAL);
    // processer.addBeforeRule(marRule);

    processer.addAfterRule(new BillCodeUniqueRule());
    processer.addAfterRule(new WriteBackPIMRule<StoreReqAppVO>(
        POBillType.MRBill.getCode()));
    processer.addAfterRule(new WriteBack422xRule<StoreReqAppVO>(
        POBillType.MRBill.getCode()));
    // ��д����
    processer.addAfterRule(new WriteBackSourceRule());
    // ������������޸ĺ��¼�
    processer.addAfterRule(new SaveAfterEventRule());
  }
}
