/* tablename: �����������뵥��ϸ */
create table po_storereq_b (pk_storereq_b char(20) not null 
/*�����������뵥��ϸ*/,
pk_group varchar2(20) null 
/*����*/,
pk_org varchar2(20) null 
/*�����֯���°汾*/,
pk_org_v varchar2(20) null 
/*�����֯*/,
crowno varchar2(20) null 
/*�к�*/,
pk_material varchar2(20) null 
/*���ϰ汾��Ϣ*/,
pk_srcmaterial varchar2(20) null 
/*������Ϣ*/,
cunitid varchar2(20) null 
/*����λ*/,
nnum number(28,8) null 
/*������*/,
castunitid varchar2(20) null 
/*��λ*/,
nastnum number(28,8) null 
/*����*/,
vchangerate varchar2(60) null 
/*������*/,
ntaxprice number(28,8) null 
/*�����Һ�˰����*/,
ntaxmny number(28,8) null 
/*���Ҽ�˰�ϼ�*/,
pk_reqstordoc varchar2(20) null 
/*����ֿ�*/,
dreqdate varchar2(19) null 
/*��������*/,
pk_apppsn varchar2(20) null 
/*������*/,
pk_appdept_v varchar2(20) null 
/*���벿��*/,
pk_appdept varchar2(20) null 
/*���벿�����°汾*/,
pk_receiveaddress varchar2(20) null 
/*�ջ���ַ*/,
cdevareaid varchar2(20) null 
/*�ջ�����*/,
cdevaddrid varchar2(20) null 
/*�ջ��ص�*/,
csourcetypecode varchar2(20) null 
/*��Դ��������*/,
csourceid varchar2(20) null 
/*��Դ����*/,
csourcebid varchar2(20) null 
/*��Դ������ϸ*/,
vsourcecode varchar2(40) null 
/*��Դ���ݺ�*/,
vsourcetrantype varchar2(20) null 
/*��Դ��������*/,
vsourcerowno varchar2(20) null 
/*��Դ�����к�*/,
cfirsttypecode varchar2(20) null 
/*Դͷ��������*/,
cfirstid varchar2(20) null 
/*Դͷ����*/,
cfirstbid varchar2(20) null 
/*Դͷ������ϸ*/,
vfirstcode varchar2(40) null 
/*Դͷ���ݺ�*/,
vfirsttrantype varchar2(20) null 
/*Դͷ��������*/,
vfirstrowno varchar2(20) null 
/*Դͷ�����к�*/,
naccuoutnum number(28,8) null 
/*�ۼƳ���������*/,
naccuoutreqnum number(28,8) null 
/*�ۼ��������������*/,
bclose char(1) default 'N' null 
/*�Ƿ�ر�*/,
pk_batchcode varchar2(20) null 
/*���κ�����*/,
vbatchcode varchar2(40) null 
/*���κ�*/,
cvendorid varchar2(20) null 
/*��Ӧ��*/,
cproductorid varchar2(20) null 
/*��������*/,
cprojectid varchar2(20) null 
/*��Ŀ*/,
cprojecttaskid varchar2(20) null 
/*��Ŀ����*/,
ccostelementid varchar2(20) null 
/*�ɱ�Ҫ��*/,
vfree1 varchar2(101) null 
/*���ɸ�������1*/,
vfree2 varchar2(101) null 
/*���ɸ�������2*/,
vfree3 varchar2(101) null 
/*���ɸ�������3*/,
vfree4 varchar2(101) null 
/*���ɸ�������4*/,
vfree5 varchar2(101) null 
/*���ɸ�������5*/,
vfree6 varchar2(101) null 
/*���ɸ�������6*/,
vfree7 varchar2(101) null 
/*���ɸ�������7*/,
vfree8 varchar2(101) null 
/*���ɸ�������8*/,
vfree9 varchar2(101) null 
/*���ɸ�������9*/,
vfree10 varchar2(101) null 
/*���ɸ�������10*/,
vbdef1 varchar2(101) null 
/*�Զ�����1*/,
vbdef2 varchar2(101) null 
/*�Զ�����2*/,
vbdef3 varchar2(101) null 
/*�Զ�����3*/,
vbdef4 varchar2(101) null 
/*�Զ�����4*/,
vbdef5 varchar2(101) null 
/*�Զ�����5*/,
vbdef6 varchar2(101) null 
/*�Զ�����6*/,
vbdef7 varchar2(101) null 
/*�Զ�����7*/,
vbdef8 varchar2(101) null 
/*�Զ�����8*/,
vbdef9 varchar2(101) null 
/*�Զ�����9*/,
vbdef10 varchar2(101) null 
/*�Զ�����10*/,
vbdef11 varchar2(101) null 
/*�Զ�����11*/,
vbdef12 varchar2(101) null 
/*�Զ�����12*/,
vbdef13 varchar2(101) null 
/*�Զ�����13*/,
vbdef14 varchar2(101) null 
/*�Զ�����14*/,
vbdef15 varchar2(101) null 
/*�Զ�����15*/,
vbdef16 varchar2(101) null 
/*�Զ�����16*/,
vbdef17 varchar2(101) null 
/*�Զ�����17*/,
vbdef18 varchar2(101) null 
/*�Զ�����18*/,
vbdef19 varchar2(101) null 
/*�Զ�����19*/,
vbdef20 varchar2(101) null 
/*�Զ�����20*/,
vbmemo varchar2(181) null 
/*��ע*/,
dbilldate char(19) null 
/*��������*/,
ccurrencyid varchar2(20) null 
/*���ұ���*/,
pk_storereq char(20) not null 
/*�����������뵥_����*/,
naccumbuyreqnum number(28,8) null 
/*�ۼ��빺������*/,
pk_reqstoorg_v varchar2(20) null 
/*ԭʼ��������֯*/,
pk_reqstoorg varchar2(20) null 
/*ԭʼ��������֯���°汾*/,
pk_nextbalanceorg_v varchar2(20) null 
/*�´�ƽ������֯*/,
pk_nextbalanceorg varchar2(20) null 
/*�´�ƽ������֯���°汾*/,
bendgather char(1) null 
/*��ƽ��*/,
naccustornum number(28,8) null 
/*�������������*/,
nnetnum number(28,8) null 
/*ת������������*/,
csourceid2 varchar2(20) null 
/*���ε���*/,
csourcebid2 varchar2(20) null 
/*���ε�����ϸ*/,
vsourcecode2 varchar2(40) null 
/*���ε��ݺ�*/,
vsourcerowno2 varchar2(20) null 
/*���ε����к�*/,
csourcetypecode2 varchar2(20) null 
/*���ε�������*/,
vsourcetrantype2 varchar2(20) null 
/*���ε��ݽ�������*/,
cfirstid2 varchar2(20) null 
/*��������*/,
cfirstbid2 varchar2(20) null 
/*����������ϸ*/,
vfirstcode2 varchar2(40) null 
/*����������*/,
vfirstrowno2 varchar2(20) null 
/*���������к�*/,
cfirsttypecode2 varchar2(20) null 
/*������������*/,
vfirsttrantype2 varchar2(20) null 
/*�������ݽ�������*/,
tgathertime char(19) null 
/*����ʱ��*/,
cgatherpsnid varchar2(20) null 
/*������*/,
cgatherid varchar2(20) null 
/*����ID*/,
naccumminusnum number(28,8) null 
/*����ƽ��ת�빺������*/,
cbs varchar2(20) null 
/*CBS*/,
 constraint pk_po_storereq_b primary key (pk_storereq_b),
 ts char(19) default to_char(sysdate,'yyyy-mm-dd hh24:mi:ss'),
dr number(10) default 0
)
/

