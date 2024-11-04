@AbapCatalog.sqlViewName: 'ZSALEVIEW'
@AccessControl.authorizationCheck: #NOT_REQUIRED
@ObjectModel.usageType.serviceQuality: #X
@ObjectModel.usageType.sizeCategory: #XL
@ObjectModel.usageType.dataClass: #MIXED
@EndUserText.label: 'Sales Analysis'
@VDM.viewType: #CONSUMPTION
@OData.publish: true
define view ZSales as select from vbrk as H
inner join vbrp as D on H.vbeln = D.vbeln
left outer join prcd_elements as CT on CT.knumv = D.knumv_ana and CT.kposn = D.posnr and CT.kschl= 'ZPOI'
//inner join tvgrt as SG on SG.vkgrp = D.vkgrp and SG.spras ='E'
//inner join tvkbt as SO on SO.vkbur =D.vkbur and SO.spras ='E'
//inner join tvkot as SOG on SOG.vkorg = H.vkorg and SOG.spras ='E'
//inner join tvtwt as DC on DC.vtweg = H.vtweg and DC.spras ='E'
//nner join t005u as RG on RG.bland = H.regio and RG.spras ='E' and RG.land1='MM'
//inner join t001w as P on P.werks = D.werks and P.spras ='E'   
//inner join t001l as S on S.werks = D.werks and S.lgort = D.lgort
{
   key H.vbeln as Bill_Document,
   key D.matnr as Material,
   // New composite key column combining Bill_Document and Material
  // key concat(concat(H.vbeln, '-'), D.matnr) as Composite_Key,
   H.vbtyp as Billing_Category,
   D.vkgrp as Sales_Group,
   D.vkbur as Sales_Office,
   concat(
       concat(substring(H.fkdat, 7, 2), '.'), 
       concat(substring(H.fkdat, 5, 2), concat('.', substring(H.fkdat, 1, 4)))
   ) as BILLING_DATE,
   H.vkorg as Sales_Org,
   H.regio as Region,
   H.vtweg as Dist_Channel,
   // Aggregate the quantities
   sum(D.volum) as Volume,
   D.voleh as V_Unit,
   D.werks as Plant,
   D.lgort as Storage_Loc,
   sum(D.netwr) as Net_Value,
   sum(D.bonba) as Rebate_Basis,
   sum(D.kzwi6) as Output_Tax,
   sum(CT.kwert) as CSD_Point
    //   P.name1 as Plant_D,
    //    DC.vtext as Dist_Channel_Desc,
    //   RG.bezei as Region,
    //   SOG.vtext as sales_org,
    //  SG.bezei as Sales_group,
    //  SO.bezei as Sales_Office,
    //   S.lgobe as Storage_Loc,
}
where H.vkorg ='1000' 
  and D.netwr <> 0
  and H.rfbsk <> 'E' 
  and H.vf_status <> 'C'  
  and H.sfakn = ' '
  and H.fkdat >= DATS_ADD_DAYS(cast($session.system_date as abap.dats), - 60, 'UNCHANGED')
  and H.fkdat <= DATS_ADD_DAYS(cast($session.system_date as abap.dats), 0, 'UNCHANGED')
group by H.vbeln, D.matnr,H.vbtyp, D.vkgrp,D.vkbur, H.fkdat,H.vkorg,H.regio,H.vtweg,D.voleh,D.werks,D.lgort,CT.kschl,CT.kwert


