package nc.vo.pu.est.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang.StringUtils;

import nc.vo.pcia.bill.entity.base.AbstractBaseBill;
import nc.vo.pcia.bill.entity.base.AbstractBaseItemVO;
import nc.vo.pcia.bill.entity.real.AbstractRealHeadVO;
import nc.vo.pcia.bill.entity.real.AbstractRealItemVO;
import nc.vo.pcia.m4632.entity.P4632BillVO;
import nc.vo.pcia.m4632.entity.P4632ItemVO;
import nc.vo.pcia.m4639.entity.P4639ItemVO;
import nc.vo.pu.costfactor.entity.CostfactorVO;
import nc.vo.pu.est.entity.GoodsEstVO;
import nc.vo.pu.m4201.entity.PurchaseinFIHeaderVO;
import nc.vo.pu.m4201.entity.PurchaseinFIItemVO;
import nc.vo.pu.m4201.entity.PurchaseinFIVO;
import nc.vo.pu.m4202.entity.VmiFIHeaderVO;
import nc.vo.pu.m4202.entity.VmiFIVO;
import nc.vo.pu.pub.util.PUSysParamUtil;
import nc.vo.pub.CircularlyAccessibleValueObject;
import nc.vo.pub.ISuperVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.model.entity.bill.AbstractBill;
import nc.vo.pubapp.pattern.model.entity.bill.IBill;
import nc.vo.pubapp.pattern.pub.ListToArrayTool;
import nc.vo.pubapp.pattern.pub.MapList;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.pubapp.scale.ScaleObjectFactory;

import nc.pubitf.ia.mi2.po.I2AdjustBackData;

import nc.bs.framework.common.InvocationInfoProxy;

/**
 * �ݹ����������Ĵ������ʱ�Բ������ݽ��д���Ĺ�����
 * 
 * @since 6.0
 * @date 2015-02-12 11:26
 * @author wangzhqf
 */
public class EstTOPCIAUtil {

  /**
   * �س����ݵĵ��۴����ߴ������ľ���
   * 
   * @param i2AdjustDatas
   */
  public static void adjustI2Price(I2AdjustBackData[] i2AdjustDatas,
      String pk_curr) {
    String pk_group = InvocationInfoProxy.getInstance().getGroupId();
    ScaleObjectFactory sof = new ScaleObjectFactory(pk_group);
    int costdg = sof.getCostPriceScaleObject().getDigit();
    int podg = sof.getPriceScaleObject().getDigit(pk_curr);
    for (I2AdjustBackData i2 : i2AdjustDatas) {
      Object str_nprice = i2.getNprice();
      if(str_nprice != null){
        podg = EstTOIAUtil.getPODigit(podg, str_nprice.toString());
      }
      if (costdg >= podg) {
        continue;
      }
      UFDouble mny = MathTool.nvl(i2.getNmny());
      UFDouble num = MathTool.nvl(i2.getNnum());
      i2.setNprice(mny.div(num, costdg));
    }
  }

  /**
   * ����P4632���ݵĵ��ݾ���Ϊ�ɱ����۾���<br>
   * ���ڽ�����ݹ�<br>
   * ���ɹ�����С�����ȸ��ڳɱ����۾���ʱ�账������������У��ͨ�����������������Ҫ����
   * 
   * @param p4632s
   */
  public static void adjustP4632Price(P4632BillVO[] p4632s) {
    String pk_group = InvocationInfoProxy.getInstance().getGroupId();
    ScaleObjectFactory sof = new ScaleObjectFactory(pk_group);
    int costdg = sof.getCostPriceScaleObject().getDigit();
    int podg   = sof.getPriceScaleObject().getDigit();
    for (P4632BillVO p4632 : p4632s) {
      for (P4632ItemVO item : p4632.getChildrenVO()) {
    	//wangzhqf 2015-03-09 17:01����I2ItemVO ����Ϊ�գ�IA�Ǳ��ֲ�����VO����,���������´���
    	  //1.ȡ���۵�С��λ,��ȡ��ĩβ����
    	  //2.�ٺ�IA�ľ������Ƚϡ�
    	  Object str_nprice = item.getNprice();
        if(str_nprice != null){
          podg = EstTOIAUtil.getPODigit(podg, str_nprice.toString());
        }
        if (costdg >= podg) {
          return;
        }
        UFDouble mny = MathTool.nvl(item.getNmny());
        UFDouble num = MathTool.nvl(item.getNnum());
        item.setNprice(mny.div(num, costdg));
      }
    }
  }

