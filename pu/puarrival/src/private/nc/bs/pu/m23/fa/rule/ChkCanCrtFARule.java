package nc.bs.pu.m23.fa.rule;

import java.util.Map;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.itf.scmpub.reference.uap.bd.material.MaterialStockClassPubService;
import nc.itf.scmpub.reference.uap.group.SysInitGroupQuery;
import nc.vo.bd.material.stock.MaterialStockVO;
import nc.vo.pu.m23.entity.ArriveItemVO;
import nc.vo.pu.m23.entity.ArriveVO;
import nc.vo.pu.m23.utils.ArrivePublicUtil;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MathTool;

/**
 * 
 * @description
 * 本类主要完成以下功能：
 * 如果未启用资产管理，不能生成资产卡
 * 来源为委外的到货单，不能生成资产卡
 * @scene
 * 生成资产卡片时
 * @param
 * 无
 *
 * @since 6.3
 * @version 2010-5-7 上午11:26:00
 * @author hanbin
 */


public class ChkCanCrtFARule implements IRule<ArriveVO> {

  @Override
  public void process(ArriveVO[] voArray) {
    // 检查资产是否启用
    this.chkAIMModuleEnable();

    for (ArriveVO aggVO : voArray) {
      // 检查是否来源于委外
      this.chkIsFromSC(aggVO);
      // 检查是否已生成下游单据
      this.chkIsHaveDownBill(aggVO);
    }

    // 检查收货仓库是为资产仓
    // 资产仓属性已经去掉
    // this.chkStoreIsCapital(voArray);
  }

  private void chkAIMModuleEnable() {
    boolean isEnable = false;
    isEnable = SysInitGroupQuery.isAIMEnabled();
    if (!isEnable) {
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
          .getNCLangRes().getStrByID("4004040_0", "04004040-0182")/*
                                                                   * @res
                                                                   * "资产信息管理模块未启用,无法生成设备卡片!"
                                                                   */);
    }
  }

  private void chkIsFromSC(ArriveVO aggVO) {
    boolean isFromSC = ArrivePublicUtil.isArriveFromSC(aggVO);
    if (isFromSC) {
      String msg =
          nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004040_0",
              "04004040-0067")/* @res "不支持来源为委外的到货单生成设备卡片！" */;
      ExceptionUtils.wrappBusinessException(msg);
    }
  }

  private void chkIsHaveDownBill(ArriveVO vo) {
    ArriveItemVO[] items = vo.getBVO();
    for (ArriveItemVO item : items) {
      if (MathTool.nvl(item.getNaccumstorenum()).doubleValue() > 0) {
        String msg =
            nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004040_0",
                "04004040-0068")/* @res "已经生成入库单，不允许再生成设备资产卡片！" */;
        ExceptionUtils.wrappBusinessException(msg);
      }
      if (MathTool.nvl(item.getNaccumreplnum()).doubleValue() > 0) {
        String msg =
            nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004040_0",
                "04004040-0069")/* @res "已经生成补货订单，不允许再生成设备卡片！" */;
        ExceptionUtils.wrappBusinessException(msg);
      }
      if (MathTool.nvl(item.getNaccumletgonum()).doubleValue() > 0) {
        String msg =
            nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004040_0",
                "04004040-0070")/* @res "已经生成紧急放行单，不允许再生成设备卡片！" */;
        ExceptionUtils.wrappBusinessException(msg);
      }
      // modify by liugxa 2011年7月8日11:21:02
      if (MathTool.nvl(item.getNaccumchecknum()).doubleValue() > 0
          && this.getIscheckStock(item)) {
        String msg =
            nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004040_0",
                "04004040-0071")/* @res "已经生成报检单，不允许再生成设备卡片！" */;
        ExceptionUtils.wrappBusinessException(msg);

      }
    }
  }

  // private void chkStoreIsCapital(ArriveVO[] vos) {
  // // <收货仓库主键,仓库VO>
  // Map<String, StordocVO> storePKToStoreVOMap = null;
  //
  // // 收货仓库主键数组
  // String key = ArriveItemVO.PK_RECEIVESTORE;
  // String[] stores =
  // (String[]) AggVOUtil.getDistinctItemFieldArray(vos, key, String.class);
  // try {
  // // 批量查询收货仓库是否资产仓
  // String[] fields = new String[1];
  // fields[0] = StordocVO.ISCAPITALSTOR;
  // StordocVO[] sroreVOArray =
  // StordocPubService.queryStordocInfoByPks(stores, fields);
  // if (!ArrayUtils.isEmpty(sroreVOArray)) {
  // storePKToStoreVOMap = CirVOUtil.createKeyVOMap(sroreVOArray);
  // }
  // }
  // catch (BusinessException ex) {
  // ExceptionUtils.wrappException(ex);
  // }
  // if (storePKToStoreVOMap == null) {
  // return;
  // }
  //
  // StringBuffer errorLins = new StringBuffer();
  // ArriveItemVO[] items = AggVOUtil.getCombinItemVOs(vos);
  // for (int i = 0, len = items.length; i < len; i++) {
  // // 收货仓库为空(收货仓库为空也可以生成资产卡片)
  // // if (StringUtils.isEmpty(items[i].getPk_receivestore())) {
  // // errorLins.append("[" + items[i].getCrowno() + "]");
  // // continue;
  // // }
  // // 收货仓库不是资产仓
  // StordocVO storVO = storePKToStoreVOMap.get(items[i].getPk_receivestore());
  // if (storVO == null) {
  // errorLins.append("[" + items[i].getCrowno() + "]");
  // continue;
  // }
  // if (!storVO.getIscapitalstor().booleanValue()) {
  // errorLins.append("[" + items[i].getCrowno() + "]");
  // continue;
  // }
  // }
  // if (errorLins.length() > 0) {
  // String msg = "行号:" + errorLins.toString() + "不是资产类的仓库不能生产资产卡片!";
  // ExceptionUtils.wrappBusinessException(msg);
  // }
  // }

  /**
   * 检查是否根据检验结果入库
   * 
   * @param item
   */
  private boolean getIscheckStock(ArriveItemVO item) {
    String mar = item.getPk_material();
    String org = item.getPk_org();
    Map<String, MaterialStockVO> map =
        MaterialStockClassPubService.queryMaterialStockInfoByPks(new String[] {
          mar
        }, new String[] {
          org
        }, new String[] {
          MaterialStockVO.ISRETINSTOBYCHK
        });
    UFBoolean ufb = map.get(mar + org).getIsretinstobychk();
    if (ufb == null || !ufb.booleanValue()) {
      return false;
    }
    return true;
  }
}
