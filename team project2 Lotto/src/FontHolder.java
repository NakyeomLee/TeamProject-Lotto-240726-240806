import java.awt.Font;
import java.awt.FontFormatException;
import java.io.IOException;

// 다운로드 받은 폰트 활용을 위한 클래스 생성

public class FontHolder {
	private Font font;

	public FontHolder() {
		try {
			font = Font.createFont(Font.TRUETYPE_FONT,
					DialogPnl.class.getResourceAsStream("/fonts/BMHANNAPro.ttf"));
			
		} catch (FontFormatException e) {
			e.printStackTrace();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Font getUseFont(int style, float size) {
		return font.deriveFont(style, size);
	}
}