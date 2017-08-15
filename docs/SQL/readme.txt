--询价
update PM_BIDINQUIRY_InquiryProduct set product_Index = 0 where product_Index is null;
--竞价
update PM_BIDPROJECT_BidProduct set product_Index = 0 where product_Index is null;
--招标
update PM_TENDERPROJECT_TenderProduct set product_Index = 0 where product_Index is null;



update PM_BIDPROJECT_BidProject set bidIssms = 2 where bidIssms is null;