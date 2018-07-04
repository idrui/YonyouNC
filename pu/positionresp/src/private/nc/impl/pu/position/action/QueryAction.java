package nc.impl.pu.position.action;

import java.util.Arrays;
import java.util.Comparator;

import nc.impl.pubapp.pattern.data.bill.BillQuery;
import nc.impl.pubapp.pattern.database.DataAccessUtils;
import nc.vo.jcom.lang.StringUtil;
import nc.vo.pu.position.entity.PositionItemVO;
import nc.vo.pu.position.entity.PositionVO;
import nc.vo.pu.pub.enumeration.PuNodeCode;
import nc.vo.pubapp.pattern.data.IRowSet;

import org.apache.commons.lang.ArrayUtils;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>�ƻ���(�ɹ���)����������ز�ѯ
 * </ul>
 * <p>
 * 
 * @author GGR
 * @time 2009-12-17 ����04:22:10
 * @since 6.0
 */
public class QueryAction {

  /**
   * ��ѯĳ��֯�ƻ��ڻ��߲ɹ�������
   * 
   * @return nc.vo.test.PositioninvHeaderVO[]
   * @param corpPK
   *          java.lang.String
   *          �쳣˵����
   */
  public PositionVO[] queryMain(String nodecode, String where) {
    // �ɹ��ڣ�1 �ƻ��ڣ�0
    int ipositiontype = -1;
    if (PuNodeCode.n40010520.code().equalsIgnoreCase(nodecode)) {
      ipositiontype = 1;
    }
    else if (PuNodeCode.n40010515.code().equalsIgnoreCase(nodecode)) {
      ipositiontype = 0;
    }

    String strWhere = " fpositiontype=" + ipositiontype;

    if (null != where) {
      strWhere += where;
    }
    return this.querty(strWhere);
  }

  private PositionVO[] querty(String where) {
    // �����Ѿ������򷽷�,���ȥ��order by code,sql���ݿ⻷���»��״�
    StringBuilder sql = new StringBuilder();
    sql.append("select distinct pk_position from po_position where dr = 0 and ")
        .append(where);
    DataAccessUtils utils = new DataAccessUtils();
    IRowSet rowset = utils.query(sql.toString());
    String[] cbillids = rowset.toOneDimensionStringArray();

    // ����id��ѯVO
    BillQuery<PositionVO> query = new BillQuery<PositionVO>(PositionVO.class);
    return this.sort(query.query(cbillids));
  }

  private PositionVO[] sort(PositionVO[] vos) {
    if (ArrayUtils.isEmpty(vos)) {
      return vos;
    }

    for (PositionVO vo : vos) {
      PositionItemVO[] items = vo.getBVO();

      Arrays.sort(items, new Comparator<PositionItemVO>() {
        @Override
        public int compare(PositionItemVO o1, PositionItemVO o2) {
          // ���ǲɹ�����ʱ�����ﱣ��ɹ���������루�ɹ�������벻����Ϊ�������
          if (o1.getMarbasclass_code() == null
              && o2.getMarbasclass_code() == null) {
            if (StringUtil.isEmptyWithTrim(o1.getMarbasclass_code())) {
              return -1;
            }
            else if (StringUtil.isEmptyWithTrim(o2.getMarbasclass_code())) {
              return 1;
            }
            return o1.getMarbasclass_code().compareTo(o2.getMarbasclass_code());
          }

          if (StringUtil.isEmptyWithTrim(o1.getMaterial_code())) {
            return -1;
          }
          else if (StringUtil.isEmptyWithTrim(o2.getMaterial_code())) {
            return 1;
          }
          return o1.getMaterial_code().compareTo(o2.getMaterial_code());
        }
      });
    }
    return vos;
  }
}
