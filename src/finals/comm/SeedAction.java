package finals.comm;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import finals.util.DataUtil;
import finals.util.SeedUtil;

/**
 * @author PoSTmedia
 *	seed 암호화 테스트
 */


public class SeedAction extends CommonAction{

	public ActionForward doExecute(
			ActionMapping mapping
			, ActionForm form
			, HttpServletRequest request
			, HttpServletResponse response
			, Map param
		){

			// 암복호화에 사용할 키 배열생성
			int[] seedKey;

			try{
				String enctext = DataUtil.notnull(param.get("enctext"));

				//라운드키 생성(default)
				seedKey = SeedUtil.getSeedRoundKey("1234567890123456");

				// TODO DB 및 파라미터 연동 필요함.
				String nomalStr = "암호화 테스트 imnida (*&^%$#@!,./;',./l;'[]=-";
				//암호화
				String encStr = SeedUtil.getSeedEncrypt(nomalStr, seedKey);
				request.setAttribute("encStr", encStr);

				//복호화
				String decStr = SeedUtil.getSeedDecrypt(encStr, seedKey);
				request.setAttribute("decStr", decStr);

				String nomalStr2 = "권희정 입니다 LODEV ~(*&^%$#@!";
				request.setAttribute("nomalStr2", nomalStr2);
				//암호화
				String encStr2 = SeedUtil.getSeedEncrypt(nomalStr2, seedKey);
				request.setAttribute("encStr2", encStr2);

				//복호화
				String decStr2 = SeedUtil.getSeedDecrypt(encStr2, seedKey);
				request.setAttribute("decStr2", decStr2);

				//--- 입력된 값의 암호화 복호화
				//암호화
				String encStr3 = SeedUtil.getSeedEncrypt(enctext, seedKey);
				request.setAttribute("encStr3", encStr3);
				//복호화
				String decStr3 = SeedUtil.getSeedDecrypt(encStr3, seedKey);
				request.setAttribute("decStr3", decStr3);


			}catch(Exception e){
				logger.error(e);
			}

			return mapping.findForward("seed");
		}
}

