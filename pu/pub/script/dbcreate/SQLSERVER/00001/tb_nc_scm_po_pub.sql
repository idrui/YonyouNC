/* tablename: �ɹ���Ʊ�������� */
create table po_invctrantype (
pk_invctrantype nchar(20) not null 
/*�ɹ���Ʊ��������ID*/,
itoarapmode int not null 
/*��Ӧ������*/,
bcheckquality nchar(1) not null 
/*�Ƿ������ϸ���*/,
bconsumepur nchar(1) not null 
/*�Ƿ������Բɹ�*/,
bsendpay nchar(1) not null 
/*��������Զ���Ӧ��*/,
pk_group nchar(20) not null 
/*��������*/,
vtrantypecode nvarchar(20) not null 
/*��������*/,
ctrantypeid nvarchar(20) null 
/*�������ͱ���*/,
bajust nchar(1) null 
/*������Ʊ*/,
 constraint pk_po_invctrantype primary key (pk_invctrantype),
 ts char(19) null default convert(char(19),getdate(),20),
dr smallint null default 0
)
go

/* tablename: �ɹ�����������??? */
create table po_potrantype (
pk_potrantype nchar(20) not null 
/*����*/,
vtrantypecode nvarchar(20) not null 
/*��������*/,
bvmi nchar(1) null 
/*��Ӧ�̼Ĵ�*/,
bdirect nchar(1) null 
/*ֱ�˲ɹ�*/,
bcheckcenpur nchar(1) null 
/*���Ʋɹ������Ƿ���ɹ�ҵ��ί�й�ϵ*/,
boverpay nchar(1) null 
/*������������*/,
breceiveplan nchar(1) null 
/*���е����ƻ�����*/,
iprtopolimit int null 
/*�빺�����ɶ������Ʒ�ʽ*/,
boutput nchar(1) null 
/*���*/,
bconfirm nchar(1) null 
/*�Է�ȷ��*/,
bconsign nchar(1) null 
/*��������*/,
bload nchar(1) null 
/*װ�˻���*/,
bcustom nchar(1) null 
/*���ػ���*/,
boutcustom nchar(1) null 
/*���ػ���*/,
bconfirmcode nchar(1) null 
/*�Է�ȷ�ϵ��ݺ�*/,
bconfirmdate nchar(1) null 
/*�Է�ȷ�ϵ�������*/,
bconfirmnum nchar(1) null 
/*�Է�ȷ�ϵ�������*/,
bconsigncode nchar(1) null 
/*�������ݺ�*/,
bconsigndate nchar(1) null 
/*������������*/,
bconsignnum nchar(1) null 
/*������������*/,
bloadcode nchar(1) null 
/*װ�˵��ݺ�*/,
bloaddate nchar(1) null 
/*װ�˵�������*/,
bcustomcode nchar(1) null 
/*���ص��ݺ�*/,
bcustomdate nchar(1) null 
/*���ص�������*/,
boutcustomcode nchar(1) null 
/*���ص��ݺ�*/,
boutcustomdate nchar(1) null 
/*���ص�������*/,
barrive nchar(1) null 
/*����*/,
bstore nchar(1) null 
/*���*/,
pk_group nvarchar(20) null 
/*����*/,
ionwayend int null 
/*������;����״̬*/,
ionwaybegin int null 
/*������;��ʼ״̬*/,
iapproveaft int null 
/*��˺���״̬*/,
ioutputaft int null 
/*�������״̬*/,
iconfirmaft int null 
/*ȷ�Ϻ���״̬*/,
iconsignaft int null 
/*��������״̬*/,
iloadaft int null 
/*װ�˺���״̬*/,
icustomaft int null 
/*���غ���״̬*/,
ioutcustomaft int null 
/*���غ���״̬*/,
ctrantypeid nvarchar(20) null 
/*�������ͱ���*/,
 constraint pk_po_potrantype primary key (pk_potrantype),
 ts char(19) null default convert(char(19),getdate(),20),
dr smallint null default 0
)
go

/* tablename: �빺���������� */
create table po_buyrtrantype (
pk_buyrtrantype nchar(20) not null 
/*�빺����������*/,
pk_group nvarchar(20) not null 
/*��������*/,
vtrantypecode nvarchar(20) not null 
/*�������ͱ���*/,
ctrantypeid nchar(20) not null 
/*��������*/,
bneedarrange nchar(1) not null 
/*��Ҫ�빺����*/,
 constraint pk_po_buyrtrantype primary key (pk_buyrtrantype),
 ts char(19) null default convert(char(19),getdate(),20),
dr smallint null default 0
)
go

