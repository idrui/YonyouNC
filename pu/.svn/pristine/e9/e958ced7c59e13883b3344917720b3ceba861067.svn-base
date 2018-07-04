package nc.bs.pu.m23.maintain.rule;

import java.util.List;
import java.util.Map;

import nc.bs.pu.m23.writeback.pu.m23.Writeback23For23BP;
import nc.impl.pubapp.bill.rewrite.BillRewriter;
import nc.impl.pubapp.bill.rewrite.ItemKeyMapping;
import nc.impl.pubapp.bill.rewrite.RewritePara;
import nc.impl.pubapp.pattern.rule.ICompareRule;
import nc.vo.pu.m23.entity.ArriveHeaderVO;
import nc.vo.pu.m23.entity.ArriveItemVO;
import nc.vo.pu.m23.entity.ArriveVO;
import nc.vo.pu.pub.enumeration.EnumOperate;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.scmpub.res.billtype.POBillType;
import nc.vo.scmpub.res.billtype.SCBillType;

import org.apache.commons.lang.ArrayUtils;

/**
 * 
 * @description
 * 基于到货单退货的退货单回写来源到货单规则
 * 此处不同于典型的回写，因为是回写同类型的单据，所以此处直接处理，没有再提接口
 * @scene
 * 
 * @param
 * 无
 *
 * @since 6.3
 * @version 2012-8-10 下午03:34:27
 * @author lixyp
 */


public class WriteBackFor23Rule implements ICompareRule<ArriveVO> {

  private EnumOperate operateType = null;

  public WriteBackFor23Rule(EnumOperate operateType) {
    this.operateType = operateType;
  }

  @Override
  public void process(ArriveVO[] vos, ArriveVO[] originVos) {
    try {
      if (ArrayUtils.isEmpty(vos)) {
        return;
      }

      // 所有订单（包括基于订单的退货单）的保存都会走这段逻辑，但在BP类中，判断了如果没有来源到货单，则直接返回，所以对其它场景没有影响。
      Map<String, List<RewritePara>> rewriteParas =
          this.getRewriterParas(vos, originVos);
      List<RewritePara> poPara = rewriteParas.get(POBillType.Order.getCode());
      List<RewritePara> scPara = rewriteParas.get(SCBillType.Order.getCode());
      if (poPara != null) {
        new Writeback23For23BP().writeBack(poPara);
      }
      else if (scPara != null) {
        new Writeback23For23BP().writeBack(scPara);
      }

      // 清空来源时间戳，基于到货单退货的场景，因为来源TS对的是到货单的TS，不清空的话，后续回写订单时会报并发。
      this.clearSourceTs(vos);
    }
    catch (Exception e) {
      ExceptionUtils.wrappBusinessException(e.getMessage());
    }
  }

  /**
   * 清空来源TS，只针对基于到货单退货的场景。
   * 
   * @param vos
   */
  private void clearSourceTs(ArriveVO[] vos) {
    for (ArriveVO vo : vos) {
      for (ArriveItemVO itemVo : vo.getBVO()) {
        if (itemVo.getCsourcearrivebid() != null) {
          itemVo.setSourcets(null);
          itemVo.setSourcebts(null);
        }
      }
    }
  }

  /**
   * 使用回写工具，获取回写参数。
   * 
   * @param vos 新VO
   * @param originVos 原始VO
   * @return
   */
  private Map<String, List<RewritePara>> getRewriterParas(ArriveVO[] vos,
      ArriveVO[] originVos) {
    ItemKeyMapping keymapping = new ItemKeyMapping();
    // 来源单据类型，虽然基于到货单退货，但仍然记录的是订单，这里比较特殊。
    keymapping.setVsrctypeKey(ArriveItemVO.CSOURCETYPECODE);
    // 来源到货单
    keymapping.setCsrcidKey(ArriveItemVO.CSOURCEARRIVEID);
    // 来源到货单明细
    keymapping.setCsrcbidKey(ArriveItemVO.CSOURCEARRIVEBID);
    // 到货主数量
    keymapping.setNnumKey(ArriveItemVO.NNUM);
    keymapping.setSrcTSKey(ArriveItemVO.SOURCETS);
    keymapping.setSrcbTSKey(ArriveItemVO.SOURCEBTS);

    BillRewriter tool = new BillRewriter(keymapping);
    tool.addSRCHeadClazz(POBillType.Arrive.getCode(), ArriveHeaderVO.class);
    tool.addSRCItemClazz(POBillType.Arrive.getCode(), ArriveItemVO.class);

    if (EnumOperate.ADD.equals(this.operateType)) {
      return tool.splitForInsert(vos);
    }
    else if (EnumOperate.DELETE.equals(this.operateType)) {
      return tool.splitForDelete(vos);
    }
    else {
      return tool.splitForUpdate(vos, originVos);
    }
  }
}
