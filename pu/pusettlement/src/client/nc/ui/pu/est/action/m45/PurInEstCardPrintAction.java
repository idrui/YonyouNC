package nc.ui.pu.est.action.m45;

import java.util.HashMap;
import java.util.Map;

import nc.ui.pu.est.action.EstCardPrintAction;
import nc.ui.pub.print.IMetaDataDataSource;
import nc.ui.pubapp.uif2app.model.BillManageModel;
import nc.vo.pu.est.entity.EstVO;
import nc.vo.pu.est.entity.m45.PurchaseInEstHeaderVO;
import nc.vo.pu.est.util.EstVOUtil;
import nc.vo.pu.m4201.entity.PurchaseinFIFeeVO;
import nc.vo.pu.m4201.entity.PurchaseinFIHeaderVO;
import nc.vo.pu.m4201.entity.PurchaseinFIItemVO;
import nc.vo.pu.m4201.entity.PurchaseinFIVO;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.pub.MathTool;

import org.apache.commons.lang.ArrayUtils;

/**
 * 暂估处理打印
 * 
 * @since 6.0
 * @version 2010-11-8 上午09:59:45
 * @author tianft
 */

public class PurInEstCardPrintAction extends EstCardPrintAction {

  private static final long serialVersionUID = 7042734595387239192L;

  private Map<String, EstVO> estVOMap;

  /**
   * @return
   */
  public PurchaseinFIVO[] getPurchaseInPrintDatas() {
    BillManageModel estModel = (BillManageModel) this.getModel();
    Object[] data = estModel.getSelectedOperaDatas();
    if (ArrayUtils.isEmpty(data)) {
      return null;
    }
    this.estVOMap = new HashMap<String, EstVO>();
    EstVO[] vos = (EstVO[]) EstVOUtil.getCloneEstData(data);
    PurchaseinFIVO[] fiVOs = new PurchaseinFIVO[vos.length];
    for (int i = 0; i < fiVOs.length; i++) {
      PurchaseinFIVO fiVO = new PurchaseinFIVO();
      EstVO estVo = vos[i];
      PurchaseinFIFeeVO[] feeVOs = (PurchaseinFIFeeVO[]) estVo.getChildrenVO();
      PurchaseInEstHeaderVO viewVo =
          (PurchaseInEstHeaderVO) estVo.getParentVO();

      PurchaseinFIHeaderVO header =
          (PurchaseinFIHeaderVO) viewVo.getVO(PurchaseinFIHeaderVO.class);

      PurchaseinFIItemVO item =
          (PurchaseinFIItemVO) viewVo.getVO(PurchaseinFIItemVO.class);
      // 费用本币价税合计 = 表体所有费用本币价税合计的合计。
      // 费用本币无税金额 = 表体所有费用本币无税金额的合计。
      // 总本币价税合计 = 本币价税合计+费用本币价税合计。
      // 总本币无税金额 = 本币无税金额+费用本币无税金额。
      if (!ArrayUtils.isEmpty(feeVOs)) {
        UFDouble feeTotalMny = UFDouble.ZERO_DBL;
        UFDouble feMny = UFDouble.ZERO_DBL;
        for (PurchaseinFIFeeVO feeVo : feeVOs) {
          feeTotalMny = MathTool.add(feeTotalMny, feeVo.getNesttotalmny());// 费用本币价税合计
          feMny = MathTool.add(feMny, feeVo.getNestmny());// 费用本币无税金额
        }
        item.setNfeemny(feMny);// 费用本币无税金额
        item.setNfeetaxmny(feeTotalMny);// 费用本币价税合计
        item.setNtotaltaxmny(MathTool.add(feeTotalMny, item.getNesttotalmny()));// 总本币价税合计
        item.setNtotalmny(MathTool.add(feMny, item.getNestmny()));// 总本币无税金额
      }

      this.estVOMap.put(item.getPk_stockps_b(), estVo);

      fiVO.setParent(header);
      fiVO.setChildrenVO(new PurchaseinFIItemVO[] {
        item
      });
      fiVOs[i] = fiVO;
    }
    return fiVOs;

  }

  @Override
  protected IMetaDataDataSource[] getDefaultMetaDataSource() {
    IMetaDataDataSource[] defaultDataSource = null;
    Object[] datas = this.getPurchaseInPrintDatas();
    defaultDataSource = new EstPrintDataSource[datas.length];

    for (int i = 0; i < defaultDataSource.length; i++) {
      PurchaseinFIVO aggvo = (PurchaseinFIVO) datas[i];
      defaultDataSource[i] =
          new EstPrintDataSource(datas[i], this.estVOMap.get(aggvo
              .getChildrenVO()[0].getPk_stockps_b()));
    }
    return defaultDataSource;
  }
}
