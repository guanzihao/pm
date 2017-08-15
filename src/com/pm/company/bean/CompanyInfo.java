package com.pm.company.bean;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import com.pm.core.bean.MetaObject;
import com.pm.task.bean.Task;

/**
 * 客户公司基本信息
 * 
 * @author youliang.fang
 */

@SuppressWarnings("serial")
@Entity
@Table(name = "PM_COMPANY_CompanyInfo")
public class CompanyInfo extends MetaObject {
    /**
     * 公司CODE，预留字段，唯一标示
     */
    @Column(name = "com_Code", length = 100)
    private String comCode;

    /**
     * 公司名称
     */
    @Column(name = "com_Name", length = 200)
    private String comName;

    /**
     * 注册地址
     */
    @Column(name = "com_Address", length = 500)
    private String comAddress;

    /**
     * 法定代表人
     */
    @Column(name = "com_Person", length = 100)
    private String comPerson;

    /**
     * 注册资金
     */
    @Column(name = "com_Capital", length = 100)
    private String comCapital;

    /**
     * 固定资产
     */
    @Column(name = "com_Assets", length = 100)
    private String comAssets;

    /**
     * 成立时间
     */
    @Column(name = "com_Foundingtime", length = 15)
    private String comFoundingtime;

    /**
     * 税务登记证号(或者三证合一)
     */
    @Column(name = "com_Dutynum", length = 50)
    private String comDutynum;

    /**
     * 开户银行
     */
    @Column(name = "com_Bank", length = 200)
    private String comBank;

    /**
     * 开户银行账号
     */
    @Column(name = "com_Bankaccount", length = 50)
    private String comBankaccount;

    /**
     * 公司网址
     */
    @Column(name = "com_Website", length = 200)
    private String comWebsite;

    /**
     * 状态
     */
    @Column(name = "com_State", length = 1)
    private String comState;
    
    /**
     * 报关人员
     */
    @Column(name = "baoguan_user")
    private String baoguanuser;
    
    /**
     * 仓储人员
     */
    @Column(name = "cangchu_user")
    private String cangchuuser;
    
    /**
     * 物流人员
     */
    @Column(name = "wuliu_user")
    private String wuliuuser;
    
    /**
     * 外贸人员
     */
    @Column(name = "waimao_user")
    private String waimaouser;
    
    
    /**
     * 创建人
     */
    @Column(name = "cj_user")
    private String cjuser;

    /**
     * 联系人
     */
    @OrderBy("createDate asc")
    @OneToMany(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY, mappedBy = "companyInfo")
    private List<CompanyInfoUser> companyInfoUsers;

    /**
     *任务集合
     */
    @OneToMany(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY, mappedBy = "companyInfo")
    private List<Task> tasks;
    
    /**
     * 客户联系人
     */
    @Column(name = "com_Link", length = 200)
    private String comLink;
    
    /**
     * 客户联系人电话
     */
    @Column(name = "com_Tel", length = 200)
    private String comTel;
    
    /**
     * 客户联系人电子邮箱
     */
    @Column(name = "com_Email", length = 200)
    private String comEmail;
    
    
    /**
     * 指定报关公司
     */
    @Column(name = "com_baoguan", length = 36)
    private String combaoguan;
    
    /**
     * 指定物流公司
     */
    @Column(name = "com_wuliu", length = 36)
    private String comwuliu;
    
    /**
     * 指定外贸公司
     */
    @Column(name = "com_waimao", length = 36)
    private String comwaimao;
    
    /**
     * 指定仓储公司
     */
    @Column(name = "com_cangchu", length = 36)
    private String comcangchu;
    
    /**
     * 公司纳税识别码
     */
    @Column(name = "com_customcode")
    private String comcustomcode;
    
    /**
     * 三证信息
     */
    @Column(name = "register_info")
    private String registerinfo;
    
    
    /**
     * 拉岁编码
     */
    @Column(name = "tax_num")
    private String taxnum;
    
    
    /**
     * 是否是平台注册人员
     */
    @Column(name = "com_source")
    private String comsource;
    
    /**
     * 备注
     */
    @Column(name = "beizhu")
    private String beizhu;
    
    public String getBeizhu() {
        return beizhu;
    }

    public void setBeizhu(String beizhu) {
        this.beizhu = beizhu;
    }

    public String getComsource() {
        return comsource;
    }

    public void setComsource(String comsource) {
        this.comsource = comsource;
    }

