import java.awt.Font;
import java.awt.FontFormatException;
import java.io.IOException;

public class FontHolder {
	private Font font;

	public FontHolder() {
		try {
			font = Font.createFont(Font.TRUETYPE_FONT,
					DialogPnl.class.getResourceAsStream("/fonts/EastSeaDokdo-Regular.ttf"));
		} catch (FontFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Font getDeriveFont(int style, float size) {
		return font.deriveFont(style, size);
	}
}