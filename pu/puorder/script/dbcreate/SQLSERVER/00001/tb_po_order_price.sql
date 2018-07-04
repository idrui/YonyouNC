/* tablename: �ɹ�ѯ����ϸ */
create table po_order_price_b (
pk_order_b nchar(20) not null 
/*�ɹ�������ϸ*/,
pk_order_price nchar(20) not null 
/*�ɹ�ѯ��_����*/,
 constraint pk_o_order_price_b primary key (pk_order_b),
 ts char(19) null default convert(char(19),getdate(),20),
dr smallint null default 0
)
go

/* tablename: �ɹ�ѯ�� */
create table po_order_price (
pk_order_price nchar(20) not null 
/*�ɹ�ѯ��*/,
pk_org nvarchar(20) null default '~' 
/*�ɹ���֯�汾��Ϣ*/,
pk_psfinanceorg nvarchar(20) null default '~' 
/*���������֯(OID)*/,
pk_arrvstoorg nvarchar(20) null default '~' 
/*�ջ������֯(OID)*/,
pk_srcmaterial nvarchar(20) null default '~' 
/*������Ϣ*/,
pk_supplier nvarchar(20) null default '~' 
/*��Ӧ��*/,
corigcurrencyid nvarchar(20) null default '~' 
/*����*/,
ftaxtypeflag int null default 1 
/*��˰���*/,
ntaxrate decimal(28,8) null 
/*˰��*/,
cunitid nvarchar(20) null default '~' 
/*����λ*/,
castunitid nvarchar(20) null default '~' 
/*��λ*/,
cqtunitid nvarchar(20) null default '~' 
/*���۵�λ*/,
dbilldate nchar(19) null 
/*��������*/,
modifiedtime nchar(19) null 
/*����޸�ʱ��*/,
nqtorigtaxnetprc decimal(28,8) null 
/*��˰����*/,
norigtaxnetprice decimal(28,8) null 
/*����˰����*/,
nqttaxnetprice decimal(28,8) null 
/*���Һ�˰����*/,
ntaxnetprice decimal(28,8) null 
/*�����Һ�˰����*/,
nqtnetprice decimal(28,8) null 
/*������˰����*/,
nnetprice decimal(28,8) null 
/*��������˰����*/,
norignetprice decimal(28,8) null 
/*����˰����*/,
nqtorignetprice decimal(28,8) null 
/*��˰����*/,
norigtaxprice decimal(28,8) null 
/*����˰����*/,
ntaxprice decimal(28,8) null 
/*�����Һ�˰����*/,
nqtorigtaxprice decimal(28,8) null 
/*��˰����*/,
nqttaxprice decimal(28,8) null 
/*���Һ�˰����*/,
norigprice decimal(28,8) null 
/*����˰����*/,
nqtorigprice decimal(28,8) null 
/*��˰����*/,
nqtprice decimal(28,8) null 
/*������˰����*/,
nprice decimal(28,8) null 
/*��������˰����*/,
nnetpricem decimal(28,8) null 
/*��������˰����ͼ�*/,
ntaxnetpricem decimal(28,8) null 
/*�����Һ�˰����ͼ�*/,
norignetpricem decimal(28,8) null 
/*����˰����ͼ�*/,
norigtaxnetpricem decimal(28,8) null 
/*����˰����ͼ�*/,
bvalidate nchar(1) null 
/*�Ƿ���Ч*/,
 constraint pk_po_order_price primary key (pk_order_price),
 ts char(19) null default convert(char(19),getdate(),20),
dr smallint null default 0
)
go

