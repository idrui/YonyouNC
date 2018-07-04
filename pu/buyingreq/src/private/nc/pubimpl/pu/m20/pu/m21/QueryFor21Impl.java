package nc.pubimpl.pu.m20.pu.m21;

import nc.bs.pu.m20.query.QueryFor21BP;
import nc.pubitf.pu.m20.pu.m21.IQuery20For21;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.pu.m20.entity.PraybillItemVO;
import nc.vo.pu.m20.entity.PraybillVO;
import nc.vo.pu.pub.util.VOSortUtils;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>请购单到采购订单的转单查询实现类
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author duy
 * @time 2010-1-31 下午03:28:13
 */
public class QueryFor21Impl implements IQuery20For21 {

	@Override
	public PraybillVO[] queryPrayBills(IQueryScheme queryScheme)
			throws BusinessException {
		try {
			// 2012.4.19 lixyp 为修改发布的交易类型转单查询查询出了非此交易类型的数据而作的修改
			// 因任务关闭无法以该任务之名签入文件，特加此注释。
			PraybillVO[] prayVOs = new QueryFor21BP(queryScheme).queryPrayBills();
			if (prayVOs == null) {
				return null;
			}
			for (PraybillVO prayVO : prayVOs) {
				PraybillItemVO[] itemVOs = prayVO.getBVO();
				VOSortUtils.ascSort(itemVOs, PraybillItemVO.CROWNO);
				prayVO.setBVO(itemVOs);
			}
			return prayVOs;
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
		return null;
	}

}
