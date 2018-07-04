package nc.bs.pu.position.maintain.rule;

import java.util.HashSet;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.jcom.lang.StringUtil;
import nc.vo.pu.position.entity.PositionHeaderVO;
import nc.vo.pu.position.entity.PositionItemVO;
import nc.vo.pu.position.entity.PositionVO;
import nc.vo.pu.pub.constant.PUParaValue.po85;
import nc.vo.pu.pub.util.PUSysParamUtil;
import nc.vo.pub.VOStatus;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * 计划岗(采购岗)物料设置保存的规则类
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>检查岗位编码是否重复
 * </ul>
 * <p>
 * 
 * @author GGR
 * @time 2009-11-20 下午04:51:25
 * @since 6.0
 */
public class CheckItemRule implements IRule<PositionVO> {

  @Override
  public void process(PositionVO[] vos) {
    for (PositionVO positionVO : vos) {
      // 检查表体行唯一性
      this.checkUnique(positionVO);
    }
  }

  private void checkUnique(PositionVO vo) {
    StringBuffer err = new StringBuffer();
    PositionItemVO[] items = vo.getBVO();
    int positiontype = vo.getHVO().getFpositiontype().intValue();

    String classkey = PositionItemVO.PK_MARBASCLASS;
    String classcode = PositionItemVO.MARBASCLASS_CODE;
    if (positiontype == PositionHeaderVO.purchasePosition) {
      if (po85.pu_marclass == PUSysParamUtil.getPO85(vo.getHVO().getPk_group())) {
        classkey = PositionItemVO.PK_MARPUCLASS;
        classcode = PositionItemVO.MARPUCLASS_CODE;
      }
    }

    HashSet<String> keys = new HashSet<String>();
    for (PositionItemVO item : items) {
      if (item.getStatus() == VOStatus.DELETED) {
        continue;
      }
      String pk_srcmaterial = item.getPk_srcmaterial();
      String key = (String) item.getAttributeValue(classkey);
      if (!StringUtil.isEmptyWithTrim(pk_srcmaterial)) {
        key += pk_srcmaterial;
      }

      if (keys.contains(key)) {
        if (StringUtil.isEmptyWithTrim(pk_srcmaterial)) {
          err.append(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
              "4004080_0", "04004080-0007", null, new String[] {
                (String) item.getAttributeValue(classcode)
              })/* @res "物料分类编码:{0}重复，请重新输入！" */);
        }
        else {
          err.append(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
              "4004080_0", "04004080-0008", null, new String[] {
                item.getMaterial_code()
              })/* @res "物料编码:{0}重复，请重新输入！" */);
        }
        err.append("\n");
      }
      else {
        keys.add(key);
      }
    }

    if (err.length() > 0) {
      ExceptionUtils.wrappBusinessException(err.toString());
    }
  }
}
