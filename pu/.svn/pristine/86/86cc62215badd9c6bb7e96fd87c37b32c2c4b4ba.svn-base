/**
 * $文件说明$
 * 
 * @author GGR
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-7-3 下午05:40:45
 */
package nc.bs.pu.position.maintain.rule;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nc.impl.pubapp.pattern.data.vo.VOInsert;
import nc.impl.pubapp.pattern.database.DataAccessUtils;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.itf.scmpub.reference.uap.bd.material.MaterialBaseClassPubService;
import nc.itf.scmpub.reference.uap.bd.material.MaterialPuClassPubService;
import nc.vo.bd.material.marbasclass.MarBasClassVO;
import nc.vo.bd.material.marpuclass.MarPuClassVO;
import nc.vo.jcom.lang.StringUtil;
import nc.vo.pu.position.entity.PositionHeaderVO;
import nc.vo.pu.position.entity.PositionItemVO;
import nc.vo.pu.position.entity.PositionTVO;
import nc.vo.pu.position.entity.PositionVO;
import nc.vo.pu.position.enumeration.EnumUseMove;
import nc.vo.pu.pub.constant.PUParaValue.po85;
import nc.vo.pu.pub.util.PUSysParamUtil;
import nc.vo.pub.VOStatus;
import nc.vo.pubapp.pattern.pub.SqlBuilder;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>岗位设置附表插入规则
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author GGR
 * @time 2010-7-3 下午05:40:45
 */
public class InsertTRule implements IRule<PositionVO> {

  @Override
  public void process(PositionVO[] vos) {
    for (PositionVO vo : vos) {
      int positiontype = vo.getHVO().getFpositiontype().intValue();

      String classkey = PositionItemVO.PK_MARBASCLASS;
      if (positiontype == PositionHeaderVO.purchasePosition) {
        //
        if (po85.pu_marclass == PUSysParamUtil.getPO85(vo.getHVO()
            .getPk_group())) {
          classkey = PositionItemVO.PK_MARPUCLASS;
        }
      }
      // 附表插入
      this.insert(vo, classkey);
    }
  }

  private void delOld(PositionVO vo) {
    String pk_position = vo.getHVO().getPk_position();

    SqlBuilder sql = new SqlBuilder();
    sql.append(" delete from po_position_t where ");
    sql.append(PositionTVO.PK_POSITION, pk_position);
    new DataAccessUtils().update(sql.toString());
  }

  private PositionTVO[] getArrFromMap(HashMap<String, PositionTVO> inVOs) {
    ArrayList<PositionTVO> tvos = new ArrayList<PositionTVO>();
    for (Map.Entry<String, PositionTVO> entryvo : inVOs.entrySet()) {
      tvos.add(entryvo.getValue());
    }

    return tvos.toArray(new PositionTVO[tvos.size()]);
  }

  private PositionTVO[] getInsert(PositionVO vo, String classkey) {
    PositionItemVO[] items = this.sort(vo);

    String cemployeeid = vo.getHVO().getCemployeeid();
    String code = vo.getHVO().getCode();
    String pk_group = vo.getHVO().getPk_group();
    String pk_org = vo.getHVO().getPk_org();
    String pk_position = vo.getHVO().getPk_position();
    Integer fpositiontype = vo.getHVO().getFpositiontype();
    HashMap<String, PositionTVO> inVOs = new HashMap<String, PositionTVO>();
    for (PositionItemVO item : items) {
      if (item.getStatus() == VOStatus.DELETED) {
        continue;
      }

      if (!StringUtil.isEmptyWithTrim(item.getMaterial_code())) {
        continue;
      }

      String classcode = item.getMarbasclass_code();

      if (PositionItemVO.PK_MARBASCLASS.equals(classkey)) {
        List<MarBasClassVO> classVOs =
            MaterialBaseClassPubService.queryFinalKey(classcode);

        if (EnumUseMove.USE == item.getFflag().intValue()) {
          for (MarBasClassVO classVO : classVOs) {
            PositionTVO inVO = new PositionTVO();
            inVO.setMarclasscode(classVO.getCode());
            inVO.setCemployeeid(cemployeeid);
            inVO.setFpositiontype(fpositiontype);
            inVO.setCode(code);
            inVO.setPk_group(pk_group);
            inVO.setPk_marclass(classVO.getPk_marbasclass());
            inVO.setPk_org(pk_org);
            inVO.setPk_position(pk_position);
            inVO.setPk_position_b(item.getPk_position_b());

            inVOs.put(classVO.getPk_marbasclass(), inVO);
          }
        }
        else {
          for (MarBasClassVO classVO : classVOs) {
            String pk_marclass = classVO.getPk_marbasclass();
            if (inVOs.containsKey(pk_marclass)) {
              inVOs.remove(pk_marclass);
            }
          }
        }
      }
      else {
        List<MarPuClassVO> classVOs =
            MaterialPuClassPubService.queryFinalKey(classcode);

        if (EnumUseMove.USE == item.getFflag().intValue()) {
          for (MarPuClassVO classVO : classVOs) {
            PositionTVO inVO = new PositionTVO();
            // by luojw 不清楚为什么之前存内码，导致采购分类保存时提示的是内码
            // 但是前台界面显示的是code，导致用户分不清是哪个物料采购分类
            // 所以改成存储code
            inVO.setMarclasscode(classVO.getCode()); // 采购分类时使用内码
            inVO.setCemployeeid(cemployeeid);
            inVO.setFpositiontype(fpositiontype);
            inVO.setCode(code);
            inVO.setPk_group(pk_group);
            inVO.setPk_marclass(classVO.getPk_marpuclass());
            inVO.setPk_org(pk_org);
            inVO.setPk_position(pk_position);
            inVO.setPk_position_b(item.getPk_position_b());

            inVOs.put(classVO.getPk_marpuclass(), inVO);
          }
        }
        else {
          for (MarPuClassVO classVO : classVOs) {
            String pk_marclass = classVO.getPk_marpuclass();
            if (inVOs.containsKey(pk_marclass)) {
              inVOs.remove(pk_marclass);
            }
          }
        }
      }

    }

    return this.getArrFromMap(inVOs);
  }

  private void insert(PositionVO vo, String classkey) {

    // 删除原来的附表数据
    this.delOld(vo);
    // 取得插入附表数据
    PositionTVO[] invos = this.getInsert(vo, classkey);

    if (null == invos || invos.length == 0) {
      return;
    }

    new VOInsert<PositionTVO>().insert(invos);
  }

  private PositionItemVO[] sort(PositionVO vo) {
    PositionItemVO[] items = vo.getBVO();
    // 不需要重新设置内码，内码已经是正确的了。
    Arrays.sort(items, new Comparator<PositionItemVO>() {
      @Override
      public int compare(PositionItemVO o1, PositionItemVO o2) {
        // 当是采购分类时，这里保存采购分类的内码（采购分类编码不能作为排序规则）
        if (o1.getMarbasclass_code() == null) {
          return 0;
        }
        if (o2.getMarbasclass_code() == null) {
          return 0;
        }
        // 需要从小到大排才行
        return o1.getMarbasclass_code().compareTo(o2.getMarbasclass_code());

      }
    });
    return items;
  }
}
