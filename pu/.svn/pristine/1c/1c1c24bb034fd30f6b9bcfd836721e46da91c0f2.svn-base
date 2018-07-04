package nc.bs.pu.m20.rewrite.ct;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nc.impl.pubapp.pattern.data.vo.VOQuery;
import nc.impl.pubapp.pattern.data.vo.VOUpdateTS;
import nc.impl.pubapp.pattern.database.DataAccessUtils;
import nc.impl.pubapp.pattern.database.IDExQueryBuilder;
import nc.itf.scmpub.reference.uap.group.SysInitGroupQuery;
import nc.vo.pu.m20.entity.PraybillItemVO;
import nc.vo.pu.m20.entity.writeback.GenNumWriteBackVO;
import nc.vo.pu.m20.enumeration.EnumOperate;
import nc.vo.pu.pub.constant.PUTempTable;
import nc.vo.pub.JavaType;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.SqlBuilder;

import org.apache.commons.lang.ArrayUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>采购合同回写请购单BP
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author GGR
 * @time 2010-5-18 上午10:00:49
 */
public class ReWrite20ForCTBP {

  /**
   * 方法功能描述：回写生成合同次数或者回写请购单表体"已生成总括订单"字段。
   * <p>
   * <b>参数说明</b>
   * 
   * @param vos
   *          <p>
   * @since 6.0
   * @author gaogr
   * @time 2010-8-18 上午10:53:42
   */
  public void backWriteNum(GenNumWriteBackVO[] vos) {

    if (!SysInitGroupQuery.isCTEnabled()) {
      return;
    }

    if (ArrayUtils.isEmpty(vos)) {
      return;
    }
    // 用于合同回写请购单
    List<List<Object>> adddata = new ArrayList<List<Object>>();
    List<List<Object>> deldata = new ArrayList<List<Object>>();
    ArrayList<PraybillItemVO> items = new ArrayList<PraybillItemVO>();
    // 用于总括订单回写请购单
    ArrayList<String> saDelPks = new ArrayList<String>();
    // Map<总括订单行pk,总括订单行号>
    Map<String, String> pk_num_map = new HashMap<String, String>();
    for (GenNumWriteBackVO vo : vos) {
      PraybillItemVO item = new PraybillItemVO();
      item.setPk_praybill_b(vo.getPk_praybill_b());
      // 是否是总括订单
      if (vo.getIsSaOrder().booleanValue()) {
        if (EnumOperate.ADD == vo.getOperateFlag()) {
          pk_num_map.put(vo.getPk_praybill_b(), vo.getCrowno());
        }
        if (EnumOperate.DELETE == vo.getOperateFlag()) {
          saDelPks.add(item.getPk_praybill_b());
        }
        items.add(item);
      }
      // 普通合同
      else {
        List<Object> rowData = new ArrayList<Object>();
        rowData.add(vo.getPk_praybill_b());
        if (EnumOperate.ADD == vo.getOperateFlag()) {
          items.add(item);
          adddata.add(rowData);
        }
        if (EnumOperate.DELETE == vo.getOperateFlag()) {
          items.add(item);
          deldata.add(rowData);
        }
      }
    }

    if (pk_num_map.size() > 0) {
      this.writeBackisSaOrderAdd(pk_num_map);
      this.upTs(items);
    }
    else if (saDelPks.size() > 0) {
      this.writeBackisSaOrderDel(saDelPks);
      this.upTs(items);
    }
    else {
      this.upAdd(adddata);

      this.upDel(deldata);

      this.upTs(items);
    }

  }

  /**
   * 回写请购单表体"已生成总括订单"字段
   * 
   * @param pk_num_map 总括订单回写请购单,保存新增或修改的请购单行pks
   */
  private void writeBackisSaOrderAdd(Map<String, String> pk_num_map) {
    // 查询出请购单表体原始的VO
    String[] bidArray =
        pk_num_map.keySet().toArray(new String[pk_num_map.keySet().size()]);
    VOQuery<PraybillItemVO> voQuery =
        new VOQuery<PraybillItemVO>(PraybillItemVO.class);
    PraybillItemVO[] oldVOArray = voQuery.query(bidArray);
    // 校验是来源请购单行否已经生成总括订单
    this.checkHasSaOrder(pk_num_map, oldVOArray);
    // 校验来源请购单行是否已经生成其他下游单据
    this.checkHasNextOrder(pk_num_map, oldVOArray);
    SqlBuilder sql = new SqlBuilder();
    sql.append("UPDATE po_praybill_b  set ");
    sql.append("bisgensaorder", UFBoolean.TRUE);
    sql.append(" where ");
    // 临时表
    IDExQueryBuilder builder =
        new IDExQueryBuilder(PUTempTable.tmp_po_20_03.name());
    sql.append(builder.buildSQL("pk_praybill_b", bidArray));
    new DataAccessUtils().update(sql.toString());
  }

