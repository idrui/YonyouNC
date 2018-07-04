package nc.pubimpl.pu.m25.arap.f1;

import java.util.Map;

import nc.bs.businessevent.BdUpdateEvent;
import nc.bs.businessevent.IBusinessEvent;
import nc.bs.businessevent.IBusinessListener;
import nc.bs.businessevent.IEventType;
import nc.bs.ml.NCLangResOnserver;
import nc.vo.arap.payable.AggPayableBillVO;
import nc.vo.arap.payable.PayableBillItemVO;
import nc.vo.arap.pub.BillEnumCollection.FromSystem;
import nc.vo.pu.pub.util.AggVOUtil;
import nc.vo.pub.AggregatedValueObject;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import org.apache.commons.lang.ArrayUtils;

/**
 * 供应商应付单修改时采购监听处理
 * 
 * @since 6.0
 * @version 2011-8-8 下午04:07:45
 * @author 田锋涛
 */

public class PayablebillUpdateListener implements IBusinessListener {

  @Override
  public void doAction(IBusinessEvent event) throws BusinessException {
    try {
      if (IEventType.TYPE_UPDATE_AFTER.equals(event.getEventType())) {
        BdUpdateEvent e = (BdUpdateEvent) event;
        AggregatedValueObject[] oldVos =
            (AggregatedValueObject[]) e.getOldObject();
        AggregatedValueObject[] newVos =
            (AggregatedValueObject[]) e.getNewObject();
        if (ArrayUtils.isEmpty(oldVos) || ArrayUtils.isEmpty(newVos)) {
          return;
        }
        AggPayableBillVO newVo = (AggPayableBillVO) newVos[0];
        AggPayableBillVO oldVo = (AggPayableBillVO) oldVos[0];
        // 不是来源于采购，不处理
        if (!FromSystem.PO.VALUE.equals(newVo.getHeadVO().getSrc_syscode())) {
          return;
        }
        // 是否增行或者删行
        this.checkAddOrDelRows(oldVo, newVo);
        // 字段编辑控制
        this.checkUpdateFields(oldVo, newVo);

      }
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
  }

  /**
   * @param oldVo
   * @param newVo
   */
  private void checkAddOrDelRows(AggPayableBillVO oldVo, AggPayableBillVO newVo) {
    // 应付单复制等vo状态及主键等无法判断是否增行或者删行，用以下方法判断
    // 1.表体数据不一致
    if (oldVo.getBodyVOs().length != newVo.getBodyVOs().length) {
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
          .getNCLangRes().getStrByID("4004050_0", "04004050-0037")/*
                                                                   * @res
                                                                   * "来源采购系统的应付单不不允许增行或者删行！"
                                                                   */);
    }
    Map<String, PayableBillItemVO> oldPayItemVOMap =
        AggVOUtil.createItemMap(new AggPayableBillVO[] {
          oldVo
        });
    // 2.旧vo与新vo表体主键不一致
    for (PayableBillItemVO item : newVo.getBodyVOs()) {
      if (oldPayItemVOMap.get(item.getPk_payableitem()) == null) {
        ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
            .getNCLangRes().getStrByID("4004050_0", "04004050-0037")/*
                                                                     * @res
                                                                     * "来源采购系统的应付单不不允许增行或者删行！"
                                                                     */);
      }
    }
  }

  private void checkUpdateFields(AggPayableBillVO oldVo, AggPayableBillVO newVo) {
    Map<String, PayableBillItemVO> oldPayItemVOMap =
        AggVOUtil.createItemMap(new AggPayableBillVO[] {
          oldVo
        });

    for (PayableBillItemVO item : newVo.getBodyVOs()) {
      for (int i = 0; i < this.getCheckFields().length; i++) {
        Object newValue = item.getAttributeValue(this.getCheckFields()[i][0]);
        Object oldValue =
            oldPayItemVOMap.get(item.getPk_payableitem()).getAttributeValue(
                this.getCheckFields()[i][0]);
        if (newValue == null && oldValue == null) {
          continue;
        }
        if (newValue != null && !newValue.equals(oldValue) || oldValue != null
            && !oldValue.equals(newValue)) {
          ExceptionUtils.wrappBusinessException(NCLangResOnserver.getInstance()
              .getStrByID("4004050_0", "04004050-0107", null, new String[] {
                this.getCheckFields()[i][1]
              })/* 来源采购系统的应付单不不允许修改【{0}】 */);
        }
      }
    }
  }

  private String[][] getCheckFields() {

    String[][] checkFields =
        new String[][] {
          {
            PayableBillItemVO.PK_CURRTYPE,
            nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("common",
                "UC000-0001755")
          /* @res "币种" */
          },// 币种
          {
            PayableBillItemVO.RATE,
            nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("common",
                "UC000-0002837")
          /* @res "汇率" */
          },// 汇率
          {
            PayableBillItemVO.PRICE,
            nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("common",
                "UC000-0000741")
          /* @res "单价" */
          },// 单价
          {
            PayableBillItemVO.TAXPRICE,
            nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("common",
                "UC000-0001160")
          /* @res "含税单价" */
          },// 含税单价
          {
            PayableBillItemVO.SUPPLIER,
            nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("common",
                "UC000-0000275")
          /* @res "供应商" */
          },// 供应商
          {
            PayableBillItemVO.MATERIAL,
            nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004050_0",
                "04004050-0038")
          /* @res "物料" */
          },// 物料
          {
            PayableBillItemVO.TAXRATE,
            nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("common",
                "UC000-0003078")
          /* @res "税率" */
          },// 税率
          {
            PayableBillItemVO.QUANTITY_CR,
            nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("common",
                "UC000-0003856")
          /* @res "贷方数量" */
          },// 贷方数量
          {
            PayableBillItemVO.LOCAL_TAX_CR,
            nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004050_0",
                "04004050-0039")
          /* @res "组织本币税金(贷方)" */
          },// 组织本币税金(贷方)
          {
            PayableBillItemVO.NOTAX_CR,
            nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004050_0",
                "04004050-0040")
          /* @res "贷方原币无税金额" */
          },// 贷方原币无税金额
          // {
          // PayableBillItemVO.TAX_CR,
          // nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004050_0",
          // "04004050-0041")
          // /* @res "贷方原币税金" */
          // },// 贷方原币税金
          {
            PayableBillItemVO.LOCAL_MONEY_CR,
            nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004050_0",
                "04004050-0042")
          /* @res "组织本币金额(贷方)" */
          }
          // 组织本币金额(贷方)
          ,
          {
            "taxcodeid",
            nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004030_2",
                "2400403002-0187")
          /*
           * @res
           * "税码"
           */
          }
        };

    return checkFields;
  }
}
