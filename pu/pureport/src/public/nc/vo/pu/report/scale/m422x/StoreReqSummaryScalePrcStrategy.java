package nc.vo.pu.report.scale.m422x;

import nc.scmmm.pub.scmpub.report.scale.SCMRptAbsScalePrcStrategy;
import nc.scmmm.vo.scmpub.report.entity.scale.SCMReportScaleMetaRegister;
import nc.vo.pu.m422x.entity.StoreReqAppItemVO;

public class StoreReqSummaryScalePrcStrategy extends SCMRptAbsScalePrcStrategy {

  private static final long serialVersionUID = -8599757425578816647L;

  @Override
  protected void registerScaleProcess(
      SCMReportScaleMetaRegister scmRptScaleRegister) {

    // 主数量，数量和累计出库数量
    scmRptScaleRegister.setNumDigits(StoreReqAppItemVO.CUNITID, new String[] {
      StoreReqAppItemVO.NNUM, StoreReqAppItemVO.NASTNUM,
      StoreReqAppItemVO.NACCUOUTNUM
    });

    // 币种不同可能导致不能汇总，所以把币种从select中去掉了，
    // 去掉币种就无法控制金额的精度了，所以在这里把原有的金额精度控制去掉了。
  }
}
