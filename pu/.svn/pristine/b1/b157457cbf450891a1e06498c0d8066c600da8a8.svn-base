/**
 * $文件说明$
 * 
 * @author lixyp
 * @version 6.1
 * @see
 * @since 6.1
 * @time 2011-12-27 上午09:54:26
 */
package nc.vo.pu.m20.rule;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.mmpps.mps0202.PoTypeEnum;
import nc.vo.pu.m20.entity.PraybillItemVO;
import nc.vo.pu.m20.entity.PraybillVO;
import nc.vo.pu.m20.enumeration.EnumPraySource;
import nc.vo.pu.pub.enumeration.POEnumBillStatus;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pubapp.AppContext;
import nc.vo.scmpub.res.billtype.MMBillType;
import nc.vo.scmpub.res.billtype.SOBillType;
import nc.vo.scmpub.res.billtype.TOBillType;

import org.apache.commons.lang.ArrayUtils;

/**
 * @description
 *              请购单设置默认值
 * @scene
 *        计划订单推式保存请购单、生产订单推式保存请购单
 * @param 无
 * @since 6.3
 * @version 2014-10-21 下午1:50:41
 * @author yanxm5
 */
public class SetDefaultValueRule implements IRule<PraybillVO> {
  private String workOrder = "1001Z900000000002215";

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
    this.setDefaultValue(vos);
  }

  private void setDefaultValue(PraybillVO[] vos) {
    UFDate prayDate = AppContext.getInstance().getBusiDate();

    for (PraybillVO vo : vos) {
      if (null != vo.getBVO() && vo.getBVO().length > 0) {
        // 根据来源单据类型设置请购来源
        String sourceType = vo.getBVO()[0].getCsourcetypecode();
        // vo对照
        if (this.workOrder.equals(sourceType)) {
          vo.getHVO().setFpraysource(EnumPraySource.M4B36.toInteger());
        }
        // 采购订单
        else if (SOBillType.Order.getCode().equals(sourceType)) {
          vo.getHVO().setFpraysource(EnumPraySource.SO.toInteger());
        }
        // 调拨订单
        else if (TOBillType.TransOrder.getCode().equals(sourceType)) {
          vo.getHVO().setFpraysource(EnumPraySource.M5X.toInteger());
        }
        // 生产订单
        else if (MMBillType.ProduceOrder.getCode().equals(sourceType)) {
          vo.getHVO().setFpraysource(EnumPraySource.MO.toInteger());
        }
      }
      // 根据计划类型设置请购来源
      if (null != vo.getHVO().getFpotype()) {
        // MPS主生产计划
        if (PoTypeEnum.MPS.equals(vo.getHVO().getFpotype())) {
          vo.getHVO().setFpraysource(EnumPraySource.MPS.toInteger());
        }
        // INVP库存计划
        else if (PoTypeEnum.INVP.equals(vo.getHVO().getFpotype())) {
          vo.getHVO().setFpraysource(EnumPraySource.ICPO.toInteger());
        }
        // MRP物料需求计划
        else if (PoTypeEnum.MRP.equals(vo.getHVO().getFpotype())) {
          vo.getHVO().setFpraysource(EnumPraySource.MPO.toInteger());
        }
      }

      // 请购日期
      if (null == vo.getHVO().getDbilldate()) {
        vo.getHVO().setDbilldate(prayDate);
      }

      // 单据状态
      if (null == vo.getHVO().getFbillstatus()) {
        vo.getHVO().setFbillstatus(POEnumBillStatus.FREE.toInteger());
      }

      // 版本号
      if (null == vo.getHVO().getNversion()) {
        vo.getHVO().setNversion(Integer.valueOf(1));
      }

      // 最新版本
      vo.getHVO().setBislatest(UFBoolean.TRUE);

      PraybillItemVO[] items = vo.getBVO();
      if (ArrayUtils.isEmpty(items)) {
        continue;
      }

      for (PraybillItemVO item : items) {
        // 采购组织可编辑
        if (null == item.getBcanpurchaseorgedit()) {
          item.setBcanpurchaseorgedit(UFBoolean.TRUE);
        }
        // 发布到电子商务
        if (null == item.getBpublishtoec()) {
          item.setBpublishtoec(UFBoolean.FALSE);
        }
        // 行关闭
        if (null == item.getBrowclose()) {
          item.setBrowclose(UFBoolean.FALSE);
        }
        // if (null == item.getDbilldate()) {
        // item.setDbilldate(prayDate);
        // }
      }
    }
  }
}
