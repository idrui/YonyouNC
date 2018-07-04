package nc.pubimpl.pu.m422x.ewm.m4b32;

import java.util.HashMap;

import nc.bs.framework.common.InvocationInfoProxy;
import nc.bs.framework.common.NCLocator;
import nc.bs.pu.m422x.maintain.StoreReqAppSaveBP;
import nc.itf.scmpub.reference.uap.pf.PfBillItfDefUtil;
import nc.itf.scmpub.reference.uap.pf.TransTypeMapping;
import nc.itf.uap.pf.IplatFormEntry;
import nc.pubitf.pu.m422x.ewm.m4b32.ISave422XFor4b32;
import nc.vo.am.constant.BillStatusConst;
import nc.vo.pu.m422x.entity.StoreReqAppHeaderVO;
import nc.vo.pu.m422x.entity.StoreReqAppItemVO;
import nc.vo.pu.m422x.entity.StoreReqAppVO;
import nc.vo.pu.m422x.rule.CurrencySetter;
import nc.vo.pu.m422x.rule.NextbalanceorgSetter;
import nc.vo.pu.m422x.rule.NumValueSetter;
import nc.vo.pu.pub.util.BillHelper;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.AppContext;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.util.VORowNoUtils;

public class Save422XFor4b32Impl implements ISave422XFor4b32 {

  @Override
  public StoreReqAppVO[] pushapproveBills(StoreReqAppVO[] storereqs)
      throws BusinessException {
    try {
      // 调用审批脚本
      IplatFormEntry iIplatFormEntry =
          (IplatFormEntry) NCLocator.getInstance().lookup(
              IplatFormEntry.class.getName());
      Object retObj = null;
      HashMap eParam = new HashMap();
      retObj =
          iIplatFormEntry.processBatch(
              new StringBuilder().append("APPROVE")
                  .append(InvocationInfoProxy.getInstance().getUserId())
                  .toString(), "422X", null, storereqs, null, eParam);
      if (retObj != null) {

        return (StoreReqAppVO[]) ((nc.vo.uap.pf.PfProcessBatchRetObject) retObj)
            .getRetObj();

      }

    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return null;
  }

  @Override
  public StoreReqAppVO[] pushSaveApproveBills(StoreReqAppVO[] storereqs)
      throws BusinessException {
    StoreReqAppVO[] returnrep = null;
    try {
      if (nc.vo.pfxx.util.ArrayUtils.isEmpty(storereqs)) {
        return null;
      }
      this.fillInformation(storereqs);
      returnrep = new StoreReqAppSaveBP().save(storereqs, null, null);

    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return returnrep;
  }

  private void fillInformation(StoreReqAppVO[] vos) {

    if (nc.vo.pfxx.util.ArrayUtils.isEmpty(vos)) {
      return;
    }
    // 补充行号
    VORowNoUtils.setVOsRowNoByRule(vos, StoreReqAppItemVO.CROWNO);

    for (StoreReqAppVO vo : vos) {
      StoreReqAppHeaderVO headvo = vo.getHVO();
      headvo.setFbillstatus(Integer.valueOf(BillStatusConst.check_pass));
      headvo.setApprover(headvo.getBillmaker());
      headvo.setTaudittime(headvo.getDbilldate());
      StoreReqAppItemVO[] itemVOs = vo.getBVO();
      if (itemVOs == null || itemVOs.length < 1) {
        String desttype = this.getTrantypeByBillitfdef("4B32", null, "422X");
        headvo.setCtrantypeid(desttype);

      }
      else {
        String sourcetypeid = itemVOs[0].getCsourcetypecode();
        String sourcetranstype = itemVOs[0].getVsourcetrantype();
        String desttype = null;
        if (sourcetypeid == null || sourcetranstype == null) {
          desttype = this.getTrantypeByBillitfdef("4B32", null, "422X");
        }
        else {

          desttype =
              this.getTrantypeByBillitfdef("4B32", sourcetranstype, "422X");
        }
        headvo.setCtrantypeid(desttype);
      }

      int[] rows = new int[itemVOs.length];
      for (int i = 0; i < itemVOs.length; ++i) {
        itemVOs[i].setPk_reqstoorg(itemVOs[i].getPk_org());
        itemVOs[i].setPk_reqstoorg_v(itemVOs[i].getPk_org_v());
        rows[i] = i;
      }

      BillHelper<StoreReqAppVO> helper = new BillHelper<StoreReqAppVO>(vo);
      new CurrencySetter(helper).setCurrency(rows);
      // new nc.vo.pu.m422x.rule.AssistUnit(helper).setAssistUnit(rows);
      // new UnitAndChangeRate(helper).setChangeRate(rows);
      // 设置数量，联动计算
      new NumValueSetter(helper).setNastnum(rows);
      // 下次平衡库存组织
      NextbalanceorgSetter nextsetter = new NextbalanceorgSetter(helper);
      nextsetter.setBclear(false);
      nextsetter.setNextbalanceorg(rows);
    }
  }

  private String getTrantypeByBillitfdef(String srcbilltype,
      String srctrantype, String destbilltype) {
    TransTypeMapping mapping = new TransTypeMapping();
    mapping.setSrcBillType(srcbilltype);
    mapping.setSrcTransType(srctrantype);
    mapping.setDestBillType(destbilltype);

    // 取得订单类型
    PfBillItfDefUtil.queryTransTypeMapping(AppContext.getInstance()
        .getPkGroup(), mapping);
    return mapping.getDestTransType();
  }
}
