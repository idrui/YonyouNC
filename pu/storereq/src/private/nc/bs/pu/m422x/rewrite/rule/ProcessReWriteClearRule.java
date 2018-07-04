package nc.bs.pu.m422x.rewrite.rule;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pu.m422x.entity.StoreReqAppHeaderVO;
import nc.vo.pu.m422x.entity.StoreReqAppItemVO;
import nc.vo.pu.m422x.entity.StoreReqAppViewVO;
import nc.vo.pu.m422x.entity.WriteBack422XForInv9ParamVO;
import nc.vo.pu.pub.enumeration.POEnumBillStatus;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * 物资需求汇总取消汇总平衡回写参数处理规则
 * 
 * @author zhangyhh
 * @since 6.3
 * @time 2014-05-07 22:00:00
 */
public class ProcessReWriteClearRule implements IRule<StoreReqAppViewVO> {

  private WriteBack422XForInv9ParamVO[] wbVos;

  public ProcessReWriteClearRule(WriteBack422XForInv9ParamVO[] wbVos) {
    this.wbVos = wbVos;
  }

  @Override
  public void process(StoreReqAppViewVO[] views) {

    for (StoreReqAppViewVO view : views) {
      if (view.getBclose().booleanValue()) {
        ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
            .getNCLangRes().getStrByID("4004010_0", "04004010-0026"));/*
                                                                       * @res
                                                                       * "上游单据已关闭，不能操作！"
                                                                       */
      }

      if (!POEnumBillStatus.APPROVE.toInteger().equals(
          view.getAttributeValue(StoreReqAppHeaderVO.FBILLSTATUS))) {
        ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
            .getNCLangRes().getStrByID("4004010_0", "04004010-0028"));/*
                                                                       * @res
                                                                       * "上游单据没有审核，不能操作！"
                                                                       */
      }

      view.setAttributeValue(StoreReqAppItemVO.CSOURCEID2, null);
      view.setAttributeValue(StoreReqAppItemVO.CSOURCEBID2, null);
      view.setAttributeValue(StoreReqAppItemVO.VSOURCECODE2, null);
      view.setAttributeValue(StoreReqAppItemVO.CSOURCETYPECODE2, null);
      view.setAttributeValue(StoreReqAppItemVO.VSOURCEROWNO2, null);
      view.setAttributeValue(StoreReqAppItemVO.VSOURCETRANTYPE2, null);

      view.setAttributeValue(StoreReqAppItemVO.CFIRSTID2, null);
      view.setAttributeValue(StoreReqAppItemVO.CFIRSTBID2, null);
      view.setAttributeValue(StoreReqAppItemVO.CFIRSTTYPECODE2, null);
      view.setAttributeValue(StoreReqAppItemVO.VFIRSTCODE2, null);
      view.setAttributeValue(StoreReqAppItemVO.VFIRSTROWNO2, null);
      view.setAttributeValue(StoreReqAppItemVO.VFIRSTTRANTYPE2, null);

      view.setAttributeValue(StoreReqAppItemVO.BENDGATHER, UFBoolean.FALSE);
      view.setAttributeValue(StoreReqAppItemVO.CGATHERPSNID, null);
      view.setAttributeValue(StoreReqAppItemVO.CGATHERID, null);
      view.setAttributeValue(StoreReqAppItemVO.TGATHERTIME, null);

      view.setAttributeValue(StoreReqAppItemVO.NNETNUM, null);
      view.setAttributeValue(StoreReqAppItemVO.NACCCUSTORNUM, null);
      view.setAttributeValue(StoreReqAppItemVO.NACCUMBUYREQNUM, null);
    }
  }
}
