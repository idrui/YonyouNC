package nc.bs.pu.m23.maintain.rule.update;

import nc.bs.framework.common.NCLocator;
import nc.impl.pubapp.pattern.rule.ICompareRule;
import nc.itf.ic.batch.IBatchGenerateForPU;
import nc.itf.pu.reference.ic.BatchServices;
import nc.vo.ic.batch.BatchcodeRelation;
import nc.vo.pu.m23.entity.ArriveHeaderVO;
import nc.vo.pu.m23.entity.ArriveItemVO;
import nc.vo.pu.m23.entity.ArriveVO;
import nc.vo.pu.m23.rule.BatchCodeFieldMap;
import nc.vo.pu.m23.rule.BatchCodeItemAdapter;
import nc.vo.pu.m23.utils.ArrivePublicUtil;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.log.TimeLog;

import org.apache.commons.lang.ArrayUtils;

/**
 * 
 * @description
 * 修改保存时，更新批次档案
 * @scene
 * 到货单修改
 * @param
 * 无
 *
 * @since 6.3
 * @version 2010-5-27 上午10:23:55
 * @author hanbin
 */

public class UpdateBatchCodeBeforeRule implements ICompareRule<ArriveVO> {

  @Override
  public void process(ArriveVO[] vos, ArriveVO[] originVOs) {
    // 需求确定到货单上不需添加生产日期失效日期校验
    // ArrivePublicUtil.checkQuality(vos);
    for (ArriveVO vo : vos) {
      for (ArriveItemVO item : vo.getBVO()) {
        item.setAttributeValue(ArriveHeaderVO.VBILLCODE, vo.getHVO()
            .getVbillcode());
      }
    }

    ArriveVO[] batchVOs = ArrivePublicUtil.getBatchCodeArriveVO(vos);
    ArriveVO[] orgBatchVOs = ArrivePublicUtil.getBatchCodeArriveVO(originVOs);
    if (ArrayUtils.isEmpty(batchVOs) && ArrayUtils.isEmpty(orgBatchVOs)) {
      return;
    }

    // 调用库存所提供的接口
    this.invokeICService(batchVOs, orgBatchVOs);
  }

  private BatchcodeRelation getBatchcodeRelation() {
    BatchcodeRelation relation = new BatchcodeRelation();
    relation.setBizflowKeyPos(ArriveHeaderVO.PK_BUSITYPE,
        BatchcodeRelation.POS_IN_HEAD);
    relation.setCbizoptorKeyPos(ArriveHeaderVO.PK_PUPSNDOC,
        BatchcodeRelation.POS_IN_HEAD);
    relation.setCdeptKeyPos(ArriveHeaderVO.PK_DEPT,
        BatchcodeRelation.POS_IN_HEAD);
    relation.setCpurorgKeyPos(ArriveHeaderVO.PK_ORG,
        BatchcodeRelation.POS_IN_HEAD);
    relation.setDbilldateKeyPos(ArriveHeaderVO.DBILLDATE,
        BatchcodeRelation.POS_IN_HEAD);
    relation.setDplanarrvdateKeyPos(ArriveItemVO.DPLANRECEIVEDATE,
        BatchcodeRelation.POS_IN_BODY);
    relation.setMaterialKeyPos(ArriveItemVO.PK_MATERIAL,
        BatchcodeRelation.POS_IN_BODY);
    relation.setPkgroup(ArriveHeaderVO.PK_GROUP);
    relation.setPkorg(ArriveHeaderVO.PK_ORG);
    relation.setProviderKeyPos(ArriveHeaderVO.PK_SUPPLIER,
        BatchcodeRelation.POS_IN_HEAD);
    relation
        .setStoorgKeyPos(ArriveItemVO.PK_ORG, BatchcodeRelation.POS_IN_BODY);
    relation.setTranspmodeKeyPos(ArriveHeaderVO.PK_TRANSPORTTYPE,
        BatchcodeRelation.POS_IN_HEAD);
    relation.setWarehouseKeyPos(ArriveItemVO.PK_RECEIVESTORE,
        BatchcodeRelation.POS_IN_BODY);
    return relation;
  }

  private ArriveVO[] invokeICService(ArriveVO[] vos, ArriveVO[] originVOs) {
    BatchcodeRelation relation = this.getBatchcodeRelation();
    BatchCodeFieldMap filedMap = new BatchCodeFieldMap();
    IBatchGenerateForPU service =
        NCLocator.getInstance().lookup(IBatchGenerateForPU.class);
    try {
      TimeLog.info(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
          "4004040_0", "04004040-0087")/* @res "调用库存提供的修改保存前批次号接口" */);
      BatchServices.generateBatchcode(vos, relation);
      service.batchProcessBeforeSaveForUpdate(vos, originVOs,
          BatchCodeItemAdapter.class, filedMap);
      TimeLog.info(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
          "4004040_0", "04004040-0088")/* @res "成功调用库存提供的修改保存前批次号接口" */);
    }
    catch (Exception ex) {
      ExceptionUtils.wrappException(ex);
    }
    return vos;
  }

}
