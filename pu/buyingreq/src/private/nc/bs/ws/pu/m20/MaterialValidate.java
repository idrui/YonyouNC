package nc.bs.ws.pu.m20;

import java.sql.SQLException;
import java.util.List;

import javax.resource.cci.ResultSet;

import nc.bs.dao.BaseDAO;
import nc.bs.dao.DAOException;
import nc.bs.framework.common.NCLocator;
import nc.itf.bd.material.assign.IMaterialAssignService;
import nc.jdbc.framework.SQLParameter;
import nc.jdbc.framework.processor.ResultSetProcessor;
import nc.vo.bd.material.MaterialVO;
import nc.vo.bd.material.MaterialVersionVO;
import nc.vo.ecpubapp.pattern.exception.ExceptionUtils;
import nc.vo.pu.m20.ws.entity.MsgCGAG000001;
import nc.vo.pu.m20.ws.entity.MsgCGAG000001Head;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;

/**
 * <p>本类主要实现功能：</p>
 *
 * <li>攀钢采购计划信息中的物料信息检查</li>
 * <li>如果物料编码不存在，根据生成规则生成物料档案 </li>
 * <li>如果物料编码存在，检查规格、型号、计量单位与现有物料档案是否一致，不一致先物料版本化，更新物料档案 </li>
 * 
 * @author lyw
 * @version 6.5
 * @time 2017年3月16日 下午6:06:10
 */
public class MaterialValidate {

	static BaseDAO baseDao = new BaseDAO();
	
	/**
	 * 比较攀钢的物料档案与NC物料档案比对
	 * 检查编码、规格、型号、计量单位是否相同                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                          
	 * @param code
	 * @param spec
	 * @param type
	 * @param unitid
	 * @return
	 */
	public static String checkuniformity(String code,String spec,String type,String unitid) {
		String sqlstr = "select pk_material from bd_material where code = ? and materialspec = ? and materialtype = ? "
				+ "and pk_measdoc = ? and dr = 0 and enablestate =2";
		SQLParameter sp = new SQLParameter();
		sp.addParam(code);
		sp.addParam(spec);
		sp.addParam(type);
		sp.addParam(unitid);
		String pkstr = null;
		try {
			 pkstr = (String) baseDao.executeQuery(sqlstr, sp, new ResultSetProcessor() {
				@Override
				public Object handleResultSet(java.sql.ResultSet rs)
						throws SQLException {
					// TODO 自动生成的方法存根
					String ll = null;
					if (rs.next()) {
						ll = rs.getString(1);
					}
					return ll;
				}
			});
		} catch (DAOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
			ExceptionUtils.wrappBusinessException("检查相同物料，执行失败!");
		}
		
		return pkstr;
	}

