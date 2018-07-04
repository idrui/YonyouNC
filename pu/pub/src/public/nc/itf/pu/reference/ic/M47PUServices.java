package nc.itf.pu.reference.ic;

import java.util.List;

import nc.impl.pubapp.pattern.data.vo.VOQuery;
import nc.impl.pubapp.pattern.database.IDExQueryBuilder;
import nc.vo.ic.general.define.MetaNameConst;
import nc.vo.ic.m47.entity.SubcontInBodyVO;
import nc.vo.pu.pub.constant.PUTempTable;

import org.apache.commons.lang.ArrayUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>委外入库单47提供给采购的服务适配
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author zhaoyha
 * @time 2010-2-3 上午08:44:05
 */
public class M47PUServices {

  /**
   * 判断上游单据是否已经生成过委外入<br>
   * 一般用于上游(到货等)的取消审批，只用累计入库数量不能判断，因入库单上只录入应收时不回写上游
   * 
   * @param srcHIDList
   * @return
   */
  public static boolean hasFromSource(List<String> srcHIDList) {
    VOQuery<SubcontInBodyVO> vq =
        new VOQuery<SubcontInBodyVO>(SubcontInBodyVO.class, new String[] {
          MetaNameConst.CGENERALBID
        });
    String where =
        new IDExQueryBuilder(PUTempTable.tmp_po_pub_01.name()).buildSQL(" and "
            + MetaNameConst.CSOURCEBILLHID,
            srcHIDList.toArray(new String[srcHIDList.size()]));
    SubcontInBodyVO[] vos = vq.query(where, null);
    if (ArrayUtils.isEmpty(vos)) {
      return false;
    }
    return true;
  }
}
