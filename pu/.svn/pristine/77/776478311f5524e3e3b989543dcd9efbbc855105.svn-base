package nc.pubimpl.pu.m25transtype;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

import nc.bs.framework.common.NCLocator;
import nc.md.persist.framework.IMDPersistenceQueryService;
import nc.pubitf.pu.m25transtype.IQueryTranstype;
import nc.vo.pu.m25trantype.entity.InvcTranTypeVO;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.SqlBuilder;

/**
 * 采购发票交易类型查询服务实现类。
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>根据集团和交易类型编码查询采购发票交易类型设置。
 * </ul>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author GGR
 * @time 2009-12-22 上午11:02:58
 */
public class QueryM25TranstypeImpl implements IQueryTranstype {

  @SuppressWarnings("unchecked")
  @Override
  public HashMap<String, InvcTranTypeVO> queryAttrByTypes(String pk_group,
      String[] transTypes, String[] sAttriName) throws BusinessException {
    HashMap<String, InvcTranTypeVO> hmap =
        new HashMap<String, InvcTranTypeVO>();
    try {
      IMDPersistenceQueryService queryService =
          NCLocator.getInstance().lookup(IMDPersistenceQueryService.class);
      SqlBuilder sqlBld = new SqlBuilder();

      sqlBld.append(InvcTranTypeVO.VTRANTYPECODE, transTypes);
      sqlBld.append(" and ");
      sqlBld.append(InvcTranTypeVO.PK_GROUP, pk_group);
      sqlBld.append(" and dr=0 ");
      Collection<InvcTranTypeVO> colet =
          queryService.queryBillOfVOByCond(InvcTranTypeVO.class,
              sqlBld.toString(), false);

      Iterator<InvcTranTypeVO> iterVO = colet.iterator();
      InvcTranTypeVO voTemp = null;
      while (iterVO.hasNext()) {
        voTemp = iterVO.next();
        hmap.put(voTemp.getVtrantypecode(), voTemp);
      }
    }
    catch (Exception e) {
      // 按规范包装异常
      ExceptionUtils.marsh(e);
    }
    return hmap;
  }
}
