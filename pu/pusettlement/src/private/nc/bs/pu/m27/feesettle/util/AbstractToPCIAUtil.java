package nc.bs.pu.m27.feesettle.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import nc.itf.pu.reference.pcia.PCIAServices;
import nc.itf.scmpub.reference.uap.group.SysInitGroupQuery;
import nc.itf.scmpub.reference.uap.pf.PfServiceScmUtil;
import nc.vo.pcia.m4639.entity.P4639BillVO;
import nc.vo.pcia.m4639.entity.P4639ItemVO;
import nc.vo.pu.costfactor.entity.CostfactorVO;
import nc.vo.pu.costfactor.entity.CostfactorViewVO;
import nc.vo.pu.est.util.EstTOIAUtil;
import nc.vo.pu.m27.entity.ICostfactorDiscountUtil;
import nc.vo.pu.m27.entity.SettleBillHeaderVO;
import nc.vo.pu.m27.entity.SettleBillItemVO;
import nc.vo.pu.m27.entity.SettleBillItemVOUtil;
import nc.vo.pu.m27.entity.SettleBillVO;
import nc.vo.pu.m27.entity.SettleFeeAllotDetailVO;
import nc.vo.pu.m27.entity.StockSettleVO;
import nc.vo.pu.pub.constant.PUParaValue;
import nc.vo.pu.pub.util.CirVOUtil;
import nc.vo.pu.pub.util.PUSysParamUtil;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.bill.SplitBill;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MapList;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.scmpub.res.billtype.PCIABillType;
import nc.vo.scmpub.res.billtype.POBillType;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>���ý��㴫PCIA�Ĺ��������࣬�ṩ��������
 * </ul>
 * <p>
 * 
 * @since 6.36
 * @author mengjian
 * @time 2014-10-25 ����09:10:32
 */
abstract public class AbstractToPCIAUtil {

  protected SettleBillHeaderVO header;

  protected Collection<SettleBillItemVO> items;

  protected Map<String, StockSettleVO> ssVOMap;

  public AbstractToPCIAUtil(SettleBillHeaderVO header,
      Collection<SettleBillItemVO> items) {
    this.header = header;
    this.items = items;
  }

  /**
   * ���ý��㴫�������Ĵ��
   * 
   * @param datactx
   */
  public void toPCIA(FeeSettleDataContext datactx) {
    if (this.items == null || this.items.size() == 0 || datactx == null) {
      return;
    }
    this.ssVOMap = datactx.getStockSettleVOMap();
    List<SettleBillItemVO> toP4639Datas = new ArrayList<SettleBillItemVO>();
    Set<SettleFeeAllotDetailVO> allAllotVOLst =
        new HashSet<SettleFeeAllotDetailVO>();
    for (SettleBillItemVO item : this.items) {
      String stockbid = item.getPk_stock_b();
      List<SettleFeeAllotDetailVO> noneEstBbvos =
          this.getFeeAllotDetailVO(datactx).get(stockbid);
      allAllotVOLst.addAll(noneEstBbvos);
      this.genToPCIAAdjBillStlItems(item, toP4639Datas, noneEstBbvos, false);
    }
    // �����ݵ�PCIA
    this.submitToPCIA(toP4639Datas, allAllotVOLst);

  }

  private void combineSettleItem(SettleBillItemVO sumItem,
      SettleBillItemVO detailItem) {
    EstTOIAUtil.sumCostfactor(sumItem, detailItem,
        SettleBillItemVO.NCOSTFACTOR1.substring(0, 11));
    String[] sumFields = {
      SettleBillItemVO.NMONEY
    };
    EstTOIAUtil.sumNumValueFields(sumItem, detailItem, sumFields);
  }

