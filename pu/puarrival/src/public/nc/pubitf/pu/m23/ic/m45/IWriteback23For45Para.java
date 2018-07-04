package nc.pubitf.pu.m23.ic.m45;

import nc.vo.pu.pub.writeback.IWriteBackPubPara;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>库存采购入库的回写到货单时的参数接口
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author hanbin
 * @time 2010-1-8 下午04:16:08
 */
public interface IWriteback23For45Para extends IWriteBackPubPara {

  // 入库单行号
  // private Integer icRowNum;

  // 新入库单行是否赠品
  // private UFBoolean newIsLargess;

  // 旧入库单行是否赠品
  // private UFBoolean oldIsLargess;

  // 上游是否赠品
  // private UFBoolean isUpLargess;

  // 质量等级ID
  // private String qualityLevelId;

  // 到货单子子表ID,按检验结果入库时此字段非空
  String getBBID();

  /** 是否用户确认了数量超容差的提示 **/
  boolean isNumUserConfirm();
}
