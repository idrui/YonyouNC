package nc.pubitf.pu.position;

/**
 * * 采购岗（计划岗）物料设置为离散制造的查询服务。
 * 
 * @since 6.0
 * @version 2011-12-28 上午10:37:31
 * @author yangtian
 */

public interface IQueryPositionForMM {
  /**
   * <p>
   * 使用场景：查询指定计划员负责的物料OID
   * <ul>
   * <li>
   * <li>aid实际独立需求:为了离散制造的“生产计划”节点的查询单据，这是一个复杂查询，会根据计划员，库存组织，物料编码，名称规格型号，等
   * <li>查询相关物料，会根据这些查询条件取交集所以离散制造那边会根据这边返回的sql拼到一个大的sql里，这里只是提供关于计划员那部分的
   * <li>限制条件。而且跟李辉确认过，他说不用传库存组织过来，他会自己处理。
   * </ul>
   * 
   * @param cemployeeid 计划员
   * @return 计划员下所有的物料OID sql( select pk_material from .....)
   */

  String getMaterialSqlByCemployee(String cemployeeid);

}