  private List<SettleBillItemVO> combineToP4639SettleItem(
      List<SettleBillItemVO> orgItemLst,
      Collection<SettleFeeAllotDetailVO> allAllotVOLst) {
    Map<String, SettleFeeAllotDetailVO> allotMap =
        this.getStlAllotMap(allAllotVOLst);
    List<SettleBillItemVO> newItemLst = new ArrayList<SettleBillItemVO>();
    Set<String> processedItem = new HashSet<String>();
    for (int i = 0; i < orgItemLst.size(); ++i) {
      SettleBillItemVO orgItem = orgItemLst.get(i);
      // ��ʱ���㵥��PK�Ѿ�����Ϊ��̯��ϸPK
      String pk_allot = orgItem.getPk_settlebill_b();
      String pk_supplier_org = orgItem.getPk_supplier();
      if (processedItem.contains(pk_allot)) {
        continue;
      }
      SettleFeeAllotDetailVO feeallotVo = allotMap.get(pk_allot);
      // �ҵ���̯����BID��ͬ���н��кϲ�
      for (int j = i + 1; j < orgItemLst.size(); ++j) {
        SettleBillItemVO nextOrgItem = orgItemLst.get(j);
        String pk_allot_next = nextOrgItem.getPk_settlebill_b();
        SettleFeeAllotDetailVO nextFeeallotVo = allotMap.get(pk_allot_next);
        String pk_supplier_next = nextOrgItem.getPk_supplier();
        if (!nextFeeallotVo.getPk_allotbillbid().equals(
            feeallotVo.getPk_allotbillbid())
            || StringUtils.isNotBlank(pk_supplier_org)
            && !pk_supplier_org.equals(pk_supplier_next)
            || StringUtils.isNotBlank(pk_supplier_next)
            && !pk_supplier_next.equals(pk_supplier_org)) {// Ҫ����ù�Ӧ��Ҳ��ͬ�źϲ�
          continue;
        }
        this.combineSettleItem(orgItem, nextOrgItem);
        processedItem.add(pk_allot_next);
      }
      newItemLst.add(orgItem);
    }
    return newItemLst;
  }

  private MapList<String, SettleFeeAllotDetailVO> getFeeAllotDetailVO(
      FeeSettleDataContext datactx) {
    if (datactx.getNoneSavedAllotDetails() == null) {
      return datactx.getBeenSavedAllotDetails();
    }
    return datactx.getNoneSavedAllotDetails();
  }

  private Map<String, SettleFeeAllotDetailVO> getStlAllotMap(
      Collection<SettleFeeAllotDetailVO> noneEstBbvos) {
    if (CollectionUtils.isEmpty(noneEstBbvos)) {
      return new HashMap<String, SettleFeeAllotDetailVO>();
    }
    return CirVOUtil.createKeyVOMap(noneEstBbvos
        .toArray(new SettleFeeAllotDetailVO[noneEstBbvos.size()]));
  }

  private void setAdjustSrcInfo(P4639BillVO[] p4639s,
      Collection<SettleFeeAllotDetailVO> allAllotVOLst) {
    Map<String, SettleFeeAllotDetailVO> allotMap =
        this.getStlAllotMap(allAllotVOLst);
    for (P4639BillVO p4639 : p4639s) {
      for (P4639ItemVO item : p4639.getChildrenVO()) {
        String pk_allot = item.getCsrcbid();
        SettleFeeAllotDetailVO allotVo = allotMap.get(pk_allot);
        item.setCadjustbillid(allotVo.getPk_allotbillid());
        item.setCadjustbillitemid(allotVo.getPk_allotbillbid());
        UFDouble adjnum = allotVo.getNallotbillnum();
        item.setNadjustnum(adjnum);
        if(POBillType.InitialEstSettleBill.getCode()
            .equals(this.getSourceTypeForVOChg())){
          item.setVsrctype(POBillType.InitialEstSettleBill.getCode());
        }
      }
    }
  }

  // private void setAdjustSrcInfo(I9BillVO[] i9s,
  // Collection<SettleFeeAllotDetailVO> allAllotVOLst) {
  // Map<String, SettleFeeAllotDetailVO> allotMap =
  // this.getStlAllotMap(allAllotVOLst);
  // for (I9BillVO i9 : i9s) {
  // for (I9ItemVO item : i9.getChildrenVO()) {
  // String pk_allot = item.getCsrcbid();
  // SettleFeeAllotDetailVO allotVo = allotMap.get(pk_allot);
  // item.setCadjustbillid(allotVo.getPk_allotbillid());
  // item.setCadjustbillitemid(allotVo.getPk_allotbillbid());
  // UFDouble adjnum = allotVo.getNallotbillnum();
  // item.setNadjustnum(adjnum);
  // }
  // }
  // }

