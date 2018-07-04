/* indexcode: i_pu_24_code */
create  index i_pu_24_code on po_pricesettle (vbillcode)
go

/* indexcode: i_pu_24_dtorg */
create  index i_pu_24_dtorg on po_pricesettle (dbilldate,
pk_org)
go

/* indexcode: i_pu_24_b_src */
create  index i_pu_24_b_src on po_pricesettle_b (csourceid)
go

/* indexcode: i_pu_24_srcbid */
create  index i_pu_24_srcbid on po_pricesettle_b (csourcebid)
go

/* indexcode: i_pu_24_b_fk */
create  index i_pu_24_b_fk on po_pricesettle_b (pk_pricesettle)
go

/* indexcode: i_pu_24_bb_fk */
create  index i_pu_24_bb_fk on po_pricesettle_bb (po_pricesettle)
go