  /**
   * 回写请购单表体"已生成总括订单"字段
   * 
   * @param saDelPks 总括订单回写请购单,保存删除的请购单行pks
   */
  private void writeBackisSaOrderDel(ArrayList<String> saDelPks) {
    SqlBuilder sql = new SqlBuilder();
    sql.append("UPDATE po_praybill_b set ");
    sql.append("bisgensaorder", UFBoolean.FALSE);
    sql.append(" where ");
    // 临时表
    IDExQueryBuilder builder =
        new IDExQueryBuilder(PUTempTable.tmp_po_20_14.name());
    sql.append(builder.buildSQL("pk_praybill_b",
        saDelPks.toArray(new String[saDelPks.size()])));
    new DataAccessUtils().update(sql.toString());
  }

  /**
   * 判断来源请购单行“已生成总括订单”是否为是，如果为是，则报错：XX行物料来源请购单已生成过总括订单，不允许再次生成
   * 
   * @param pk_num_map Map<总括订单行pk,总括订单行行号>
   * @param oldVOArray 总括订单回写请购单行pks对应于数据库中的原始PraybillItemVO
   */
  private void checkHasSaOrder(Map<String, String> pk_num_map,
      PraybillItemVO[] oldVOArray) {
    // 记录来源请购单行“已生成总括订单”为是的总括订单行行号
    ArrayList<String> rowNums = new ArrayList<String>();
    for (PraybillItemVO bvo : oldVOArray) {
      // 已经生成总括订单
      if (bvo.getBisgensaorder().booleanValue()) {
        rowNums.add(pk_num_map.get(bvo.getPk_praybill_b()));
      }
    }
    if (rowNums.size() > 0) {
      StringBuilder sbMsg = new StringBuilder();
      for (String str : rowNums) {
        sbMsg.append("[");
        sbMsg.append(str);
        sbMsg.append("]");
      }
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
          .getNCLangRes().getStrByID("4004020_0", "04004020-0104", null,
              new String[] {
                sbMsg.toString()
              })/* @res "{0}行物料来源请购单已生成过总括订单，不允许再次生成。\n" */);
    }
  }

  /**
   * 判断来源请购单行是否已经生成其他下游单据，如果已经生成，保存时报错：XX行物料来源请购单已生成下游单据
   * 
   * @param pk_num_map Map<总括订单行pk,总括订单行行号>
   * @param oldVOArray 总括订单回写请购单行pks对应于数据库中的原始PraybillItemVO
   */
  private void checkHasNextOrder(Map<String, String> pk_num_map,
      PraybillItemVO[] oldVOArray) {
    // 记录来源请购单行已经生成其他下游单据 的总括订单行行号
    ArrayList<String> rowNums = new ArrayList<String>();
    for (PraybillItemVO bvo : oldVOArray) {
      // 已经生成价格审批单,询报价单,采购订单或者委外订单,发布到电子商务
      if (this.isIntUPZero(bvo.getNpriceauditbill())
          || this.isIntUPZero(bvo.getNquotebill())
          || this.isUFDoubleUPZero(bvo.getNaccumulatenum())
          || bvo.getBpublishtoec().booleanValue()) {
        rowNums.add(pk_num_map.get(bvo.getPk_praybill_b()));
      }
    }
    if (rowNums.size() > 0) {
      StringBuilder sbMsg = new StringBuilder();
      for (String str : rowNums) {
        sbMsg.append("[");
        sbMsg.append(str);
        sbMsg.append("]");
      }
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
          .getNCLangRes().getStrByID("4004020_0", "04004020-0105", null,
              new String[] {
                sbMsg.toString()
              })/* @res "{0}行物料来源请购单已生成下游单据。\n" */);
    }
  }

  private boolean isIntUPZero(Integer i) {
    if (null != i && i.intValue() > 0) {
      return true;
    }
    return false;
  }

  private boolean isUFDoubleUPZero(UFDouble d) {
    if (null != d && d.doubleValue() > 0d) {
      return true;
    }
    return false;
  }

  private void upAdd(List<List<Object>> adddata) {
    if (adddata.size() > 0) {
      String addsql =
          "UPDATE po_praybill_b set ngenct = coalesce(ngenct,0) + 1  WHERE pk_praybill_b = ? ";
      new DataAccessUtils().update(addsql, new JavaType[] {
        JavaType.String
      }, adddata);
    }
  }

  private void upDel(List<List<Object>> deldata) {
    if (deldata.size() > 0) {
      String delsql =
          "UPDATE po_praybill_b set ngenct = coalesce(ngenct,0) - 1  WHERE pk_praybill_b = ? ";
      new DataAccessUtils().update(delsql, new JavaType[] {
        JavaType.String
      }, deldata);
    }
  }

  private void upTs(ArrayList<PraybillItemVO> items) {
    if (items.size() == 0) {
      return;
    }

    new VOUpdateTS<PraybillItemVO>().update(items
        .toArray(new PraybillItemVO[items.size()]));
  }
}
