package nc.vo.pu.est.util;

import nc.vo.pu.est.entity.GoodsEstVO;
import nc.vo.pu.est.util.EstVOUtil.ScaleKeyInfo;
import nc.vo.pubapp.scale.BillScaleProcessor;
import nc.vo.pubapp.scale.FieldInfo;
import nc.vo.pubapp.scale.PosEnum;

/**
 * 暂估vo精度处理类
 * 
 * @since 6.0
 * @version 2011-8-11 下午08:09:34
 * @author 田锋涛
 */

public class EstVoScaleUtil {

  /**
   * @param scale
   */
  public void setScale(BillScaleProcessor scale) {
    this.fillUpBillScaleProcessor(scale).process();
  }

  /**
   * @param scale
   */
  public void setScaleForVmi(BillScaleProcessor scale) {
    BillScaleProcessor processor = this.fillUpBillScaleProcessor(scale);
    ScaleKeyInfo keyInfo = EstVOUtil.getGoodsEstScaleKeyInfo();
    String[] totalMnyKeys =
        new String[] {
          GoodsEstVO.NFEEMNY, GoodsEstVO.NFEETAXMNY, GoodsEstVO.NTOTALMNY,
          GoodsEstVO.NTOTALTAXMNY
        };

    // 本币金额精度
    processor.setMnyCtlInfo(totalMnyKeys, PosEnum.head, null,
        keyInfo.getCurrencyKey(), PosEnum.head, null);
    processor.process();
  }

  /**
   * @param scale
   */
  private BillScaleProcessor fillUpBillScaleProcessor(BillScaleProcessor scale) {
    ScaleKeyInfo keyInfo = EstVOUtil.getGoodsEstScaleKeyInfo();
    // 主单位数量精度
    scale.setNumCtlInfo(keyInfo.getNumKeys(), PosEnum.body, null,
        keyInfo.getunitKey(), PosEnum.body, null);
    // 本币单价精度
    scale.setPriceCtlInfo(keyInfo.getCurrPriceKeys(), PosEnum.body, null,
        keyInfo.getCurrencyKey(), PosEnum.body, null);
    // 原币单价精度
    scale.setPriceCtlInfo(keyInfo.getOcurrPriceKeys(), PosEnum.body, null,
        keyInfo.getOcurrencyKey(), PosEnum.body, null);
    // 本币金额精度
    scale.setMnyCtlInfo(keyInfo.getMnyKeys(), PosEnum.body, null,
        keyInfo.getCurrencyKey(), PosEnum.body, null);
    // 原币金额精度
    scale.setMnyCtlInfo(keyInfo.getOmnyKeys(), PosEnum.body, null,
        keyInfo.getOcurrencyKey(), PosEnum.body, null);
    // 税率精度
    scale.setTaxRateCtlInfo(keyInfo.getTaxRateKey(), PosEnum.body, null);
    // 折本汇率精度
    FieldInfo[] fdInfos = keyInfo.getExchgRateKeyInfo();
    scale.setOrgExchangeCtlInfo(fdInfos[0], fdInfos[1], fdInfos[2], fdInfos[3]);
    return scale;
  }

}
