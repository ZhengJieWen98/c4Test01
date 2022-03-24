package csdn.pojo;

/**
 * 部门
 */
public class Department {
    private String department;//部门
    private String jobNumber;//工号
    private String name;//姓名
    private double basicSalary;//底薪
    private double postWage;//岗位工资
    private double performanceBonus;//绩效奖金
    private double attendanceBonus;//全勤奖金
    private double attendanceDeduction;//考勤扣除
    private double penalties_for_non_compliance;//违规处罚
    private double transportationSubsidies;//交通补助
    private double communicationsGrants;//通信补助

    private double endowmentInsuranceSelf;//个人养老保险
    private double endowmentInsuranceCompany;//公司养老保险

    private double medicareSelf;//个人医疗保险
    private double medicareCompany;//公司医疗保险

    private double unemploymentInsuranceSelf;//个人失业保险
    private double unemploymentInsuranceConpany;//公司失业保险

    private double employmentInjuryInsuranceSelf;//个人工伤保险
    private double employmentInjuryInsuranceConpany;//公司工伤保险

    private double birthInsuranceSelf;//个人生育保险
    private double birthInsuranceConpany;//公司生育保险

    private double reservedFundsSelf;//个人公积金
    private double reservedFundsConpany;//公司公积金

    public double getEndowmentInsuranceSelf() {
        return endowmentInsuranceSelf;
    }

    public void setEndowmentInsuranceSelf(double endowmentInsuranceSelf) {
        this.endowmentInsuranceSelf = endowmentInsuranceSelf;
    }

    public double getEndowmentInsuranceCompany() {
        return endowmentInsuranceCompany;
    }

    public void setEndowmentInsuranceCompany(double endowmentInsuranceCompany) {
        this.endowmentInsuranceCompany = endowmentInsuranceCompany;
    }

    public double getMedicareSelf() {
        return medicareSelf;
    }

    public void setMedicareSelf(double medicareSelf) {
        this.medicareSelf = medicareSelf;
    }

    public double getMedicareCompany() {
        return medicareCompany;
    }

    public void setMedicareCompany(double medicareCompany) {
        this.medicareCompany = medicareCompany;
    }

    public double getUnemploymentInsuranceSelf() {
        return unemploymentInsuranceSelf;
    }

    public void setUnemploymentInsuranceSelf(double unemploymentInsuranceSelf) {
        this.unemploymentInsuranceSelf = unemploymentInsuranceSelf;
    }

    public double getUnemploymentInsuranceConpany() {
        return unemploymentInsuranceConpany;
    }

    public void setUnemploymentInsuranceConpany(double unemploymentInsuranceConpany) {
        this.unemploymentInsuranceConpany = unemploymentInsuranceConpany;
    }

    public double getEmploymentInjuryInsuranceSelf() {
        return employmentInjuryInsuranceSelf;
    }

    public void setEmploymentInjuryInsuranceSelf(double employmentInjuryInsuranceSelf) {
        this.employmentInjuryInsuranceSelf = employmentInjuryInsuranceSelf;
    }

    public double getEmploymentInjuryInsuranceConpany() {
        return employmentInjuryInsuranceConpany;
    }

    public void setEmploymentInjuryInsuranceConpany(double employmentInjuryInsuranceConpany) {
        this.employmentInjuryInsuranceConpany = employmentInjuryInsuranceConpany;
    }

    public double getBirthInsuranceSelf() {
        return birthInsuranceSelf;
    }

    public void setBirthInsuranceSelf(double birthInsuranceSelf) {
        this.birthInsuranceSelf = birthInsuranceSelf;
    }

    public double getBirthInsuranceConpany() {
        return birthInsuranceConpany;
    }

    public void setBirthInsuranceConpany(double birthInsuranceConpany) {
        this.birthInsuranceConpany = birthInsuranceConpany;
    }

    public double getReservedFundsSelf() {
        return reservedFundsSelf;
    }

    public void setReservedFundsSelf(double reservedFundsSelf) {
        this.reservedFundsSelf = reservedFundsSelf;
    }

    public double getReservedFundsConpany() {
        return reservedFundsConpany;
    }

    public void setReservedFundsConpany(double reservedFundsConpany) {
        this.reservedFundsConpany = reservedFundsConpany;
    }