/* tablename: �����������뵥 */
create table po_storereq (pk_storereq char(20) not null 
/*�����������뵥*/,
pk_group varchar2(20) null 
/*����*/,
pk_org varchar2(20) null 
/*�����֯���°汾*/,
pk_org_v varchar2(20) null 
/*�����֯*/,
vbillcode varchar2(40) null 
/*���뵥��*/,
dbilldate char(19) null 
/*��������*/,
pk_appdepth varchar2(20) null 
/*���벿�����°汾*/,
pk_appdepth_v varchar2(20) null 
/*���벿��*/,
pk_apppsnh varchar2(20) null 
/*������*/,
vtrantypecode varchar2(20) null 
/*�������ͱ������*/,
fbillstatus integer default 0 null 
/*����״̬*/,
burgency char(1) default 'N' null 
/*����*/,
ntotalastnum number(28,8) null 
/*������*/,
ntotalorigmny number(28,8) null 
/*��˰�ϼ�*/,
pk_project varchar2(20) null 
/*��Ŀ*/,
creator varchar2(20) null 
/*������*/,
creationtime char(19) null 
/*����ʱ��*/,
billmaker varchar2(20) null 
/*�Ƶ���*/,
dmakedate char(19) null 
/*�Ƶ�����*/,
approver varchar2(20) null 
/*������*/,
taudittime varchar2(19) null 
/*��������*/,
modifier varchar2(20) null 
/*����޸���*/,
modifiedtime varchar2(19) null 
/*����޸�ʱ��*/,
iprintcount integer default 0 null 
/*��ӡ����*/,
vdef1 varchar2(101) null 
/*�Զ�����1*/,
vdef2 varchar2(101) null 
/*�Զ�����2*/,
vdef3 varchar2(101) null 
/*�Զ�����3*/,
vdef4 varchar2(101) null 
/*�Զ�����4*/,
vdef5 varchar2(101) null 
/*�Զ�����5*/,
vdef6 varchar2(101) null 
/*�Զ�����6*/,
vdef7 varchar2(101) null 
/*�Զ�����7*/,
vdef8 varchar2(101) null 
/*�Զ�����8*/,
vdef9 varchar2(101) null 
/*�Զ�����9*/,
vdef10 varchar2(101) null 
/*�Զ�����10*/,
vdef11 varchar2(101) null 
/*�Զ�����11*/,
vdef12 varchar2(101) null 
/*�Զ�����12*/,
vdef13 varchar2(101) null 
/*�Զ�����13*/,
vdef14 varchar2(101) null 
/*�Զ�����14*/,
vdef15 varchar2(101) null 
/*�Զ�����15*/,
vdef16 varchar2(101) null 
/*�Զ�����16*/,
vdef17 varchar2(101) null 
/*�Զ�����17*/,
vdef18 varchar2(101) null 
/*�Զ�����18*/,
vdef19 varchar2(101) null 
/*�Զ�����19*/,
vdef20 varchar2(101) null 
/*�Զ�����20*/,
vmemo varchar2(181) null 
/*��ע*/,
freqtypeflag integer null 
/*��������*/,
ctrantypeid varchar2(20) null 
/*����������������*/,
 constraint pk_po_storereq primary key (pk_storereq),
 ts char(19) default to_char(sysdate,'yyyy-mm-dd hh24:mi:ss'),
dr number(10) default 0
)
/

