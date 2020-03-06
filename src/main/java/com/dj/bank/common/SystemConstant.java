package com.dj.bank.common;

public interface SystemConstant {

    /**
     * 系统正在维护...请稍后再试
     */
    public static final String ERROR = "系统正在维护...请稍后再试";

    /**
     * 登陆输入为null
     */
    public static final String LOGIN_NULL = "登陆输入为null";

    /**
     * 用户被拉黑
     */
    public static final String IS_DEL_NOT = "用户被拉黑";


    /**
     * 1：正常显示
     */
    public static final Integer IS_DEL = 1;

    /**
     * 还款分期
     */
    public static final Integer REFUND_DATE = 5;


    /**
     * 1：未删除
     */
    Integer NOT_DELETE_IS_DEL = 1;

    /**
     * 2：删除
     */
    Integer DELETE_IS_DEL = 2;

    /**
     * 分页2
     */
    Integer PAGE_SIZE = 2;

    /**
     * 输入不能为空
     */
    String NOT_NULL = "输入不能为空";

    /**
     * 服务器异常
     */
    String EXCEPTION = "";

    /**
     * 账号或密码错误
     */
    String NUMBER_PWD_ERROR = "账号或密码错误";

    /**
     * 该邮箱已注册
     */
    String EMAIL_ERROR = "该邮箱已注册";

    /**
     * 该电话号已注册
     */
    String PHONE_ERROR = "该电话号已注册";

    /**
     * 该用户名已注册
     */
    String NAME_ERROR = "该用户名已注册";

    /**
     * 输入有误
     */
    String INPUT_ERROR = "输入有误";
    /**
     * @Description:父级pid -1
     * @Author: Liuwf
     * @Date:
     * @param null:
     * @return: null
     **/
    Integer PARENT_ID = 0;
    /**
     * @Description:parentId=0
     * @Author: Liuwf
     * @Date:
     * @param null:
     * @return: null
     **/
    String PARENT_NAME  = "-";
    /**
     * @Description:用户session USER_SESSION
     * @Author: Liuwf
     * @Date:
     * @param null:
     * @return: null
     **/
    String USER_SESSION = "USER_SESSION";
    /**
     * @Description:用户资源USER_RESOURCE
     * @Author: Liuwf
     * @Date:
     * @param null:
     * @return: null
     **/
    String  USER_RESOURCE = "USER_RESOURCE";
    /**
     * @Description:用户名不存在
     * @Author: Liuwf
     * @Date:
     * @param null:
     * @return: null
     **/
    String NULL_USERNAME  = "用户名不存在";
    /**
     * @Description:手机号不存在，请注册
     * @Author: Liuwf
     * @Date:
     * @param null:
     * @return: null
     **/
    String PHONE_REGISTER  = "手机号不存在，请注册";
    /**
     * @Description:操作成功
     * @Author: Liuwf
     * @Date:
     * @param null:
     * @return: null
     **/
    String SUCCESS = "操作成功";
    /**
     * @Description: null
     * @Author: Liuwf
     * @Date:
     * @param null:
     * @return: null
     **/
    String NULL  = null;
    /**
     * @Description: 银行类型p_id = 10
     * @Author: Liuwf
     * @Date:
     * @param null:
     * @return: null
     **/
    Integer BANK_TYPE_PID= 10;


}
