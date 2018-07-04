package nc.bs.pu.m23.writeback.pu.m23;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nc.bs.framework.common.NCLocator;
import nc.impl.pubapp.bill.rewrite.RewritePara;
import nc.impl.pubapp.pattern.data.vo.VOUpdate;
import nc.impl.pubapp.pattern.pub.LockOperator;
import nc.pubitf.pu.m23.pubquery.IArrivePubQuery;
import nc.vo.pu.m23.entity.ArriveItemVO;
import nc.vo.pu.m23.entity.ArriveVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MathTool;

import org.apache.commons.lang.ArrayUtils;

/**
 * 基于到货单退货的回写规则。用于回写到货单累计退货主数量。
 * 
 * @since 6.0
 * @version 2012-8-11 上午09:23:59
 * @author lixyp
 */
public class Writeback23For23BP {

  private List<RewritePara> rewriteParas = null;

  /**
   * 回写规则对外公开的接口方法。
   * 
   * @param paras 回写参数
   */
  public void writeBack(List<RewritePara> paras) {
    try {
      if (paras == null || paras.isEmpty()) {
        return;
      }
      this.rewriteParas = paras;

      // 根据来源到货单表体ID查询原到货单表体VO，并更新数量。
      ArriveVO[] vos = this.queryOriginVos();
      if (ArrayUtils.isEmpty(vos)) {
        return;
      }

      ArriveItemVO[] itemVos = this.getUpdatedItemVos(vos);

      // 更新数据库。
      VOUpdate<ArriveItemVO> bo = new VOUpdate<ArriveItemVO>();
      bo.update(itemVos, new String[] {
        ArriveItemVO.NACCUMBACKNUM
      });
    }
    catch (Exception e) {
      ExceptionUtils.wrappBusinessException(e.getMessage());
    }
  }

  private void checkTs(Map<String, UFDateTime> tsChkMap, ArriveVO[] vos) {
    UFDateTime time = null;
    for (ArriveVO vo : vos) {
      time = tsChkMap.get(vo.getHVO().getPk_arriveorder());
      if (time != null && !time.equals(vo.getHVO().getTs())) {
        ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
            .getNCLangRes().getStrByID("4004040_0", "04004040-0206")/*
                                                                     * @res
                                                                     * "出现并发，请重新操作"
                                                                     */);
      }
      for (ArriveItemVO itemVo : vo.getBVO()) {
        time = tsChkMap.get(itemVo.getPk_arriveorder_b());
        if (time != null && !time.equals(itemVo.getTs())) {
          ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
              .getNCLangRes().getStrByID("4004040_0", "04004040-0206")/*
                                                                       * @res
                                                                       * "出现并发，请重新操作"
                                                                       */);
        }
      }
    }
  }

  /**
   * 获取表体VO数组，同时更新表体的值。
   * 
   * @param vos 来源到货单VO数组
   * @param paras 回写参数
   * @return 到货单表体VO数组
   */
  private ArriveItemVO[] getUpdatedItemVos(ArriveVO[] vos) {
    List<ArriveItemVO> itemVos = new ArrayList<ArriveItemVO>();
    for (ArriveVO vo : vos) {
      for (ArriveItemVO itemVo : vo.getBVO()) {
        for (RewritePara rewritePara : this.rewriteParas) {
          if (itemVo.getPk_arriveorder_b().equals(rewritePara.getCsrcbid())) {
            UFDouble wbNum = rewritePara.getNnum();
            // 因为退货数量是负数，所以在这里取一下反。
            wbNum = MathTool.oppose(wbNum);

            UFDouble naccumBackNum =
                MathTool.add(itemVo.getNaccumbacknum(), wbNum);
            if (MathTool.lessThan(naccumBackNum, UFDouble.ZERO_DBL)) {
              String message =
                  nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
                      "4004040_0", "04004040-0195")/* @res "累计退货数量已超出到货单原有数量！" */;
              ExceptionUtils.wrappBusinessException(message);
            }

            itemVo.setNaccumbacknum(naccumBackNum);
            itemVos.add(itemVo);
          }
        }
      }
    }

    return itemVos.toArray(new ArriveItemVO[itemVos.size()]);
  }

  /**
   * 根据回写参数中的来源单据ID，调用到货单查询接口，查询来源到货单。
   * 
   * @return 来源到货单
   * @throws BusinessException
   */
  private ArriveVO[] queryOriginVos() {
    try {
      Map<String, UFDateTime> tsChkMap = new HashMap<String, UFDateTime>();
      List<String> bidList = new ArrayList<String>();
      for (RewritePara rewritePara : this.rewriteParas) {
        tsChkMap.put(rewritePara.getCsrcid(), rewritePara.getSrcTS());

        if (rewritePara.getCsrcbid() != null) {
          tsChkMap.put(rewritePara.getCsrcbid(), rewritePara.getSrcbTS());
          bidList.add(rewritePara.getCsrcbid());
        }
      }
      if (bidList.isEmpty()) {
        return null;
      }

      String[] bids = bidList.toArray(new String[bidList.size()]);
      LockOperator lock = new LockOperator();
      String message =
          nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004040_0",
              "04004040-0205")/* @res "对来源到货单加锁失败" */;
      lock.lock(bids, message);

      IArrivePubQuery arriveQuery =
          NCLocator.getInstance().lookup(IArrivePubQuery.class);
      ArriveVO[] vos = arriveQuery.queryAggVOByBids(bids);

      this.checkTs(tsChkMap, vos);
      return vos;
    }
    catch (Exception e) {
      ExceptionUtils.wrappBusinessException(e.getMessage());
    }
    return null;
  }

}