  /**
   * �Է��ռ�������ݣ���տ����֯���ֿ�
   * 
   * @param pfivos ���񸱱�VO����
   */
  public static void clearCenterSettleInfo(IBill[] pfivos) {
    String[] names =
        {
          PurchaseinFIHeaderVO.PK_ORG, PurchaseinFIHeaderVO.PK_ORG_V,
          PurchaseinFIHeaderVO.PK_STORDOC
        };
    if (pfivos[0] instanceof VmiFIVO) {
      names =
          new String[] {
            VmiFIHeaderVO.PK_STOREORG, VmiFIHeaderVO.PK_STOREORG_V,
            VmiFIHeaderVO.PK_STORDOC
          };
    }
    for (IBill pfivo : pfivos) {
      UFBoolean normpur =
          (UFBoolean) pfivo.getParent().getAttributeValue(GoodsEstVO.BNORMPUR);
      if (UFBoolean.TRUE.equals(normpur)) {
        continue;
      }

      EstTOPCIAUtil.clearInfo(pfivo.getParent(), names);
    }
  }

  /**
   * ����ԴBID��ͬ��I9��IG�Ľ�ȫ���н��кϲ�<br>
   * ���ɱ�Ҫ�أ���������
   * 
   * @param adjustBills I9��IG�ĵ���
   */
  public static void combineAdjustBillRow(AbstractBill[] adjustBills) {
    for (AbstractBill bill : adjustBills) {
      MapList<String, CircularlyAccessibleValueObject> nitemMap =
          new MapList<String, CircularlyAccessibleValueObject>();
      for (CircularlyAccessibleValueObject item : bill.getChildrenVO()) {
        nitemMap.put(
            (String) item.getAttributeValue(AbstractRealItemVO.CSRCBID), item);
      }
      // �ϲ����ݱ���
      EstTOPCIAUtil.combineItems(bill, nitemMap);
    }
  }

  /**
   * ���Ļ��ܴ�������㵥��������Ͳ���
   * 
   * @param iaBill ������㵥��
   * @param pk_fiOrg ������֯
   * @param iaStockField ������㵥�ݵ���������ֶ�
   */
  public static void setStockTranTypeForVMI(AbstractBaseBill iaBill,
      String pk_fiOrg, String iaStockField) {
    String stocTranType = PUSysParamUtil.getPO54(pk_fiOrg);
    if (!StringUtils.isEmpty(stocTranType)) {
      iaBill.getParentVO().setAttributeValue(iaStockField, stocTranType);
    }
  }

  /**
   * ���Ļ��ܴ�������㵥��������Ͳ���
   * 
   * @param iaBills ������㵥��
   * @param srcVos ���Ļ��ܲ���vo
   */
  public static void setStockTranTypeForVMI(AbstractBaseBill[] iaBills,
      VmiFIVO[] srcVos) {
    Map<String, VmiFIVO> vmifiMap = new HashMap<String, VmiFIVO>();
    for (VmiFIVO vo : srcVos) {
      vmifiMap.put(vo.getParentVO().getPk_stockps(), vo);
    }
    for (AbstractBaseBill iaBill : iaBills) {
      AbstractBaseItemVO item = iaBill.getChildrenVO()[0];
      String id = (String) item.getAttributeValue(AbstractRealItemVO.CSRCID);
      VmiFIVO vmiFiVO = vmifiMap.get(id);
      if (vmiFiVO == null) {// ������ȷ�Ļ����ﲻ��Ϊ��
        continue;
      }
      String pk_fiOrg = vmiFiVO.getParentVO().getPk_financeorg();
      EstTOPCIAUtil.setStockTranTypeForVMI(iaBill, pk_fiOrg,
          AbstractRealHeadVO.CTRANTYPEID);
    }
  }

  /**
   * �ϲ��еĳɱ�Ҫ��
   * 
   * @param sumItem
   * @param detailItem
   * @param costfactorPreName
   */
  public static void sumCostfactor(CircularlyAccessibleValueObject sumItem,
      CircularlyAccessibleValueObject detailItem, String costfactorPreName) {
    for (int i = 1; i <= CostfactorVO.MAX_NUM; i++) {
      String factorKey = costfactorPreName + String.valueOf(i);
      UFDouble sumFactor = (UFDouble) sumItem.getAttributeValue(factorKey);
      UFDouble detailFactor =
          (UFDouble) detailItem.getAttributeValue(factorKey);
      sumFactor = MathTool.add(sumFactor, detailFactor);
      sumItem.setAttributeValue(factorKey, MathTool.isZero(sumFactor) ? null
          : sumFactor);
    }
  }

