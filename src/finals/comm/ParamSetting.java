package finals.comm;

import java.net.InetAddress;

public class ParamSetting {

	public String serverMode = "REAL"; //서버 구분 셋팅 : TEST,REAL

    //생성자
    public ParamSetting() {
    	//setValue();
    }

    //서버정보로 값 세팅
    public void setValue() {
    	try{
    		String strHost = getLocalipAddress();

    		//회사 내부이면
    		if (strHost.substring(0, 12).equals("121.126.244.")) {
        		if (strHost.substring(12).equals("130")) {
        		} else if (strHost.substring(12).equals("137")) {
        			serverMode = "TEST"; System.out.println("서버 : " + strHost);
        		} else {
        			serverMode = "TEST_LOG"; System.out.println("서버 : " + strHost);
        		}
    		}

    	}catch (Exception e){
    		System.out.println("default Domain Error" + e);
    	}
    }

	//서버정보 알아내기
    public String getLocalipAddress() {
        String strHostip = "";
        InetAddress address[] = null;

        try{
            InetAddress local = InetAddress.getLocalHost(); //자신의 컴퓨터이름을 얻기
            address = InetAddress.getAllByName(local.getHostAddress()); //해당 컴퓨터의 모든 ip주소(getHostName() )
            // for (int i=0;i<address.length;i++){ System.out.println(address[i]);}//출력 컴퓨터 이름/ipAddress}
            strHostip = address[0].getHostAddress();
        }catch (Exception e){
            System.out.println("getLocalipAddress Error" + e);
        }
        return strHostip;
    }

}
