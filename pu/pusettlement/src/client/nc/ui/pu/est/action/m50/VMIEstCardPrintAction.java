package nc.ui.pu.est.action.m50;

import nc.ui.pu.est.action.EstCardPrintAction;
import nc.ui.pub.print.IMetaDataDataSource;
import nc.ui.pubapp.uif2app.model.BillManageModel;
import nc.vo.pu.est.entity.EstVO;
import nc.vo.pu.est.entity.m50.VmiEstHeaderVO;
import nc.vo.pu.est.entity.m50.VmiEstVO;
import nc.vo.pu.est.util.EstVOUtil;
import nc.vo.pu.m4202.entity.VmiFIFeeVO;
import nc.vo.pu.m4202.entity.VmiFIHeaderVO;
import nc.vo.pu.m4202.entity.VmiFIVO;
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

public class VMIEstCardPrintAction extends EstCardPrintAction {

  private static final long serialVersionUID = 7042734595387239192L;

  public Object[] getVMIPrintDatas() {
    BillManageModel estModel = (BillManageModel) this.getModel();
    Object[] data = estModel.getSelectedOperaDatas();
    if (ArrayUtils.isEmpty(data)) {
      return null;
    }
    EstVO[] vos = (EstVO[]) EstVOUtil.getCloneEstData(data);
    VmiFIVO[] fiVOs = new VmiFIVO[vos.length];
    for (int i = 0; i < fiVOs.length; i++) {
      VmiFIVO fiVO = new VmiFIVO();
      VmiEstVO estVo = (VmiEstVO) vos[i];
      VmiFIFeeVO[] feeVOs = estVo.getChildrenVO();
      VmiEstHeaderVO viewVo = estVo.getParentVO();

      VmiFIHeaderVO header = (VmiFIHeaderVO) viewVo.getVO(VmiFIHeaderVO.class);
      // 费用本币价税合计 = 表体所有费用本币价税合计的合计。
      // 费用本币无税金额 = 表体所有费用本币无税金额的合计。
      // 总本币价税合计 = 本币价税合计+费用本币价税合计。
      // 总本币无税金额 = 本币无税金额+费用本币无税金额。
      if (!ArrayUtils.isEmpty(feeVOs)) {
        UFDouble feeTotalMny = UFDouble.ZERO_DBL;
        UFDouble feeMny = UFDouble.ZERO_DBL;
        for (VmiFIFeeVO feeVo : feeVOs) {
          feeTotalMny = MathTool.add(feeTotalMny, feeVo.getNesttotalmny());// 费用本币价税合计
          feeMny = MathTool.add(feeMny, feeVo.getNestmny());// 费用本币无税金额
        }
        header.setNfeemny(feeMny);// 费用本币无税金额
        header.setNfeetaxmny(feeTotalMny);// 费用本币价税合计
        header.setNtotaltaxmny(MathTool.add(feeTotalMny,
            header.getNesttotalmny()));// 总本币价税合计
        header.setNtotalmny(MathTool.add(feeMny, header.getNestmny()));// 总本币无税金额
      }
      fiVO.setParent(header);
      fiVOs[i] = fiVO;
    }

    return fiVOs;
  }

  @Override
  protected IMetaDataDataSource[] getDefaultMetaDataSource() {
    IMetaDataDataSource[] defaultDataSource = null;
    Object[] datas = this.getVMIPrintDatas();
    BillManageModel estModel = (BillManageModel) this.getModel();
    defaultDataSource = new EstPrintDataSource[datas.length];
    for (int i = 0; i < defaultDataSource.length; i++) {
      defaultDataSource[i] =
          new EstPrintDataSource(datas[i],
              (VmiEstVO) estModel.getSelectedData());
    }
    return defaultDataSource;
  }
}
