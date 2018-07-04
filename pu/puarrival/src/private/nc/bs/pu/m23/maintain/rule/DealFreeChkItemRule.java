package nc.bs.pu.m23.maintain.rule;

import java.util.Map;

import nc.impl.pu.m23.utils.ArrivePrivateUtil;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pu.m23.entity.ArriveItemVO;
import nc.vo.pu.m23.entity.ArriveVO;
import nc.vo.pu.pub.util.AggVOUtil;
import nc.vo.pub.AggregatedValueObject;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.pub.MathTool;

/**
 * 
 * @description
 * 新增保存、修改保存都会用到此规则，本类主要完成以下功能：
 * 如果表体行是免检物料，处理其报检数量、合格数量、不合格数量
 * @scene
 * 到货单新增修改
 * @param
 * 无
 *
 * @since 6.3
 * @version 2010-1-14 下午03:42:46
 * @author hanbin
 */

public class DealFreeChkItemRule implements IRule<ArriveVO> {

  @Override
  public void process(ArriveVO[] voArray) {

    for (ArriveVO aggVO : voArray) {
      // 处理表体行的免检物料
      this.dealFreeChkItem(aggVO);
    }
  }

  /**
   * 方法功能描述：如果表体行是免检物料，处理其报检数量、合格数量、不合格数量
   * <p>
   * <b>参数说明</b>
   * 
   * @param aggVO
   *          <p>
   * @since 6.0
   * @author hanbin
   * @throws BusinessException
   * @time 2010-1-14 下午04:03:20
   */
  private void dealFreeChkItem(ArriveVO aggVO) {
    ArriveItemVO[] itemVOArray = aggVO.getBVO();
    // 供应商、库存组织
    String supplierPK = aggVO.getHVO().getPk_supplier();
    String stockOrgPK = aggVO.getHVO().getPk_org();

    // 根据物料PK+库存组织+供应商,判断物料是否免检
    AggregatedValueObject[] vos = new AggregatedValueObject[1];
    vos[0] = aggVO;
    String[] mrlPKS =
        (String[]) AggVOUtil.getDistinctItemFieldArray(vos,
            ArriveItemVO.PK_MATERIAL, String.class);
    Map<String, UFBoolean> mrlToIsFreeChkMap =
        ArrivePrivateUtil.isMaterialFreeChk(mrlPKS, supplierPK, stockOrgPK);

    // 到货主数量、累计报检数量、合格数量、不合格数量
    UFDouble nnum = null;
    UFDouble nelignum = null;
    UFDouble nnotelignum = null;
    UFDouble checknum = null;
    for (int i = 0; i < itemVOArray.length; i++) {
      nnum = MathTool.nvl(itemVOArray[i].getNnum());
      checknum = MathTool.nvl(itemVOArray[i].getNaccumchecknum());
      nelignum = MathTool.nvl(itemVOArray[i].getNelignum());
      nnotelignum = MathTool.nvl(itemVOArray[i].getNnotelignum());

      // 如果物料是免检，则：合格数量 = 到货数量,不合格数量 = 0
      if (mrlToIsFreeChkMap != null
          && mrlToIsFreeChkMap.get(itemVOArray[i].getPk_material())
              .booleanValue()) {
        nelignum = nnum;
        nnotelignum = new UFDouble(0);
      }
      else {
        // 累计报检数量 = 合格数量 + 不合格数量
        checknum = nelignum.add(nnotelignum);
      }
      itemVOArray[i].setNaccumchecknum(checknum);
      itemVOArray[i].setNelignum(nelignum);
      itemVOArray[i].setNnotelignum(nnotelignum);
    }
  }
}