    public String getTaxnum() {
        return taxnum;
    }

    public void setTaxnum(String taxnum) {
        this.taxnum = taxnum;
    }

    public String getComcustomcode() {
        return comcustomcode;
    }

    public String getCjuser() {
        return cjuser;
    }

    public void setCjuser(String cjuser) {
        this.cjuser = cjuser;
    }

    public void setComcustomcode(String comcustomcode) {
        this.comcustomcode = comcustomcode;
    }

    public String getRegisterinfo() {
        return registerinfo;
    }

    public String getBaoguanuser() {
        return baoguanuser;
    }

    public void setBaoguanuser(String baoguanuser) {
        this.baoguanuser = baoguanuser;
    }

    public String getCangchuuser() {
        return cangchuuser;
    }

    public void setCangchuuser(String cangchuuser) {
        this.cangchuuser = cangchuuser;
    }

    public String getWuliuuser() {
        return wuliuuser;
    }

    public void setWuliuuser(String wuliuuser) {
        this.wuliuuser = wuliuuser;
    }

    public String getWaimaouser() {
        return waimaouser;
    }

    public void setWaimaouser(String waimaouser) {
        this.waimaouser = waimaouser;
    }

    public void setRegisterinfo(String registerinfo) {
        this.registerinfo = registerinfo;
    }

    public String getComTel() {
        return comTel;
    }

    public void setComTel(String comTel) {
        this.comTel = comTel;
    }

    public String getComwuliu() {
        return comwuliu;
    }

    public void setComwuliu(String comwuliu) {
        this.comwuliu = comwuliu;
    }

    public String getComwaimao() {
        return comwaimao;
    }

    public void setComwaimao(String comwaimao) {
        this.comwaimao = comwaimao;
    }

    public String getCombaoguan() {
        return combaoguan;
    }

    public void setCombaoguan(String combaoguan) {
        this.combaoguan = combaoguan;
    }

    public String getComcangchu() {
        return comcangchu;
    }

    public void setComcangchu(String comcangchu) {
        this.comcangchu = comcangchu;
    }

    public void setComLink(String comLink) {
        this.comLink = comLink;
    }

    public String getComLink() {
        return comLink;
    }



    public String getComEmail() {
        return comEmail;
    }

    public void setComEmail(String comEmail) {
        this.comEmail = comEmail;
    }

    public String getComName() {
        return comName;
    }

    public void setComName(String comName) {
        this.comName = comName;
    }

    public String getComAddress() {
        return comAddress;
    }

    public void setComAddress(String comAddress) {
        this.comAddress = comAddress;
    }

    public String getComPerson() {
        return comPerson;
    }

    public void setComPerson(String comPerson) {
        this.comPerson = comPerson;
    }

    public String getComCapital() {
        return comCapital;
    }

    public void setComCapital(String comCapital) {
        this.comCapital = comCapital;
    }

    public String getComAssets() {
        return comAssets;
    }

    public void setComAssets(String comAssets) {
        this.comAssets = comAssets;
    }

    public String getComFoundingtime() {
        return comFoundingtime;
    }

    public void setComFoundingtime(String comFoundingtime) {
        this.comFoundingtime = comFoundingtime;
    }

    public String getComDutynum() {
        return comDutynum;
    }

    public void setComDutynum(String comDutynum) {
        this.comDutynum = comDutynum;
    }

    public String getComBank() {
        return comBank;
    }

    public void setComBank(String comBank) {
        this.comBank = comBank;
    }

    public String getComBankaccount() {
        return comBankaccount;
    }

    public void setComBankaccount(String comBankaccount) {
        this.comBankaccount = comBankaccount;
    }

    public String getComWebsite() {
        return comWebsite;
    }

    public void setComWebsite(String comWebsite) {
        this.comWebsite = comWebsite;
    }

    public String getComCode() {
        return comCode;
    }

    public void setComCode(String comCode) {
        this.comCode = comCode;
    }

    public String getComState() {
        return comState;
    }

    public void setComState(String comState) {
        this.comState = comState;
    }

    public List<CompanyInfoUser> getCompanyInfoUsers() {
        return companyInfoUsers;
    }

    public void setCompanyInfoUsers(List<CompanyInfoUser> companyInfoUsers) {
        this.companyInfoUsers = companyInfoUsers;
    }

    public String getEntityView() {
        return "客户公司基本信息";
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }
}
