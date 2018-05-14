package cn.yat.ldap;

public class LdapUser {
    //chinese
    private String name;
    //english
    private String sAMAccountName;
//    private String department;
    private String mail;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
//    public String getDepartment() {
//        return department;
//    }
//    public void setDepartment(String department) {
//        this.department = department;
//    }
    public String getMail() {
        return mail;
    }
    public void setMail(String mail) {
        this.mail = mail;
    }
    public String getsAMAccountName() {
        return sAMAccountName;
    }
    public void setsAMAccountName(String sAMAccountName) {
        this.sAMAccountName = sAMAccountName;
    }
}
