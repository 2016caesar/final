package finals.util;

/**
 * 페이징 처리 유틸
 *
 */
public class PageUtil {

	public static int limit=3;	    //페이지당 게시물 갯수
	public static int limitpage=10;	// 페이지 링크 번호 갯수

	/**
	 * 현재 페이지 번호
	 *
	 * @param  String page
	 * @return int page
	 */
	public static int getCurrentPage(String page) {
		int thisPage = 0;
		if(page == null || page.trim().length()==0){
	    	thisPage = 1;
	    }else{
	    	thisPage = Integer.parseInt(page);
	    }
		return thisPage;
	}


	/**
	 * 자료의 총 페이지 수 구하기
	 * @param totalsize - 총 게시물 수
	 * @return totalpage - 총페이지 수 반환
	 */
	public static int totalPage(int totalsize){

		int totalpage = 0;
		if(totalsize == 0) {
			return totalpage = 1;
		} else {
			int p = totalsize % limit;
			int m = totalsize / limit;

			if ( p > 0 ) {
				 totalpage = m + 1 ;
			} else {
				 totalpage = m ;
			}
			return totalpage  ;
		}
	}

	/**
	 * 자료의 총 페이지 수 구하기 - PageDivCustom(String type, int limit , int limitpage , int totalsize ,int page) 에서 사용
	 * @param totalsize - 총 게시물 수
	 * @param totalsize limit - 페이지당 게시물 갯수
	 * @return totalpage - 총페이지 수 반환
	 */
	public static int totalPageCustom(int totalsize,int limit){

		int totalpage = 0;
		if(totalsize == 0) {
			return totalpage = 1;
		} else {
			int p = totalsize % limit;
			int m = totalsize / limit;
			if ( p > 0 ) {
				 totalpage = m + 1 ;
			} else {
				 totalpage = m ;
			}
			return totalpage  ;
		}
	}

	/**
	 * 현재 페이지 번호를 파라미터로 받아 실질적으로 쿼리에서 가져올 첫번째 게시물 번호 반환
	 * @param int thisPage : 현재 페이지 번호
	 * @return int startNum : 시작하는 게시물 번호 (로우넘 번호)
	 */

	public static int getStartNum(int thisPage) {

	    int startNum = 0;
	    if (thisPage==1) {
	    	startNum = 0;
		} else {
			startNum = (thisPage-1) * limit;
		}

	    return startNum;
	}

	/**
	 * 리스트 페이지 나눈 후 Navigator 반환 사용자 설정에 의한 표시
	 * @param type - 페이징 타입
	 * @param totalsize - 총 게시물 수
	 * @param page - 현재 페이지
	 * @return String pageTag
	 */

	public static String PageDiv(String type, int totalsize ,int page){
			String stfont = "";
			String edfont = "";
			String first  = "<img src=\"../images/btn_pg_prev.gif\" alt=\"처음\" >";
			String last   = "<img src=\"../images/btn_pg_next.gif\" alt=\"마지막\" >";
			String before = "<img src=\"../images/btn_pg_prev.gif\" alt=\"이전\" >";
			String next   = "<img src=\"../images/btn_pg_next.gif\" alt=\"다음\" >";

			int totalpage = totalPage(totalsize);
			int startpage = (int)((page-1) / limitpage) * limitpage+1;
			int endpage = startpage + limitpage;
			if (endpage>totalpage){endpage = totalpage+1;}

			StringBuffer pagediv = new StringBuffer();

//			pagediv.append("<a href=\"javascript:GoPage('"+type+"','"+1+"')\" class=\"direction\" >" + first + "<span>처음</span></a>\n");

			if((startpage-limitpage)>=0)
			{
				pagediv.append("<a href=\"javascript:GoPage('"+type+"','"+(startpage-1)+"');\">" + before + "</a>\n");
			}

			for(int i=startpage;i<endpage;i++)
			{
				if(page==i)
				  pagediv.append("<strong>"+""+stfont+i+""+edfont+"</strong>\n");
				else
				pagediv.append("<a href=\"javascript:GoPage('"+type+"','"+i+"');\">"+""+stfont+i+""+edfont+"</a>\n");
			}

			if(startpage+limitpage<totalpage+1)
			{
				pagediv.append("<a href=\"javascript:GoPage('"+type+"','"+endpage+"');\">" + next + "</a>\n");
			}

//			pagediv.append("<a href=\"javascript:GoPage('"+type+"','"+totalpage+"')\" class=\"direction\" >" + last + "<span>마지막</span></a>\n");

			return pagediv.toString();
		}

	/**
	 * 리스트 페이지 나눈 후 Navigator 반환 사용자 설정에 의한 표시
	 * @param String type - 페이징 타입 : 한페이지에서 두개이상의 페이징 존재시
	 * @param int limit - 페이지당 게시물 갯수
	 * @param int limitpage - 페이지 링크 번호 갯수
	 * @param int totalsize - 총 게시물 수
	 * @param int page - 현재 페이지
	 * @return String pageTag
	 */

	public static String PageDivCustom(String type, int limit , int limitpage , int totalsize ,int page){
			String stfont = "";
			String edfont = "";
			String first  = "<img src=\"../images/btn_pg_prev.gif\" alt=\"처음\" >";
			String last   = "<img src=\"../images/btn_pg_next.gif\" alt=\"마지막\" >";
			String before = "<img src=\"../images/btn_pg_prev.gif\" alt=\"이전\" >";
			String next   = "<img src=\"../images/btn_pg_next.gif\" alt=\"다음\" >";

			int totalpage = totalPageCustom(totalsize,limit);
			int startpage = (int)((page-1) / limitpage) * limitpage+1;
			int endpage = startpage + limitpage;
			if (endpage>totalpage){endpage = totalpage+1;}

			StringBuffer pagediv = new StringBuffer();

//			pagediv.append("<a href=\"javascript:GoPage('"+type+"','"+1+"')\" class=\"direction\" >" + first + "<span>처음</span></a>\n");

			if((startpage-limitpage)>=0)
			{
				pagediv.append("<a href=\"javascript:GoPage('"+type+"','"+(startpage-1)+"');\">" + before + "</a>\n");
			}

			for(int i=startpage;i<endpage;i++)
			{
				if(page==i)
				  pagediv.append("<strong>"+""+stfont+i+""+edfont+"</strong>\n");
				else
				pagediv.append("<a href=\"javascript:GoPage('"+type+"','"+i+"');\">"+""+stfont+i+""+edfont+"</a>\n");
			}

			if(startpage+limitpage<totalpage+1)
			{
				pagediv.append("<a href=\"javascript:GoPage('"+type+"','"+endpage+"');\">" + next + "</a>\n");
			}

//			pagediv.append("<a href=\"javascript:GoPage('"+type+"','"+totalpage+"')\" class=\"direction\" >" + last + "<span>마지막</span></a>\n");

			return pagediv.toString();
		}

}
