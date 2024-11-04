@AbapCatalog.sqlViewName: 'ZSDMMVIEW'
@AccessControl.authorizationCheck: #NOT_REQUIRED
@ObjectModel.usageType.serviceQuality: #X
@ObjectModel.usageType.sizeCategory: #XL
@ObjectModel.usageType.dataClass: #MIXED
@EndUserText.label: 'Material Master'
@OData.publish: true
define view Z_SD_MM as select from mara as M
left outer join makt as MD on MD.matnr = M.matnr and MD.spras ='E'  
left outer join t134t as MT on MT.mtart = M.mtart and MT.spras ='E'  
left outer join t023t as MG on MG.matkl = M.matkl and MG.spras ='E'
left outer join t024x as B on B.labor = M.labor and B.spras ='E'
left outer join t006a as U on U.msehi = M.meins and U.spras ='E'
{
    key M.matnr as Material,
    MD.maktx as Material_Description,
    MT.mtbez as Material_type,
    MG.wgbez as Material_Grp,
    B.lbtxt as Brand,
    U.msehl as B_UOM
}where  MT.mtbez like 'U%'
