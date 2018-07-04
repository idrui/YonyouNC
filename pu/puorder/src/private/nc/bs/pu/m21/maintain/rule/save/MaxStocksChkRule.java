/**
 * $�ļ�˵��$
 *
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2009-12-30 ����09:01:54
 */
package nc.bs.pu.m21.maintain.rule.save;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import nc.bs.ml.NCLangResOnserver;
import nc.bs.pu.m21.maintain.rule.FreezeInfoSetter;
import nc.impl.pubapp.pattern.data.vo.VOUpdate;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.itf.pu.reference.ic.ATPServices;
import nc.itf.scmpub.reference.uap.bd.material.MaterialPubService;
import nc.vo.bd.material.MaterialVO;
import nc.vo.bd.material.plan.MaterialPlanVO;
import nc.vo.jcom.lang.StringUtil;
import nc.vo.pu.m21.context.OrderContext;
import nc.vo.pu.m21.entity.OrderHeaderVO;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pu.m21.exception.AskMaxStockException;
import nc.vo.pu.pub.constant.PUParaValue;
import nc.vo.pu.pub.util.AggVOUtil;
import nc.vo.pu.pub.util.CirVOUtil;
import nc.vo.pu.pub.util.PUSysParamUtil;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.data.ValueUtils;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MapList;
import nc.vo.pubapp.pattern.pub.MathTool;

/**
 * @description
 *              �ɹ�������߿�������
 * @scene
 *        �ɹ����������޸�
 * @param OrderContext ctx �ɹ���������ʱǰ̨����̨�Ļ�����Ϣ
 * @since 6.3
 * @version 2014-10-22 ����3:15:45
 * @author luojw
 */
public class MaxStocksChkRule implements IRule<OrderVO> {
  private OrderContext ctx;

  public MaxStocksChkRule(OrderContext ctx) {
    this.ctx = ctx;
  }

  @Override
  public void process(OrderVO[] vos) {
    // ���˵��Ѿ�������˻�������Vo
    OrderVO[] vosFiltered = this.filterFreezeAndReturnVos(vos);
    if (vosFiltered.length == 0) {
      return;
    }

    for (OrderVO vo : vosFiltered) {
      this.checkMaxStock(vo);
    }
  }

  private void check(List<OrderItemVO> items, Map<String, UFDouble> atps,
      Map<String, MaterialPlanVO> maxstockMap, String vbillcode,
      StringBuilder errMsg, String errInfo) {
    for (OrderItemVO item : items) {
      UFDouble atp = atps.get(item.getPk_srcmaterial());
      if (maxstockMap.get(item.getPk_material()) == null) {
        continue;
      }
      MaterialPlanVO stockVO = maxstockMap.get(item.getPk_material());
      if (null == stockVO) {
        continue;
      }
      UFDouble maxstock = stockVO.getMaxstornum();
      if (null == maxstock) {
        continue;
      }
      if (MathTool.compareTo(atp, maxstock) > 0) {
        errMsg.append(NCLangResOnserver.getInstance().getStrByID("4004030_0",
            "04004030-0238", null, new String[] {
              vbillcode, item.getCrowno(), errInfo
            })/* ���ݺţ�{0} �кţ�{1}{2} */);
      }
    }
  }

  private void checkMaxStock(OrderVO vo) {
    MapList<String, OrderItemVO> ml = this.getStockOrgAndItems(vo);
    if (ml.size() == 0) {
      return;
    }

    StringBuilder errMsg = new StringBuilder();
    Set<String> orgs = ml.keySet();
    for (String org : orgs) {
      // ��ȡPO00��߿�����ƿ��Ʋ���ֵ���������ڿ����֯��
      PUParaValue.ctrltype PO00 = PUSysParamUtil.getPO00(org);

      // ������
      if (PUParaValue.ctrltype.not_control.equals(PO00)) {
        continue;
      }

      List<OrderItemVO> items = ml.get(org);
      // ���ϵ�PK����
      String[][] pks = this.getMaterialPks(items);
      String[] oids = pks[0];
      String[] vids = pks[1];

      UFDate[] date = this.getPlanArriveDate(items, vo.getHVO().getDbilldate());

      // ��ȡ���ϵ���߿��
      Map<String, MaterialPlanVO> maxstock = this.queryMaxStock(vids, org);

      // ��ȡ���Ŀ�����
      Map<String, UFDouble> atps = ATPServices.queryATP(org, oids, date);

      // ������
      if (PUParaValue.ctrltype.not_save.equals(PO00)) {
        this.chkAndNoSave(items, atps, maxstock, vo.getHVO().getVbillcode(),
            errMsg);
        if (errMsg.length() > 0) {
          ExceptionUtils.wrappBusinessException(errMsg.toString());
        }
      }
      // ��ʾ
      else if (PUParaValue.ctrltype.ask.equals(PO00)) {
        this.chkAndAsk(items, atps, maxstock, vo.getHVO().getVbillcode(),
            errMsg);
        if (errMsg.length() > 0) {
          ExceptionUtils.wrappException(new AskMaxStockException(errMsg
              .toString()));
        }
      }
      // ����
      else if (PUParaValue.ctrltype.freeze.equals(PO00)) {
        this.chkAndFreeze(items, atps, maxstock, vo.getHVO().getVbillcode(),
            errMsg);
        if (errMsg.length() > 0) {
          new FreezeInfoSetter(new OrderVO[] {
            vo
          }).setFreezeInfo(NCLangResOnserver.getInstance().getStrByID(
              "4004030_0", "04004030-0239")/* ����߿���򶩵����� */);
          VOUpdate<OrderHeaderVO> update = new VOUpdate<OrderHeaderVO>();
          update.update(new OrderHeaderVO[] {
            vo.getHVO()
          }, new String[] {
            OrderHeaderVO.BFROZEN, OrderHeaderVO.VFROZENREASON,
            OrderHeaderVO.PK_FREEZEPSNDOC, OrderHeaderVO.TFREEZETIME
          });
        }
      }
    }
  }

