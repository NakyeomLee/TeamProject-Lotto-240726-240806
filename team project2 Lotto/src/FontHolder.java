import java.awt.Font;
import java.awt.FontFormatException;
import java.io.IOException;

// 다운로드 받은 폰트 활용을 위한 클래스 생성

public class FontHolder {
	private Font font;

	public FontHolder() {

		// 폰트 사용중에 발생할 예외 처리
		try {
			font = Font.createFont(Font.TRUETYPE_FONT, DialogPnl.class.getResourceAsStream("/fonts/발표자료 글꼴.ttf"));

		} catch (FontFormatException e) {
			e.printStackTrace();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// 폰트 사용할 부분에 호출시킬 메소드
	// (폰트의 스타일, 폰트 크기)
	public Font getUseFont(int style, float size) {
		return font.deriveFont(style, size);
	}
}