
using System.Text;
using System;
namespace QuickFillForm.Core.Model
{
    /**
     * 学员数据模型
     * */
    public class StudentModel
    {
        // 学员编号
        public string Code { get; set; }

        // 姓名
        public string Name { get; set; }

        // 性别
        public string Sex { get; set; }

        // 出生日期
        public string Birthday { get; set; }

        // 证件类型
        public string CertificateType { get; set; }

        // 证件号码
        public string CertificateCode { get; set; }

        // 暂住证号码
        public string TempLiveCode { get; set; }

        // 申请类型
        public string ApplyType { get; set; }

        // 申请车型
        public string ApplyCarType { get; set; }

        // 原准驾车型
        public string AllowedCarType { get; set; }

        // 国籍
        public string Nationality { get; set; }

        // 户籍
        public string CensusRegister { get; set; }

        // 所属辖区
        public string Area { get; set; }

        // 固定电话
        public string Telephone { get; set; }

        // 移动电话
        public string Mobilephone { get; set; }

        // 电子邮箱
        public string Email { get; set; }

        // 邮政编码
        public string Postalcode { get; set; }

        // 身高
        public string Stature { get; set; }

        // 左视力
        public string LeftEyesight { get; set; }

        // 右视力
        public string RightEyesight { get; set; }

        // 辨色力
        public string Distinguish { get; set; }

        // 听力
        public string Audition { get; set; }

        // 上肢
        public string UpperLimb { get; set; }

        // 左下肢
        public string LeftLowerLimb { get; set; }

        // 右下肢
        public string RightLowerLimb { get; set; }

        // 躯干颈部
        public string Neck { get; set; }

        // 体检日期
        public string CheckTime { get; set; }

        // 体检医院
        public string CheckHospital { get; set; }

        // 代理/监护人
        public string Guardian { get; set; }

        // 代理/监护人证件类型
        public string GuardianCertificateType { get; set; }

        // 代理/监护人证件号码
        public string GuardianCertificateCode { get; set; }

        // 报名日期
        public string EnrollTime { get; set; }

        // 报名点
        public string EnrollSite { get; set; }

        // 工作单位
        public string WorkSite { get; set; }

        // 报名途径
        public string EnrollType { get; set; }

        // 推荐人
        public string Referee { get; set; }

        // 省
        public string Province { get; set; }

        // 市
        public string City { get; set; }

        // 区
        public string District { get; set; }

        // 联系地址
        public string ContactAddress { get; set; }

        // 户口所在地
        public string RegisterAddress { get; set; }

        // 现居住地
        public string LivingAddress { get; set; }

        // 邮政地址
        public string PostalAddress { get; set; }

        // 学员状态
        public string State { get; set; }

        // 学习车型
        public string StudyCarType { get; set; }

        public override string ToString()
        {
            StringBuilder builder = new StringBuilder();
            builder.AppendLine("编号：" + this.Code);
            builder.AppendLine("姓名：" + this.Name);
            builder.AppendLine("性别：" + this.Sex);
            builder.AppendLine("出生日期：" + this.Birthday);
            builder.AppendLine("证件类型：" + this.CertificateType);
            builder.AppendLine("证件号码：" + this.CertificateCode);
            builder.AppendLine("暂住证号：" + this.TempLiveCode);
            builder.AppendLine("申请类型：" + this.ApplyType);
            builder.AppendLine("学习车型：" + this.ApplyCarType);
            builder.AppendLine("原准驾车型：" + this.AllowedCarType);
            builder.AppendLine("国籍：" + this.Nationality);
            builder.AppendLine("户籍区域：" + this.CensusRegister);
            builder.AppendLine("所属辖区：" + this.Area);
            builder.AppendLine("登记地址-省：" + this.Province);
            builder.AppendLine("登记地址-市：" + this.City);
            builder.AppendLine("登记地址-区：" + this.District);
            builder.AppendLine("登记地址：" + this.RegisterAddress);
            builder.AppendLine("联系地址：" + this.ContactAddress);
            builder.AppendLine("固定电话：" + this.Telephone);
            builder.AppendLine("移动电话：" + this.Mobilephone);
            builder.AppendLine("电子邮箱：" + this.Email);
            builder.AppendLine("邮政编码：" + this.Postalcode);
            builder.AppendLine("身高：" + this.Stature);
            builder.AppendLine("左视力：" + this.LeftEyesight);
            builder.AppendLine("右视力：" + this.RightEyesight);
            builder.AppendLine("辨色力：" + this.Distinguish);
            builder.AppendLine("听力：" + this.Audition);
            builder.AppendLine("上肢：" + this.UpperLimb);
            builder.AppendLine("左下肢：" + this.LeftLowerLimb);
            builder.AppendLine("右下肢：" + this.RightLowerLimb);
            builder.AppendLine("躯干颈部：" + this.Neck);
            builder.AppendLine("体检日期：" + this.CheckTime);
            builder.AppendLine("体检医院：" + this.CheckHospital);
            builder.AppendLine("报名日期：" + this.EnrollTime);
            builder.AppendLine("介绍人：" + this.Referee);
            return builder.ToString();
        }
    }
}
