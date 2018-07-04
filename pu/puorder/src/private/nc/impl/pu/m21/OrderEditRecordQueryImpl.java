/**
 * $文件说明$
 * 
 * @author wanghuid
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-10-29 下午05:41:34
 */
package nc.impl.pu.m21;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import nc.impl.pubapp.pattern.data.bill.BillQuery;
import nc.impl.pubapp.pattern.database.DataAccessUtils;
import nc.impl.pubapp.pattern.database.IDExQueryBuilder;
import nc.itf.pu.m21.IOrderEditRecordQuery;
import nc.vo.pu.m21.entity.OrderHeaderVO;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pu.m21.enumeration.EnumActive;
import nc.vo.pu.pub.constant.PUTempTable;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.data.IRowSet;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.SqlBuilder;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wanghuid
 * @time 2010-10-29 下午05:41:34
 */
public class OrderEditRecordQueryImpl implements IOrderEditRecordQuery {

	/**
	 * 父类方法重写
	 * 
	 * @see nc.itf.pu.m21.IOrderEditRecordQuery#queryOrderPrice()
	 */
	@Override
	public OrderVO[] queryOrderPrice(List<String> bvoPkList)
			throws BusinessException {

		try {
			SqlBuilder sqlbd = new SqlBuilder();
			sqlbd
					.append("select distinct po_order.pk_order,po_order.nversion from po_order po_order ");
			sqlbd
					.append(" inner join po_order_b po_order_b on po_order.pk_order = po_order_b.pk_order ");
			sqlbd.append(" and po_order_b.dr = 0 where po_order.");

			sqlbd.append(OrderHeaderVO.BISLATEST, "N");
			sqlbd.append(" and po_order_b.");
			// 临时表
			IDExQueryBuilder builder = new IDExQueryBuilder(
					PUTempTable.tmp_po_21_43.name());
			sqlbd.append(builder.buildSQL(OrderItemVO.PK_SRCORDER_B,
					bvoPkList.toArray(new String[bvoPkList.size()])));
			sqlbd.append(" and po_order_b.");
			sqlbd.append(OrderItemVO.FISACTIVE,
					(Integer) EnumActive.REVISEHISTORY.value());
			sqlbd.append(" and po_order.dr = 0 order by po_order.nversion");

			DataAccessUtils utils = new DataAccessUtils();
			IRowSet rowset = utils.query(sqlbd.toString());
			List<String> ids = new ArrayList<String>();
			while (rowset.next()) {
				ids.add(rowset.getString(0));
			}
			BillQuery<OrderVO> billquery = new BillQuery<OrderVO>(OrderVO.class);
			OrderVO[] ordervo = billquery.query(ids.toArray(new String[ids.size()]));
			return ordervo;
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
		return null;
	}
}