    public Department(){

    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getJobNumber() {
        return jobNumber;
    }

    public void setJobNumber(String jobNumber) {
        this.jobNumber = jobNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getBasicSalary() {
        return basicSalary;
    }

    public void setBasicSalary(double basicSalary) {
        this.basicSalary = basicSalary;
    }

    public double getPostWage() {
        return postWage;
    }

    public void setPostWage(double postWage) {
        this.postWage = postWage;
    }

    public double getPerformanceBonus() {
        return performanceBonus;
    }

    public void setPerformanceBonus(double performanceBonus) {
        this.performanceBonus = performanceBonus;
    }

    public double getAttendanceBonus() {
        return attendanceBonus;
    }

    public void setAttendanceBonus(double attendanceBonus) {
        this.attendanceBonus = attendanceBonus;
    }

    public double getAttendanceDeduction() {
        return attendanceDeduction;
    }

    public void setAttendanceDeduction(double attendanceDeduction) {
        this.attendanceDeduction = attendanceDeduction;
    }

    public double getPenalties_for_non_compliance() {
        return penalties_for_non_compliance;
    }

    public void setPenalties_for_non_compliance(double penalties_for_non_compliance) {
        this.penalties_for_non_compliance = penalties_for_non_compliance;
    }

    public double getTransportationSubsidies() {
        return transportationSubsidies;
    }

    public void setTransportationSubsidies(double transportationSubsidies) {
        this.transportationSubsidies = transportationSubsidies;
    }

    public double getCommunicationsGrants() {
        return communicationsGrants;
    }

    public void setCommunicationsGrants(double communicationsGrants) {
        this.communicationsGrants = communicationsGrants;
    }

    //得到正收益和
    public double getAdd(){
        return basicSalary+postWage+performanceBonus+attendanceBonus+transportationSubsidies+communicationsGrants;
    }
    ////考勤扣除、违规处罚等负收益项
    public double getMinusYield(){
        return attendanceDeduction+penalties_for_non_compliance;
    }

    //应发工资=工资-扣款
    public double getSalary(){
        return getAdd()-getMinusYield();
    }

    //个人缴纳部分
    public double getSelf(){
        return endowmentInsuranceSelf+medicareSelf+unemploymentInsuranceSelf+employmentInjuryInsuranceSelf+birthInsuranceSelf+reservedFundsSelf;
    }
    //企业缴纳部分
    public double getConpany(){
        return endowmentInsuranceCompany+medicareCompany+unemploymentInsuranceConpany+employmentInjuryInsuranceConpany+birthInsuranceConpany+reservedFundsConpany;
    }
    //应发工资+企业缴纳五险一金”的结果填入“企业人员月度工资成本支付表.xlsx“的“企业支出成本”一栏。
    public double getAllConpany(){
        return getSalary()+getConpany();
    }
    //应发工资-五险一金个人缴纳部分”的结果等于“应税金额” 个人税
    public double getTax(){
        double gz = getSalary()-getSelf();
        double s;
        if(gz<3000){
            s=gz*0.003;
        }else if(gz<=12000){
            s=gz*0.1;
        }else if(gz<=25000){
            s=gz*0.2;
        }else if(gz<=35000){
            s=gz*0.25;
        }else if(gz<=55000){
            s=gz*0.3;
        }else if(gz<=80000){
            s=gz*0.35;
        }else {
            s=gz*0.45;
        }
        return s;
    }

    //“应发工资-五险一金个人缴纳部分-个税”的结果填入“企业人员月度工资成本支付表.xlsx“的“实发工资”一栏。
    public double getNetPayroll(){
        return getSalary()-getSelf()-getTax();
    }

    @Override
    public String toString() {
        return "工号:"+jobNumber+
                " 姓名:"+name+
                " 部门:"+department+
                " 工资:"+getAdd()+
                " 扣款:"+getMinusYield()+
                "养老(个人):"+endowmentInsuranceSelf+
                "医疗(个人):"+medicareSelf+
                "失业(个人):"+unemploymentInsuranceSelf+
                "工伤(个人):"+employmentInjuryInsuranceSelf+
                "生育(个人):"+birthInsuranceSelf+
                "公积金(个人):"+reservedFundsSelf+
                "合计(个人):"+getSelf()+
                "养老(公司):"+endowmentInsuranceCompany+
                "医疗(公司):"+medicareCompany+
                "失业(公司):"+unemploymentInsuranceConpany+
                "工伤(公司):"+employmentInjuryInsuranceConpany+
                "生育(公司):"+birthInsuranceConpany+
                "公积金(公司):"+reservedFundsConpany+
                "合计(公司):"+getConpany()+
                "个税金额:"+getTax()+
                " 应发工资:"+getSalary()+
                "实发工资"+getNetPayroll()+
                "企业支出成本:"+getAllConpany();

    }

}
