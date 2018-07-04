package nc.impl.pu.m20;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.web.client.RestTemplate;

import nc.bs.dao.BaseDAO;
import nc.itf.pu.m20.IParybillstaus;
import nc.jdbc.framework.processor.ResultSetProcessor;
import nc.vo.ecpubapp.pattern.exception.ExceptionUtils;
import nc.vo.pu.ms.MsgAGCG000001Head;

public class ParybillstausImpl implements IParybillstaus {

	@Override
	public String MsgAGXSHT0001(MsgAGCG000001Head msgAGCG000001Head) {
		String flag=this.MsgAGCG000001HeadSend(msgAGCG000001Head);
		return flag;
	}
	public String MsgAGCG000001HeadSend(MsgAGCG000001Head msgAGCG000001Head) {
		RestTemplate rt = new RestTemplate();
		String cs = rt.postForObject(getRestURL("MsgAGCG000001"),msgAGCG000001Head,String.class);//发送
		return cs;
	}
	
	/**
	 * 通用查询:查询电文Rest路径
	 * @param telId - 电文ID
	 * @return String 返回请求路径
	 * 生产环境与测试环境通过数据库表:RT_BASEURL配置,该方法不需要做任何修改
	 * 开发者:chenqk
	 */
	@SuppressWarnings("serial")
	public String getRestURL(String telId){
		BaseDAO baseDao = new BaseDAO();
		String rsSql = "SELECT URL_PATH FROM RT_BASEURL WHERE PK_ID='"+ telId + "'";
		String retString = null;
		try {
			retString =(String) baseDao.executeQuery(rsSql, new ResultSetProcessor() {
				@Override
				public String handleResultSet(ResultSet rs) throws SQLException {
					String url = null;
					while (rs.next()) {
						url = rs.getString(1);
					}
					return url;
				}
			});
		} catch (Exception e) {
			ExceptionUtils.wrappException(e);
		}
		return retString.trim();
	}

}