  /**
   * ����ָ������ֵ�ֶε�����VO��
   * 
   * @param sumItem
   * @param detailItem
   * @param fields
   */
  public static void sumNumValueFields(CircularlyAccessibleValueObject sumItem,
      CircularlyAccessibleValueObject detailItem, String[] sumFields) {
    for (String sumFd : sumFields) {
      UFDouble sumMny = (UFDouble) sumItem.getAttributeValue(sumFd);
      UFDouble detailMny = (UFDouble) detailItem.getAttributeValue(sumFd);
      sumMny = MathTool.add(sumMny, detailMny);
      sumItem.setAttributeValue(sumFd, MathTool.isZero(sumMny) ? null : sumMny);
    }
  }

  private static void clearInfo(ISuperVO vo, String[] names) {
    for (String name : names) {
      vo.setAttributeValue(name, null);
    }
  }

  private static void combineItems(AbstractBill bill,
      MapList<String, CircularlyAccessibleValueObject> nitemMap) {
    List<CircularlyAccessibleValueObject> nitemLst =
        new ArrayList<CircularlyAccessibleValueObject>();
    for (Entry<String, List<CircularlyAccessibleValueObject>> entry : nitemMap
        .entrySet()) {
      List<CircularlyAccessibleValueObject> itemLst = entry.getValue();
      CircularlyAccessibleValueObject sumItem = itemLst.get(0);
      for (int i = 1; i < itemLst.size(); i++) {
        // ���ɱ�Ҫ�ػ���
        EstTOIAUtil.sumCostfactor(sumItem, itemLst.get(i),
            P4639ItemVO.NFACTOR1.substring(0, 7));
        String[] sumFields = {
          AbstractBaseItemVO.NMNY
        };
        EstTOIAUtil.sumNumValueFields(sumItem, itemLst.get(i), sumFields);
      }
      nitemLst.add(sumItem);
    }
    CircularlyAccessibleValueObject[] nItems =
        new ListToArrayTool<CircularlyAccessibleValueObject>()
            .convertToArray(nitemLst);
    bill.setChildrenVO(nItems);
  }
  
  
  /**
   * ����PurchaseinFIVO����(��������)�ĵ��ݾ���Ϊ�ɱ����۾���<br>
   * ��������.�Ƴɱ����� (pk_stockps_b.nestcalcostprice)
   * �����ݹ�<br>
   * ���ɹ�����С�����ȸ��ڳɱ����۾���ʱ�账������������У��ͨ�����������������Ҫ����
   * 
   * @param m4201s
   */
  public static void adjustM4201Price(PurchaseinFIVO[] m4201s) {
	    String pk_group = InvocationInfoProxy.getInstance().getGroupId();
	    ScaleObjectFactory sof = new ScaleObjectFactory(pk_group);
	    int costdg = sof.getCostPriceScaleObject().getDigit();
	    int podg   = sof.getPriceScaleObject().getDigit();
	    for (PurchaseinFIVO m4201 : m4201s) {
	      for (PurchaseinFIItemVO item : m4201.getChildrenVO()) {
	    	//wangzhqf 2015-03-09 17:01����I2ItemVO ����Ϊ�գ�IA�Ǳ��ֲ�����VO����,���������´���
	    	  //1.ȡ���۵�С��λ,��ȡ��ĩβ����
	    	  //2.�ٺ�IA�ľ������Ƚϡ�
	    	  String str_nprice = item.getNestcalcostprice().toString();
	    	  if(str_nprice != null){
	    	  	while(str_nprice.endsWith("0")){
		    		  str_nprice = str_nprice.substring(0,str_nprice.length()-1);
		    	  }
		    	  if(str_nprice.indexOf(".")!=-1){
		    		  String decimail_after = str_nprice.substring(str_nprice.indexOf(".")+1, str_nprice.length());
		    		  podg = decimail_after.length();
		    	  }
	    	  }
	        if (costdg >= podg) {
	          continue;
	        }
	        UFDouble mny = MathTool.nvl(item.getNmny());
	        UFDouble num = MathTool.nvl(item.getNestnum());
	        item.setNestcalcostprice(mny.div(num, costdg));
	      }
	    }
	  }
}
