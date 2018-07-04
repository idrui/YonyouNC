/**
 * $�ļ�˵��$
 * 
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-7-6 ����03:56:25
 */
package nc.bs.pu.est;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import nc.bs.dao.BaseDAO;
import nc.bs.dao.DAOException;
import nc.bs.pu.est.rule.FeeUnEstAPRule;
import nc.bs.pu.est.rule.FeeUnEstIARule;
import nc.bs.pu.est.rule.GoodsEstInfoClearRule;
import nc.bs.pu.est.rule.GoodsUnEstAPRule;
import nc.bs.pu.est.rule.GoodsUnEstIARule;
import nc.impl.pubapp.pattern.data.view.ViewUpdate;
import nc.impl.pubapp.pattern.database.IDExQueryBuilder;
import nc.impl.pubapp.pattern.rule.plugin.IPluginPoint;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.vo.pu.est.entity.EstVO;
import nc.vo.pu.est.entity.FeeEstVO;
import nc.vo.pu.est.entity.GoodsEstVO;
import nc.vo.pu.est.util.EstVOUtil;
import nc.vo.pu.pub.constant.PUTempTable;
import nc.vo.pu.pub.util.CirVOUtil;
import nc.vo.pub.ISuperVO;
import nc.vo.pub.IVOMeta;
import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.ListToArrayTool;
import nc.vo.pubapp.pattern.pub.MathTool;

import org.apache.commons.lang.ArrayUtils;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>ȡ���ݹ�BP
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author zhaoyha
 * @time 2010-7-6 ����03:56:25
 */
public class CancelEstBP {
  private Class<? extends SuperVO> fdClazz;

  private IPluginPoint plugpt;

  private String[] updateItems;

  /**
   * CancelEstBP �Ĺ�����
   * 
   * @param plugpt ����Ĳ����
   * @param updateItems ��Ҫ���µĻ����ݹ���Ϣ
   * @param fdClazz ���÷�̯VO����
   */
  public CancelEstBP(IPluginPoint plugpt, String[] updateItems,
      Class<? extends SuperVO> fdClazz) {
    this.fdClazz = fdClazz;
    this.plugpt = plugpt;
    this.updateItems = updateItems;
  }

  /** ȡ�������ݹ��ͷ����ݹ� **/
  public void cancelEst(EstVO[] vos) {
    AroundProcesser<EstVO> prcr = new AroundProcesser<EstVO>(this.plugpt);
    this.addUnEstRule(prcr);
    prcr.before(vos);
    ViewUpdate<GoodsEstVO> vupdate = new ViewUpdate<GoodsEstVO>();
    GoodsEstVO head = vos[0].getParentVO();
    IVOMeta mmeta = head.getMetaData().getMainVOMeta();
    Class<? extends ISuperVO> updateClass =
        head.getMetaData().getVOClass(mmeta);
    GoodsEstVO[] heads = this.getHeads(vos);
    if (!ArrayUtils.isEmpty(heads)) {
      vupdate.update(heads, updateClass, this.updateItems);
      this.updateHeads(heads);
    }
    FeeEstVO[] fees = (FeeEstVO[]) EstVOUtil.getFeeEstVOs(vos);
    this.realDelFeeDatas(fees);
    prcr.after(vos);
  }

  /** ֻȡ�������ݹ� **/
  public void cancelFeeEst(EstVO[] vos) {
    AroundProcesser<EstVO> prcr = new AroundProcesser<EstVO>(this.plugpt);
    this.addFeeUnEstRule(prcr);
    prcr.before(vos);
    FeeEstVO[] fees = (FeeEstVO[]) EstVOUtil.getFeeEstVOs(vos);
    this.realDelFeeDatas(fees);
    prcr.after(vos);
  }

  private void addFeeUnEstRule(AroundProcesser<EstVO> prcr) {
    prcr.addBeforeFinalRule(new FeeUnEstIARule());// ȡ�������ݹ��ɱ�
    // prcr.addBeforeFinalRule(new FeeUnEstPCIARule());// ȡ�������ݹ��ɱ�(��������)
    prcr.addBeforeFinalRule(new FeeUnEstAPRule());// ����ȡ���ݹ�Ӧ��
  }

  private void addUnEstRule(AroundProcesser<EstVO> prcr) {
    prcr.addBeforeFinalRule(new GoodsUnEstIARule());// ȡ�������ݹ��ɱ�
    // prcr.addBeforeFinalRule(new GoodsUnEstPCIARule());// ȡ�������ݹ��ɱ�(��������)
    prcr.addBeforeFinalRule(new FeeUnEstIARule());// ȡ�������ݹ��ɱ�
    // prcr.addBeforeFinalRule(new FeeUnEstPCIARule());// ȡ�������ݹ��ɱ�(��������)
    prcr.addBeforeFinalRule(new GoodsUnEstAPRule());// ȡ�������ݹ�Ӧ��
    prcr.addBeforeFinalRule(new FeeUnEstAPRule());// ����ȡ���ݹ�Ӧ��
    prcr.addBeforeFinalRule(new GoodsEstInfoClearRule());// ��������ݹ���һЩ��Ϣ
  }

  /** �õ��ݹ�VO��ͷ���飬��Ҫ���µĲɹ�����ͼ **/
  private GoodsEstVO[] getHeads(EstVO[] vos) {
    List<GoodsEstVO> heads = new ArrayList<GoodsEstVO>();
    for (int i = 0; i < vos.length; i++) {
      GoodsEstVO head = vos[i].getParentVO();
      // �Ѿ������
      UFDouble estSttlNum = head.getNaccestcoststtlnum();
      if (!UFDouble.ZERO_DBL.equals(MathTool.nvl(estSttlNum))) {
        continue;
      }
      heads.add(head);
    }
    if (0 == heads.size()) {
      return null;
    }
    return new ListToArrayTool<GoodsEstVO>().convertToArray(heads);
  }

  /** ����ɾ�������ݹ���¼ **/
  private void realDelFeeDatas(FeeEstVO[] fees) {
    if (ArrayUtils.isEmpty(fees)) {
      return;
    }
    Set<String> feeids =
        CirVOUtil.getDistinctFieldSet(fees, FeeEstVO.PK_STOCKPS_FEE);
    IDExQueryBuilder idqb =
        new IDExQueryBuilder(PUTempTable.tmp_po_est_01.name());
    String[] fids = feeids.toArray(new String[feeids.size()]);
    String insql = idqb.buildAnotherSQL(FeeEstVO.PK_STOCKPS_FEE, fids);
    BaseDAO dao = new BaseDAO();
    try {
      dao.deleteByClause(this.fdClazz, insql);
      dao.deleteByPKs(fees[0].getClass(), fids);
    }
    catch (DAOException e) {
      ExceptionUtils.wrappException(e);
    }
  }

  /**
   * ������ⵥ(VMI)�����ı�ͷ��һЩ��Ϣ
   * 
   * @param vos �����ݹ�VO
   **/
  protected void updateHeads(GoodsEstVO[] vos) {
    //
  }

}
