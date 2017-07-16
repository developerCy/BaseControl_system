package common;

import java.net.URLEncoder;

/**
 * Created by changyu on 2017/5/25 0025.
 */
public class AliPayConfig {
    //支付宝公钥
    public static final String ALI_PAY_PUBLIC_KEY="MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAsdYx0igR9TpGCDsAZ15ewfrrzM5lz2mRRjZ2iK3z6Y0YcayHMVmiOnWTcefo5z2I3feytqbWgU2x08QJX8n7nNWILUlWWwStWO4xKff7iqpSgxErJsfTGwRceiG4Zc1LNOVStFPPyDzFKmkD1H1hau9d9RnoBbEQ1EQhDRCWOmQq6RMUXH3Q7j46JG6VNoJtLOxa67ZQT0K8as3ppSX7+n7iqoxkdmvD3s4AZwcL5JCZLm+SKVtlVvwLR8EILoLkAUY3nCH2U2aAZDuxdX0lIfFUSPvoFC6KWoKDOwMAHHPOmiw3MDzc4eusKNk1CSnInXqTz9hn0iQZxO/vyZUuowIDAQAB";
    //ISV应用ID
    public static final String ISV_APPID="2017071307741095";
    //应用私钥
    public static final String ALI_PAY_ISV_PRIVATE_KEY="MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQC3WUxqyLcd9Gv3zG+d8Ws0Qs1kKMri6cFsZaFjqERTOq367MupeGs9GXlAIf6m6oUgWDDdAwRXwKSHTNGsUCwmCYHDmOprK4amSLlpzQ4j9WoLtWcK3j5jj4IGt5JpBANciVtSlmB4wpvDvXjfJq/iGFUQXx4N1OqzMKuVf95oFlLoSfhu1nU0cwOYZoWHG853dkWZc79P8KljEFjuf4CZp7Zatw8KXT7uyFvs9/7MydW0MQBtGb0eRhcKlz/qEnnsq+6gzbTz6x7hQNfIWMpLg3W9Z4YLs9fUqQeZZIXPdZVG/GDdqGQjvggTZohoSui/gs2XtHgknNSsEShD3VjtAgMBAAECggEBAJXqDBOb/i4d9lHs9K6+ARwAKxRkTk/vNdo91xI3OXacGjl7eKMe9QBGXd/Ua/Vq+GMT+RAdMWXW/DAs3Zv5CcAoMdQOqk3qjvRTrlcV9SPtvEHKl3BJe9XIN1bqrmnzgBQfs235yvOym84WlEhRx6oknxj9zQMePrE5cHKS1PVhNrNWfmTyczTeVnZOfdhR/tzYtT4eRvfIrIC8ritswbAgm6I5zptqVtY00pPQ9IhNsJrLqdf2g7nuZh41Ihd7THSJaiKhk02vMxWRmvzwbIwI5F2eIPakSXn7H/ZXjkCmd3Va4lZNs/y3KCSKgOKxFQUEIFVmOwM0QerXtNbNkfkCgYEA88Jrj5X648UeXTFrxMMn0v72Q8dfQsvZQqaFFpBxOyB13Z+l3S14Ve3qhWnZSkbv8T3sbzR2oVSyKPnyNq9d6OSzKtolqR1dRZG4yY7FR+b0Zu9UxsFPpw5DOqkwUfAAZqebPCliuBvERqitimK7xQ6q7nUhP+YUcGJSeD1N4IMCgYEAwI5JahAvZBxIeI45zdA2aSU9+8S+cNlvnmotMEUuK1i3fWSeXKv6ngjJ/6H5cxh3rMs1SUQtc/m5OzxXkJkNPBmSemU6amBNY5LU67XqLsvWsBE9m2PIKUEO96aHl/BKLP1UXFEOO+n1bsUWlWkoaIXLL6BcRyvlHXaO8bFgxc8CgYBD6qO1egBRfhabuK1iQU3TSB6te9ta7Rk3rNXgF5a09rJgcZ7xJT4mEwgI7BkSzPkFqk86HNAA22xYZmvKOMpdnXseMXHU0M2wGI33+sOU6RCnrX9dcXcUALFSotlHuyshbwmdlZpf2byyQ0U3DZNrsE6H6jK9bwm61mpL/ii4qQKBgCaQ2PdbQoKkf4dVxRIJr7wjb2SrpRDpG8f4sBBI6tRXsnh03Jrm/w0D6aE/ibyZm+6JzKRd+VSPIaBRl6AoZ1QwMvw8phSEMTvOPsLu7nZs8RTWfyZON9q7YwoK2oF96P1Vx72tplItq7CB3OIT67nzghyJ27GNgxgBlOOniSBVAoGAcZLU8l/Nx0/jyaGh2/Ig5K8881f+1GyLJfOwHPzxct2lKNKCX3nYOIdYNfIlfSF4gZmU9P6nhUTlr4ZgNQN1r76DyZk9i+DnHddRSGDk/ocQ/raxjt/djqzT7tbB+J/08gUilnbBdr0ZQegWRyWFuJut+E3/Kkxc8AHBWLq1m6M=";
    //系统商PID
    public static final String ALI_PAY_ISV_PID="2088911067646860";
    //支付回调通知地址
    public static final String ALI_PAY_NOTIFY_URL="http://106.15.93.226/pay/pay_result";
    //支付宝接口调用地址
    public static final String ALI_GATE_WAY="https://openapi.alipay.com/gateway.do";
    //反扫method
    public static final String TRADE_PAY="alipay.trade.pay";
    //正扫method
    public static final String PRECREATE_TRADE_PAY="alipay.trade.precreate";
    //支付宝退款
    public static final String REFUND="alipay.trade.refund";
    //支付宝交易查询
    public static final String QUERY="alipay.trade.query";
    //支付宝交易撤销
    public static final String CANCEL="alipay.trade.cancel";
    //授权令牌
    public static final String APP_AUTH_TOKEN="201707BBfe69e75615a140038e974c2f8984aB00";
    //授权回调地址
    public static final String ISV_AUTH="https://openauth.alipay.com/oauth2/appToAppAuth.htm?app_id="+ISV_APPID+"&redirect_uri="+ URLEncoder.encode("http://106.15.93.226/isv/auth");



}
