/**
 * $�ļ�˵��$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-9-26 ����08:48:18
 */
package nc.impl.pu.m21.action.rule.approve;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import nc.bs.framework.common.NCLocator;
import nc.bs.ml.NCLangResOnserver;
import nc.impl.pubapp.pattern.data.vo.VOInsert;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.pubitf.pu.m21transtype.IPoTransTypeQuery;
import nc.vo.pu.m21.entity.OrderHeaderVO;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pu.m21.entity.StatusOnWayItemVO;
import nc.vo.pu.m21.pub.TrantypeUtil;
import nc.vo.pu.m21transtype.entity.PoTransTypeVO;
import nc.vo.pu.m21transtype.enumeration.OnwayStatus;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import org.apache.commons.lang.ArrayUtils;

/**
 * @description
 *              �������ʱ����;״̬���в���һ������
 * @scene
 *        �ɹ���������
 * @param ��
 * @since 6.3
 * @version 2014-10-20 ����4:50:52
 * @author luojw
 */
public class InsertStatusOnWayRule implements IRule<OrderVO> {

  /**
   * ���෽����д
   * 
   * @see nc.impl.pubapp.pattern.rule.IRule#process(E[])
   */
  @Override
  public void process(OrderVO[] vos) {
    if (ArrayUtils.isEmpty(vos)) {
      return;
    }
    Map<String, PoTransTypeVO> transtypeMap = this.getTransType(vos);

    Set<StatusOnWayItemVO> statusOnWayItemSet =
        new HashSet<StatusOnWayItemVO>();
    for (OrderVO vo : vos) {
      this.getStatusOnWay(vo, statusOnWayItemSet, transtypeMap);
    }

    // ���Ϊ�վͲ���po_order_bb����������ݡ�
    if (0 == statusOnWayItemSet.size()) {
      return;
    }

    VOInsert<StatusOnWayItemVO> insertService =
        new VOInsert<StatusOnWayItemVO>();
    insertService.insert(statusOnWayItemSet
        .toArray(new StatusOnWayItemVO[statusOnWayItemSet.size()]));

  }

  /**
   * ����������������;״̬��
   * <p>
   * <b>����˵��</b>
   * 
   * @param vo
   * @param statusOnWayItemSet
   * @param transtypeMap
   *          <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-9-26 ����09:14:36
   */
  private void getStatusOnWay(OrderVO vo,
      Set<StatusOnWayItemVO> statusOnWayItemSet,
      Map<String, PoTransTypeVO> transtypeMap) {
    // �����ƻ� wulla
    // ��ϲ��һ�κ͵����ƻ�û��ϵ
    OrderHeaderVO headerVO = vo.getHVO();
    String vtrantypecode = headerVO.getVtrantypecode();
    PoTransTypeVO transTypeVO = transtypeMap.get(vtrantypecode);
    if (transTypeVO == null) {
      ExceptionUtils.wrappBusinessException(NCLangResOnserver.getInstance()
          .getStrByID("4004030_0", "04004030-0234", null, new String[] {
            vtrantypecode
          })/* �ɹ�������������{0}��չ��û�����ݣ����� */);
      return;
    }
    Integer iapproveaft =
        TrantypeUtil.getInstance().getNextStatus(
            OnwayStatus.STATUS_AUDIT.toInt(), transTypeVO);

    // �����һ������״̬Ϊ�գ�����������
    if (iapproveaft == null) {
      return;
    }

    OrderItemVO[] itemVOs = vo.getBVO();
    for (OrderItemVO itemVO : itemVOs) {

      StatusOnWayItemVO statusOnWayItemVO = new StatusOnWayItemVO();
      // �˴���������;��������
      // statusOnWayItemVO.setDbilldate(headerVO.getDbilldate());
      statusOnWayItemVO.setDplanarrvdate(itemVO.getDplanarrvdate());
      statusOnWayItemVO.setFonwaystatus(iapproveaft);
      statusOnWayItemVO.setIsoperated(UFBoolean.FALSE);
      statusOnWayItemVO.setNonwaynum(itemVO.getNnum());
      // ������Ϊ���ɲ�������
      statusOnWayItemVO.setNmaxhandlenum(itemVO.getNnum());
      statusOnWayItemVO.setPk_group(itemVO.getPk_group());
      statusOnWayItemVO.setPk_order(itemVO.getPk_order());
      statusOnWayItemVO.setPk_order_b(itemVO.getPk_order_b());
      statusOnWayItemVO.setPk_org(itemVO.getPk_org());
      statusOnWayItemVO.setPk_org_v(itemVO.getPk_org_v());
      // �˴���������;���ݺ�
      // statusOnWayItemVO.setVbillcode(vbillcode);

      statusOnWayItemSet.add(statusOnWayItemVO);
    }
  }

  /**
   * ��������������������Ӧ�������һ״̬Map
   * <p>
   * <b>����˵��</b>
   * 
   * @param vos
   * @return <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-9-26 ����09:00:27
   */
  private Map<String, PoTransTypeVO> getTransType(OrderVO[] vos) {
    Set<String> set = new HashSet<String>();
    for (OrderVO vo : vos) {
      String vtrantypecode = vo.getHVO().getVtrantypecode();
      set.add(vtrantypecode);
    }

    IPoTransTypeQuery service =
        NCLocator.getInstance().lookup(IPoTransTypeQuery.class);
    try {
      // return service.queryAttrByTypes(set.toArray(new String[set.size()]),
      // new String[] {
      // PoTransTypeVO.BOUTPUT, PoTransTypeVO.BCONFIRM,
      // PoTransTypeVO.BCONSIGN, PoTransTypeVO.BLOAD, PoTransTypeVO.BCUSTOM,
      // PoTransTypeVO.BOUTCUSTOM, PoTransTypeVO.BARRIVE,
      // PoTransTypeVO.BSTORE
      // });
      return service
          .queryAttrByTypes(set.toArray(new String[set.size()]), null);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }

    return null;
  }

}
