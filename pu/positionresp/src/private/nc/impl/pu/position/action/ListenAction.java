package nc.impl.pu.position.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nc.impl.pubapp.pattern.data.vo.VOInsert;
import nc.impl.pubapp.pattern.data.vo.VOQuery;
import nc.impl.pubapp.pattern.database.DataAccessUtils;
import nc.itf.scmpub.reference.uap.bd.material.MaterialBaseClassPubService;
import nc.itf.scmpub.reference.uap.bd.material.MaterialPuClassPubService;
import nc.vo.bd.material.marbasclass.MarBasClassVO;
import nc.vo.bd.material.marpuclass.MarPuClassVO;
import nc.vo.pu.position.entity.PositionHeaderVO;
import nc.vo.pu.position.entity.PositionItemVO;
import nc.vo.pu.position.entity.PositionTVO;
import nc.vo.pu.position.enumeration.EnumUseMove;
import nc.vo.pubapp.pattern.pub.SqlBuilder;

/**
 * 当物料基本档案发生变化时跟新t表数据
 * 
 * @since 6.0
 * @version 2011-5-19 下午05:20:23
 * @author liugxa
 */
public class ListenAction {

  public void updates() {
    // 删除原来的附表数据
    this.delOld();
    // 取得插入附表数据
    PositionTVO[] invos = this.getInsert();
    if (null == invos || invos.length == 0) {
      return;
    }
    new VOInsert<PositionTVO>().insert(invos);
  }

  private void delOld() {
    SqlBuilder sql = new SqlBuilder();
    sql.append(" delete from po_position_t ");
    new DataAccessUtils().update(sql.toString());
  }

  private PositionTVO[] getArrFromMap(HashMap<String, PositionTVO> inVOs) {
    ArrayList<PositionTVO> tvos = new ArrayList<PositionTVO>();
    for (Map.Entry<String, PositionTVO> entryvo : inVOs.entrySet()) {
      tvos.add(entryvo.getValue());
    }

    return tvos.toArray(new PositionTVO[tvos.size()]);
  }

  private PositionTVO[] getInsert() {
    Map<PositionItemVO, PositionHeaderVO> map = this.sort();
    if (null == map) {
      return null;
    }

    HashMap<String, PositionTVO> inVOs = new HashMap<String, PositionTVO>();
    for (Map.Entry<PositionItemVO, PositionHeaderVO> mapEty : map.entrySet()) {
      PositionHeaderVO vo = mapEty.getValue();
      PositionItemVO item = mapEty.getKey();
      String cemployeeid = vo.getCemployeeid();
      String code = vo.getCode();
      String pk_group = vo.getPk_group();
      String pk_org = vo.getPk_org();
      String pk_position = vo.getPk_position();
      Integer fpositiontype = vo.getFpositiontype();
      String classcode = item.getMarbasclass_code();

      if (classcode != null) {
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
            inVO.setMarclasscode(classVO.getInnercode()); // 采购分类时使用内码
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

  private Map<PositionItemVO, PositionHeaderVO> sort() {
    DataAccessUtils utils = new DataAccessUtils();
    StringBuilder sbr = new StringBuilder();
    sbr.append("SELECT pp.pk_position,ppb.pk_position_b FROM po_position_b ppb");
    sbr.append(" INNER JOIN po_position pp ON");
    sbr.append(" pp.pk_position = ppb.pk_position WHERE ");
    sbr.append(" ppb.dr = 0 ");
    sbr.append(" and (ppb.pk_marbasclass !='~'  OR ppb.pk_marpuclass!=null)");
    String[][] pks = utils.query(sbr.toString()).toTwoDimensionStringArray();
    if (pks == null || pks.length == 0) {
      return null;
    }
    String[] bids = new String[pks.length];
    String[] hids = new String[pks.length];
    for (int i = 0; i < pks.length; i++) {
      bids[i] = pks[i][1];
      hids[i] = pks[i][0];
    }

    PositionItemVO[] items =
        new VOQuery<PositionItemVO>(PositionItemVO.class).query(bids);
    PositionHeaderVO[] heads =
        new VOQuery<PositionHeaderVO>(PositionHeaderVO.class).query(hids);

    if (items == null) {
      return null;
    }
    if (items[0].getPk_marpuclass() != null) {
      for (PositionItemVO itemVO : items) {
        List<MarPuClassVO> classVOs =
            MaterialPuClassPubService.queryFinalKey(itemVO
                .getMarbasclass_code() == null ? itemVO.getMarpuclass_code()
                : itemVO.getMarbasclass_code());
        itemVO.setMarbasclass_code(classVOs.size() == 0 ? itemVO
            .getMarpuclass_code() : classVOs.get(0).getInnercode());
      }
    }
    Map<PositionItemVO, PositionHeaderVO> map =
        new HashMap<PositionItemVO, PositionHeaderVO>();
    for (PositionItemVO vo : items) {
      for (PositionHeaderVO hvo : heads) {
        if (vo.getPk_position().equals(hvo.getPk_position())) {
          map.put(vo, hvo);
        }
      }
    }
    return map;
  }
}
