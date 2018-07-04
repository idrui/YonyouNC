package nc.impl.pu.m23.qc.action.rule;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import nc.bs.ml.NCLangResOnserver;
import nc.impl.pubapp.pattern.rule.IFilterRule;
import nc.itf.scmpub.reference.uap.bd.material.MaterialPubService;
import nc.vo.bd.material.stock.MaterialStockVO;
import nc.vo.pu.m23.entity.ArriveItemVO;
import nc.vo.pu.m23.entity.ArriveVO;
import nc.vo.pu.pub.util.AggVOUtil;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import org.apache.commons.lang.ArrayUtils;

/**
 * 
 * @description
 * 检验时，如果其物料库存信息中的“免检”为是，则过滤该行（可能是多行），不生成报检单
 * @scene
 * 到货单质检
 * @param
 * 无
 *
 * @since 6.3
 * @version 2010-6-1 上午10:05:56
 * @author hanbin
 */

public class FilterFreeChkItemRule implements IFilterRule<ArriveVO> {

  private boolean isCheck = false;

  public FilterFreeChkItemRule(boolean isCheck) {
    this.isCheck = isCheck;
  }

  @Override
  public ArriveVO[] process(ArriveVO[] vos) {
    // 免检存货是否报检,如果是则不用过滤免检物料,否则需要过滤免检物料
    if (this.isCheck) {
      return vos;
    }
    List<ArriveVO> retVOs = new ArrayList<ArriveVO>();
    for (ArriveVO vo : vos) {
      ArriveVO newvo = this.chkOneVOEnable(vo);
      if (newvo == null || ArrayUtils.isEmpty(newvo.getBVO())) {
        continue;
      }
      retVOs.add(newvo);
    }
    return retVOs.toArray(new ArriveVO[retVOs.size()]);
  }

  /*
   * 批量查询物料库存信息中的“免检”属性
   */
  private ArriveVO chkOneVOEnable(ArriveVO vo) {
    // 批量查询物料库存信息中的“免检”属性
    String[] mrls =
        (String[]) AggVOUtil.getDistinctItemFieldArray(new ArriveVO[] {
          vo
        }, ArriveItemVO.PK_MATERIAL, String.class);
    String org = vo.getHVO().getPk_org();// 库存组织
    String[] fields = new String[1];
    fields[0] = MaterialStockVO.CHKFREEFLAG;// 是否免检
    Map<String, MaterialStockVO> mrlMap = null;
    mrlMap = MaterialPubService.queryMaterialStockInfo(mrls, org, fields);
    if (mrlMap == null || mrls.length > mrlMap.size()) {
      String msg =
          nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004040_0",
              "04004040-0137")/* @res "查询物料的库存页签信息时，找不到对应的信息！" */;
      ExceptionUtils.wrappBusinessException(msg);
      return null;
    }
    // 过滤表体行中免检类物料

    List<ArriveItemVO> newItems = new ArrayList<ArriveItemVO>();
    for (ArriveItemVO item : vo.getBVO()) {
      UFBoolean bfree = mrlMap.get(item.getPk_material()).getChkfreeflag();
      if (bfree != null && bfree.booleanValue()) {
        String msg =
            NCLangResOnserver.getInstance().getStrByID("4004040_0",
                "04004040-0173", null, new String[] {
                  item.getCrowno()
                })/* 第{0}行 为免检产品，不允许报检！ */;
        ExceptionUtils.wrappBusinessException(msg);
        // continue;// 如果物料是免检的，此行不允许报检
      }
      newItems.add(item);
    }

    if (newItems.size() == 0) {
      String msg =
          nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004040_0",
              "04004040-0138")/* @res "所有表体行的物料都为免检产品，不允许报检！" */;
      ExceptionUtils.wrappBusinessException(msg);
    }
    // 构造过滤后免检物料行的单据
    ArriveVO newvo = new ArriveVO();
    newvo.setHVO(vo.getHVO());
    newvo.setBVO(newItems.toArray(new ArriveItemVO[newItems.size()]));
    return newvo;
  }
}
