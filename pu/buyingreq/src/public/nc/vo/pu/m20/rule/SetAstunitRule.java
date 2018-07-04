/**
 * $文件说明$
 * 
 * @author lixyp
 * @version 6.1
 * @see
 * @since 6.1
 * @time 2011-12-28 下午01:30:54
 */
package nc.vo.pu.m20.rule;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.itf.pu.reference.uap.bd.material.MaterialPuService;
import nc.itf.scmpub.reference.uap.bd.material.MaterialPubService;
import nc.vo.bd.material.MaterialConvertVO;
import nc.vo.jcom.lang.StringUtil;
import nc.vo.pu.m20.entity.PraybillItemVO;
import nc.vo.pu.m20.entity.PraybillVO;
import nc.vo.pu.pub.util.PUSysParamUtil;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.scale.ScaleUtils;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;

/**
 * @description
 *              请购单根据参数PO08设置单位，同时设置换算率和固定换算率
 * @scene
 *        计划订单推式保存请购单、生产订单推式保存请购单
 * @param 无
 * @since 6.3
 * @version 2014-10-21 下午2:08:57
 * @author yanxm5
 */
public class SetAstunitRule implements IRule<PraybillVO> {

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
    this.setAstunit(vos);
  }

  /**
   * 方法功能描述：根据参数PO08设置单位。
   * <p>
   * <b>参数说明</b>
   * 
   * @param vos <p>
   * @since 6.1
   * @author lixyp
   * @time 2011-12-28 下午01:32:21
   */
  public void setAstunit(PraybillVO[] vos) {
    Map<String, String> astunit = this.getPk(vos);

    List<PraybillItemVO> itemList = new ArrayList<PraybillItemVO>();
    List<PraybillItemVO> changeList = new ArrayList<PraybillItemVO>();
    HashMap<String, UFBoolean> po08map = new HashMap<String, UFBoolean>();
    Set<String> mateiralset = new HashSet<String>();
    Set<String> astunitset = new HashSet<String>();
    for (PraybillVO vo : vos) {
      PraybillItemVO[] items = vo.getBVO();
      if (ArrayUtils.isEmpty(items)) {
        continue;
      }
      for (int i = 0, len = items.length; i < len; i++) {
        itemList.add(items[i]);
        String pk_org = items[i].getPk_purchaseorg();
        String pk_material = items[i].getPk_material();
        mateiralset.add(pk_material);
        if (null != pk_org && null != pk_material) {
          UFBoolean po08 = UFBoolean.FALSE;
          if (po08map.containsKey(pk_org)) {
            po08 = po08map.get(pk_org);
          }
          else {
            po08 = PUSysParamUtil.getPO08(pk_org);
            po08map.put(pk_org, po08);
          }
          if (UFBoolean.TRUE.equals(po08)) {
            // 物料默认采购单位 为空 不处理
            String oldCastunitid = items[i].getCastunitid();
            String purDefault = astunit.get(pk_material);
            if (!StringUtils.isBlank(purDefault)
                && !purDefault.equals(oldCastunitid)) {
              items[i].setCastunitid(purDefault);
              changeList.add(items[i]);
              astunitset.add(purDefault);
            }
            else {
              astunitset.add(oldCastunitid);
            }
          }
          else {
            astunitset.add(items[i].getCastunitid());
          }
        }
        if(items[i].getCastunitid()==null){
        	items[i].setCastunitid(items[i].getCunitid());
        }
      }
    }

    Map<String, MaterialConvertVO> convertvoMap =
        new HashMap<String, MaterialConvertVO>();
    if (mateiralset.size() > 0 && astunitset.size() > 0) {
      String[] pk_materials =
          mateiralset.toArray(new String[mateiralset.size()]);
      String[] castunits = astunitset.toArray(new String[astunitset.size()]);
      convertvoMap =
          MaterialPuService.queryMaterialConvertVOByMaterialAndMeasdoc(
              pk_materials, castunits);
    }

    ScaleUtils su = new ScaleUtils(vos[0].getHVO().getPk_group());
    for (PraybillItemVO itemvo : changeList) {
      String pk_material = itemvo.getPk_material();
      String castunitid = itemvo.getCastunitid();
      String cunitid = itemvo.getCunitid();
      String changeRate = null;
      if (cunitid != null && cunitid.equals(castunitid)) {
        changeRate = "1/1";
        itemvo.setVchangerate(su.adjustHslScale(changeRate));
      }
      else {
        MaterialConvertVO convertvo =
            convertvoMap.get(pk_material + castunitid);
        if (convertvo != null) {
          changeRate = convertvo.getMeasrate();
          itemvo.setVchangerate(su.adjustHslScale(changeRate));
        }
      }
    }

    // 设置固定换率和换算率
    for (PraybillItemVO itemvo : itemList) {
      String pk_material = itemvo.getPk_material();
      String castunit = itemvo.getCastunitid();
      String cunit = itemvo.getCunitid();
      if (pk_material == null || castunit == null) {
        continue;
      }
      UFBoolean isFixedRate = UFBoolean.FALSE;
      String vchangerate = null;
      if (castunit.equals(cunit)) {
        isFixedRate = UFBoolean.TRUE;
        vchangerate = "1/1";
      }
      else {
        MaterialConvertVO convertvo = convertvoMap.get(pk_material + castunit);
        if (convertvo != null) {
          isFixedRate = convertvo.getFixedflag();
          vchangerate = convertvo.getMeasrate();
        }
      }

      itemvo.setBfixedrate(isFixedRate);

      if (itemvo.getVchangerate() == null) {
        itemvo.setVchangerate(su.adjustHslScale(vchangerate));
      }
    }
  }

  private Map<String, String> getPk(PraybillVO[] vos) {
    ArrayList<String> pk_materials = new ArrayList<String>();
    for (PraybillVO vo : vos) {
      PraybillItemVO[] items = vo.getBVO();
      if (ArrayUtils.isEmpty(items)) {
        continue;
      }

      for (int i = 0, len = items.length; i < len; i++) {
        String pk_material = items[i].getPk_material();
        if (!StringUtil.isEmptyWithTrim(pk_material)) {
          pk_materials.add(pk_material);
        }
      }
    }

    return MaterialPubService.queryPuMeasdocIDByPks(pk_materials
        .toArray(new String[pk_materials.size()]));
  }
}