	/**
	 * 根据物料编码获取物料主键
	 * @param materialcode
	 * @return
	 */
	public static String getPKByName(String materialcode) {
		// TODO 自动生成的方法存根
		String sqlstr = "select pk_material from bd_material where code = '" + materialcode + "'";
		String pkstr = null;
		try {
			pkstr = (String) baseDao.executeQuery(sqlstr, new ResultSetProcessor(){
				@Override
				public Object handleResultSet(java.sql.ResultSet rs)
						throws SQLException {
					// TODO 自动生成的方法存根
					String ll = null;
					if (rs.next()) {
						ll = rs.getString(1);
					};
					return ll;
				}});
		} catch (DAOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
			ExceptionUtils.wrappBusinessException("物料PK查询，执行失败!");
		}
		return pkstr;
	}
	/**
	 * 生成物料档案
	 * @param pk_group
	 * @param pk_org
	 * @param pk_user
	 * @param materialcode
	 * @param materialname
	 * @param pk_measdoc
	 * @param materialspec
	 * @param materialtype
	 * @param marbaseclass
	 * @param graphid
	 * @param ddatetime 
	 * @param ddate 
	 * @param version 
	 * @return
	 */
	public static String generateMaterialDoc(String pk_group, String pk_org,
			String pk_user, String materialcode, String materialname,
			String pk_measdoc, String materialspec, String materialtype,
			String marbaseclass, String graphid, UFDate ddate, UFDateTime ddatetime, Integer version) {
		// TODO 自动生成的方法存根
		//多版本物料
		MaterialVO vo = new MaterialVO();
		//最新物料档案
		MaterialVersionVO vvo = new MaterialVersionVO();
		String pk_material = null;
		vo.setCode(materialcode);
		vo.setCreationtime(ddatetime);
		vo.setCreator(pk_user);
		vo.setGraphid(graphid);
		vo.setMaterialspec(materialspec);
		vo.setMaterialtype(materialtype);
		vo.setName(materialname);
		vo.setPk_marbasclass(marbaseclass);
		vo.setPk_measdoc(pk_measdoc);
		vo.setPk_group(pk_group);
		vo.setPk_org(pk_group);
		vo.setVersion(version);
		//启用
		vo.setEnablestate(2);
		//分布式 2-下级上报
		vo.setDataoriginflag(2);
		//入库容差
		vo.setIntolerance(new UFDouble(0));
		//是否最新
		vo.setLatest(UFBoolean.TRUE);
		//出库关闭下容差（%）
		vo.setOutcloselowerlimit(new UFDouble(0));
		//出库容差（%）
		vo.setOuttolerance(new UFDouble(0));
		//产品簇
		vo.setProductfamily(UFBoolean.FALSE);
		//物料最新版本
		vvo.setCode(materialcode);
		vvo.setCreationtime(ddatetime);
		vvo.setCreator(pk_user);
		vvo.setGraphid(graphid);
		vvo.setMaterialspec(materialspec);
		vvo.setMaterialtype(materialtype);
		vvo.setName(materialname);
		vvo.setPk_marbasclass(marbaseclass);
		vvo.setPk_measdoc(pk_measdoc);
		vvo.setPk_group(pk_group);
		vvo.setPk_org(pk_org);
		vvo.setVersion(version);
		//启用
		vvo.setEnablestate(2);
		//分布式 2-下级上报
		vvo.setDataoriginflag(2);
		//入库容差
		vvo.setIntolerance(new UFDouble(0));
		//出库关闭下容差（%）
		vvo.setOutcloselowerlimit(new UFDouble(0));
		//出库容差（%）
		vvo.setOuttolerance(new UFDouble(0));
		//产品簇
		vvo.setProductfamily(UFBoolean.FALSE);
		try {
			//生成集团级物料档案
			pk_material = baseDao.insertVO(vo);
			try {
				getAssignService().assignMaterialForPf(pk_material, pk_org, null);
			} catch (BusinessException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
			vvo.setPk_material(pk_material);
			baseDao.insertVO(vvo);
		} catch (DAOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return pk_material;
	}
	/**
	 * @return
	 */
	private static IMaterialAssignService getAssignService() {
		// TODO 自动生成的方法存根
		IMaterialAssignService assignService = NCLocator.getInstance().lookup(IMaterialAssignService.class);
		return assignService;
	}

	/**
	 * 根据物料编码，获取对应的当前最新物料版本
	 * @param pk_material
	 * @return
	 */
	public static Integer getVersion(String materialcode) {
		// TODO 自动生成的方法存根
		String sqlstr = "select max(version) from bd_material where code = '" + materialcode + "'";
		Integer version = null;
		try {
			version = (Integer) baseDao.executeQuery(sqlstr, new ResultSetProcessor(){
				@Override
				public Object handleResultSet(java.sql.ResultSet rs)
						throws SQLException {
					// TODO 自动生成的方法存根
					Integer ll = null;
					if (rs.next()) {
						ll = rs.getInt(1);
					};
					return ll;
				}});
		} catch (DAOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
			ExceptionUtils.wrappBusinessException("物料版本PK查询，执行失败!");
		}
		return version;
	}
	/**
	 * 根据物料编码，删除原有版本物料 for bd_material_v
	 * @param materialcode
	 */
	public static void deleteMaterial(String materialcode) {
		// TODO 自动生成的方法存根
		String wherestr = " code = '" + materialcode + "'";
		try {
			baseDao.deleteByClause(MaterialVersionVO.class, wherestr);
		} catch (DAOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}

	}
	

}
