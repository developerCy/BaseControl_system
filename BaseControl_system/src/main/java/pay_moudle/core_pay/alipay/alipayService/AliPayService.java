package pay_moudle.core_pay.alipay.alipayService;

import com.alipay.api.AlipayApiException;
import net.sf.json.JSONObject;

import java.io.UnsupportedEncodingException;

/**
 * Created by changyu on 2017/3/1 0001.
 */
public interface AliPayService {
    String Ali_trade_pay(JSONObject json) throws UnsupportedEncodingException, AlipayApiException;
    String Ali_refund(JSONObject json) throws AlipayApiException;
    String Ali_query(JSONObject json) throws AlipayApiException;
    String Ali_cancel(JSONObject json) throws AlipayApiException;
}
