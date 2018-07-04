package nc.pubitf.pu.m422x.ewm.m4b36;

import java.util.Map;

import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDouble;

/**
 * 物资需求申请提供给工单的回写表体“累计消减主数量”字段服务
 * 主要场景：若工单的计划物料页签数据来源于维修计划，则工单保存、删除时，将工单当前行的计划物料主数量回写到，
 * 维修计划生成的物资需求申请单对应数据行的“累计消减主数量”。
 * 
 * @since 6.5
 * @version 2014-1-24 上午11:14:33
 * @author fanly3
 */
public interface IReWrite422XFor4B36 {

  /**
   * 回写物资需求申请单上的“累计消减主数量”
   * 
   * @param map 工单穿过来的参数，结构为Map<维修计划计划物料孙表id，本次消减主数量>
   * @throws BusinessException
   */
  void backWriteNum(Map<String, UFDouble> map) throws BusinessException;
}
