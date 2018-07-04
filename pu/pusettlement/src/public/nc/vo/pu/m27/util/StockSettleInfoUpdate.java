package nc.vo.pu.m27.util;

import java.util.HashMap;
import java.util.Map;

import nc.vo.pu.m27.entity.MatchMaterialVO;
import nc.vo.pu.m27.entity.SettleBillItemVO;
import nc.vo.pu.m27.entity.SettleBillVO;
import nc.vo.pu.m27.entity.StockSettleVO;
import nc.vo.pu.m4201.entity.PurchaseinFIItemVO;
import nc.vo.pu.m4201.enumeration.EnumToIAFlag;
import nc.vo.pu.pub.util.CirVOUtil;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.pub.MathTool;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;

/**
 * 结算信息更新（用于费用分摊，只更新未暂估过和未确认过的结算金额和数量）
 * 
 * @since 6.0
 * @version 2011-3-19 下午09:51:20
 * @author zhaoyha
 */
public class StockSettleInfoUpdate {

  private StockSettleVO[] origSSVos;

  private MatchMaterialVO[] uiMMVos;

  private MatchMaterialVO[] updatedMMVos;

  private StockSettleVO[] updatedSSVos;

  /**
   * @param uiMMVos 原始的匹配VO
   */
  public StockSettleInfoUpdate(MatchMaterialVO[] uiMMVos) {
    this.uiMMVos = uiMMVos;
  }

  /**
   * @param origSSVos 原始的库存结算VO
   */
  public StockSettleInfoUpdate(StockSettleVO[] origSSVos) {
    this.origSSVos = origSSVos;
  }

  public MatchMaterialVO[] getUpdatedMMVO() {
    return this.updatedMMVos;
  }

  public StockSettleVO[] getUpdatedSSVO() {
    return this.updatedSSVos;
  }

  public void process(SettleBillVO[] vos) {
    this.cloneMMVO();// 必须克隆，一般用于模拟结算后分摊，所以不能破坏原来的数据
    this.cloneSSVO();// 先克隆,保证有返回数据
    if (ArrayUtils.isEmpty(vos)) {
      return;
    }
    Map<String, MatchMaterialVO> mmVoMap =
        null == this.updatedMMVos ? new HashMap<String, MatchMaterialVO>()
            : CirVOUtil.createKeyVOMap(this.updatedMMVos);
    Map<String, StockSettleVO> ssVoMap =
        null == this.updatedSSVos ? new HashMap<String, StockSettleVO>()
            : CirVOUtil.createKeyVOMap(this.updatedSSVos);
    for (SettleBillVO vo : vos) {
      for (SettleBillItemVO item : vo.getChildrenVO()) {
        String pk_stock_b = item.getPk_stock_b();
        if (StringUtils.isBlank(pk_stock_b)) {
          continue;
        }
        StockSettleVO ssVo =
            null == mmVoMap.get(pk_stock_b) ? null : mmVoMap.get(pk_stock_b)
                .getStockSettleVO();
        this.updateSSVO(item, ssVo);
        ssVo = ssVoMap.get(pk_stock_b);
        this.updateSSVO(item, ssVo);
      }
    }
  }

  private void cloneMMVO() {
    if (ArrayUtils.isEmpty(this.uiMMVos)) {
      this.updatedMMVos = this.uiMMVos;
      return;
    }
    this.updatedMMVos = new MatchMaterialVO[this.uiMMVos.length];
    for (int i = 0; i < this.uiMMVos.length; ++i) {
      MatchMaterialVO clonedMMVo = (MatchMaterialVO) this.uiMMVos[i].clone();
      this.updatedMMVos[i] = clonedMMVo;
      StockSettleVO ssVo = clonedMMVo.getStockSettleVO();
      if (null == ssVo) {
        continue;
      }
      clonedMMVo.setStockSettleVO((StockSettleVO) ssVo.clone());
    }
  }

  private void cloneSSVO() {
    if (ArrayUtils.isEmpty(this.origSSVos)) {
      this.updatedSSVos = this.origSSVos;
      return;
    }
    // this.updatedSSVos =
    // Constructor.construct(this.origSSVos[0].getClass(),
    // this.origSSVos.length);
    this.updatedSSVos = new StockSettleVO[this.origSSVos.length];
    for (int i = 0; i < this.origSSVos.length; ++i) {
      this.updatedSSVos[i] = (StockSettleVO) this.origSSVos[i].clone();
    }
  }

  private void updateSSVO(SettleBillItemVO item, StockSettleVO ssVo) {
    // 暂估或确认过的入库单行不处理，因其分摊时不依赖于结算数据
    if (null == ssVo || !MathTool.isZero(ssVo.getNestnum())
        || EnumToIAFlag.ConfirmToIA.toInteger().equals(ssVo.getFdirtoiatype())) {
      return;
    }
    UFDouble stlnum = item.getNsettlenum();
    UFDouble stlmny = item.getNgoodsmoney();
    UFDouble accstlnum = ssVo.getNaccumsettlenum();
    UFDouble accpreestmny = ssVo.getNaccpreeststtlmny();
    ssVo.setAttributeValue(StockSettleVO.NACCUMSETTLENUM,
        MathTool.add(accstlnum, stlnum));
    ssVo.setAttributeValue(PurchaseinFIItemVO.NACCPREESTSTTLMNY,
        MathTool.add(accpreestmny, stlmny));
  }

}
