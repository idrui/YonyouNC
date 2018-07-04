package nc.bs.pu.m21.pf.function;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import nc.impl.pubapp.pattern.data.vo.VOQuery;
import nc.itf.scmpub.reference.uap.bd.material.MaterialPubService;
import nc.vo.bd.material.MaterialVO;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.scmf.pu.cenpurule.entity.CenPuRuleItemVO;
import nc.vo.scmf.pu.cenpurule.entity.ControlMode;

public abstract class CenPurRuleFunction {
  private int ctrltype;

  public int getCtrltype() {
    return this.ctrltype;
  }

  public void setCtrltype(int ctrltype) {
    this.ctrltype = ctrltype;
  }

  private Map<String, MaterialVO> getMaterialMap(OrderVO ordervo) {
    Set<String> set = new HashSet<String>();
    for (OrderItemVO itemVO : ordervo.getBVO()) {
      set.add(itemVO.getPk_material());
    }
    String[] mats = set.toArray(new String[set.size()]);
    return MaterialPubService.queryMaterialBaseInfo(mats, new String[] {
      MaterialVO.PK_MATERIAL, MaterialVO.FEE, MaterialVO.DISCOUNTFLAG
    });
  }

  protected UFBoolean handWhenNeedNotEequalsAct(Integer fneedpurtype, String err)
      throws BusinessException {
    return UFBoolean.FALSE;
  }

  protected UFBoolean meetCenPuRule(OrderVO ordervo) throws BusinessException {
    // TODO 提供接口
    Set<String> cenpubset = new HashSet<String>();
    for (OrderItemVO itemVO : ordervo.getBVO()) {
      if (itemVO.getPk_cenpurule_b() != null) {
        cenpubset.add(itemVO.getPk_cenpurule_b());
      }
    }
    if (cenpubset.size() == 0) {
      return UFBoolean.TRUE;
    }

    VOQuery<CenPuRuleItemVO> query =
        new VOQuery<CenPuRuleItemVO>(CenPuRuleItemVO.class);
    String[] bids = cenpubset.toArray(new String[cenpubset.size()]);
    CenPuRuleItemVO[] ruleitemvos = query.query(bids);
    Map<String, Integer> rulemap = new HashMap<String, Integer>();
    for (CenPuRuleItemVO rulevo : ruleitemvos) {
      rulemap.put(rulevo.getPk_cenpurule_b(), rulevo.getFctrlmode());
    }
    Map<String, MaterialVO> matmap = this.getMaterialMap(ordervo);
    //用两个StringBuilder分别记录控制类型为强制和提示的不满足表体记录信息。
    //如果强制的StringBuilder不为空，则给出强制提示；
    //如果强制的StringBuilder为空，而提示的StringBuilder不为空，则给出强制提示。
    StringBuilder forceBuilder = new StringBuilder();
    StringBuilder hintBuilder = new StringBuilder();
    Integer fctrlmode = null;
    for (OrderItemVO itemVO : ordervo.getBVO()) {
      String pk_material = itemVO.getPk_material();
      MaterialVO matvo = matmap.get(pk_material);
      if (matvo == null || UFBoolean.TRUE.equals(matvo.getFee())
          || UFBoolean.TRUE.equals(matvo.getDiscountflag())) {
        continue;
      }
      Integer fneedpurtype = itemVO.getFneedpurtype();
      if (null == fneedpurtype) {
        continue;
      }
      if (this.getCtrltype() != fneedpurtype.intValue()) {
        continue;
      }
      if (!fneedpurtype.equals(itemVO.getFactpurtype())) {
        fctrlmode = rulemap.get(itemVO.getPk_cenpurule_b());
        if(fctrlmode != null && ControlMode.FORCE.toInt() == fctrlmode.intValue()){
        	forceBuilder.append("[" + itemVO.getCrowno() + "]");
        }else if(fctrlmode != null && ControlMode.HINT.toInt() == fctrlmode.intValue()){
        	hintBuilder.append("[" + itemVO.getCrowno() + "]");
        }
      }
    }
    
    if(forceBuilder.length() > 0){
    	return this.handWhenNeedNotEequalsAct(ControlMode.FORCE.toInt(), forceBuilder.toString());
    }else if(hintBuilder.length() > 0){
    	return this.handWhenNeedNotEequalsAct(ControlMode.HINT.toInt(), hintBuilder.toString());
    }
    return UFBoolean.TRUE;
  }
}
