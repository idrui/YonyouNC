package nc.pubimpl.pu.m422x.ewm.m4b36;

import java.util.Map;

import nc.pubitf.pu.m422x.ewm.m4b36.IReWrite422XFor4B36;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDouble;

/**
 * �����ۼ�����������636�������Ѿ�ɾ�������Բ����ڴ˻�д��
 * �������������ṩ�������Ļ�д����ʵ����
 * 
 * @since 6.5
 * @version 2014-1-24 ����03:25:12
 * @author fanly3
 */
public class ReWrite422XFor4b36Impl implements IReWrite422XFor4B36 {

  @Override
  public void backWriteNum(Map<String, UFDouble> map) throws BusinessException {
    // try {
    // if (map == null || map.isEmpty()) {
    // return;
    // }
    // // ��ѯҪ��д�������������뵥��
    // VOQuery<StoreReqAppItemVO> query =
    // new VOQuery<StoreReqAppItemVO>(StoreReqAppItemVO.class, new String[] {
    // StoreReqAppItemVO.PK_STOREREQ_B, StoreReqAppItemVO.CSOURCEBID,
    // StoreReqAppItemVO.NACCUMMINUSNUM
    // });
    //
    // SqlBuilder sql = new SqlBuilder();
    // sql.append(" and ");
    // IDExQueryBuilder builder =
    // new IDExQueryBuilder(PUTempTable.tmp_po_422X_03.name());
    // sql.append(builder.buildSQL(PUEntity.M422X_B_TABLE + "."
    // + StoreReqAppItemVO.CSOURCEBID,
    // map.keySet().toArray(new String[map.keySet().size()])));
    // StoreReqAppItemVO[] itemVOs = query.query(sql.toString(), null);
    // // �����ۼ�����������
    // for (StoreReqAppItemVO item : itemVOs) {
    // item.setNaccumminusnum(MathTool.add(item.getNaccumminusnum(),
    // map.get(item.getCsourcebid())));
    // }
    //
    // VOUpdate<StoreReqAppItemVO> voUpdate = new VOUpdate<StoreReqAppItemVO>();
    // voUpdate.update(itemVOs, new String[] {
    // StoreReqAppItemVO.NACCUMMINUSNUM
    // });
    // }
    // catch (Exception e) {
    // ExceptionUtils.marsh(e);
    // }
  }
}
