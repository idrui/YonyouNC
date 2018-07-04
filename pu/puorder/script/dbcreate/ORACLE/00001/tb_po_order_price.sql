/* tablename: �ɹ�ѯ����ϸ */
create table po_order_price_b (pk_order_b char(20) not null 
/*�ɹ�������ϸ*/,
pk_order_price char(20) not null 
/*�ɹ�ѯ��_����*/,
 constraint pk_o_order_price_b primary key (pk_order_b),
 ts char(19) default to_char(sysdate,'yyyy-mm-dd hh24:mi:ss'),
dr number(10) default 0
)
/

/* tablename: �ɹ�ѯ�� */
create table po_order_price (pk_order_price char(20) not null 
/*�ɹ�ѯ��*/,
pk_org varchar2(20) default '~' null 
/*�ɹ���֯�汾��Ϣ*/,
pk_psfinanceorg varchar2(20) default '~' null 
/*���������֯(OID)*/,
pk_arrvstoorg varchar2(20) default '~' null 
/*�ջ������֯(OID)*/,
pk_srcmaterial varchar2(20) default '~' null 
/*������Ϣ*/,
pk_supplier varchar2(20) default '~' null 
/*��Ӧ��*/,
corigcurrencyid varchar2(20) default '~' null 
/*����*/,
ftaxtypeflag integer default 1 null 
/*��˰���*/,
ntaxrate number(28,8) null 
/*˰��*/,
cunitid varchar2(20) default '~' null 
/*����λ*/,
castunitid varchar2(20) default '~' null 
/*��λ*/,
cqtunitid varchar2(20) default '~' null 
/*���۵�λ*/,
dbilldate char(19) null 
/*��������*/,
modifiedtime char(19) null 
/*����޸�ʱ��*/,
nqtorigtaxnetprc number(28,8) null 
/*��˰����*/,
norigtaxnetprice number(28,8) null 
/*����˰����*/,
nqttaxnetprice number(28,8) null 
/*���Һ�˰����*/,
ntaxnetprice number(28,8) null 
/*�����Һ�˰����*/,
nqtnetprice number(28,8) null 
/*������˰����*/,
nnetprice number(28,8) null 
/*��������˰����*/,
norignetprice number(28,8) null 
/*����˰����*/,
nqtorignetprice number(28,8) null 
/*��˰����*/,
norigtaxprice number(28,8) null 
/*����˰����*/,
ntaxprice number(28,8) null 
/*�����Һ�˰����*/,
nqtorigtaxprice number(28,8) null 
/*��˰����*/,
nqttaxprice number(28,8) null 
/*���Һ�˰����*/,
norigprice number(28,8) null 
/*����˰����*/,
nqtorigprice number(28,8) null 
/*��˰����*/,
nqtprice number(28,8) null 
/*������˰����*/,
nprice number(28,8) null 
/*��������˰����*/,
nnetpricem number(28,8) null 
/*��������˰����ͼ�*/,
ntaxnetpricem number(28,8) null 
/*�����Һ�˰����ͼ�*/,
norignetpricem number(28,8) null 
/*����˰����ͼ�*/,
norigtaxnetpricem number(28,8) null 
/*����˰����ͼ�*/,
bvalidate char(1) null 
/*�Ƿ���Ч*/,
 constraint pk_po_order_price primary key (pk_order_price),
 ts char(19) default to_char(sysdate,'yyyy-mm-dd hh24:mi:ss'),
dr number(10) default 0
)
/

