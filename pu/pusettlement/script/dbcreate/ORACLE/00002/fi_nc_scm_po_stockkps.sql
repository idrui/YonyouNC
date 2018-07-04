/* indexcode: i_pu_45_code */
create  index i_pu_45_code on po_purchaseinfi (vbillcode asc)
/

/* indexcode: i_pu_45_dtorg */
create  index i_pu_45_dtorg on po_purchaseinfi (dbilldate asc,
pk_org asc)
/

/* indexcode: i_pu_45fd_fk */
create  index i_pu_45fd_fk on po_purchaseinfi_fd (pk_stockps_fee asc)
/

/* indexcode: i_pu_45fd_bybid */
create  index i_pu_45fd_bybid on po_purchaseinfi_fd (pk_distbybill_b asc)
/

/* indexcode: i_pu_45_b_dtfi */
create  index i_pu_45_b_dtfi on po_purchaseinfi_b (dbizdate asc,
pk_financeorg asc)
/

/* indexcode: i_pu_45_b_estdt */
create  index i_pu_45_b_estdt on po_purchaseinfi_b (dtocostapdate asc)
/

/* indexcode: i_pu_45_b_ordb */
create  index i_pu_45_b_ordb on po_purchaseinfi_b (pk_order_b asc)
/

/* indexcode: i_pu_45_b_fk */
create  index i_pu_45_b_fk on po_purchaseinfi_b (pk_stockps asc)
/

/* indexcode: i_pu_45_b_ordh */
create  index i_pu_45_b_ordh on po_purchaseinfi_b (pk_order asc)
/

/* indexcode: i_pu_45fee_fkh */
create  index i_pu_45fee_fkh on po_purchaseinfi_fee (pk_stockps asc)
/

/* indexcode: i_pu_45fee_fkb */
create  index i_pu_45fee_fkb on po_purchaseinfi_fee (pk_stockps_b asc)
/

/* indexcode: i_pu_45fee_stlb */
create  index i_pu_45fee_stlb on po_purchaseinfi_fee (pk_firstsettle_b asc)
/

