package nc.bs.pu.est.rule;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import nc.bs.framework.common.NCLocator;
import nc.impl.pubapp.env.BSContext;
import nc.itf.pu.costfactor.ICostFactorQueryService;
import nc.vo.pu.costfactor.entity.CostfactorViewVO;
import nc.vo.pu.est.entity.EstVO;
import nc.vo.pu.est.entity.FeeEstVO;
import nc.vo.pu.est.entity.GoodsEstVO;
import nc.vo.pu.est.rule.FeeEstVatValue;
import nc.vo.pu.est.util.EstVODefualtValueUtil;
import nc.vo.pu.pub.util.AggVOHelper;
import nc.vo.pu.pub.util.CirVOUtil;
import nc.vo.pu.pub.util.IKeyValue;
import nc.vo.pu.pub.util.ListUtil;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.AssertUtils;
import nc.vo.pubapp.pattern.pub.Constructor;
import nc.vo.pubapp.pattern.pub.ListToArrayTool;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.ArrayUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>暂估时，向表体（费用暂估）增加默认行（成本要素中定义）
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author zhaoyha
 * @time 2010-8-12 下午03:05:01
 */
public class EstFeeItemProcRule {
  private Class<? extends FeeEstVO> feeClazz;

  public EstFeeItemProcRule(Class<? extends FeeEstVO> feeClazz) {
    this.feeClazz = feeClazz;
    AssertUtils.assertValue(null != feeClazz, nc.vo.ml.NCLangRes4VoTransl
        .getNCLangRes().getStrByID("4004060_0", "04004060-0063")/*
                                                                 * @res
                                                                 * "必须指定费用暂估VO类类型！"
                                                                 */);
  }

  public void process(EstVO[] vos) {
    if (ArrayUtils.isEmpty(vos)) {
      return;
    }
    String pk_org = vos[0].getParentVO().getPk_financeorg();
    CostfactorViewVO[] costFees = this.getEstFeeItems(pk_org);
    if (ArrayUtils.isEmpty(costFees)) {
      return;
    }
    for (EstVO vo : vos) {
      this.addFeeItems(vo, costFees);
    }
    this.setVATInfo(vos);
  }

  private void addFeeItems(EstVO vo, CostfactorViewVO[] fees) {
    FeeEstVO[] items = vo.getChildrenVO();
    List<FeeEstVO> newItems = new ArrayList<FeeEstVO>();
    Map<String, ArrayList<FeeEstVO>> itemMap =
        ArrayUtils.isEmpty(items) ? null : CirVOUtil.getFieldVOList(items,
            FeeEstVO.PK_SRCFEEMATERIAL);
    for (int i = 0; i < fees.length; i++) {
      String pk_material = fees[i].getPk_srcmaterial();
      FeeEstVO newitem = null;
      if (null != itemMap && itemMap.containsKey(pk_material)) {
        newitem = itemMap.get(pk_material).get(0);
        itemMap.remove(pk_material);
      }
      else {
        newitem = Constructor.construct(this.feeClazz);
        this.setNewFeeItemDefaultValue(newitem, vo.getParentVO(), fees[i]);
      }
      newItems.add(newitem);
    }
    if (null != itemMap && !MapUtils.isEmpty(itemMap)) {
      for (Entry<String, ArrayList<FeeEstVO>> entry : itemMap.entrySet()) {
        newItems.add(entry.getValue().get(0));
      }
    }
    if (!ListUtil.isEmpty(newItems)) {
      FeeEstVO[] nitems =
          new ListToArrayTool<FeeEstVO>().convertToArray(newItems);
      vo.setChildrenVO(nitems);
    }
  }

  private CostfactorViewVO[] getEstFeeItems(String pk_org) {
    ICostFactorQueryService service =
        NCLocator.getInstance().lookup(ICostFactorQueryService.class);
    CostfactorViewVO[] feeitems = null;
    try {
      feeitems = service.queryEstCostItems(pk_org);
    }
    catch (BusinessException e) {
      // 日志异常
      ExceptionUtils.wrappException(e);
    }
    return feeitems;
  }

  private void setNewFeeItemDefaultValue(FeeEstVO newItem, GoodsEstVO head,
      CostfactorViewVO fee) {
    // 调用工具设置暂估费用VO默认值
    EstVODefualtValueUtil.setFeeEstDefValue(newItem, head, BSContext
        .getInstance().getUserID());
    String pk_material = fee.getPk_material();
    String pk_srcmaterial = fee.getPk_srcmaterial();
    newItem.setPk_feematerial(pk_material);// 物料
    newItem.setPk_srcfeematerial(pk_srcmaterial);

    // wuxla v61 不再设置税率，根据税码在后规则中设置
    // newItem.setNesttaxrate(fee.getNmartaxrate());// 税率
  }

  protected void setVATInfo(EstVO[] vos) {
    List<EstVO> list = new ArrayList<EstVO>();
    for (EstVO vo : vos) {
      FeeEstVO[] feevos = vo.getChildrenVO();
      if (ArrayUtils.isEmpty(feevos)) {
        continue;
      }

      List<FeeEstVO> feeList = new ArrayList<FeeEstVO>();
      for (FeeEstVO feevo : feevos) {
        if (feevo.getPrimaryKey() == null) {
          feeList.add(feevo);
        }
      }
      if (feeList.size() > 0) {
        FeeEstVO[] newFeevos = feeList.toArray(new FeeEstVO[feeList.size()]);
        EstVO newVO = new EstVO();
        newVO.setParentVO(vo.getParentVO());
        newVO.setChildrenVO(newFeevos);
        list.add(newVO);
      }
    }

    if (list.size() == 0) {
      return;
    }

    EstVO[] newvos = list.toArray(new EstVO[list.size()]);
    // wuxla v61 查询时设置vat信息
    IKeyValue[] keyValues = new IKeyValue[newvos.length];
    for (int i = 0; i < newvos.length; ++i) {
      keyValues[i] = new AggVOHelper<EstVO>(newvos[i]);
    }
    FeeEstVatValue vatvalue = new FeeEstVatValue();
    vatvalue.setVatValue(keyValues, newvos[0].getParentVO().getPk_financeorg());
  }

}
