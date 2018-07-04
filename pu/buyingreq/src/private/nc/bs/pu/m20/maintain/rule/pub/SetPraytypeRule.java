package nc.bs.pu.m20.maintain.rule.pub;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.itf.scmpub.reference.uap.pf.PfBillItfDefUtil;
import nc.itf.scmpub.reference.uap.pf.TransTypeMapping;
import nc.vo.jcom.lang.StringUtil;
import nc.vo.pu.m20.entity.PraybillItemVO;
import nc.vo.pu.m20.entity.PraybillVO;
import nc.vo.scmpub.res.billtype.POBillType;

import org.apache.commons.lang.ArrayUtils;

/**
 * @description
 *              请购单设置默认请购类型
 * @scene
 *        计划订单推式保存请购单、生产订单推式保存请购单
 * @param 无
 * @since 6.3
 * @version 2014-10-21 下午2:04:09
 * @author yanxm5
 */
public class SetPraytypeRule implements IRule<PraybillVO> {

  /**
   * 父类方法重写
   * 
   * @see nc.impl.pubapp.pattern.rule.IRule#process(E[])
   */
  @Override
  public void process(PraybillVO[] vos) {
    if (ArrayUtils.isEmpty(vos)) {
      return;
    }
    this.setPraytype(vos);
  }

  /**
   * 方法功能描述：询请购类型。
   * <p>
   * <b>参数说明</b>
   * 
   * @param vos
   *          <p>
   * @since 6.1
   * @author lixyp
   * @time 2011-12-27 上午10:43:57
   */
  public void setPraytype(PraybillVO[] vos) {
    if (ArrayUtils.isEmpty(vos)) {
      return;
    }
    for (PraybillVO vo : vos) {
      if (StringUtil.isEmptyWithTrim(vo.getHVO().getCtrantypeid())) {

        PraybillItemVO[] items = vo.getBVO();
        if (ArrayUtils.isEmpty(items)) {
          continue;
        }

        TransTypeMapping map = new TransTypeMapping();
        map.setDestBillType(POBillType.PrayBill.getCode());
        map.setSrcBillType(items[0].getCsourcetypecode());
        map.setSrcTransType(items[0].getVsrctrantypecode());

        PfBillItfDefUtil.queryTransTypeMapping(vo.getHVO().getPk_group(), map);
        vo.getHVO().setCtrantypeid(map.getDestTransType());
        vo.getHVO().setVtrantypecode(map.getDestTransTypeCode());
      }
    }
  }
}
