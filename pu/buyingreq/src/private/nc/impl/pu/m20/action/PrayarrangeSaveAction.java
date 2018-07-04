/**
 * 
 */
package nc.impl.pu.m20.action;

import java.util.HashMap;
import java.util.Map;

import nc.bs.pu.m20.maintain.PrayarrangeUpdateBP;
import nc.bs.uap.lock.PKLock;
import nc.impl.pubapp.pattern.database.DataAccessUtils;
import nc.impl.pubapp.pattern.database.IDQueryBuilder;
import nc.vo.bd.meta.BatchOperateVO;
import nc.vo.pu.m20.entity.PrayarrangeViewVO;
import nc.vo.pu.m20.entity.PraybillItemVO;
import nc.vo.pu.pub.util.ArrayUtil;
import nc.vo.pubapp.pattern.data.IRowSet;
import nc.vo.pubapp.pattern.exception.CarrierRuntimeException;
import nc.vo.pubapp.pattern.exception.TLockFailedException;
import nc.vo.pubapp.pattern.exception.TVersionConflictException;
import nc.vo.pubapp.pattern.log.Log;

import org.apache.commons.lang.ArrayUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>对请购安排数据的修改
 * </ul>
 * <p>
 * <p>
 * 
 * @version
 * @since
 * @author luojw
 * @time 2014-6-25 下午7:33:08
 */
public class PrayarrangeSaveAction {

  /**
   * 为取消安排提供的更新方法
   * 
   * @param batchVO
   * @return
   *         下午8:26:39
   */
  public BatchOperateVO batchCancelArrange(BatchOperateVO batchVO) {
    String[] names = new String[] {
      PraybillItemVO.BISARRANGE,"pk_employee","sts_req","tmstp_dispatch"
    };
    return this.batchOperate(batchVO, names);
  }

  /**
   * 为请购安排保存提供的更新方法
   * 
   * @param batchVO
   * @return
   *         下午8:26:05
   */
  public BatchOperateVO batchSave(BatchOperateVO batchVO) {
    String[] names =
        new String[] {
          PraybillItemVO.PK_PURCHASEORG_V, PraybillItemVO.PK_PURCHASEORG,
          PraybillItemVO.PK_SUGGESTSUPPLIER, PraybillItemVO.PK_EMPLOYEE,
          PraybillItemVO.BISARRANGE,PraybillItemVO.STS_REQ,PraybillItemVO.TMSTP_DISPATCH,
        };
    return this.batchOperate(batchVO, names);
  }

  /**
   * 将修改的数据进行更新
   * 
   * @param batchVO
   * @param names
   * @return
   *         下午8:27:21
   */
  private BatchOperateVO batchOperate(BatchOperateVO batchVO, String[] names) {
    PrayarrangeViewVO[] updObjs =
        ArrayUtil.convertArrayType(batchVO.getUpdObjs());
    String[] ids = this.getIds(updObjs);
    this.lock(ids);
    Map<String, String> origTs = this.getOrigTs(ids);
    this.checkTs(updObjs, origTs);

    if (!ArrayUtils.isEmpty(updObjs)) {
      new PrayarrangeUpdateBP().update(updObjs, names);
    }
    return batchVO;
  }

  /**
   * 验证时间戳
   * 
   * @param objs
   * @param origTs
   *          下午4:42:06
   */
  private void checkTs(PrayarrangeViewVO[] objs, Map<String, String> origTs) {
    if (objs.length != origTs.size()) {
      this.throwUnSynchronizedException();
    }
    int len = objs.length;
    for (int i = 0; i < len; i++) {
      String ts = objs[i].getTs().toString();
      String id = objs[i].getPk_praybill_b();
      this.checkTs(ts, origTs.get(id));
    }
  }

  /**
   * 对比两个时间戳是否相同
   * 
   * @param ts
   * @param origTs
   *          下午4:42:29
   */
  private void checkTs(String ts, String origTs) {
    boolean flag = true;
    if (ts == null && origTs == null) {
      flag = true;
    }
    else if (ts == null) {
      flag = false;
    }
    else if (!ts.equals(origTs)) {
      flag = false;
    }
    if (!flag) {
      this.throwUnSynchronizedException();
    }
  }

  /**
   * 获取ID值
   * 
   * @param objs
   * @return String[]
   *         下午4:41:07
   */
  private String[] getIds(PrayarrangeViewVO[] objs) {
    int length = objs.length;
    String[] ids = new String[length];
    for (int i = 0; i < length; i++) {
      ids[i] = objs[i].getPk_praybill_b();
    }
    return ids;
  }

  /**
   * 根据id值 获取Ts
   * 
   * @param ids
   * @return Map<String, String>
   *         下午4:41:41
   */
  private Map<String, String> getOrigTs(String[] ids) {
    StringBuilder sql = new StringBuilder();
    sql.append("select pk_praybill_b, ts from po_praybill_b")
        .append(" where dr = 0 and ")
        .append(
            new IDQueryBuilder().buildSQL(PraybillItemVO.PK_PRAYBILL_B, ids));
    DataAccessUtils util = new DataAccessUtils();
    IRowSet rowset = util.query(sql.toString());
    Map<String, String> map = new HashMap<String, String>();
    while (rowset.next()) {
      map.put(rowset.getString(0), rowset.getString(1));
    }
    return map;
  }

  /**
   * 通过锁定单据的id锁定单据
   * 
   * @param ids
   *          下午8:24:52
   */
  private void lock(String[] ids) {
    boolean success = false;
    success = PKLock.getInstance().addBatchDynamicLock(ids);
    if (!success) {
      TLockFailedException ex =
          new TLockFailedException(ids, nc.vo.ml.NCLangRes4VoTransl
              .getNCLangRes().getStrByID("4004020_0", "04004020-0114")/* 锁定单据ID失败 */);
      Log.error(ex);
      throw new CarrierRuntimeException(ex);
    }

  }

  /**
   * 如果时间戳不一样，就抛出异常
   * 下午4:42:57
   */
  private void throwUnSynchronizedException() {
    TVersionConflictException ex = new TVersionConflictException();
    Log.error(ex);
    throw new CarrierRuntimeException(ex);
  }

}
