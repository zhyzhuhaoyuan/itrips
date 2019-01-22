package cn.itrip.auth.service;

import java.util.HashMap;
import java.util.Set;

import javax.annotation.Resource;

import com.cloopen.rest.sdk.CCPRestSmsSDK;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import cn.itrip.common.SystemConfig;


@Service("smsService")
public class SmsServiceImpl implements SmsService {
	@Override
	public void send(String phoneTo, String templateId, String[] datas) throws Exception {
		HashMap<String, Object> result = null;

		//初始化SDK
		CCPRestSmsSDK restAPI = new CCPRestSmsSDK();

		restAPI.init("app.cloopen.com", "8883");

		restAPI.setAccount("8aaf070866235bc5016665af87ae2234", "d3b08d1723cb49f1b92eacc68b71d9e5");

		restAPI.setAppId("8aaf070866235bc5016665af8808223b");

		result = restAPI.sendTemplateSMS(phoneTo,"1" ,datas);

		System.out.println("SDKTestGetSubAccounts result=" + result);
		if("000000".equals(result.get("statusCode"))){
			//正常返回输出data包体信息（map）
			HashMap<String,Object> data = (HashMap<String, Object>) result.get("data");
			Set<String> keySet = data.keySet();
			for(String key:keySet){
				Object object = data.get(key);
				System.out.println(key +" = "+object);
			}
		}else{
			//异常返回输出错误码和错误信息
			System.out.println("错误码=" + result.get("statusCode") +" 错误信息= "+result.get("statusMsg"));
		}
	}
}
