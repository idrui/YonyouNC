package nc.vo.pu.m422x.vochange;

import nc.vo.pf.change.ChangeVOAdjustContext;
import nc.vo.pf.change.IChangeVOAdjust;
import nc.vo.pu.m422x.entity.StoreReqAppItemVO;
import nc.vo.pu.m422x.entity.StoreReqAppVO;
import nc.vo.pu.m422x.rule.AssistUnit;
import nc.vo.pu.m422x.rule.CurrencySetter;
import nc.vo.pu.m422x.rule.NextbalanceorgSetter;
import nc.vo.pu.m422x.rule.NumValueSetter;
import nc.vo.pu.m422x.rule.UnitAndChangeRate;
import nc.vo.pu.pub.util.ArrayUtil;
import nc.vo.pu.pub.util.BillHelper;
import nc.vo.pub.AggregatedValueObject;
import nc.vo.pub.BusinessException;

import org.apache.commons.lang.ArrayUtils;

public class ChangeVOAdjust4D26To422X implements IChangeVOAdjust {

  @Override
  public AggregatedValueObject adjustAfterChange(AggregatedValueObject srcVO,
      AggregatedValueObject destVO, ChangeVOAdjustContext adjustContext)
      throws BusinessException {
    if (null == destVO) {
      return null;
    }
    // 调用批处理方法
    return this.batchAdjustAfterChange(new AggregatedValueObject[] {
      srcVO
    }, new AggregatedValueObject[] {
      destVO
    }, adjustContext)[0];
  }

  @Override
  public AggregatedValueObject adjustBeforeChange(AggregatedValueObject srcVO,
      ChangeVOAdjustContext adjustContext) throws BusinessException {
    if (null == srcVO) {
      return null;
    }
    // 调用批处理方法
    return this.batchAdjustBeforeChange(new AggregatedValueObject[] {
      srcVO
    }, adjustContext)[0];
  }

  @Override
  public AggregatedValueObject[] batchAdjustAfterChange(
      AggregatedValueObject[] srcVOs, AggregatedValueObject[] destVOs,
      ChangeVOAdjustContext adjustContext) throws BusinessException {
    StoreReqAppVO[] vos = ArrayUtil.convertArrayType(destVOs);
    this.fillInformation(vos);
    return vos;
  }

  @Override
  public AggregatedValueObject[] batchAdjustBeforeChange(
      AggregatedValueObject[] srcVOs, ChangeVOAdjustContext adjustContext)
      throws BusinessException {
    return srcVOs;
  }

  private void fillInformation(StoreReqAppVO[] vos) {
    if (ArrayUtils.isEmpty(vos)) {
      return;
    }

    for (StoreReqAppVO vo : vos) {
      StoreReqAppItemVO[] itemVOs = vo.getBVO();
      int[] rows = new int[itemVOs.length];
      for (int i = 0; i < itemVOs.length; ++i) {
        rows[i] = i;
      }
      BillHelper<StoreReqAppVO> helper = new BillHelper<StoreReqAppVO>(vo);
      new AssistUnit(helper).setAssistUnit(rows);
      new UnitAndChangeRate(helper).setChangeRate(rows);
      // 设置数量，联动计算
      new NumValueSetter(helper).setNastnum(rows);
      new CurrencySetter(helper).setCurrency(rows);
      // 下次平衡库存组织
      NextbalanceorgSetter nextsetter = new NextbalanceorgSetter(helper);
      nextsetter.setBclear(false);
      nextsetter.setNextbalanceorg(rows);
    }
  }

}
