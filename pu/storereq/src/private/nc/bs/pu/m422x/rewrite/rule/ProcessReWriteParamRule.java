package nc.bs.pu.m422x.rewrite.rule;

import java.util.Map;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pu.m422x.entity.StoreReqAppHeaderVO;
import nc.vo.pu.m422x.entity.StoreReqAppItemVO;
import nc.vo.pu.m422x.entity.StoreReqAppViewVO;
import nc.vo.pu.m422x.entity.WriteBack422XForInv9ParamVO;
import nc.vo.pu.pub.enumeration.POEnumBillStatus;
import nc.vo.pu.pub.util.CirVOUtil;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * 物资需求汇总回写参数处理规则
 * 
 * @author zhangyhh
 * @since 6.3
 * @time 2014-05-07 22:00:00
 */
public class ProcessReWriteParamRule implements IRule<StoreReqAppViewVO> {

  private WriteBack422XForInv9ParamVO[] wbVos;

  public ProcessReWriteParamRule(WriteBack422XForInv9ParamVO[] wbVos) {
    this.wbVos = wbVos;
  }

  @Override
  public void process(StoreReqAppViewVO[] views) {
    Map<String, StoreReqAppViewVO> viewMap = CirVOUtil.createKeyVOMap(views);

    for (WriteBack422XForInv9ParamVO vo : this.wbVos) {
      StoreReqAppViewVO view = viewMap.get(vo.getPk_storereq_b());

      if (view.getBclose().booleanValue()) {
        ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
            .getNCLangRes().getStrByID("4004010_0", "04004010-0026"));/*
                                                                       * @res
                                                                       * "上游单据已关闭，不能操作！"
                                                                       */
      }
      // 并发校验
      if (!view.getAttributeValue(StoreReqAppItemVO.TS).toString().trim()
          .equals(vo.getStrBTS().trim())) {
        ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
            .getNCLangRes().getStrByID("4004010_0", "04004010-0027"));/*
                                                                       * @res
                                                                       * "上游单据已被其他人修改！"
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
      view.setAttributeValue(StoreReqAppItemVO.CSOURCEID2, vo.getPk_downbill());
      view.setAttributeValue(StoreReqAppItemVO.CSOURCEBID2,
          vo.getPk_downbill_b());
      view.setAttributeValue(StoreReqAppItemVO.VSOURCECODE2,
          vo.getVdownbillcode());
      view.setAttributeValue(StoreReqAppItemVO.CSOURCETYPECODE2,
          vo.getVdownbilltype());
      view.setAttributeValue(StoreReqAppItemVO.VSOURCEROWNO2,
          vo.getVdownbillrownum());
      view.setAttributeValue(StoreReqAppItemVO.VSOURCETRANTYPE2,
          vo.getVdowntranstype());

      view.setAttributeValue(StoreReqAppItemVO.CFIRSTID2, vo.getCm5xbillid());
      view.setAttributeValue(StoreReqAppItemVO.CFIRSTBID2, vo.getCm5xbillbid());
      view.setAttributeValue(StoreReqAppItemVO.CFIRSTTYPECODE2,
          vo.getVm5xbilltype());
      view.setAttributeValue(StoreReqAppItemVO.VFIRSTCODE2,
          vo.getVm5xbillcode());
      view.setAttributeValue(StoreReqAppItemVO.VFIRSTROWNO2, vo.getVm5xrowno());
      view.setAttributeValue(StoreReqAppItemVO.VFIRSTTRANTYPE2,
          vo.getVm5xtranstype());

      view.setAttributeValue(StoreReqAppItemVO.BENDGATHER, vo.getBIsBalance());
      view.setAttributeValue(StoreReqAppItemVO.CGATHERPSNID,
          vo.getcSummaryPsn());
      view.setAttributeValue(StoreReqAppItemVO.CGATHERID, vo.getCSummaryID());
      view.setAttributeValue(StoreReqAppItemVO.TGATHERTIME,
          vo.getDSummaryTime());

      view.setAttributeValue(StoreReqAppItemVO.NNETNUM, vo.getNnetnum());
      view.setAttributeValue(StoreReqAppItemVO.NACCCUSTORNUM,
          vo.getNaccustornum());
      view.setAttributeValue(StoreReqAppItemVO.NACCUMBUYREQNUM,
          vo.getNaccpraynum());
      view.setAttributeValue(StoreReqAppItemVO.NACCUMMINUSNUM,
          vo.getNaccumminusnum());
    }
  }
}
