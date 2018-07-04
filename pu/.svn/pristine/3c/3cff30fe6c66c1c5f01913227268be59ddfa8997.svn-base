package nc.impl.pu.m25.pricequery;

import java.util.Map;

import nc.itf.pu.m25.IPriceQuery;
import nc.itf.scmpub.reference.uap.bd.material.MaterialPubService;
import nc.vo.bd.material.fi.MaterialFiVO;
import nc.vo.jcom.lang.StringUtil;
import nc.vo.pu.m25.entity.InvoicePriceQueryVO;

/**
 * 计划价查询，供物料编辑后的计划价查询
 * 
 * @since 6.0
 * @version 2011-7-29 下午02:26:02
 * @author 田锋涛
 */

public class PlanPriceQuery implements IPriceQuery {

  public PlanPriceQuery() {//
  }

  @Override
  public void query(InvoicePriceQueryVO[] para) {
    String fiOrg = this.getFiOrg(para);
    if (null == fiOrg) {
      return;
    }
    String[] mpks = this.getMaterial(para);
    Map<String, MaterialFiVO> mFiVoMap =
        MaterialPubService.getFIInfo(mpks, fiOrg, new String[] {
          MaterialFiVO.PLANPRICE, MaterialFiVO.COSTPRICE
        });
    if (null == mFiVoMap) {
      return;
    }
    // 调用物料的服务，查物料财务组织页签
    for (InvoicePriceQueryVO vo : para) {
      String mpk = vo.getPk_material();
      if (mFiVoMap.containsKey(mpk)) {
        this.setFiPrice(vo, mFiVoMap.get(mpk));
      }
    }
  }

  private String getFiOrg(InvoicePriceQueryVO[] para) {
    for (InvoicePriceQueryVO vo : para) {
      if (!StringUtil.isEmptyWithTrim(vo.getPk_org())) {
        return vo.getPk_org();
      }
    }
    return null;
  }

  private String[] getMaterial(InvoicePriceQueryVO[] para) {
    String[] pks = new String[para.length];
    for (int i = 0; i < pks.length; i++) {
      pks[i] = para[i].getPk_material();
    }
    return pks;
  }

  protected void setFiPrice(InvoicePriceQueryVO paraVo, MaterialFiVO mFiVo) {
    paraVo.setNprice(mFiVo.getPlanprice());
  }

}
