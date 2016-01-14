package com.kunter.ibuy.admin.eo;

public class Account {
    /**
     * id
     */
    private String id;

    /**
     * 管理员账号
     */
    private String account;

    /**
     * 管理员名称
     */
    private String name;

    /**
     * 管理员密码
     */
    private String password;

    /**
     * 管理员状态
     */
    private String status;

    /**
     * 管理员手机
     */
    private String phone;

    /**
     * 获取id
     *
     * @return id
     */
    public String getId() {
        return id;
    }

    /**
     * 设置id
     *
     * @param id id
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * 获取管理员账号
     *
     * @return 管理员账号
     */
    public String getAccount() {
        return account;
    }

    /**
     * 设置管理员账号
     *
     * @param account 管理员账号
     */
    public void setAccount(String account) {
        this.account = account == null ? null : account.trim();
    }

    /**
     * 获取管理员名称
     *
     * @return 管理员名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置管理员名称
     *
     * @param name 管理员名称
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * 获取管理员密码
     *
     * @return 管理员密码
     */
    public String getPassword() {
        return password;
    }

    /**
     * 设置管理员密码
     *
     * @param password 管理员密码
     */
    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    /**
     * 获取管理员状态
     *
     * @return 管理员状态
     */
    public String getStatus() {
        return status;
    }

    /**
     * 设置管理员状态
     *
     * @param status 管理员状态
     */
    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    /**
     * 获取管理员手机
     *
     * @return 管理员手机
     */
    public String getPhone() {
        return phone;
    }

    /**
     * 设置管理员手机
     *
     * @param phone 管理员手机
     */
    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }
}