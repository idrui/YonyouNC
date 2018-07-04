package nc.vo.pu.m422x.vochange;

import nc.vo.pf.change.ChangeVOAdjustContext;
import nc.vo.pf.change.IChangeVOAdjust;
import nc.vo.pu.m422x.entity.StoreReqAppItemVO;
import nc.vo.pu.m422x.entity.StoreReqAppVO;
import nc.vo.pu.m422x.rule.CurrencySetter;
import nc.vo.pu.pub.util.ArrayUtil;
import nc.vo.pu.pub.util.BillHelper;
import nc.vo.pub.AggregatedValueObject;
import nc.vo.pub.BusinessException;

import org.apache.commons.lang.ArrayUtils;

/**
 * 物资及服务需求单到物资需求申请单VO对照前后处理类
 * 
 * @since 6.3.1
 * @version 2013-8-8 下午07:12:46
 * @author fanly3
 */
public class ChangeVOAdjust4D14To422X implements IChangeVOAdjust {

  @Override
  public AggregatedValueObject adjustAfterChange(AggregatedValueObject srcVO,
      AggregatedValueObject destVO, ChangeVOAdjustContext adjustContext)
      throws BusinessException {
    if (destVO != null) {
      // 调用批处理方法
      return this.batchAdjustAfterChange(new AggregatedValueObject[] {
        srcVO
      }, new AggregatedValueObject[] {
        destVO
      }, adjustContext)[0];
    }
    return null;
  }

  @Override
  public AggregatedValueObject adjustBeforeChange(AggregatedValueObject srcVO,
      ChangeVOAdjustContext adjustContext) throws BusinessException {
    if (srcVO != null) {
      // 调用批处理方法
      return this.batchAdjustBeforeChange(new AggregatedValueObject[] {
        srcVO
      }, adjustContext)[0];
    }
    return null;
  }

  @Override
  public AggregatedValueObject[] batchAdjustAfterChange(
      AggregatedValueObject[] srcVOs, AggregatedValueObject[] destVOs,
      ChangeVOAdjustContext adjustContext) throws BusinessException {
    StoreReqAppVO[] vos = ArrayUtil.convertArrayType(destVOs);
    // 补全物资需求申请单信息
    this.fillInformation(vos);
    return vos;
  }

  @Override
  public AggregatedValueObject[] batchAdjustBeforeChange(
      AggregatedValueObject[] srcVOs, ChangeVOAdjustContext adjustContext)
      throws BusinessException {
    return srcVOs;
  }

  /**
   * 补全vo相关信息
   * 
   * @param vos 物资需求申请单聚合VO
   */
  private void fillInformation(StoreReqAppVO[] vos) {
    if (ArrayUtils.isEmpty(vos)) {
      return;
    }

    for (StoreReqAppVO vo : vos) {
      StoreReqAppItemVO[] itemVOs = vo.getBVO();
      for (StoreReqAppItemVO item : itemVOs) {
        item.setCsourcetypecode("1001ZP1000000005GEZN");
      }

      int[] rows = new int[itemVOs.length];
      for (int i = 0; i < itemVOs.length; ++i) {
        rows[i] = i;
      }
      BillHelper<StoreReqAppVO> helper = new BillHelper<StoreReqAppVO>(vo);
      // 不用重新取采购默认单位，直接VO对照过来，数量和换算率也是
      // new AssistUnit(helper).setAssistUnit(rows);
      // new UnitAndChangeRate(helper).setChangeRate(rows);
      // new NumValueSetter(helper).setNastnum(rows);
      // 设置币种
      new CurrencySetter(helper).setCurrency(rows);
    }
  }
}
