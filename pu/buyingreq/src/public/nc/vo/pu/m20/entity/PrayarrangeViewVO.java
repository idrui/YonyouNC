/**
 * 
 */
package nc.vo.pu.m20.entity;

import java.util.ArrayList;
import java.util.List;

import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.model.entity.view.AbstractDataView;
import nc.vo.pubapp.pattern.model.meta.entity.view.DataViewMetaFactory;
import nc.vo.pubapp.pattern.model.meta.entity.view.IDataViewMeta;
import nc.vo.pubapp.pattern.model.tool.BillComposite;

import org.apache.commons.lang.ArrayUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul> 
 * <li> 请购安排的视图VO
 * </ul>
 * <p>
 * <p>
 *
 * @version
 * @since
 * @author luojw
 * @time 2014-6-19 上午10:30:36
 */
public class PrayarrangeViewVO extends AbstractDataView {

	/**
	 *  serialVersionUID
	 */
	private static final long serialVersionUID = -6444664774183203389L;

	public static PraybillVO[] getPraybillVO(AbstractDataView[] views) {
	    if (ArrayUtils.isEmpty(views)) {
	      return null;
	    }

	    List<PraybillHeaderVO> heads = new ArrayList<PraybillHeaderVO>();
	    List<PraybillItemVO> items = new ArrayList<PraybillItemVO>();

	    for (AbstractDataView view : views) {
	      heads.add((PraybillHeaderVO) view.getVO(PraybillHeaderVO.class));
	      items.add((PraybillItemVO) view.getVO(PraybillItemVO.class));
	    }

	    BillComposite<PraybillVO> bc =
	        new BillComposite<PraybillVO>(PraybillVO.class);
	    PraybillVO vo = new PraybillVO();
	    bc.append(vo.getMetaData().getParent(),
	        heads.toArray(new PraybillHeaderVO[heads.size()]));
	    bc.append(vo.getMetaData().getVOMeta(PraybillItemVO.class),
	        items.toArray(new PraybillItemVO[items.size()]));
	    return bc.composite();
	    
	}

	/** 行关闭 **/
	public UFBoolean getBrowclose() {
		return (UFBoolean) this.getAttributeValue(PraybillItemVO.BROWCLOSE);
	}

	/** 本币币种 **/
	public String getCcurrencyid() {
		return (String) this.getAttributeValue(PraybillHeaderVO.CCURRENCYID);
	}

	/** 表体时间戳 **/
	public UFDateTime getTs(){
		return (UFDateTime) this.getAttributeValue(PraybillItemVO.TS);
	}
	
	public PraybillHeaderVO getHead() {
		return (PraybillHeaderVO) this.getVO(PraybillHeaderVO.class);
	}

	public PraybillItemVO getItem() {
		return (PraybillItemVO) this.getVO(PraybillItemVO.class);
	}
	  
	  
	
	/* 
	 * 父类方法重写
	 *
	 * @see nc.vo.pubapp.pattern.model.entity.view.AbstractDataView#getMetaData()
	 */
	@Override
	public IDataViewMeta getMetaData() {
		return DataViewMetaFactory.getInstance().getBillViewMeta(PraybillVO.class);
	}
	
	/** 累计订货数量 **/
	public UFDouble getNaccumulatenum() {
		return (UFDouble) this.getAttributeValue(PraybillItemVO.NACCUMULATENUM);
	}

	/** 数量 **/
	public UFDouble getNassistnum() {
		return (UFDouble) this.getAttributeValue(PraybillItemVO.NASTNUM);
	}

	/** 生成合同次数 **/
	public Integer getNgenct() {
		return (Integer) this.getAttributeValue(PraybillItemVO.NGENCT);
	}

	/** 主数量 **/
	public UFDouble getNnum() {
		return (UFDouble) this.getAttributeValue(PraybillItemVO.NNUM);
	}

	/** 生成价格审批单次数 **/
	public Integer getNpriceauditbill() {
		return (Integer) this.getAttributeValue(PraybillItemVO.NPRICEAUDITBILL);
	}

	/** 生成询报价单次数 **/
	public Integer getNquotebill() {
		return (Integer) this.getAttributeValue(PraybillItemVO.NQUOTEBILL);
	}

	/** 本币价税合计 **/
	public UFDouble getNtaxmny() {
		return (UFDouble) this.getAttributeValue(PraybillItemVO.NTAXMNY);
	}

	/** 主本币含税单价 **/
	public UFDouble getNtaxprice() {
		return (UFDouble) this.getAttributeValue(PraybillItemVO.NTAXPRICE);
	}

	/** 请购单行ID **/
	public String getPk_praybill_b() {
		return (String) this.getAttributeValue(PraybillItemVO.PK_PRAYBILL_B);
	}

	/** 采购组织 **/
	public String getPk_purchaseorg() {
		return (String) this.getAttributeValue(PraybillItemVO.PK_PURCHASEORG);
	}

	/** 已安排 **/
	public UFBoolean getBisarrange() {
		return (UFBoolean) this.getAttributeValue(PraybillItemVO.BISARRANGE);
	}

	
	public void setHead(PraybillHeaderVO head) {
		this.setVO(head);
	}

	public void setItem(PraybillItemVO item) {
		this.setVO(item);
	}

	/** 累计订货数量 **/
	public void setNaccumulatenum(UFDouble naccumulatenum) {
		this.setAttributeValue(PraybillItemVO.NACCUMULATENUM, naccumulatenum);
	}

	/** 数量 **/
	public void setNassistnum(UFDouble nassistnum) {
		this.setAttributeValue(PraybillItemVO.NASTNUM, nassistnum);
	}

	/** 生成合同次数 **/
	public void setNgenct(Integer ngenct) {
		this.setAttributeValue(PraybillItemVO.NGENCT, ngenct);
	}

	/** 主数量 **/
	public void setNnum(UFDouble nnum) {
		this.setAttributeValue(PraybillItemVO.NNUM, nnum);
	}

	/** 生成价格审批单次数 **/
	public void setNpriceauditbill(Integer npriceauditbill) {
		this.setAttributeValue(PraybillItemVO.NPRICEAUDITBILL, npriceauditbill);
	}

	/** 生成询报价单次数 **/
	public void setNquotebill(Integer nquotebill) {
		this.setAttributeValue(PraybillItemVO.NQUOTEBILL, nquotebill);
	}

	/** 本币价税合计 **/
	public void setNtaxmny(UFDouble ntaxmny) {
		this.setAttributeValue(PraybillItemVO.NTAXMNY, ntaxmny);
	}

	/** 主本币含税单价 **/
	public void setNtaxprice(UFDouble ntaxprice) {
		this.setAttributeValue(PraybillItemVO.NTAXPRICE, ntaxprice);
	}
	
	/** 已安排 **/
	public void setBisarrange(UFBoolean bisarrange){
		this.setAttributeValue(PraybillItemVO.BISARRANGE, bisarrange);
	}

}
