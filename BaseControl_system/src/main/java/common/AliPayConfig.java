package common;

import java.net.URLEncoder;

/**
 * Created by changyu on 2017/5/25 0025.
 */
public class AliPayConfig {
    //支付宝公钥
    public static final String ALI_PAY_PUBLIC_KEY="MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDDI6d306Q8fIfCOaTXyiUeJHkrIvYISRcc73s3vF1ZT7XN8RNPwJxo8pWaJMmvyTn9N4HQ632qJBVHf8sxHi/fEsraprwCtzvzQETrNRwVxLO5jVmRGi60j8Ue1efIlzPXV9je9mkjzOmdssymZkh2QhUrCmZYI/FCEa3/cNMW0QIDAQAB";
    //ISV应用ID
    public static final String ISV_APPID="2016071301611237";
    //应用私钥
    public static final String ALI_PAY_ISV_PRIVATE_KEY="MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCdJRsnnWvDEJiYSzP5mIJJ42Nim5gPHhwGjgOM9Fqm/uD0/XFbmx9+s7dH9qEwlgAlHFJFsilpl3iXvnIH08cpLbCzqtKpXwwzLfHHcHznfWgtvuAHsMeAx0DsSaC7KZJQ7/tdsDok1Mc+Jdn2eSAHcipQLZ+x8YiTM25ApB/5tLH/XfbVeb2a89et8vcv7fScaHCHRkXDJlIqqq/hEPN+AMZdGdfe8Q/pFYC45Q4mta6Dl6XTNGpd5XlRpjFr5+NiS/Q+7+RfbJdi+ps9QTnXl9XKHgUwwMkBl7+GpGHvZm8kt0bmM+Jxwg/I6CFRwj1u7kK42sA7gJUIctQsMp+5AgMBAAECggEAXUM7ZXxxCzjbR29CbnyLvqxcPJaZXUeCXIWGJ+6RqpCUE/Iih7p9q6WfPe9qZLpxZZT0YvbEyy0hXfWiGXEfvxmlyEu2cNy/EdTPrIzfJEfyP0wMC6RoEs6ugk2EPfADLVPv+/9t0pQRl5nBTR0/3Atuf11EbMgrRSl94pFFhN0/I6MRFQ4pPMVaPf5ueugELLsmaoKcF0DDop4PLaa3uVzDuSjur94/NxDFcZHC6jJsXi9VhPw3t+zQp/jNAZm72suvHE495Z1Yo7FGsxX4Hy1HgguY5If1NdbCRPkbhjrqfTpVwWI2RAlU89rUVHBmQavYe1TzKZzBtZvQkImLTQKBgQDoihTfKmUj/Fh0MlxbzC/Zyx1iBuM5B7CguZavyVhhM5eP74aWTGhPLTZfW+PGMuUj5PAEqgBWz6ZwQOpnEWX463PTeryT6r8bSdMn8q/wCJjtlZMRBOqSGcYue8jjlb7/PWX/jA3CCxQB9qR30N1Xj2Ou4mSA9uPjwNeCiKoaBwKBgQCs/8W1PH9mwXQv6yHymLdnZ8ITJNjErOUmhEQic6FXe0ceQpQUcuBFVEfHKDy348XBubA1JQGF+s2lgpbpomH4prJ3qOIxJaDdoKDZeMEd91HmOoMF6jyaXxMquSDgOD3n8SHUmZQDLRkiPKeB1USvaixvybXHOZuZCVUn0MoIPwKBgQCatnvqepduGM91B6ihPrm7asLy3GhDIlWmqbOS4yAVE7FMB24BA3cG7qrVSRrDXEtN1VzHjZ/KIj+3Qclyp4Wz1ltqoadjtnRNoJGHa5UY22976t89HItAhztu49albJT11pDNNA5XQu2d3M5SFTmerv0AdwmtMBYxCrWX2Htr8wKBgFLEA1iT2vfUMbwDKDiiK4yDLWxvQm3FsdPdj6NT4qqxxfKKM8Icu4MvsS9s5q+rfx8RsWWbkMECnoRCl+Sq1WhaiCOLXrvYnr+pa4rfpLSrcDlPYQPhbqEEP20/KKVdAvEGggLufsuONeoVTqZk2/l4OE47GIZ53yE2P9FOqHmNAoGAEeNAfTTKZxH1ZgngR4p9+ht94+nLESaG/wUL6dMABFTQNdHqPOQ1jMJL3Y7BXEbnmI0yhZ9xuNeNtoYMZxrFyZqzoFgeF1UrvJVsxILBwMVrS2C1KAsPZBi+JSfTent3ptnLYIw3dddqLNOIv4Q1JY+2NRxPdbOKEbEyO8NaPDU=";
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
    public static final String APP_AUTH_TOKEN="201707BBe41b84f0aab2472d917e33bc96167X86";
    //授权回调地址
    public static final String ISV_AUTH="https://openauth.alipay.com/oauth2/appToAppAuth.htm?app_id="+ISV_APPID+"&redirect_uri="+ URLEncoder.encode("http://106.15.93.226/isv/auth");



}
