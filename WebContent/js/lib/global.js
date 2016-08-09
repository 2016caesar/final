/**
* 값을 찍어줄때 빈문자열 처리하여 반환
* 이 처리가 안되면 값이 널인 엘리먼트에서 오류 발생
* html 태그를 치환하지 않고 그대로 리턴
*/
function elementValue(obj,len) {
	if (typeof(obj) == "undefined" || obj == null) {return "";}
	var str = (obj.firstChild == null) ? "" : obj.firstChild.nodeValue;
	if (len != null) {
		str = fnSubstring(str,len);
	}
	return str;
}
/**
* 값을 찍어줄때 빈문자열 처리하여 반환
* 이 처리가 안되면 값이 널인 엘리먼트에서 오류 발생
*/
function elementValueNvl(obj,len) {
	var str = elementValue(obj,len);
	str = str.replace(/</g, "&lt;");
	str = str.replace(/>/g, "&gt;");
	str = str.replace(/\"/g, "&quot;");
	str = str.replace(/\'/g, "&#039");
	return str;
}
/**
* 값을 찍어줄때 빈문자열 처리하여 반환
* 이 처리가 안되면 값이 널인 엘리먼트에서 오류 발생
* 언어별 문자열 자르기 처리하여 반환
*/
function elementValueNvlLanguage(obj,kor,eng,chn,jpn) {
	if (typeof(obj) == "undefined" || obj == null) {return "";}
	var str = (obj.firstChild == null) ? "" : obj.firstChild.nodeValue;
	return fnLangString(str,kor,eng,chn,jpn);
}
/**
* elementValueNvlLanguage 와 동일하나 cdata 데이터 중 줄바꿈 값이 있는 경우 데이터를 못읽어오는 문제 보완
*/
function elementValueNvlLanguageLong(obj,kor,eng,chn,jpn) {
	if (typeof(obj) == "undefined" || obj == null) {return "";}
	if (obj.firstChild == null) {return "";}

	var content = obj.firstChild;
	while(content != null && content.nodeType != 4) content = content.nextSibling;
	return fnLangString(content.nodeValue,kor,eng,chn,jpn);
}
/**
* 값을 찍어줄때 빈문자열 처리하여 반환
* 이 처리가 안되면 값이 널인 엘리먼트에서 오류 발생
* \n --> <br /> 처리 함
*/
function elementValueNvlBr(obj,len) {
	var str = elementValueNvl(obj,len);
	str = str.replace(/\n/g, "<br />");
	return str;
}

