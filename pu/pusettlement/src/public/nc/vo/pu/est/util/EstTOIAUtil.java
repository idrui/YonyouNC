package nc.vo.pu.est.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import nc.bs.framework.common.InvocationInfoProxy;
import nc.pubitf.ia.mi2.po.I2AdjustBackData;
import nc.vo.ia.bill.entity.base.AbstractBaseBill;
import nc.vo.ia.bill.entity.base.AbstractBaseItemVO;
import nc.vo.ia.bill.entity.real.AbstractRealHeadVO;
import nc.vo.ia.bill.entity.real.AbstractRealItemVO;
import nc.vo.ia.mi2.entity.I2BillVO;
import nc.vo.ia.mi2.entity.I2ItemVO;
import nc.vo.ia.mi9.entity.I9ItemVO;
import nc.vo.pu.costfactor.entity.CostfactorVO;
import nc.vo.pu.est.entity.GoodsEstVO;
import nc.vo.pu.m4201.entity.PurchaseinFIHeaderVO;
import nc.vo.pu.m4202.entity.VmiFIHeaderVO;
import nc.vo.pu.m4202.entity.VmiFIVO;
import nc.vo.pu.pub.util.PUSysParamUtil;
import nc.vo.pub.CircularlyAccessibleValueObject;
import nc.vo.pub.ISuperVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.model.entity.bill.IBill;
import nc.vo.pubapp.pattern.pub.ListToArrayTool;
import nc.vo.pubapp.pattern.pub.MapList;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.pubapp.scale.ScaleObjectFactory;

import org.apache.commons.lang.StringUtils;

/**
 * 暂估到存货核算时对财务数据进行处理的工具类
 * 
 * @since 6.0
 * @version 2011-1-11 下午04:00:46
 * @author zhaoyha
 */
public class EstTOIAUtil {

  /**
   * 回冲数据的单价处理，走存货核算的精度
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
   * 获取采购价格的精度
   * 
   * @param initPodg
   * @param initPrice
   * @return
   */
  protected static int getPODigit(int initPodg, String initPrice) {
    int podg = initPodg;
    String price = initPrice;
    while (price.endsWith("0")) {
      price = price.substring(0, price.length() - 1);
    }
    if (price.indexOf(".") != -1) {
    	String decimail_after = price.substring(
    	    price.indexOf(".") + 1, price.length());
    	podg = decimail_after.length();
    }
    return podg;
  }

  /**
   * 调整I2单据的单据精度为成本单价精度<br>
   * 用于结算和暂估<br>
   * 当采购单价小数精度高于成本单价精度时需处理，否则存货核算校验通不过；其它情况不需要处理
   * 
   * @param i2s
   */
  public static void adjustI2Price(I2BillVO[] i2s) {
    String pk_group = InvocationInfoProxy.getInstance().getGroupId();
    ScaleObjectFactory sof = new ScaleObjectFactory(pk_group);
    int costdg = sof.getCostPriceScaleObject().getDigit();
    int podg   = sof.getPriceScaleObject().getDigit();
    for (I2BillVO i2 : i2s) {
      for (I2ItemVO item : i2.getChildrenVO()) {
    	  //wangzhqf 2015-03-09 17:01由于I2ItemVO 币种为空，IA那边又不给做VO对照,这里做如下处理：
    	  //1.取单价的小数位,截取掉末尾的零
    	  //2.再和IA的精度做比较。
        Object str_nprice = item.getNprice();
        if(str_nprice != null){
          podg = EstTOIAUtil.getPODigit(podg, str_nprice.toString());
        }
        if (costdg >= podg) {
          //继续走下一条的校验
          continue;
        }
        UFDouble mny = MathTool.nvl(item.getNmny());
        UFDouble num = MathTool.nvl(item.getNnum());
        item.setNprice(mny.div(num, costdg));
      }
    }
  }

  /**
   * 对分收集结的数据，清空库存组织及仓库
   * 
   * @param pfivos 财务副本VO数组
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

      EstTOIAUtil.clearInfo(pfivo.getParent(), names);
    }
  }

  /**
   * 将来源BID相同的I9和IG的进全部行进行合并<br>
   * 金额，成本要素，调整数量
   * 
   * @param adjustBills I9或IG的单据
   */
  public static void combineAdjustBillRow(AbstractBaseBill[] adjustBills) {
    for (AbstractBaseBill bill : adjustBills) {
      MapList<String, AbstractBaseItemVO> nitemMap =
          new MapList<String, AbstractBaseItemVO>();
      for (AbstractBaseItemVO item : bill.getChildrenVO()) {
        nitemMap.put(
            (String) item.getAttributeValue(AbstractRealItemVO.CSRCBID), item);
      }
      // 合并单据表体
      EstTOIAUtil.combineItems(bill, nitemMap);
    }
  }

  /**
   * 消耗汇总传存货核算单据入库类型补充
   * 
   * @param iaBill 存货核算单据
   * @param pk_fiOrg 财务组织
   * @param iaStockField 存货核算单据的入库类型字段
   */
  public static void setStockTranTypeForVMI(AbstractBaseBill iaBill,
      String pk_fiOrg, String iaStockField) {
    String stocTranType = PUSysParamUtil.getPO54(pk_fiOrg);
    if (!StringUtils.isEmpty(stocTranType)) {
      iaBill.getParentVO().setAttributeValue(iaStockField, stocTranType);
    }
  }

  /**
   * 消耗汇总传存货核算单据入库类型补充
   * 
   * @param iaBills 存货核算单据
   * @param srcVos 消耗汇总财务vo
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
      if (vmiFiVO == null) {// 数据正确的话这里不会为空
        continue;
      }
      String pk_fiOrg = vmiFiVO.getParentVO().getPk_financeorg();
      EstTOIAUtil.setStockTranTypeForVMI(iaBill, pk_fiOrg,
          AbstractRealHeadVO.CTRANTYPEID);
    }
  }

  /**
   * 合并行的成本要素
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
   * 汇总指定的数值字段到汇总VO中
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

  private static void combineItems(AbstractBaseBill bill,
      MapList<String, AbstractBaseItemVO> nitemMap) {
    List<AbstractBaseItemVO> nitemLst = new ArrayList<AbstractBaseItemVO>();
    for (Entry<String, List<AbstractBaseItemVO>> entry : nitemMap.entrySet()) {
      List<AbstractBaseItemVO> itemLst = entry.getValue();
      AbstractBaseItemVO sumItem = itemLst.get(0);
      for (int i = 1; i < itemLst.size(); i++) {
        // 将成本要素汇总
        EstTOIAUtil.sumCostfactor(sumItem, itemLst.get(i),
            I9ItemVO.NFACTOR1.substring(0, 7));
        String[] sumFields = {
          AbstractBaseItemVO.NMNY
        };
        EstTOIAUtil.sumNumValueFields(sumItem, itemLst.get(i), sumFields);
      }
      nitemLst.add(sumItem);
    }
    AbstractBaseItemVO[] nItems =
        new ListToArrayTool<AbstractBaseItemVO>().convertToArray(nitemLst);
    bill.setChildrenVO(nItems);
  }
}
