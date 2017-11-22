package louis.fangwangyi.util;

import android.content.Context;
import android.net.wifi.WifiManager;


/**
 * һЩ���ù���
 * @author Louis
 *
 */
public class CommonUtil {
	
	/**
	 * �ж�wifi�Ƿ�����
	 * @return
	 */
	public static boolean isWifiConnected(Context context) {
		WifiManager manager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
		return manager.isWifiEnabled();
	}
}
