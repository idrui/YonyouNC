package nc.itf.pu.pub;

import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.model.entity.bill.IBill;
import nc.vo.scmpub.page.PageQueryVO;
import nc.vo.scmpub.res.billtype.IBillType;

public interface IPuMaintainApp<E extends IBill> {
  /**
   * 单据查询
   * 
   * @param scheme UI端组织的查询方案
   * @return 按照单据号进行排序的单据分页。懒加载形式，只有第一页的第一张单据
   *         才有表体数据。没有查询到数据时返回零长度的数组
   * @throws BusinessException
   */
  PageQueryVO queryPuApp(IQueryScheme scheme, Class<E> clazz, String bislatest,
      String status, String vbillcode, IBillType billtype)
      throws BusinessException;

  /**
   * 单据查询
   * 
   * @param ids 单据主键数组
   * @return 懒加载形式，只有第一张单据才有表体数据。
   * @throws BusinessException
   */
  IBill[] queryPuApp(String[] ids, Class<E> clazz) throws BusinessException;
}
