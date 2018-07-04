package nc.impl.pu.m23.qc.action.rule;

import java.util.ArrayList;
import java.util.List;

import nc.impl.pubapp.pattern.data.vo.VOOperator;
import nc.impl.pubapp.pattern.database.DataAccessUtils;
import nc.impl.pubapp.pattern.database.IDExQueryBuilder;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.itf.scmpub.reference.uap.group.SysInitGroupQuery;
import nc.vo.pu.m23.entity.ArriveBbVO;
import nc.vo.pu.m23.entity.ArriveItemVO;
import nc.vo.pu.m23.entity.ArriveVO;
import nc.vo.pu.pub.constant.PUTempTable;
import nc.vo.pu.pub.util.AggVOUtil;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.data.IRowSet;
import nc.vo.pubapp.pattern.data.ValueUtils;
import nc.vo.pubapp.pattern.pub.SqlBuilder;
import nc.vo.qc.pub.util.QCSysParamUtil;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;

/**
 * 
 * @description
 * 检查是否重复报检，如果是则删除子子表数据
 * @scene
 * 到货单报检
 * @param
 * 无
 *
 * @since 6.3
 * @version 2010-7-15 下午05:01:20
 * @author hanbin
 */


public class ChkReApplyAndDelChkDetailRule implements IRule<ArriveVO> {

  @Override
  public void process(ArriveVO[] vos) {
    String pk_org = vos[0].getHVO().getPk_org();
    if (!SysInitGroupQuery.isQCEnabled()
        || UFBoolean.FALSE.equals(ValueUtils.getUFBoolean(QCSysParamUtil
            .getINI01(pk_org)))) {
      return;
    }
    // 根据子表主键查询对应的检验结果明细
    String[] bids =
        (String[]) AggVOUtil.getDistinctItemFieldArray(vos,
            ArriveItemVO.PK_ARRIVEORDER_B, String.class);
    String[] hids =
        (String[]) AggVOUtil.getDistinctItemFieldArray(vos,
            ArriveItemVO.PK_ARRIVEORDER, String.class);
    if (ArrayUtils.isEmpty(bids)) {
      return;
    }

    SqlBuilder sql = new SqlBuilder();
    sql.append(" select pk_arriveorder_bb from po_arriveorder_bb");
    sql.append(" where dr = 0 and ");
    IDExQueryBuilder idBuild =
        new IDExQueryBuilder(PUTempTable.tmp_po_23_05.name());
    sql.append(idBuild.buildSQL("pk_arriveorder_b", bids));
    sql.append(" and ");
    sql.append(idBuild.buildSQL("pk_arriveorder", hids));

    DataAccessUtils utils = new DataAccessUtils();
    IRowSet rowset = utils.query(sql.toString());
    List<String> bbIds = new ArrayList<String>();
    while (rowset.next()) {
      String bbid = rowset.getString(0);
      if (StringUtils.isNotEmpty(bbid) && !bbIds.contains(bbid)) {
        bbIds.add(bbid);
      }
    }
    if (bbIds.size() == 0) {
      return;
    }
    VOOperator<ArriveBbVO> util = new VOOperator<ArriveBbVO>();
    String[] bbidArray = bbIds.toArray(new String[0]);
    ArriveBbVO[] details = util.query(ArriveBbVO.class, bbidArray);

    // 删除对应的检验明细
    util.delete(details);
  }
}