  protected void genToPCIAAdjBillStlItems(SettleBillItemVO item,
      List<SettleBillItemVO> toP4639Datas,
      List<SettleFeeAllotDetailVO> noneEstBbvos, boolean forceToCost) {
    List<SettleBillItemVO> allToLst =
        this.getToPCIAStlItemsByAllot(item, noneEstBbvos);
    Map<String, SettleFeeAllotDetailVO> stlAllotMap =
        this.getStlAllotMap(noneEstBbvos);
    for (SettleBillItemVO toIAStlItem : allToLst) {
      // �������󣨼��������������������ý����Ӧ�ķ�̯�����Ѿ������ɱ���
      // ���ʽΪ����������棬forceToCost�����ݹ����ķ��ûس�ʱ��ǿ���߳ɱ�
      if (!forceToCost
          && PUParaValue.po13.profit_loss.equals(this.getParaPO13())
          && this.goodsHavenToIA(toIAStlItem, stlAllotMap)) {
      }
      else {
        toP4639Datas.add(toIAStlItem);
      }
    }
  }

  protected PUParaValue.po13 getParaPO13() {
    // ����PO13(����ת�뷽ʽ)
    String org = this.header.getPk_org();
    if (StringUtils.isEmpty(org)) {
      String msg =
          nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004060_0",
              "04004060-0065")/* @res "���з��ý���ȥ����ʱ����������֯Ϊ�մ���" */;
      ExceptionUtils.wrappBusinessException(msg);
      return null;
    }
    return PUSysParamUtil.getPO13(org);
  }

  /**
   * ��������������VO����ʱԴͷ�������ͱ���
   * <p>
   * <b>����˵��</b>
   * 
   * @return <p>
   * @since 6.0
   * @author hanbin
   * @time 2010-9-10 ����09:21:32
   */
  abstract protected String getSourceTypeForVOChg();

  /**
   * ���ݿɴ�����Ľ�������б���������VO�����ľۺϽ���VO�������зֵ��ȴ���
   * 
   * @param sItemLst
   * @return
   */
  protected SettleBillVO[] getToPCIASettleVO(List<SettleBillItemVO> sItemLst) {
    SettleBillVO vo = new SettleBillVO();
    vo.setParent(this.header);
    vo.setChildrenVO(sItemLst.toArray(new SettleBillItemVO[sItemLst.size()]));
    SplitBill<SettleBillVO> split = new SplitBill<SettleBillVO>();
    // ��浥�ݡ��ɱ�����Ĭ�Ϸֵ�����(���Ը��ǰ��������͡���浥�ݡ���Ӧ�̡��ɱ���ֵ���
    // ������ڼ�ͳɱ���ֵ��Ǵ������
    split.appendKey(SettleBillItemVO.PK_COSTREGION);
    split.appendKey(SettleBillItemVO.PK_STOCK);
    split.appendKey(SettleBillItemVO.PK_SUPPLIER);
    return split.split(new SettleBillVO[] {
      vo
    });
  }

  /**
   * ��ԭʼ�Ľ��������ɴ�����Ŀ�¡������
   * 
   * @param origItem ԭʼ������
   * @param adjMny Ҫ������������Ľ��
   * @param allotVo ��Ӧ�ķ�̯��ϸ
   * @return
   */
  protected SettleBillItemVO getToPCIAStlItem(SettleBillItemVO origItem,
      UFDouble adjMny, SettleFeeAllotDetailVO allotVo) {
    if (MathTool.isZero(adjMny)
        || !UFBoolean.TRUE.equals(this.ssVOMap.get(origItem.getPk_stock_b())
            .getBaffectcost())
        || !UFBoolean.TRUE.equals(this.header.getBtoia())) {
      return null;// �Ե������Ϊ��������෢Ʊ����Ӱ��ɱ�����ⵥ��������δ���ɱ���̯��ϸ����Ҫ��IA,
    }
    // ����ITEM
    SettleBillItemVO copyItem = (SettleBillItemVO) origItem.clone();
    // �������
    copyItem.setNmoney(adjMny);
    // ��¼��ϸID
    String bbid = allotVo.getPk_settle_feeallot();
    copyItem.setPk_settle_feeallot(bbid);
    copyItem.setPk_settlebill_b(bbid);
    // ��V5X���߼������ý���ʹ�÷��÷�Ʊ�Ĺ�Ӧ��
//    copyItem.setPk_supplier(allotVo.getPk_supplier());//����V65����ȡ��ⵥ��Ӧ��

    // ���ò����ȥ�����������ۡ��ɱ�Ҫ�أ������ȫ�����ơ�
    copyItem.setNsettlenum(null);
    copyItem.setNprice(null);
    copyItem.setNgoodsmoney(null);
    copyItem.setNgoodsprice(null);
    for (int j = 0; j < CostfactorVO.MAX_NUM; j++) {
      ICostfactorDiscountUtil.setNcostfactor(copyItem, j, null);
    }
    if (null != allotVo.getCostfactorvo()) {// Ϊ�մ������ۿ�
      ICostfactorDiscountUtil.setNcostfactor(copyItem, allotVo
          .getCostfactorvo().getIfactororder().intValue() - 1, adjMny);
    }
    return copyItem;
  }

  protected List<SettleBillItemVO> getToPCIAStlItemsByAllot(
      SettleBillItemVO item, List<SettleFeeAllotDetailVO> noneEstBbvos) {
    List<SettleBillItemVO> sItemLst = new ArrayList<SettleBillItemVO>();
    // û�н��й������ݹ������ý���ʱֱ�Ӵ�IA
    if (CollectionUtils.isEmpty(noneEstBbvos)) {
      return sItemLst;
    }
    for (int i = 0, len = noneEstBbvos.size(); i < len; i++) {
      CostfactorViewVO cfvo = noneEstBbvos.get(i).getCostfactorvo();
      // ֻ�н��ɱ��ķ��ú��ۿ۲ż��봫���
      if (null != cfvo && UFBoolean.FALSE.equals(cfvo.getBentercost())) {
        continue;
      }
      UFDouble allotmoney = noneEstBbvos.get(i).getNallotmoney();
      SettleBillItemVO copyItem =
          this.getToPCIAStlItem(item, allotmoney, noneEstBbvos.get(i));
      if (null == copyItem) {
        continue;
      }
      sItemLst.add(copyItem);
    }
    return sItemLst;
  }

  protected boolean goodsHavenToIA(SettleBillItemVO toIAStlItem,
      Map<String, SettleFeeAllotDetailVO> stlAllotMap) {
    String pk_allot = toIAStlItem.getPk_settle_feeallot();
    SettleFeeAllotDetailVO stlAllotVo = stlAllotMap.get(pk_allot);
    // �����̯���ݵ�ID�Ǳ��ν����ID��˵����ǰδ��������򷵻�false
    if (toIAStlItem.getPk_settlebill().equals(stlAllotVo.getPk_allotbillid())) {
      return false;
    }
    return true;
  }

  /**
   * mengjian �ɹ������������ڽ���ʱ�账����ڵ���������
   * 
   * @param toI9Datas
   */
  protected void processToP4639SettleItem(List<SettleBillItemVO> toI9Datas) {
    // �ɹ������������ڽ���ʱ�账����ڵ���������
  }

  /**
   * �����ݵ�PCIA
   * 
   * @param toP4639Datas
   * @param allAllotVOLst
   */
  protected void submitToPCIA(List<SettleBillItemVO> toP4639Datas,
      Set<SettleFeeAllotDetailVO> allAllotVOLst) {
    String srcBt = this.getSourceTypeForVOChg();
    if (toP4639Datas.size() > 0) {
      List<SettleBillItemVO> newToI9Datas =
          this.combineToP4639SettleItem(toP4639Datas, allAllotVOLst);
      SettleBillVO[] settlevos = this.getToPCIASettleVO(newToI9Datas);
      SettleBillItemVOUtil.clearCenterPurInfo(settlevos, this.ssVOMap);
      P4639BillVO[] p4639s =
          PfServiceScmUtil.executeVOChange(srcBt, PCIABillType.RKTZ.getCode(),
              settlevos);
      // ���õ����������ԴBID����Ϣ
      this.setAdjustSrcInfo(p4639s, allAllotVOLst);
      // ����ⵥ������
      // mengjian by 20141021 �����������Ĵ������ʱ
      if (SysInitGroupQuery.isPCIAEnabled()) {
        PCIAServices.insertI9ForPOFeeSettle(p4639s);
      }
    }
  }

}
