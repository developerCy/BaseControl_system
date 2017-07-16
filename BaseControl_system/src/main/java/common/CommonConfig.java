package common;

/**
 * Created by changyu on 2017/5/31 0031.
 */
public class CommonConfig {
    //支付失败
    public static final String PAY_RESULT_FALSE="{\"code\":-1,\"trade_status\":\"false\",\"sub_msg\":\"支付失败,服务端数据异常\"}";
    //退款失败
    public static final String REFUND_RESULT_FALSE="{\"code\":-1,\"refund_status\":\"false\",\"sub_msg\":\"退款失败,服务端数据异常\"}";
    //查询失败
    public static final String QUERY_RESULT_FALSE="{\"code\":-1,\"query_status\":\"false\",\"sub_msg\":\"查询失败,服务端数据异常\"}";
    //撤单失败
    public static final String CANCEL_RESULT_FALSE="{\"code\":-1,\"cancel_status\":\"false\",\"sub_msg\":\"撤单失败,服务端数据异常\"}";
    //成功
    public static final String SUCCESS="{\"code\":10000,\"status\":\"success\"}";

}
