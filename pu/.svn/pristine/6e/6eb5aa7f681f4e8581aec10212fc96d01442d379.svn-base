package nc.bs.pu.m23.maintain.rule;

import java.util.Vector;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pu.m23.entity.ArriveItemVO;
import nc.vo.pu.m23.entity.ArriveVO;
import nc.vo.pu.m23.rule.ChkEmptyWhenSave;
import nc.vo.pu.m23.rule.ChkNumSignWhenSave;
import nc.vo.pub.VOStatus;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.scmpub.util.VOFieldLengthChecker;

import org.apache.commons.lang.ArrayUtils;

/**
 * 
 * @description
 * 新增保存、修改保存都会用到此规则，本类主要完成以下功能：
 * 检查自定义项
 * 检查是否存在非资产物料入资产仓
 * @scene
 * 
 * @param
 * 无
 *
 * @since 6.3
 * @version 2010-1-14 下午03:42:46
 * @author hanbin
 */

public class ValiVOBeforSaveRule implements IRule<ArriveVO> {

  @Override
  public void process(ArriveVO[] voArray) {

    ChkNumSignWhenSave chkNum = new ChkNumSignWhenSave();
    ChkEmptyWhenSave chkEmpty = new ChkEmptyWhenSave();
    for (ArriveVO aggVO : voArray) {
      // 检查表体数据是否为空
      this.checkBodyItemEmpty(aggVO);
      // 检查非空项
      chkEmpty.chkEmpty(aggVO);
      // 检查长度
      VOFieldLengthChecker.checkVOFieldsLength(aggVO);
      // 检查数量符号的正负性
      chkNum.chkNumSign(aggVO);
      // 检查是否存在非资产物料入资产仓
      // this.chkInvAndCapitalStore(aggVO);
    }
  }

  /**
   * 方法功能描述：检查表体数据是否为空
   * <p>
   * <b>参数说明</b>
   *
   * @param aggVO
   *          <p>
   * @since 6.0
   * @author hanbin
   * @time 2010-1-14 下午04:03:20
   */
  private void checkBodyItemEmpty(ArriveVO aggVO) {
    if (ArrayUtils.isEmpty(aggVO.getBVO())) {
      String msg = nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004040_0","04004040-0091")/*@res "到货单保存时表体数据不能为空！"*/;
      ExceptionUtils.wrappBusinessException(msg);
    }
    Vector<ArriveItemVO> nodritems = new Vector<ArriveItemVO>();
    for (ArriveItemVO item : aggVO.getBVO()) {
      if (item.getStatus() == VOStatus.DELETED) {
        continue;
      }
      nodritems.add(item);
    }
    if (nodritems.size() == 0) {
      String msg = nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004040_0","04004040-0091")/*@res "到货单保存时表体数据不能为空！"*/;
      ExceptionUtils.wrappBusinessException(msg);
    }
  }

  /**
   * 方法功能描述：检查是否存在非资产物料入资产仓
   * <p>
   * <b>参数说明</b>
   *
   * @param vo
   *          <p>
   * @since 6.0
   * @author hanbin
   * @throws BusinessException
   * @time 2010-1-14 下午04:03:31
   */
  // private void chkInvAndCapitalStore(ArriveVO vo) {
  // // <物料主键,物料VO>、<收货仓库主键,仓库VO>
  // Map<String, MaterialVO> mrlPKToMrlVOMap = this.queryMaterialAsset(vo);
  // // 资产仓属性已经去掉
  // // Map<String, StordocVO> storePKToStoreVOMap =
  // // this.queryStoreIscapital(vo);
  // if (storePKToStoreVOMap == null || mrlPKToMrlVOMap == null) {
  // return;
  // }
  //
  // ArriveItemVO[] itemVOArray = vo.getBVO();
  // StringBuffer errorLins = new StringBuffer();
  // for (int i = 0, len = itemVOArray.length; i < len; i++) {
  // if (itemVOArray[i].getPk_receivestore() == null
  // || storePKToStoreVOMap.get(itemVOArray[i].getPk_receivestore()) == null) {
  // continue;
  // }
  // if (itemVOArray[i].getPk_material() == null
  // || mrlPKToMrlVOMap.get(itemVOArray[i].getPk_material()) == null) {
  // continue;
  // }
  //
  // // 资产仓属性已经去掉
  // // 不允许存在非资产类物料入资产仓,即不允许：收货仓库 = 资产仓，物料 = 非资产物料
  // // if (storePKToStoreVOMap.get(itemVOArray[i].getPk_receivestore())
  // // .getIscapitalstor().booleanValue()) {
  // // if (!mrlPKToMrlVOMap.get(itemVOArray[i].getPk_material()).getAsset()
  // // .booleanValue()) {
  // // errorLins.append("行:" + itemVOArray[i].getCrowno() + "" + "\n");
  // // }
  // // }
  // }
  // if (errorLins.length() > 0) {
  // String msg = errorLins.toString() + "资产仓不能入非资产类存货!";
  // ExceptionUtils.wrappBusinessException(msg);
  // }
  // }

  // private Map<String, MaterialVO> queryMaterialAsset(ArriveVO aggVO) {
  // Map<String, MaterialVO> mrlPKToMrlVOMap = null;
  // // 物料主键数组、收货仓库主键数组
  // AggregatedValueObject[] vos = new AggregatedValueObject[1];
  // vos[0] = aggVO;
  // String[] mrlPKArray =
  // (String[]) AggVOUtil.getDistinctItemFieldArray(vos,
  // ArriveItemVO.PK_MATERIAL, String.class);
  // try {
  // // 批量查询物料是否资产类
  // String[] fields = new String[1];
  // fields[0] = MaterialVO.ASSET;
  // MaterialVO[] mrlVOArray =
  // MaterialPubService.queryMaterialBaseInfoByPks(mrlPKArray, fields);
  // if (!ArrayUtils.isEmpty(mrlVOArray)) {
  // mrlPKToMrlVOMap = CirVOUtil.createKeyVOMap(mrlVOArray);
  // }
  // }
  // catch (BusinessException ex) {
  // ExceptionUtils.wrappException(ex);
  // }
  // return mrlPKToMrlVOMap;
  // }

  // private Map<String, StordocVO> queryStoreIscapital(ArriveVO aggVO) {
  // Map<String, StordocVO> storePKToStoreVOMap = null;
  // // 物料主键数组、收货仓库主键数组
  // AggregatedValueObject[] vos = new AggregatedValueObject[1];
  // vos[0] = aggVO;
  // String[] storePKArray =
  // (String[]) AggVOUtil.getDistinctItemFieldArray(vos,
  // ArriveItemVO.PK_RECEIVESTORE, String.class);
  // try {
  // // 批量查询收货仓库是否资产仓
  // String[] fields = new String[1];
  // fields[0] = StordocVO.ISCAPITALSTOR;
  //
  // StordocVO[] sroreVOArray =
  // StordocPubService.queryStordocInfoByPks(storePKArray, fields);
  // if (!ArrayUtils.isEmpty(sroreVOArray)) {
  // storePKToStoreVOMap = CirVOUtil.createKeyVOMap(sroreVOArray);
  // }
  // }
  // catch (BusinessException ex) {
  // ExceptionUtils.wrappException(ex);
  // }
  // return storePKToStoreVOMap;
  // }
}