  private void chkAndAsk(List<OrderItemVO> items, Map<String, UFDouble> atps,
      Map<String, MaterialPlanVO> maxstockMap, String vbillcode,
      StringBuilder errMsg) {
    // ����û��Ѿ�ȷ�Ϲ��������棬��ֱ�ӷ���
    if (ValueUtils.getBoolean(this.ctx.getMaxStockConfirm())) {
      return;
    }
    this.check(items, atps, maxstockMap, vbillcode, errMsg, NCLangResOnserver
        .getInstance().getStrByID("4004030_0", "04004030-0136")/* ������߿�棬�Ƿ������ */);
  }

  private void chkAndFreeze(List<OrderItemVO> items,
      Map<String, UFDouble> atps, Map<String, MaterialPlanVO> maxstockMap,
      String vbillcode, StringBuilder errMsg) {
    this.check(items, atps, maxstockMap, vbillcode, errMsg, "");
  }

  private void chkAndNoSave(List<OrderItemVO> items,
      Map<String, UFDouble> atps, Map<String, MaterialPlanVO> maxstockMap,
      String vbillcode, StringBuilder errMsg) {
    this.check(
        items,
        atps,
        maxstockMap,
        vbillcode,
        errMsg,
        nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004030_0",
            "04004030-0119")/* @res "������߿�棡" */);
  }

  private OrderVO[] filterFreezeAndReturnVos(OrderVO[] vos) {
    List<OrderVO> unFreezeVos = new ArrayList<OrderVO>();
    for (OrderVO vo : vos) {
      // ���˵�������˻��Ķ���
      if (ValueUtils.getBoolean(vo.getHVO().getBfrozen())
          || ValueUtils.getBoolean(vo.getHVO().getBreturn())) {
        continue;
      }
      unFreezeVos.add(vo);
    }
    return unFreezeVos.toArray(new OrderVO[unFreezeVos.size()]);
  }

  private String[][] getMaterialPks(List<OrderItemVO> itemVos) {
    String[][] pks = new String[2][itemVos.size()];
    OrderItemVO[] items = itemVos.toArray(new OrderItemVO[itemVos.size()]);
    Set<String> oids =
        CirVOUtil.getDistinctFieldSet(items, OrderItemVO.PK_SRCMATERIAL);
    Set<String> vids =
        CirVOUtil.getDistinctFieldSet(items, OrderItemVO.PK_MATERIAL);
    pks[0] = oids.toArray(new String[oids.size()]);
    pks[1] = vids.toArray(new String[vids.size()]);

    return pks;
  }

  private UFDate[] getPlanArriveDate(List<OrderItemVO> itemVos, UFDate orderDate) {
    UFDate[] date = new UFDate[itemVos.size()];
    for (int i = 0; i < date.length; i++) {
      OrderItemVO item = itemVos.get(i);
      date[i] = item.getDplanarrvdate();
      if (date[i] == null) {
        date[i] = orderDate;
      }
    }

    return date;
  }

  /**
   * ���������������Ѷ����ı������ϰ����ջ������֯���з��飬���ҹ��˵���������ۿ������ϣ����˵���Ʒ
   * <p>
   * <b>����˵��</b>
   * 
   * @param vo ����
   * @return �����Ķ�������
   *         <p>
   * @since 6.0
   * @author duy
   * @time 2010-7-24 ����04:41:25
   */
  private MapList<String, OrderItemVO> getStockOrgAndItems(OrderVO vo) {
    MapList<String, OrderItemVO> ml = new MapList<String, OrderItemVO>();
    Map<String, MaterialVO> map = this.queryMaterials(vo);
    OrderItemVO[] items = vo.getBVO();
    for (OrderItemVO item : items) {
      String pk_arrvstoreorg = item.getPk_arrvstoorg();
      if (StringUtil.isEmptyWithTrim(pk_arrvstoreorg)) {
        continue;
      }
      MaterialVO material = map.get(item.getPk_srcmaterial());
      if (ValueUtils.getBoolean(material.getFee())
          || ValueUtils.getBoolean(material.getDiscountflag())
          || ValueUtils.getBoolean(item.getBlargess())) {
        continue;
      }
      ml.put(pk_arrvstoreorg, item);
    }
    return ml;
  }

  private Map<String, MaterialVO> queryMaterials(OrderVO vo) {
    String[] pks =
        (String[]) AggVOUtil.getDistinctItemFieldArray(new OrderVO[] {
          vo
        }, OrderItemVO.PK_SRCMATERIAL, String.class);
    if (pks == null || pks.length == 0) {
      return new HashMap<String, MaterialVO>();
    }
    Map<String, MaterialVO> map = null;
    String[] fields = new String[] {
      MaterialVO.PK_MATERIAL, MaterialVO.FEE, MaterialVO.DISCOUNTFLAG
    };
    map = MaterialPubService.queryMaterialBaseInfo(pks, fields);
    return map;
  }

  private Map<String, MaterialPlanVO> queryMaxStock(String[] pks,
      String pk_stockorg) {
    if (pks == null || pks.length == 0) {
      return new HashMap<String, MaterialPlanVO>();
    }

    Map<String, MaterialPlanVO> map = null;
    map =
        MaterialPubService.queryMaterialPlanMapInfoByPks(pks, pk_stockorg,
            new String[] {
              MaterialPlanVO.PK_MATERIAL, MaterialPlanVO.MAXSTORNUM
            });
    return map;
  }

}
